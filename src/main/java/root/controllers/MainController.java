package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import root.entities.Role;
import root.entities.User;
import root.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping(path = "/")
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    private void addAttributes(Principal principal, Model model) {
        model.addAttribute("roles", Role.values());
        if (principal != null) {
            model.addAttribute("currentUser", userService.getUserByEmail(principal.getName()));
        }
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }

    @GetMapping(path = "/user")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("updatedUser", new User());
        return "panel";
    }
}
