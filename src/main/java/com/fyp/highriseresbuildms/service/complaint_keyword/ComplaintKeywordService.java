package com.fyp.highriseresbuildms.service.complaint_keyword;

import com.fyp.highriseresbuildms.dto.ComplaintKeywordDto;
import com.fyp.highriseresbuildms.entity.ComplaintKeyword;

import java.util.List;

public interface ComplaintKeywordService {
    void addComplaintKeyword(ComplaintKeywordDto complaintKeywordDto);
    List<String> getAllKeyword(String category);
    List<ComplaintKeyword> getAllKeyword();
    List<String> extractKeyword(List<ComplaintKeyword> complaintKeywordList);
    void deleteKeyword(String keyword);
    List<ComplaintKeyword> getAllKeywordAsc();
    boolean checkDuplicatedKeyword(ComplaintKeywordDto complaintKeywordDto);
    String lemmaAnalysis(String keyword);
    void initComplaintKeywords();
    void initHelper(String keyword,String category);
}
