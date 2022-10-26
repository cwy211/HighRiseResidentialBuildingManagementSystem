package com.fyp.highriseresbuildms.dto;

import com.fyp.highriseresbuildms.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MaintenanceFundUsageDto {
    private int id;

    private String title;

    private String description;

    private BigDecimal price;

    private LocalDate paymentDate;

    private String usageMonthYear;

    private LocalDateTime uploadDateTime;

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
