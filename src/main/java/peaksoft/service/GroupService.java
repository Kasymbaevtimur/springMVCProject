package peaksoft.service;

import peaksoft.entities.Course;
import peaksoft.entities.Group;

import java.util.List;

public interface GroupService {

    List<Course> getCoursesByGroup(Long id);
    List<Group> getAllGroups();

    void addGroup(Group group, Long courseId);

    Group getGroupById(Long id);


    void updateGroup(Group group, Long id);

    void deleteGroup(Group group);


}

