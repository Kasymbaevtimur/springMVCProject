package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.StudentDAO;
import peaksoft.entities.Student;
import peaksoft.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public void addStudent(Student student, Long groupId) {
        studentDAO.addStudent(student, groupId);

    }

    @Override
    public Student getStudentById(Long id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        studentDAO.updateStudent(student, id);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDAO.deleteStudent(student);

    }

    @Override
    public List<Student> getByName(String name) {
        return studentDAO.getByName(name);
    }

   public List<Student>getStudentsByCompany(Long companyId){
        return studentDAO.getStudentsByCompany(companyId);
    }
    public List<Student>getStudentsByTeacher(Long teacherId){
        return studentDAO.getStudentsByTeacher(teacherId);
    }
}
