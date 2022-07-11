package peaksoft.dao;

import peaksoft.entities.Group;

import java.util.List;

public interface GroupDAO {

    List<Group> getAllGroup();

    void addGroup(Group group,Long courseId);

    Group getGroupById(Long id);

    void updateGroup(Group group, Long id);

    void deleteGroup(Group group);

}
