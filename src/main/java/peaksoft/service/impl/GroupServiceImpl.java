package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDAO;
import peaksoft.dao.GroupDAO;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.GroupService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDAO groupDAO;
    private final CourseDAO courseDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO, CourseDAO courseDAO) {
        this.groupDAO = groupDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDAO.getAllGroup();
    }

    @Override
    public void addGroup(Group group, Long courseId) {
        List<Course> courses = new ArrayList<>();
        Course course = courseDAO.getCourseById(courseId);
        courses.add(course);
        group.setCourses(courses);
        List<Group> groups = new ArrayList<>();
        course.setGroups(groups);
        groupDAO.addGroup(group);

    }

    @Override
    public Group getGroupById(Long id) {
        return groupDAO.getGroupById(id);
    }

    public Group getByStudentName(String studentName) {
        return (Group) groupDAO.getByStudentName(studentName);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        groupDAO.updateGroup(group, id);
    }

    @Override
    public void deleteGroup(Group group) {
        groupDAO.deleteGroup(group);
    }
}
