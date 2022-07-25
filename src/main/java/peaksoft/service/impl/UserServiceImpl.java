package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.UserDAO;
import peaksoft.entities.User;
import peaksoft.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userDAO.getUserByUsername(username);
//
//        if (user==null){
//            throw  new UsernameNotFoundException("User not found ! ! !");
//        }
//        return new MyUser(user);
//    }
}
