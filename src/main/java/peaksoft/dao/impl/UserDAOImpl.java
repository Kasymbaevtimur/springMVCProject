package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.UserDAO;
import peaksoft.entities.Role;
import peaksoft.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User getUserByUserName(String username) {
        User user = entityManager.createQuery(
                    "SELECT u from User u WHERE u.username = :username", User.class).
            setParameter("username", username).getSingleResult();

        return user;
    }

    @Override
    public List<Role> getRoleByUser(Long roleId) {
        return null;
    }


    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void updateUser(User user, Long id) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
