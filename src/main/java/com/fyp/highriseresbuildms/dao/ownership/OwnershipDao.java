package com.fyp.highriseresbuildms.dao.ownership;

import com.fyp.highriseresbuildms.entity.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OwnershipDao extends JpaRepository<Ownership,Integer> {
    Ownership findOwnershipByOwner_UserId(String userId);
    long countOwnershipsByStartDateIsBefore(LocalDate date);
}
