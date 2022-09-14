package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping (value = "/admin")
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/users")
    public List<User> getAllusers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
//    @GetMapping()
//    public String showUser(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("allusers", userService.getAllUsers());
//        model.addAttribute("user", user);
//        model.addAttribute("users", new User());
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "admin";
//
//    }

//    @GetMapping("/new")
//    public String newUser2(Model model,@AuthenticationPrincipal User user) {
//        model.addAttribute("listRoles",roleService.getAllRoles());
//        model.addAttribute("user", user);
//        return "/admin";
//    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody  User user) {
        userService.addUser(user);
         return ResponseEntity.ok(HttpStatus.OK);
    }




//    @PostMapping("/new")
//    public String creatUser(@ModelAttribute("user") User user, Model model, @RequestParam("listRoles") ArrayList<Integer> roles) {
//        model.addAttribute("listRoles",roleService.getAllRoles());
//        user.setRoles(roleService.findByIdRoles(roles));
//        userService.addUser(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("/{id}")
//    public String updateUser(@ModelAttribute("user") User user,Model model, @PathVariable("id") int id) {
//        model.addAttribute("user",userService.findById(id));
//        model.addAttribute("listRoles", roleService.getAllRoles());
//        return "/admin";
//    }

//    @PutMapping("/{id}")
//    public String patchUser(@ModelAttribute("user") User user, @RequestParam("listRoles") ArrayList<Integer>roles) {
//        user.setRoles(roleService.findByIdRoles(roles));
//        userService.updateUser(user);
//        return "redirect:/admin";
//
//    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);

}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
