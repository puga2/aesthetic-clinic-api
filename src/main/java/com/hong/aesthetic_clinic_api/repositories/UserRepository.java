package com.hong.aesthetic_clinic_api.repositories;

import com.hong.aesthetic_clinic_api.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    //this for CRUD behavior & some pagination as well
    Optional<User> findById(UUID id);

}
