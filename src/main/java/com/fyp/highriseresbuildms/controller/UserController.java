package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.dto.UserRegistrationDto;
import com.fyp.highriseresbuildms.entity.AdminDetails;
import com.fyp.highriseresbuildms.entity.User;
import com.fyp.highriseresbuildms.service.complaint_category.ComplaintCategoryService;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/registerNewUser",
            method = RequestMethod.POST)
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @RequestMapping(value = "/registerNewAdmin",
            method = RequestMethod.POST)
    public User registerNewAdmin(@RequestBody User user) {
        return userService.registerNewAdmin(user);
    }

    @RequestMapping(value = "/forAdmin",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "You are Admin";
    }

    @RequestMapping(value = "/forUser",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "You are User";
    }

    @RequestMapping(value = "/registerNewUserDetails",
            method = RequestMethod.POST)
    public User registerNewUserDetails(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.registerNewUserDetails(userRegistrationDto);
    }

    @RequestMapping(value = "/registerNewOwnerDetails",
            method = RequestMethod.POST)
    public User registerNewOwnerDetails(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userService.registerNewOwnerDetails(userRegistrationDto);
    }

    @RequestMapping(value = "/getAllUserDetails",
            method = RequestMethod.GET)
    public List<User> getAllUserDetails(){
        return userService.getAllUserDetails();
    }

    @RequestMapping(value = "/registerNewAdminDetails",
            method = RequestMethod.POST)
    public User registerNewAdminDetails(@RequestBody AdminDetails adminDetails) {
        return userService.registerNewAdminDetails(adminDetails);
    }

    @RequestMapping(value = "/getOnlyUserDetails",
            method = RequestMethod.GET)
    public List<User> getOnlyUserDetails(){
        return userService.getOnlyUserDetails();
    }


    @RequestMapping(value = "/getNotInactiveUserDetails",
            method = RequestMethod.GET)
    public List<User> getNotInactiveUserDetails(){
        return userService.getNotInactiveUserDetails();
    }

    @RequestMapping(value = "/getUserByUserId",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public User getUserByUserId(@RequestParam String userId){
        return userService.getUserByUserId(userId);
    }

    @RequestMapping(value = "/getOwnUserProfile",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('User')")
    public User getOwnUserProfile(){
        return userService.getOwnUserProfile();
    }

    @RequestMapping(value = "/getOwnAdminProfile",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public User getOwnAdminProfile(){
        return userService.getOwnAdminProfile();
    }



    @RequestMapping(value = "/updateUserDetails",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity updateUserDetails(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.updateUserDetails(userRegistrationDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteUser",
            method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity deleteUser(@RequestParam String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/searchUsersByName",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<User> searchUsersByName(@RequestParam String searchTerm){
        return userService.searchUsersByName(searchTerm);
    }

    @RequestMapping(value = "/getUserByUnitNo",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<User> getUserByUnitNo(@RequestParam String unitNo){
        return userService.getUserByUnitNo(unitNo);
    }

    @PostConstruct
    public void initAdmin(){
        userService.initAdmin();
    }




}
