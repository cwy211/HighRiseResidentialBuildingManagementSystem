package com.fyp.highriseresbuildms.service.user;

import com.fyp.highriseresbuildms.dao.role.RoleDao;
import com.fyp.highriseresbuildms.dao.unit.UnitDao;
import com.fyp.highriseresbuildms.dao.user.UserDao;
import com.fyp.highriseresbuildms.dao.user.UserDetailsDao;
import com.fyp.highriseresbuildms.dto.UserRegistrationDto;
import com.fyp.highriseresbuildms.entity.AdminDetails;
import com.fyp.highriseresbuildms.entity.Role;
import com.fyp.highriseresbuildms.entity.User;
import com.fyp.highriseresbuildms.entity.UserDetails;
import com.fyp.highriseresbuildms.service.facility.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UnitDao unitDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FacilityService facilityService;

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public User registerNewUser(User user) {
        User newUser = new User();
        newUser.setUserId(user.getUserId());
        newUser.setUserPassword(getEncodedPassword(user.getUserPassword()));
        newUser.setUserRole(roleDao.findByRoleName("User"));
        newUser.setUserStatus("Active");
        return userDao.save(newUser);
    }

    @Override
    public User registerNewAdmin(User admin) {
        User newAdmin = new User();
        newAdmin.setUserId(admin.getUserId());
        newAdmin.setUserPassword(getEncodedPassword(admin.getUserPassword()));
        newAdmin.setUserRole(roleDao.findByRoleName("Admin"));
        newAdmin.setUserStatus("Active");
        return userDao.save(newAdmin);
    }

    @Override
    public User registerNewUserDetails(UserRegistrationDto userRegistrationDto) {
        UserDetails newUser = new UserDetails();
        newUser.setUserId(userRegistrationDto.getUserId());
        newUser.setUserPassword(getEncodedPassword(userRegistrationDto.getUserPassword()));
        newUser.setUserRole(roleDao.findByRoleName("User"));
        newUser.setUserStatus("Active");
        newUser.setUserContact(userRegistrationDto.getUserContact());
        newUser.setUserFirstName(userRegistrationDto.getUserFirstName());
        newUser.setUserLastName(userRegistrationDto.getUserLastName());
        newUser.setUserGender(userRegistrationDto.getUserGender());
        newUser.setUserUnit(unitDao.findById(userRegistrationDto.getUserUnit()).get());
        return userDao.save(newUser);
    }

    @Override
    public User registerNewOwnerDetails(UserRegistrationDto userRegistrationDto) {
        UserDetails newUser = new UserDetails();
        newUser.setUserId(userRegistrationDto.getUserId());
        newUser.setUserPassword(getEncodedPassword(userRegistrationDto.getUserPassword()));
        newUser.setUserRole(roleDao.findByRoleName("User"));
        newUser.setUserStatus("Owner");
        newUser.setUserContact(userRegistrationDto.getUserContact());
        newUser.setUserFirstName(userRegistrationDto.getUserFirstName());
        newUser.setUserLastName(userRegistrationDto.getUserLastName());
        newUser.setUserGender(userRegistrationDto.getUserGender());
        newUser.setUserUnit(unitDao.findById(userRegistrationDto.getUserUnit()).get());
        return userDao.save(newUser);
    }

    @Override
    public List<User> getAllUserDetails() {
        return userDao.findAll();
    }

    @Override
    public User registerNewAdminDetails(AdminDetails adminDetails) {
        AdminDetails newAdmin = new AdminDetails();
        newAdmin.setUserPassword(getEncodedPassword(adminDetails.getUserPassword()));
        newAdmin.setUserRole(roleDao.findByRoleName("Admin"));
        newAdmin.setUserStatus("Active");
        newAdmin.setAdminContact(adminDetails.getAdminContact());
        newAdmin.setAdminFirstName(adminDetails.getAdminFirstName());
        newAdmin.setAdminLastName(adminDetails.getAdminLastName());
        newAdmin.setAdminGender(adminDetails.getAdminGender());
        return userDao.save(newAdmin);
    }

    @Override
    public List<User> getOnlyUserDetails() {
        return userDao.findUserByUserRoleRoleName("User");
    }

    @Override
    public List<User> getNotInactiveUserDetails() {
        return userDao.findUserByUserRoleRoleNameAndUserStatusNot("User","Inactive");
    }

    @Override
    public User getUserByUserId(String userId) {
        return userDao.findById(userId).get();
    }

    @Override
    public void updateUserDetails(UserRegistrationDto userRegistrationDto) {
        UserDetails user = userDao.findUserByUserId(userRegistrationDto.getUserId());
        user.setUserUnit(unitDao.findById(userRegistrationDto.getUserUnit()).get());
        user.setUserGender(userRegistrationDto.getUserGender());
        user.setUserLastName(userRegistrationDto.getUserLastName());
        user.setUserFirstName(userRegistrationDto.getUserFirstName());
        user.setUserContact(userRegistrationDto.getUserContact());
        userDao.save(user);
    }

    @Override
    public List<User> searchUsersByName(String searchTerm) {
        return userDetailsDao.findUserDetailsByUserFirstNameContainingOrUserLastNameContaining(searchTerm,searchTerm);
    }

    @Override
    public List<User> getUserByUnitNo(String unitNo) {
        return userDetailsDao.findUserDetailsByUserUnit_UnitIdEquals(unitNo);
    }

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }

    public User getUser(String userName){
        return userDao.findById(userName).get();
    }

    @Override
    public void deleteUser(String userId) {
        User user = getUserByUserId(userId);
        user.setUserStatus("Inactive");
    }

    @Override
    public User getOwnUserProfile() {
        return userDao.findUserByUserId(getUserName());
    }

    @Override
    public User getOwnAdminProfile() {
        return userDao.findAdminByUserId(getUserName());
    }

    @Override
    public void initAdmin() {
        if(!userDao.findById("HRRBMS10001").isPresent()){
            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleDao.save(adminRole);

            Role userRole = new Role();
            userRole.setRoleName("User");
            userRole.setRoleDescription("Resident role");
            roleDao.save(userRole);

            AdminDetails newAdmin = new AdminDetails();
            newAdmin.setUserPassword(getEncodedPassword("adminpassword"));
            newAdmin.setUserRole(roleDao.findByRoleName("Admin"));
            newAdmin.setUserStatus("Active");
            newAdmin.setAdminContact("0123456789");
            newAdmin.setAdminFirstName("admin");
            newAdmin.setAdminLastName("chong");
            newAdmin.setAdminGender("M");
            userDao.save(newAdmin);

            facilityService.initFacility();
        }
    }


}
