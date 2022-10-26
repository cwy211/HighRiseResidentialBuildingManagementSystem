package com.fyp.highriseresbuildms.service.ownership;

import com.fyp.highriseresbuildms.dao.ownership.OwnershipDao;
import com.fyp.highriseresbuildms.dao.unit.UnitDao;
import com.fyp.highriseresbuildms.dao.user.UserDao;
import com.fyp.highriseresbuildms.dto.OwnershipDto;
import com.fyp.highriseresbuildms.entity.Ownership;
import com.fyp.highriseresbuildms.entity.Unit;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OwnershipServiceImpl implements OwnershipService{

    @Autowired
    private OwnershipDao ownershipDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UnitDao unitDao;

    @Autowired
    private UserService userService;

    @Override
    public void addOwnership(OwnershipDto ownershipDto) {
        Ownership ownership = new Ownership();
        ownership.setOwner(userDao.findById(ownershipDto.getOwner()).get());
        Unit ownedUnit = unitDao.findById(ownershipDto.getOwnedUnit()).get();
        ownership.setOwnedUnit(ownedUnit);
        ownership.setStartDate(ownershipDto.getStartDate());
        ownershipDao.save(ownership);
        ownedUnit.setUnitOwnedStatus("Owned");
        unitDao.save(ownedUnit);
    }

    @Override
    public Ownership getOwnershipByUserId() {
        return ownershipDao.findOwnershipByOwner_UserId(userService.getUserName());
    }

    @Override
    public List<Ownership> getAllOwnerships() {
        return ownershipDao.findAll();
    }
}
