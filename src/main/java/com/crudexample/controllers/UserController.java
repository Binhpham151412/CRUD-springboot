package com.crudexample.controllers;

import com.crudexample.user.UserEntity;
import com.crudexample.user.UserEntityNotFoundException;
import com.crudexample.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping(value = "/users")
    public ModelAndView listAll() {
        ModelAndView modelAndView = new ModelAndView("users");
        List<UserEntity> listUsers = userService.listAll();
        modelAndView.addObject("listUsers", listUsers);
        return modelAndView;
    }

    @GetMapping(value = "/users/new")
    public ModelAndView showNewForm() {
        ModelAndView modelAndView = new ModelAndView("user_form");
        modelAndView.addObject("user", new UserEntity());
        modelAndView.addObject("pageTitle", "Add New User");
        return modelAndView;
    }

    @PostMapping(value = "/users/save")
    public ModelAndView saveUser(UserEntity user, RedirectAttributes ra){
        ModelAndView modelAndView = new ModelAndView("users");
        userService.save(user);
        ra.addFlashAttribute("message","the user has been saved successfully");
        List<UserEntity> listUsers = userService.listAll();
        modelAndView.addObject("listUsers", listUsers);
        return modelAndView;
    }

//    @PostMapping(value = "/users/save")
//    public RedirectView redirectView (UserEntity user, RedirectAttributes ra){
//        userService.save(user);
//        ra.addFlashAttribute("message","the user has been saved successfully");
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("localhost:8080/users");
//        return redirectView;
//    }

//    @PostMapping(value = "/users/save")
//    public ResponseEntity<Void> redirect(UserEntity user, RedirectAttributes ra) {
//        userService.save(user);
//        ra.addFlashAttribute("message","the user has been saved successfully");
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .location(URI.create("localhost:8080/users"))
//                .build();
//    }

    @GetMapping(value = "users/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id")Integer id, Model model, RedirectAttributes ra){
        try {
            UserEntity userEntity = userService.get(id);
            model.addAttribute("user",userEntity);
            model.addAttribute("pageTitle", "Edit User (ID: "+ id + ")");
            return new ModelAndView("user_form");
        } catch (UserEntityNotFoundException e) {
            ra.addFlashAttribute("message","the user has been saved successfully");
            return new ModelAndView("user_form");
        }


    }
}
