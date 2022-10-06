package com.crudexample.controllers;

import com.crudexample.user.UserEntity;
import com.crudexample.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @GetMapping(value = "/users")
    public ModelAndView listAll(){
        ModelAndView modelAndView = new ModelAndView("users");
        List<UserEntity> listUsers = userService.listAll();
        modelAndView.addObject("listUsers", listUsers);
        return modelAndView;
    }

    @GetMapping(value = "/users/new")
    public ModelAndView showNewForm(){
        ModelAndView modelAndView = new ModelAndView("user_form");
        modelAndView.addObject("user", new UserEntity());
        return modelAndView;
    }


    @PostMapping(value = "/users/save")
    public String saveUser(UserEntity user, RedirectAttributes ra){
        userService.save(user);
        ra.addFlashAttribute("message","the user has been saved successfully");
        return "redirect:/users";
    }

}
