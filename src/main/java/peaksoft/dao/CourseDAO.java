package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Group;

import java.util.List;

public interface CourseDAO {

    List<Group> getGroupsByCourse(Long courseId);

    List<Course> getAllCourses();

    void addCourse(Course course, Long companyId);

    Course getCourseById(Long id);

    void updateCourse(Course course, Long id);

    void deleteCourse(Course course);
}
