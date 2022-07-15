package peaksoft.service;


import peaksoft.entities.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    void addStudent(Student student, Long groupId);

    Student getStudentById(Long id);

    void updateStudent(Student student, Long id);

    void deleteStudent(Student student);
    List<Student>getByName(String name);
    List<Student>getStudentsByCompany(Long companyId);
    List<Student>getStudentsByTeacher(Long teacherId);


}
