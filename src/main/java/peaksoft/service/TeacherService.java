package peaksoft.service;

import peaksoft.entities.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    void addTeacher(Teacher teacher, Long courseId);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher, Long id);

    void deleteTeacher(Teacher teacher);
}
