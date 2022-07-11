package peaksoft.dao;

import peaksoft.entities.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    void addStudent(Student student,Long groupId);

    Student getStudentById(Long id);

    void updateStudent(Student student);

    void deleteStudent(Student student);
}
