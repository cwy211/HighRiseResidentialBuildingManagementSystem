package com.fyp.highriseresbuildms.service.complaint_category;

import com.fyp.highriseresbuildms.dao.complaint_category.ComplaintCategoryDao;
import com.fyp.highriseresbuildms.entity.ComplaintCategory;
import com.fyp.highriseresbuildms.entity.ComplaintKeyword;
import com.fyp.highriseresbuildms.service.complaint_keyword.ComplaintKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ComplaintCategoryServiceImpl implements ComplaintCategoryService{
    @Autowired
    private ComplaintCategoryDao complaintCategoryDao;

    @Autowired
    private ComplaintKeywordService complaintKeywordService;

    @Override
    public void addComplaintCategory(ComplaintCategory complaintCategory) {
        complaintCategoryDao.save(complaintCategory);
    }

    @Override
    public List<ComplaintCategory> getSortedComplaintCategory() {
        return complaintCategoryDao.findAllByOrderByRankAsc();
    }

    @Override
    public void updateComplaintCategoryRank(List<String> sequence) {
        int rank=1;
        for(String category:sequence){
            ComplaintCategory complaintCategory = complaintCategoryDao.findById(category).get();
            complaintCategory.setRank(rank);
            rank++;
        }
    }

    @Override
    public void initCategories() {
        if(!complaintCategoryDao.findById("Lift System").isPresent()){
            List<String> categoryList = Stream.of("Lift System",
                            "Power Supply","Cleaning Services",
                            "Fire Protection System","Water Supply",
                            "Security System","Swimming Pool",
                            "Landscaping and Gardening","Painting",
                            "Sport and Recreational Facilities",
                            "Social Facilities","Others"
                    )
                    .collect(Collectors.toList());
            int rank=1;
            for(String category:categoryList){
                ComplaintCategory complaintCategory = new ComplaintCategory();
                complaintCategory.setComplaintCategoryName(category);
                complaintCategory.setRank(rank);
                complaintCategoryDao.save(complaintCategory);
                rank++;
            }
            complaintKeywordService.initComplaintKeywords();
        }

    }
}
