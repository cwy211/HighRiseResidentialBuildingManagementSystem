package com.fyp.highriseresbuildms.dao.complaint_category;

import com.fyp.highriseresbuildms.entity.ComplaintCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintCategoryDao extends JpaRepository<ComplaintCategory,String> {
    List<ComplaintCategory> findAllByOrderByRankAsc();
    ComplaintCategory findComplaintCategoryByComplaintCategoryName(String category);
}
