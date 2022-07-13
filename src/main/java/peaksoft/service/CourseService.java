package peaksoft.service;

import peaksoft.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    void addCourse(Course course, Long companyId);

    Course getCourseById(Long id);

    void updateCourse(Course course, Long id);

    void deleteCourse(Course course);
}
