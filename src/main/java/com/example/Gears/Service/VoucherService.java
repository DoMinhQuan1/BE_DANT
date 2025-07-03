package com.example.Gears.Service;

import com.example.Gears.Entity.Voucher;
import com.example.Gears.Request.VoucherRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoucherService {
    
    // CRUD operations
    Voucher createVoucher(VoucherRequest request);
    Voucher updateVoucher(Long voucherId, VoucherRequest request);
    void deleteVoucher(Long voucherId);
    Optional<Voucher> getVoucherById(Long voucherId);
    List<Voucher> getAllVouchers();
    
    // Business operations
    Optional<Voucher> getVoucherByCode(String voucherCode);
    List<Voucher> getActiveVouchers();
    List<Voucher> getValidVouchers();
    List<Voucher> getExpiringVouchers(int daysAhead);
    
    // Validation and calculation
    boolean isValidVoucher(String voucherCode);
    BigDecimal calculateDiscount(String voucherCode, BigDecimal orderAmount);
    boolean applyVoucher(String voucherCode, Long billId);
    
    // Search operations
    List<Voucher> searchVouchersByName(String voucherName);
    List<Voucher> getVouchersByDiscountType(Voucher.DiscountType discountType);
    List<Voucher> getVouchersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    // Statistics
    long getActiveVoucherCount();
    long getExhaustedVoucherCount();
    List<Voucher> getTopUsedVouchers(int limit);
    
    // Utility methods
    String generateVoucherCode();
    boolean isVoucherCodeExists(String voucherCode);
    void deactivateExpiredVouchers();
} 