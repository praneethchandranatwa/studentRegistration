package com.awt.signin.signin.controller;

import com.awt.signin.signin.entity.Courses;
import com.awt.signin.signin.entity.Section;
import com.awt.signin.signin.entity.SubSection;
import com.awt.signin.signin.repository.CoursesRepository;
import com.awt.signin.signin.repository.SectionRepository;
import com.awt.signin.signin.repository.SubSectionRepository;
import com.awt.signin.signin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;





@RestController
public class CourseController {

    @Value("${tt.tt}")
    private List<String> tt;

    @Autowired
    private CoursesRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubSectionRepository subSectionRepository;

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/getlist")
    public String getLiost()
    {
        boolean isMatch = tt.stream().anyMatch(s->s.equals("2001"));
        boolean isMatch2 = tt.stream().anyMatch(s->s.equals("2002"));
        System.out.println(isMatch +","+ isMatch2);
        return "Hello";
    }

    @GetMapping("/courses")
    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/sections")
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @GetMapping("/subsections")
    public List<SubSection> getAllSubSections() {
        return subSectionRepository.findAll();
    }


    @PostMapping("/createCourses")
    public ResponseEntity<String> createCourses(@RequestBody List<Courses> coursesList) {
        courseService.createCourses(coursesList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saved Courses Successfully");
    }


    @DeleteMapping("/courses/{course_id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int course_id) {
        String result = courseService.deleteCourse(course_id);
        if (result.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }


    @GetMapping("/courses/{course_id}")
    public ResponseEntity<Courses> getCourseWithSectionsAndSubsections(@PathVariable int course_id) {
        Courses course = courseService.getCourseWithSectionsAndSubsections(course_id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/sections/{section_id}")
    public ResponseEntity<Section> getSectionWithSubsections(@PathVariable int section_id) {
        Section section = courseService.getSectionWithSubsections(section_id);
        if (section != null) {
            return ResponseEntity.ok(section);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/sections/{section_id}")
    public ResponseEntity<String> deleteSection(@PathVariable int section_id) {
        String result = courseService.deleteSection(section_id);
        if (result.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }


    @GetMapping("/subsections/{subsection_id}")
    public ResponseEntity<SubSection> getSubsections(@PathVariable int subsection_id) {
        SubSection subSection = courseService.getSubsection(subsection_id);
        if (subSection != null) {
            return ResponseEntity.ok(subSection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/subsections/{subsection_id}")
    public ResponseEntity<String> deleteSubSection(@PathVariable int subsection_id) {
        String result = courseService.deleteSubSection(subsection_id);
        if (result.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}


