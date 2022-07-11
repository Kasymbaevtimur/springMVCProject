package peaksoft.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Company;
import peaksoft.entities.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class CourseDAOImpl implements CourseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final CompanyDAO companyDAO;

    @Autowired
    public CourseDAOImpl(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }


    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = entityManager.createQuery("from Course", Course.class).getResultList();
        Comparator<Course> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        courses.sort(comparator);
        return courses;
    }

    @Override
    public void addCourse(Course course, Long companyId) {
        Company company = companyDAO.getCompanyById(companyId);
        course.setCompany(company);
        entityManager.persist(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void updateCourse(Course course) {
//        Course course1=getCourseById(id);
        entityManager.merge(course);
    }

    @Override
    public void deleteCourse(Course course) {
        entityManager.remove(entityManager.contains(course) ? course : entityManager.merge(course));
    }
}
