package com.fyp.highriseresbuildms.service.user;

import com.fyp.highriseresbuildms.dto.UserRegistrationDto;
import com.fyp.highriseresbuildms.entity.AdminDetails;
import com.fyp.highriseresbuildms.entity.User;
import com.fyp.highriseresbuildms.entity.UserDetails;

import java.util.List;

public interface UserService {
    User registerNewUser(User user);
    User registerNewAdmin(User admin);
    User registerNewUserDetails(UserRegistrationDto userRegistrationDto);
    User registerNewOwnerDetails(UserRegistrationDto userRegistrationDto);
    List<User> getAllUserDetails();
    User registerNewAdminDetails(AdminDetails adminDetails);
    List<User> getOnlyUserDetails();
    List<User> getNotInactiveUserDetails();
    User getUserByUserId(String userId);
    void updateUserDetails(UserRegistrationDto userRegistrationDto);
    List<User> searchUsersByName(String searchTerm);
    List<User> getUserByUnitNo(String unitNo);
    String getUserName();
    User getUser(String userName);
    void deleteUser(String userId);
    User getOwnUserProfile();
    User getOwnAdminProfile();
    void initAdmin();
}
