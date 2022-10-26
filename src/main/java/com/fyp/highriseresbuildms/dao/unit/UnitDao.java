package com.fyp.highriseresbuildms.dao.unit;

import com.fyp.highriseresbuildms.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitDao extends JpaRepository<Unit,String> {

    @Query("select unitId from Unit")
    List<String> getAllUnitId();

    @Query("select unitId from Unit where unitOwnedStatus='Owned'")
    List<String> getAllOwnedUnitId();

    @Query("select unitId from Unit where unitOwnedStatus='Vacant'")
    List<String> getAllVacantUnitId();
}
