package com.fyp.highriseresbuildms.service.complaint_category;

import com.fyp.highriseresbuildms.entity.ComplaintCategory;

import java.util.List;

public interface ComplaintCategoryService {
    void addComplaintCategory(ComplaintCategory complaintCategory);
    List<ComplaintCategory> getSortedComplaintCategory();
    void updateComplaintCategoryRank(List<String> sequence);
    void initCategories();
}
