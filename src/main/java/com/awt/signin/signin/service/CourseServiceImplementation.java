package com.awt.signin.signin.service;



import com.awt.signin.signin.entity.Courses;
import com.awt.signin.signin.entity.Section;
import com.awt.signin.signin.entity.SubSection;
import com.awt.signin.signin.repository.CoursesRepository;
import com.awt.signin.signin.repository.SectionRepository;
import com.awt.signin.signin.repository.SubSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImplementation implements CourseService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubSectionRepository subSectionRepository;

    @Autowired
    public CourseServiceImplementation(
            CoursesRepository courseRepository,
            SectionRepository sectionRepository,
            SubSectionRepository subSectionRepository
    ) {
        this.coursesRepository = courseRepository;
        this.sectionRepository = sectionRepository;
        this.subSectionRepository = subSectionRepository;
    }

    @Override
    public void createCourses(List<Courses> coursesList) {
        List<Courses> savedCourses = new ArrayList<>();
        for (Courses courses : coursesList) {
            Optional<Courses> existingCourse = coursesRepository.findByCourseName(courses.getCourseName());

            if (existingCourse.isPresent()) {
                Courses existingCourseEntity = existingCourse.get();
                existingCourseEntity.setSections(courses.getSections());
                courses = existingCourseEntity;
            }

            List<Section> sections = courses.getSections();
            if (sections != null) {
                for (Section section : sections) {
                    Optional<Section> existingSection = sectionRepository.findBySectionName(section.getSectionName());

                    if (existingSection.isPresent()) {
                        Section existingSectionEntity = existingSection.get();
                        existingSectionEntity.setPercentage(section.getPercentage());
                        existingSectionEntity.setSubSections(section.getSubSections());
                        section.setSection_id(existingSectionEntity.getSection_id());
                        section.setCourses(existingSectionEntity.getCourses());
                    } else {
                        section.setCourses(courses);
                    }

                    List<SubSection> subSections = section.getSubSections();
                    if (subSections != null) {
                        for (SubSection subSection : subSections) {
                            Optional<SubSection> existingSubSection = subSectionRepository.findBySubsectionName(subSection.getSubsectionName());

                            if (existingSubSection.isPresent()) {
                                SubSection existingSubSectionEntity = existingSubSection.get();
                                existingSubSectionEntity.setReferenceLinks(subSection.getReferenceLinks());
                                subSection.setSubSectionId(existingSubSectionEntity.getSubSectionId());
                                subSection.setSection(existingSubSectionEntity.getSection());
                                subSection.setCourses(existingSubSectionEntity.getCourses());
                            } else {
                                subSection.setSection(section);
                                subSection.setCourses(courses);
                            }
                        }
                    }
                }
            }

            savedCourses.add(courses);
        }
        coursesRepository.saveAll(savedCourses);
    }

    @Override
    public String deleteCourse(int courseId) {
        Optional<Courses> courseOptional = coursesRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            coursesRepository.delete(courseOptional.get());
            return "Course and its associated sections and subsections deleted successfully.";
        } else {
            return "Course with ID " + courseId + " not found";
        }
    }


    @Override
    public Courses getCourseWithSectionsAndSubsections(int courseId) {
        Optional<Courses> optionalCourse = coursesRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Courses course = optionalCourse.get();

            List<Section> sections = sectionRepository.findByCourses(course);
            for (Section section : sections) {
                List<SubSection> subSections = subSectionRepository.findBySection(section);
                section.setSubSections(subSections);
            }
            course.setSections(sections);

            return course;
        } else {
            return null;
        }
    }

    @Override
    public SubSection getSubsection(int subsectionId) {
        Optional<SubSection> optionalSubSection = subSectionRepository.findById(subsectionId);
        return optionalSubSection.orElse(null);
    }

    @Override
    public String deleteSubSection(int subsectionId) {
        Optional<SubSection> optionalSubSection = subSectionRepository.findById(subsectionId);
        if (optionalSubSection.isPresent()) {
            subSectionRepository.delete(optionalSubSection.get());
            return "Subsection deleted successfully.";
        } else {
            return "Subsection with ID " + subsectionId + " not found";
        }
    }




@Override
public Section getSectionWithSubsections(int sectionId) {
    Optional<Section> optionalSection = sectionRepository.findById(sectionId);
    if (optionalSection.isPresent()) {
        Section section = optionalSection.get();
        List<SubSection> subSections = subSectionRepository.findBySection(section);
        section.setSubSections(subSections);
        return section;
    } else {
        return null;
    }
}

    @Override
    public String deleteSection(int sectionId) {
        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        if (optionalSection.isPresent()) {
            sectionRepository.delete(optionalSection.get());
            return "Section and its associated subsections deleted successfully.";
        } else {
            return "Section with ID " + sectionId + " not found";
        }
    }
}