package com.fyp.highriseresbuildms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GenericGenerator(name = "sequence_user_id", strategy = "com.fyp.highriseresbuildms.utility.UserIdGenerator")
    @GeneratedValue(generator = "sequence_user_id")
    @Column(name="user_id", length = 20)
    private String userId;

    @Column(name="user_password", length = 255)
    private String userPassword;

    @ManyToOne
    @JoinColumn(name="user_role")
    private Role userRole;

    @Column(name="user_status", length = 10)
    private String userStatus;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
