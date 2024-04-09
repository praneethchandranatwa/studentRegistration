package com.awt.signin.signin.repository;


import com.awt.signin.signin.entity.Choices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Choices, Long> {
}
