package com.hong.aesthetic_clinic_api.Config;

import com.hong.aesthetic_clinic_api.Services.AuthenticationService;
import com.hong.aesthetic_clinic_api.domain.UserRole;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import com.hong.aesthetic_clinic_api.domain.entities.Branch;
import com.hong.aesthetic_clinic_api.domain.entities.User;
import com.hong.aesthetic_clinic_api.repositories.BranchRepository;
import com.hong.aesthetic_clinic_api.repositories.UserRepository;
import com.hong.aesthetic_clinic_api.security.ClinicUserDetailsService;
import com.hong.aesthetic_clinic_api.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService){
        return new JwtAuthenticationFilter(authenticationService);
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository, BranchRepository branchRepository){
        ClinicUserDetailsService clinicUserDetailsService = new ClinicUserDetailsService(userRepository);

        String email = "super@test.com";
        userRepository.findByEmail(email).orElseGet(()->{
            Branch branch = branchRepository.findAll().stream().findFirst()
                    .orElseGet(()->branchRepository.save(
                            Branch.builder()
                                    // set any other required Branch fields here
                                    .name("Head Office")
                                    .phone("078868213")
                                    .address("PhnomPenh")
                                    .build()
                    ));
            User newUser = User.builder()
                    .fullName("Kim hongTest")
                    .email(email)
                    .username(email)
                    .password(passwordEncoder().encode("password"))
                    .role(UserRole.SUPERADMIN)
                    .status(UserStatus.ACTIVE)
                    .branch(branch)
                    .build();
            return  userRepository.save(newUser);
//   .username(email)                 // required by getUsername()
//                    .password(passwordEncoder().encode("password"))
//                    .role(Role.SUPER_ADMIN)          // use your real enum value
//                    .status(UserStatus.ACTIVE)
        });
        return clinicUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger / OpenAPI documentation endpoints
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Allow Branch endpoints while developing Branch module
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/branches/**").permitAll()

                        // Optional: permit any request until Auth is implemented
//                        .anyRequest().permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        // If your JwtAuthenticationFilter throws errors when headers are missing,
        // you can comment out the filter line until Auth is ready:
        // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws  Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
