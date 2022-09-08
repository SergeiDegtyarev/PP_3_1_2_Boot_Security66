package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping (value = "/admin")
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping()
//    public String user(@ModelAttribute("user1") User user) {
//
//        return "admin";
//
//    }

    @GetMapping()
    public String showUser(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("allusers", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("users", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";

    }

    @GetMapping("/new")
    public String newUser2(Model model,@AuthenticationPrincipal User user) {
        model.addAttribute("listRoles",roleService.getAllRoles());
        model.addAttribute("user", user);
        return "/admin";
    }
    @PostMapping("/new")
    public String creatUser(@ModelAttribute("user") User user, Model model, @RequestParam("listRoles") ArrayList<Integer> roles) {
        model.addAttribute("listRoles",roleService.getAllRoles());
        user.setRoles(roleService.findByIdRoles(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,Model model, @PathVariable("id") int id) {
        model.addAttribute("user",userService.findById(id));
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "/admin";
    }

    @PostMapping("/{id}")
    public String patchUser(@ModelAttribute("user") User user, @RequestParam("listRoles") ArrayList<Integer>roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin";

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
