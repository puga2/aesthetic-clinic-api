package com.hong.aesthetic_clinic_api.security;

import com.hong.aesthetic_clinic_api.domain.entities.User;
import com.hong.aesthetic_clinic_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class ClinicUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email)
               .orElseThrow(()->new UsernameNotFoundException("User not found with email:"+email));
       return new ClinicUserDetails(user);
    }
}
