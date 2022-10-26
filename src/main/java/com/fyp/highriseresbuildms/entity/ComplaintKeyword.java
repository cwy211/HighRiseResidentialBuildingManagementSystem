package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;

@Entity
@Table(name="complaint_keyword")
public class ComplaintKeyword {
    @Id
    @Column(name="keyword",length = 20)
    private String keyword;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="keyword_category")
    private ComplaintCategory complaintCategory;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ComplaintCategory getComplaintCategory() {
        return complaintCategory;
    }

    public void setComplaintCategory(ComplaintCategory complaintCategory) {
        this.complaintCategory = complaintCategory;
    }
}
