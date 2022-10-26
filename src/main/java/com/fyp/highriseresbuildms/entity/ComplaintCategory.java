package com.fyp.highriseresbuildms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="complaint_category")
public class ComplaintCategory {
    @Id
    @Column(name="cc_name",length = 100)
    private String complaintCategoryName;

    @Column(name="cc_rank")
    private int rank;


    @OneToMany(mappedBy="complaintCategory",fetch
            = FetchType.LAZY)
    @JsonIgnore
    private List<ComplaintKeyword> keywordListList = new ArrayList<>();

    public String getComplaintCategoryName() {
        return complaintCategoryName;
    }

    public void setComplaintCategoryName(String complaintCategoryName) {
        this.complaintCategoryName = complaintCategoryName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<ComplaintKeyword> getKeywordListList() {
        return keywordListList;
    }

    public void setKeywordListList(List<ComplaintKeyword> keywordListList) {
        this.keywordListList = keywordListList;
    }

}
