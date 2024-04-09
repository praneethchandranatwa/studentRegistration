package com.awt.signin.signin.repository;

import com.awt.signin.signin.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {

    List<Quiz> findAllBySubSection_SubSectionId(Long subSectionId);
}
