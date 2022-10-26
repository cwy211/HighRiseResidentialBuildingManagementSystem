package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.dto.ComplaintKeywordDto;
import com.fyp.highriseresbuildms.entity.Complaint;
import com.fyp.highriseresbuildms.entity.ComplaintCategory;
import com.fyp.highriseresbuildms.service.complaint.ComplaintService;
import com.fyp.highriseresbuildms.service.complaint_category.ComplaintCategoryService;
import com.fyp.highriseresbuildms.service.complaint_keyword.ComplaintKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintCategoryService complaintCategoryService;

    @Autowired
    private ComplaintKeywordService complaintKeywordService;

    @Autowired
    private ComplaintService complaintService;

    @RequestMapping(value ="/createComplaint",
            method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('User')")
    public ResponseEntity createComplaint(@RequestBody Complaint complaint){
        complaintService.createComplaint(complaint);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/addComplaintCategory",
            method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity addComplaintCategory(@RequestBody ComplaintCategory complaintCategory){
        complaintCategoryService.addComplaintCategory(complaintCategory);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/addComplaintKeyword",
            method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity addComplaintKeyword(@RequestBody ComplaintKeywordDto complaintKeywordDto){
        if(complaintKeywordService.checkDuplicatedKeyword(complaintKeywordDto)){
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }
        complaintKeywordService.addComplaintKeyword(complaintKeywordDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value ="/getAllComplaint",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<Complaint> getAllComplaint(){

        return complaintService.getAllComplaint();
    }

    @RequestMapping(value ="/getAllComplaintBySentimentAsc",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<Complaint> getAllComplaintBySentimentAsc(){

        return complaintService.getAllComplaintSentimentAsc();
    }

    @RequestMapping(value ="/getAllComplaintMaintenancePriorityAsc",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<Complaint> getAllComplaintMaintenancePriorityAsc(){

        return complaintService.getAllComplaintMaintenancePriorityAsc();
    }

    @RequestMapping(value ="/getComplaintByUserName",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('User')")
    public List<Complaint> getComplaintByUserName(){

        return complaintService.getComplaintByUserName();
    }

    @RequestMapping(value ="/getComplaintById",
            method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public Complaint getComplaintById(@RequestParam int id){

        return complaintService.getComplaintById(id);
    }

    @RequestMapping(value ="/handleComplaint",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity handleComplaint(@RequestBody Complaint complaint){
        complaintService.handleComplaint(complaint);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value ="/processComplaint",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity processComplaint(@RequestBody Complaint complaint){
        complaintService.processComplaintStatus(complaint);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value ="/getAllComplaintCategory",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<ComplaintCategory> getAllComplaintCategory(){

        return complaintCategoryService.getSortedComplaintCategory();
    }

    @RequestMapping(value ="/updateComplaintCategoryRank",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity updateComplaintCategoryRank(@RequestBody List<String> sequence){
        complaintCategoryService.updateComplaintCategoryRank(sequence);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value ="/getKeywordByComplaintCategory",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<String> getKeywordByComplaintCategory(@RequestParam String category){

        return complaintKeywordService.getAllKeyword(category);
    }

    @RequestMapping(value ="/deleteKeyword",
            method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity deleteKeyword(@RequestParam String keyword){
        complaintKeywordService.deleteKeyword(keyword);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostConstruct
    public void initCategories(){
        complaintCategoryService.initCategories();
    }
}
