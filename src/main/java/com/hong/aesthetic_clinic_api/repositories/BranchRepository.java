package com.hong.aesthetic_clinic_api.repositories;

import com.hong.aesthetic_clinic_api.domain.BranchStatus;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import com.hong.aesthetic_clinic_api.domain.entities.Branch;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BranchRepository extends  JpaRepository<Branch,UUID> {
// Derive query -spring data generate the sql from the methood name
//    List<Branch> findByStatus(UserStatus status);

    // Case - insentivie partial name seach e.g for a search box
//    List<Branch> findAllByNameContainingIgnoreCase(String name);

    //Custom JPql, for anything derived-query naming cant express cleanly
    // ប្រើជាមួយ JPQL Query ផ្ទាល់
//    @Query("SELECT b FROM Branch b WHERE b.status = :status ORDER BY b.name ASC")
//    List<Branch>findAllActiveOrderedByName(@Param("status") UserStatus status);


    // Derived query - Spring Data generates the SQL from the method name
    List<Branch>findByStatus(BranchStatus status);

    // Case-insensitive partial name search, e.g. for a search box
    List<Branch>findAllByNameContainingIgnoreCase(String name);

    // Custom JPQL, for anything derived-query naming can't express cleanly
    // ប្រើជាមួយ JPQL Query ផ្ទាល់
    @Query("SELECT b FROM Branch b WHERE b.status = :status ORDER BY b.name ASC")
    List<Branch>findAllActiveOrderByName(@Param("status")BranchStatus status);
}
