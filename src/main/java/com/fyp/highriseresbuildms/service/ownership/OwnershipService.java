package com.fyp.highriseresbuildms.service.ownership;

import com.fyp.highriseresbuildms.dto.OwnershipDto;
import com.fyp.highriseresbuildms.entity.Ownership;

import java.util.List;

public interface OwnershipService {
    void addOwnership (OwnershipDto ownershipDto);
    Ownership getOwnershipByUserId();
    List<Ownership> getAllOwnerships();
}
