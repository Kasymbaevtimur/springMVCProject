package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.entities.User;
import peaksoft.service.UserService;

@Controller
public class MainPageController {
    private final UserService userService;

    @Autowired
    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile/{username}")
    public String getUser(Model model, @PathVariable("username") String username) {
        User user = userService.getUserByUserName(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping
    public String MainPage() {

        return "page";
    }


}
