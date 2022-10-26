package com.fyp.highriseresbuildms.dao.complaint_keyword;

import com.fyp.highriseresbuildms.entity.ComplaintKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintKeywordDao extends JpaRepository<ComplaintKeyword,String> {
    List<ComplaintKeyword> findAllByOrderByComplaintCategory_RankAsc();
}
