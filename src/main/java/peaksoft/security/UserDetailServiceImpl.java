package peaksoft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import peaksoft.entities.User;
import peaksoft.service.UserService;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =userService.getUserByUserName(username);

        if (user==null){
            throw  new UsernameNotFoundException("User not found ! ! !");
        }
      return new MyUser(user);
    }
}
