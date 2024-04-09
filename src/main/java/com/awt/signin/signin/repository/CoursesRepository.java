package com.awt.signin.signin.repository;

import com.awt.signin.signin.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Integer>{

    Optional<Courses> findByCourseName(String courseName);



    Optional<Courses> findByCourseNameIgnoreCase(String courseName);
}
