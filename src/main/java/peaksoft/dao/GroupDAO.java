package peaksoft.dao;

import peaksoft.entities.Group;

import javax.persistence.TypedQuery;
import java.util.List;

public interface GroupDAO {

    List<Group> getAllGroup();

    void addGroup(Group group);

    Group getGroupById(Long id);


    void updateGroup(Group group, Long id);

    void deleteGroup(Group group);

}
