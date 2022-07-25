package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Role;
import peaksoft.entities.User;

import java.util.List;

public interface UserDAO {

    public User getUserByUserName(String username);

    List<Role> getRoleByUser(Long roleId);

    List<User> getAllUsers();

    void addUser(User user);

    User getUserById(Long id);

    void updateUser(User user, Long id);

    void deleteUser(User user);
}
