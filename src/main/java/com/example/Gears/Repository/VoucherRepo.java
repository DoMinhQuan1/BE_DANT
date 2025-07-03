package com.example.Gears.Repository;

import com.example.Gears.Entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepo extends JpaRepository<Voucher, Long> {
    
    // Tìm voucher theo mã code
    Optional<Voucher> findByVoucherCode(String voucherCode);
    
    // Tìm voucher theo mã code và trạng thái active
    Optional<Voucher> findByVoucherCodeAndIsActiveTrue(String voucherCode);
    
    // Tìm tất cả voucher đang active
    List<Voucher> findByIsActiveTrue();
    
    // Tìm voucher theo trạng thái active và thời gian hiện tại
    @Query("SELECT v FROM Voucher v WHERE v.isActive = true " +
           "AND v.startDate <= :currentTime " +
           "AND v.endDate >= :currentTime " +
           "AND (v.usageLimit IS NULL OR v.usedCount < v.usageLimit)")
    List<Voucher> findValidVouchers(@Param("currentTime") LocalDateTime currentTime);
    
    // Tìm voucher theo mã code và kiểm tra tính hợp lệ
    @Query("SELECT v FROM Voucher v WHERE v.voucherCode = :voucherCode " +
           "AND v.isActive = true " +
           "AND v.startDate <= :currentTime " +
           "AND v.endDate >= :currentTime " +
           "AND (v.usageLimit IS NULL OR v.usedCount < v.usageLimit)")
    Optional<Voucher> findValidVoucherByCode(@Param("voucherCode") String voucherCode, 
                                            @Param("currentTime") LocalDateTime currentTime);
    
    // Tìm voucher theo tên (tìm kiếm mờ)
    List<Voucher> findByVoucherNameContainingIgnoreCase(String voucherName);
    
    // Tìm voucher theo loại giảm giá
    List<Voucher> findByDiscountType(Voucher.DiscountType discountType);
    
    // Tìm voucher sắp hết hạn (trong vòng 7 ngày)
    @Query("SELECT v FROM Voucher v WHERE v.isActive = true " +
           "AND v.endDate BETWEEN :currentTime AND :sevenDaysLater")
    List<Voucher> findExpiringVouchers(@Param("currentTime") LocalDateTime currentTime,
                                      @Param("sevenDaysLater") LocalDateTime sevenDaysLater);
    
    // Đếm số voucher đang active
    long countByIsActiveTrue();
    
    // Đếm số voucher đã sử dụng hết
    @Query("SELECT COUNT(v) FROM Voucher v WHERE v.usageLimit IS NOT NULL AND v.usedCount >= v.usageLimit")
    long countExhaustedVouchers();
    
    // Tìm voucher theo khoảng thời gian tạo
    List<Voucher> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Tìm voucher theo khoảng thời gian hiệu lực
    @Query("SELECT v FROM Voucher v WHERE v.startDate >= :startDate AND v.endDate <= :endDate")
    List<Voucher> findByValidityPeriod(@Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);
    
    // Kiểm tra xem voucher code đã tồn tại chưa
    boolean existsByVoucherCode(String voucherCode);
    
    // Tìm voucher có số lần sử dụng cao nhất
    @Query("SELECT v FROM Voucher v ORDER BY v.usedCount DESC")
    List<Voucher> findTopUsedVouchers();
    
    // Tìm voucher chưa được sử dụng
    List<Voucher> findByUsedCount(Integer usedCount);
} 