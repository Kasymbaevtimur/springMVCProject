package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.GroupDAO;
import peaksoft.dao.StudentDAO;
import peaksoft.entities.Group;
import peaksoft.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final GroupDAO groupDAO;

    public StudentDAOImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public void addStudent(Student student, Long groupId) {
        Group group = groupDAO.getGroupById(groupId);
        student.setGroup(group);
        entityManager.persist(student);

    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery(" from Student ", Student.class).getResultList();
        Comparator<Student> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        students.sort(comparator);
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        Student student1 = getStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        entityManager.merge(student1);
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student) ? student : entityManager.merge(student));

    }

    @Override
    public List<Student> getByName(String name) {
        List<Student>students=entityManager.createQuery("select s from Student  s where s.firstName=?1",Student.class).setParameter(1,name).getResultList();
        return students;
    }
}
