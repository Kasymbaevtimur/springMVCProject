package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.GroupDAO;
import peaksoft.entities.Group;
import peaksoft.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDAO groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDAO.getAllGroup();
    }

    @Override
    public void addGroup(Group group,Long courseId) {
        groupDAO.addGroup(group,courseId);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupDAO.getGroupById(id);
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
