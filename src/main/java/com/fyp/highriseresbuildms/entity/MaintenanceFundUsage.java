package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="maintenance_fund_usage")
public class MaintenanceFundUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mfu_id")
    private int id;

    @Column(name="mfu_title",length = 100)
    private String title;

    @Column(name="mfu_description")
    private String description;

    @Column(name="mfu_price")
    private BigDecimal price;

    @Column(name="mfu_paymentDate")
    private LocalDate paymentDate;

    @Column(name="mfu_month_year",length = 7)
    private String usageMonthYear;

    @Column(name="mfu_uploadDateTime")
    private LocalDateTime uploadDateTime;

    @ManyToOne
    @JoinColumn(name="mfu_uploader")
    private User uploader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getUsageMonthYear() {
        return usageMonthYear;
    }

    public void setUsageMonthYear(String usageMonthYear) {
        this.usageMonthYear = usageMonthYear;
    }

    public LocalDateTime getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(LocalDateTime uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
