package root.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import root.entities.Role;
import root.entities.User;
import root.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    private void addAttributes(Principal principal, Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("currentUser", userService.getUserByEmail(principal.getName()));
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("updatedUser", new User());
    }

    @GetMapping
    public String sendPanel(Model model) {
        return "panel";
    }

    @PostMapping(path = "/new")
    public String saveUser(@ModelAttribute(name = "newUser") @Valid User user,
                           BindingResult bindingResult, Model model) {

        userService.validateEmail(user.getEmail(), bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUserError", true);
            return "panel";
        }

        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping(path = "/edit/{id}")
    public String editUser(@PathVariable(name = "id") Long id,
                           @ModelAttribute(name = "updatedUser") @Valid User updatedUser,
                           BindingResult bindingResult, Model model) {

        if (!userService.getUserById(id).getEmail().equals(updatedUser.getEmail())) {
            userService.validateEmail(updatedUser.getEmail(), bindingResult);
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("editingUserError", updatedUser);
            return "panel";
        }

        userService.update(updatedUser);
        return "redirect:/admin";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id,
                             @ModelAttribute(name = "currentUser", binding = false) User currentUser,
                             HttpServletRequest request) throws ServletException {

        userService.delete(id);
        if (currentUser.getId().equals(id)) {
            request.logout();
            return "redirect:/";
        }
        return "redirect:/admin";
    }
}
