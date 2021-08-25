package springboot_bootstrap_312.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springboot_bootstrap_312.webapp.model.User;
import springboot_bootstrap_312.webapp.service.RoleService;
import springboot_bootstrap_312.webapp.service.UserService;
import springboot_bootstrap_312.webapp.service.UserServiceImpl;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String index(@AuthenticationPrincipal UserDetails currentUser, ModelMap model) {
        User user = userServiceImpl.findByUsername(currentUser.getUsername());
        System.out.println(user);
        model.addAttribute("loggingUser", user);
        return "admin_page";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String showAllUsers(@AuthenticationPrincipal UserDetails currentUser, ModelMap model) {
        User user = userServiceImpl.findByUsername(currentUser.getUsername());
        model.addAttribute("loggingUser", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("modifyRoles", user.getModifyRoles());
        return "admin_page";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
