package com.example.Gears.Request;

import com.example.Gears.Entity.Voucher;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VoucherRequest {
    
    private String voucherCode;
    
    private String voucherName;
    
    private String description;
    
    private Voucher.DiscountType discountType;
    
    private BigDecimal discountValue;
    
    private BigDecimal minOrderAmount;
    
    private BigDecimal maxDiscountAmount;
    
    private Integer usageLimit;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    
    private Boolean isActive = true;
    
    // Constructors
    public VoucherRequest() {}
    
    public VoucherRequest(String voucherCode, String voucherName, Voucher.DiscountType discountType, 
                         BigDecimal discountValue, LocalDateTime startDate, LocalDateTime endDate) {
        this.voucherCode = voucherCode;
        this.voucherName = voucherName;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    // Getters and Setters
    public String getVoucherCode() {
        return voucherCode;
    }
    
    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }
    
    public String getVoucherName() {
        return voucherName;
    }
    
    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Voucher.DiscountType getDiscountType() {
        return discountType;
    }
    
    public void setDiscountType(Voucher.DiscountType discountType) {
        this.discountType = discountType;
    }
    
    public BigDecimal getDiscountValue() {
        return discountValue;
    }
    
    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }
    
    public BigDecimal getMinOrderAmount() {
        return minOrderAmount;
    }
    
    public void setMinOrderAmount(BigDecimal minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }
    
    public BigDecimal getMaxDiscountAmount() {
        return maxDiscountAmount;
    }
    
    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }
    
    public Integer getUsageLimit() {
        return usageLimit;
    }
    
    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    // Validation methods
    public boolean isValidDates() {
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
    
    public boolean isValidDiscountValue() {
        if (discountType == Voucher.DiscountType.PERCENTAGE) {
            return discountValue.compareTo(BigDecimal.valueOf(100)) <= 0;
        }
        return true;
    }
    
    public boolean isValidMaxDiscount() {
        if (maxDiscountAmount != null && discountType == Voucher.DiscountType.PERCENTAGE) {
            return maxDiscountAmount.compareTo(BigDecimal.ZERO) > 0;
        }
        return true;
    }
} 