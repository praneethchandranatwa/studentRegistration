package com.awt.signin.signin.repository;

import com.awt.signin.signin.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {


     Registration findByUserEmailIgnoreCase(String emailId);

    Boolean existsByUserEmail(String email);



}
