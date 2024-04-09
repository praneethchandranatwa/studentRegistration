package com.awt.signin.signin.service;


import com.awt.signin.signin.entity.Courses;
import com.awt.signin.signin.entity.Section;
import com.awt.signin.signin.entity.SubSection;

import java.util.List;

public interface CourseService {
    void createCourses(List<Courses> coursesList);

    String deleteCourse(int courseId);

    Courses getCourseWithSectionsAndSubsections(int courseId);

    Section getSectionWithSubsections(int sectionId);

    String deleteSection(int sectionId);

    SubSection getSubsection(int subsectionId);

    String deleteSubSection(int subsectionId);
}

