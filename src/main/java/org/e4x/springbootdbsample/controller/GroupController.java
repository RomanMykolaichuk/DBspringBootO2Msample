/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.e4x.springbootdbsample.controller;

import javax.validation.Valid;
import org.e4x.springbootdbsample.DAO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author User
 */
@Controller
public class GroupController {
    
    @Autowired
    GroupRepository groupRepo;
    
     @GetMapping("/addgroup")
    public String showSignUpForm(Group group) {
        return "add-group";
    }

    @PostMapping("/add")
    public String addNewUser(@Valid Group group, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-group";
        }

        groupRepo.save(group);
        model.addAttribute("groups", groupRepo.findAll());
        return "groups";
    }

    @GetMapping("/groups")
    public String initUsers(Model model) {

        model.addAttribute("groups", groupRepo.findAll());
        return "groups";
    }

    /*@GetMapping("/")
    public String getAllUsers(Model model) {

       // model.addAttribute("groups", groupRepo.findAll());
        return "index";
    }*/

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("group", group);
        return "update-group";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid Group group,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            group.setId(id);
            return "update-group";
        }

        groupRepo.save(group);
        model.addAttribute("groups", groupRepo.findAll());
        return "groups";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        groupRepo.delete(group);
        model.addAttribute("groups", groupRepo.findAll());
        return "groups";
    }
    
    
    

}
