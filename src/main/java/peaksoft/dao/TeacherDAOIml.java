package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Course;
import peaksoft.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;


@Repository
@Transactional
public class TeacherDAOIml implements TeacherDAO {
    @PersistenceContext
    private EntityManager entityManager;

    private final CourseDAO courseDAO;

    @Autowired
    public TeacherDAOIml(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacher = entityManager.createQuery("from Teacher", Teacher.class).getResultList();
        Comparator<Teacher> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        teacher.sort(comparator);
        return teacher;
    }

@Override
public void addTeacher(Teacher teacher, Long courseId) {
    Course course = courseDAO.getCourseById(courseId);
    teacher.setCourse(course);
    entityManager.persist(teacher);
}
    @Override
    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        entityManager.remove(entityManager.contains(teacher) ? teacher : entityManager.merge(teacher));

    }
}
