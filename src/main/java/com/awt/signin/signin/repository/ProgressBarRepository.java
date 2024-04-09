package com.awt.signin.signin.repository;

import com.awt.signin.signin.entity.ProgressBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressBarRepository extends JpaRepository<ProgressBar,Long> {
}
