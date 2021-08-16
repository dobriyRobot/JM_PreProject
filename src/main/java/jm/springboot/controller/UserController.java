package jm.springboot.controller;

import jm.springboot.model.Role;
import jm.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import jm.springboot.model.User;
import jm.springboot.service.UserService;
import jm.springboot.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String index(@AuthenticationPrincipal UserDetails currentUser, ModelMap model) {
        User user = userServiceImpl.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String showAllUsers(ModelMap model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "admin_page";
    }

    @GetMapping("/admin/add")
    public String addNewUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin,
                             @RequestParam(required = false) String roleUser) {
        return addRoleToSet(user, roleAdmin, roleUser);
    }

    private String addRoleToSet(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin,
                                @RequestParam(required = false) String roleUser) {
        Set<Role> roles = new HashSet<>();

        if (roleAdmin != null) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        }
        if (roleUser != null) {
            roles.add(roleService.getRoleByName("ROLE_USER"));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "update";
    }

    @PatchMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin,
                                                                @RequestParam(required = false) String roleUser) {
        return addRoleToSet(user, roleAdmin, roleUser);
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}