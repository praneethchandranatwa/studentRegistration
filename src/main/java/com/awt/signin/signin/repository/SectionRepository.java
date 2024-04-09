package com.awt.signin.signin.repository;

import com.awt.signin.signin.entity.Courses;
import com.awt.signin.signin.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface SectionRepository extends JpaRepository<Section,Integer> {
    Optional<Section> findBySectionName(String sectionName);

    Optional<Section> findBySectionNameIgnoreCase(String sectionName);

    List<Section> findByCourses(Courses course);
}
