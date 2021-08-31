package com.example.springmvcrestsecurity.restapp.controller;

import com.example.springmvcrestsecurity.restapp.model.User;
import com.example.springmvcrestsecurity.restapp.service.UserService;
import com.example.springmvcrestsecurity.restapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping()
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/login";
        } else if (auth.getAuthorities().contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long id = userServiceImpl.findByEmail(auth.getName()).getId();
        Cookie currentUserCookie = new Cookie("UserId", "" + id);
        currentUserCookie.setMaxAge(60 * 60);
        response.addCookie(currentUserCookie);
        Boolean isAdmin = userServiceImpl.isAdmin(auth.getName());
        Cookie checkAdminCookie = new Cookie("Admin", "" + isAdmin);
        currentUserCookie.setMaxAge(60 * 60);
        response.addCookie(checkAdminCookie);
        return "index";
    }

    @GetMapping("/user")
    public String user(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long id = userServiceImpl.findByEmail(auth.getName()).getId();
        Cookie currentUserCookie = new Cookie("UserId", "" + id);
        currentUserCookie.setMaxAge(60 * 60);
        response.addCookie(currentUserCookie);
        Boolean isAdmin = userServiceImpl.isAdmin(auth.getName());
        Cookie checkAdminCookie = new Cookie("Admin", "" + isAdmin);
        currentUserCookie.setMaxAge(60 * 60);
        response.addCookie(checkAdminCookie);
        return "index";
    }
}
