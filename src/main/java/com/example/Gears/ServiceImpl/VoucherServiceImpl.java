package com.example.Gears.ServiceImpl;

import com.example.Gears.Entity.Bill;
import com.example.Gears.Entity.Voucher;
import com.example.Gears.Repository.BillRepo;
import com.example.Gears.Repository.VoucherRepo;
import com.example.Gears.Request.VoucherRequest;
import com.example.Gears.Service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class VoucherServiceImpl implements VoucherService {
    
    @Autowired
    private VoucherRepo voucherRepo;
    
    @Autowired
    private BillRepo billRepo;
    
    @Override
    public Voucher createVoucher(VoucherRequest request) {
        // Validate request
        if (request.getVoucherCode() == null || request.getVoucherCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Mã voucher không được để trống");
        }
        
        if (request.getVoucherName() == null || request.getVoucherName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên voucher không được để trống");
        }
        
        if (request.getDiscountType() == null) {
            throw new IllegalArgumentException("Loại giảm giá không được để trống");
        }
        
        if (request.getDiscountValue() == null || request.getDiscountValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Giá trị giảm giá phải lớn hơn 0");
        }
        
        if (request.getStartDate() == null || request.getEndDate() == null) {
            throw new IllegalArgumentException("Ngày bắt đầu và kết thúc không được để trống");
        }
        
        if (!request.getStartDate().isBefore(request.getEndDate())) {
            throw new IllegalArgumentException("Ngày bắt đầu phải trước ngày kết thúc");
        }
        
        // Validate discount value for percentage type
        if (request.getDiscountType() == Voucher.DiscountType.PERCENTAGE && 
            request.getDiscountValue().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Phần trăm giảm giá không được vượt quá 100%");
        }
        
        // Check if voucher code already exists
        if (voucherRepo.existsByVoucherCode(request.getVoucherCode())) {
            throw new IllegalArgumentException("Mã voucher đã tồn tại");
        }
        
        // Create voucher
        Voucher voucher = new Voucher();
        voucher.setVoucherCode(request.getVoucherCode());
        voucher.setVoucherName(request.getVoucherName());
        voucher.setDescription(request.getDescription());
        voucher.setDiscountType(request.getDiscountType());
        voucher.setDiscountValue(request.getDiscountValue());
        voucher.setMinOrderAmount(request.getMinOrderAmount());
        voucher.setMaxDiscountAmount(request.getMaxDiscountAmount());
        voucher.setUsageLimit(request.getUsageLimit());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());
        voucher.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);
        
        return voucherRepo.save(voucher);
    }
    
    @Override
    public Voucher updateVoucher(Long voucherId, VoucherRequest request) {
        Optional<Voucher> existingVoucher = voucherRepo.findById(voucherId);
        if (!existingVoucher.isPresent()) {
            throw new IllegalArgumentException("Voucher không tồn tại");
        }
        
        Voucher voucher = existingVoucher.get();
        
        // Update fields
        if (request.getVoucherName() != null) {
            voucher.setVoucherName(request.getVoucherName());
        }
        if (request.getDescription() != null) {
            voucher.setDescription(request.getDescription());
        }
        if (request.getDiscountType() != null) {
            voucher.setDiscountType(request.getDiscountType());
        }
        if (request.getDiscountValue() != null) {
            voucher.setDiscountValue(request.getDiscountValue());
        }
        if (request.getMinOrderAmount() != null) {
            voucher.setMinOrderAmount(request.getMinOrderAmount());
        }
        if (request.getMaxDiscountAmount() != null) {
            voucher.setMaxDiscountAmount(request.getMaxDiscountAmount());
        }
        if (request.getUsageLimit() != null) {
            voucher.setUsageLimit(request.getUsageLimit());
        }
        if (request.getStartDate() != null) {
            voucher.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            voucher.setEndDate(request.getEndDate());
        }
        if (request.getIsActive() != null) {
            voucher.setIsActive(request.getIsActive());
        }
        
        return voucherRepo.save(voucher);
    }
    
    @Override
    public void deleteVoucher(Long voucherId) {
        if (!voucherRepo.existsById(voucherId)) {
            throw new IllegalArgumentException("Voucher không tồn tại");
        }
        voucherRepo.deleteById(voucherId);
    }
    
    @Override
    public Optional<Voucher> getVoucherById(Long voucherId) {
        return voucherRepo.findById(voucherId);
    }
    
    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepo.findAll();
    }
    
    @Override
    public Optional<Voucher> getVoucherByCode(String voucherCode) {
        return voucherRepo.findByVoucherCode(voucherCode);
    }
    
    @Override
    public List<Voucher> getActiveVouchers() {
        return voucherRepo.findByIsActiveTrue();
    }
    
    @Override
    public List<Voucher> getValidVouchers() {
        return voucherRepo.findValidVouchers(LocalDateTime.now());
    }
    
    @Override
    public List<Voucher> getExpiringVouchers(int daysAhead) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime future = now.plusDays(daysAhead);
        return voucherRepo.findExpiringVouchers(now, future);
    }
    
    @Override
    public boolean isValidVoucher(String voucherCode) {
        Optional<Voucher> voucher = voucherRepo.findValidVoucherByCode(voucherCode, LocalDateTime.now());
        return voucher.isPresent();
    }
    
    @Override
    public BigDecimal calculateDiscount(String voucherCode, BigDecimal orderAmount) {
        Optional<Voucher> voucherOpt = voucherRepo.findValidVoucherByCode(voucherCode, LocalDateTime.now());
        if (!voucherOpt.isPresent()) {
            return BigDecimal.ZERO;
        }
        
        Voucher voucher = voucherOpt.get();
        return voucher.calculateDiscount(orderAmount);
    }
    
    @Override
    public boolean applyVoucher(String voucherCode, Long billId) {
        Optional<Voucher> voucherOpt = voucherRepo.findValidVoucherByCode(voucherCode, LocalDateTime.now());
        if (!voucherOpt.isPresent()) {
            return false;
        }
        
        Optional<Bill> billOpt = billRepo.findById(billId);
        if (!billOpt.isPresent()) {
            return false;
        }
        
        Voucher voucher = voucherOpt.get();
        Bill bill = billOpt.get();
        
        // Calculate discount
        BigDecimal orderAmount = BigDecimal.valueOf(bill.getTotal());
        BigDecimal discount = voucher.calculateDiscount(orderAmount);
        if (discount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        
        // Apply discount to bill
        long newTotal = orderAmount.subtract(discount).longValue();
        bill.setTotal(newTotal);
        billRepo.save(bill);
        
        // Increment voucher usage
        voucher.incrementUsage();
        voucherRepo.save(voucher);
        
        return true;
    }
    
    @Override
    public List<Voucher> searchVouchersByName(String voucherName) {
        return voucherRepo.findByVoucherNameContainingIgnoreCase(voucherName);
    }
    
    @Override
    public List<Voucher> getVouchersByDiscountType(Voucher.DiscountType discountType) {
        return voucherRepo.findByDiscountType(discountType);
    }
    
    @Override
    public List<Voucher> getVouchersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return voucherRepo.findByValidityPeriod(startDate, endDate);
    }
    
    @Override
    public long getActiveVoucherCount() {
        return voucherRepo.countByIsActiveTrue();
    }
    
    @Override
    public long getExhaustedVoucherCount() {
        return voucherRepo.countExhaustedVouchers();
    }
    
    @Override
    public List<Voucher> getTopUsedVouchers(int limit) {
        List<Voucher> allVouchers = voucherRepo.findTopUsedVouchers();
        return allVouchers.subList(0, Math.min(limit, allVouchers.size()));
    }
    
    @Override
    public String generateVoucherCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        
        do {
            code.setLength(0);
            for (int i = 0; i < 8; i++) {
                code.append(chars.charAt(random.nextInt(chars.length())));
            }
        } while (voucherRepo.existsByVoucherCode(code.toString()));
        
        return code.toString();
    }
    
    @Override
    public boolean isVoucherCodeExists(String voucherCode) {
        return voucherRepo.existsByVoucherCode(voucherCode);
    }
    
    @Override
    public void deactivateExpiredVouchers() {
        List<Voucher> activeVouchers = voucherRepo.findByIsActiveTrue();
        LocalDateTime now = LocalDateTime.now();
        
        for (Voucher voucher : activeVouchers) {
            if (voucher.getEndDate().isBefore(now)) {
                voucher.setIsActive(false);
                voucherRepo.save(voucher);
            }
        }
    }
} 