package peaksoft.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.CourseDAO;
import peaksoft.dao.GroupDAO;
import peaksoft.entities.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;

@Transactional
@Repository
public class GroupDAOImpl implements GroupDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final CourseDAO courseDAO;

    @Autowired
    public GroupDAOImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Group> getAllGroup() {
        List<Group> groups = entityManager.createQuery("from Group", Group.class).getResultList();
        Comparator<Group> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        groups.sort(comparator);
        return groups;
    }

    @Override
    public void addGroup(Group group) {
        entityManager.persist(group);

    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);

    }



    @Override
    public void updateGroup(Group group, Long id) {
        Group group1 = getGroupById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        entityManager.merge(group1);
    }


    @Override
    public void deleteGroup(Group group) {
        entityManager.remove(entityManager.contains(group) ? group : entityManager.merge(group));

    }
}
