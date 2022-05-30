package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import root.entities.Role;
import root.entities.User;
import root.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute
    private void addAttributes(Model model) {
        model.addAttribute("roles", Role.values());
    }

    @GetMapping
    public String getAdminPage(Principal principal, Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("currentUser", userService.getUserByEmail(principal.getName()));
        return "admin";
    }

    @GetMapping(path = "/list")
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/list";
    }

    @GetMapping(path = "/new")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping(path = "/new")
    public String saveUser(@ModelAttribute(name = "newUser") @Valid User user,
                           BindingResult bindingResult) {

        userService.validateEmail(user.getEmail(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "users/new";
        }

        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping(path = "/edit/{id}")
    public String editUser(@ModelAttribute(name = "user") @Valid User user,
                           BindingResult bindingResult) {

        if (!userService.getUserById(user.getId()).getEmail().equals(user.getEmail())) {
            userService.validateEmail(user.getEmail(), bindingResult);
        }

        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
