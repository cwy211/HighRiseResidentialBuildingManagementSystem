package com.fyp.highriseresbuildms.dao.user;

import com.fyp.highriseresbuildms.entity.User;
import com.fyp.highriseresbuildms.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailsDao extends JpaRepository<UserDetails,String> {
    List<User> findUserDetailsByUserFirstNameContainingOrUserLastNameContaining(String firstName, String lastName);
    List<User> findUserDetailsByUserUnit_UnitIdEquals(String unitNo);
}
