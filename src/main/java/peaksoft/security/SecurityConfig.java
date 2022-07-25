package peaksoft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/").hasAnyAuthority("USER","ADMIN","INSTRUCTOR")

               .antMatchers("/companies/**").hasAnyAuthority("ADMIN")

               .antMatchers("/courses").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/courses/addCourse").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/courses/saveCourse").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/courses/{id}/updateCourse").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/courses/group/{courseId}").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/courses/delete/{id}").hasAuthority("ADMIN")

               .antMatchers("/groups").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/groups/addGroup").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/groups/saveGroup").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/groups/{id}/updateGroup").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/groups/course/{groupId}").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/groups/search").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/groups/delete").hasAuthority("ADMIN")

               .antMatchers("/teachers/**").hasAuthority("ADMIN")

               .antMatchers("/students").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/students/addStudent").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/students/saveStudent").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/students/{id}/updateStudent").hasAnyAuthority("ADMIN","INSTRUCTOR")
               .antMatchers("/students/delete").hasAuthority("ADMIN")



               .anyRequest().authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .permitAll()
               .and()
               .logout().permitAll();
    }
}

