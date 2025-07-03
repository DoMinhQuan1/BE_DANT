package com.example.Gears.Controller;

import com.example.Gears.Entity.Voucher;
import com.example.Gears.Model.Response;
import com.example.Gears.Request.VoucherRequest;
import com.example.Gears.Service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vouchers")
@CrossOrigin(origins = "*")
public class VoucherController {
    
    @Autowired
    private VoucherService voucherService;
    
    // Tạo voucher mới
    @PostMapping
    public ResponseEntity<Response<Voucher>> createVoucher(@RequestBody VoucherRequest request) {
        try {
            Voucher voucher = voucherService.createVoucher(request);
            return ResponseEntity.ok(new Response<Voucher>(voucher));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Response<Voucher>("400", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<Voucher>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Cập nhật voucher
    @PutMapping("/{voucherId}")
    public ResponseEntity<Response<Voucher>> updateVoucher(@PathVariable Long voucherId, 
                                                 @RequestBody VoucherRequest request) {
        try {
            Voucher voucher = voucherService.updateVoucher(voucherId, request);
            return ResponseEntity.ok(new Response<Voucher>(voucher));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Response<Voucher>("400", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<Voucher>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Xóa voucher
    @DeleteMapping("/{voucherId}")
    public ResponseEntity<Response<String>> deleteVoucher(@PathVariable Long voucherId) {
        try {
            voucherService.deleteVoucher(voucherId);
            return ResponseEntity.ok(new Response<String>("200", "Xóa voucher thành công"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Response<String>("400", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<String>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy voucher theo ID
    @GetMapping("/{voucherId}")
    public ResponseEntity<Response<Voucher>> getVoucherById(@PathVariable Long voucherId) {
        try {
            Optional<Voucher> voucher = voucherService.getVoucherById(voucherId);
            if (voucher.isPresent()) {
                return ResponseEntity.ok(new Response<Voucher>(voucher.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<Voucher>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy voucher theo mã code
    @GetMapping("/code/{voucherCode}")
    public ResponseEntity<Response<Voucher>> getVoucherByCode(@PathVariable String voucherCode) {
        try {
            Optional<Voucher> voucher = voucherService.getVoucherByCode(voucherCode);
            if (voucher.isPresent()) {
                return ResponseEntity.ok(new Response<Voucher>(voucher.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<Voucher>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy tất cả voucher
    @GetMapping
    public ResponseEntity<Response<List<Voucher>>> getAllVouchers() {
        try {
            List<Voucher> vouchers = voucherService.getAllVouchers();
            Response<List<Voucher>> response = new Response<List<Voucher>>();
            response.setRowCount((long) vouchers.size());
            response.setResults(vouchers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<List<Voucher>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy voucher đang active
    @GetMapping("/active")
    public ResponseEntity<Response<List<Voucher>>> getActiveVouchers() {
        try {
            List<Voucher> vouchers = voucherService.getActiveVouchers();
            Response<List<Voucher>> response = new Response<List<Voucher>>();
            response.setRowCount((long) vouchers.size());
            response.setResults(vouchers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<List<Voucher>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy voucher hợp lệ
    @GetMapping("/valid")
    public ResponseEntity<Response<List<Voucher>>> getValidVouchers() {
        try {
            List<Voucher> vouchers = voucherService.getValidVouchers();
            Response<List<Voucher>> response = new Response<List<Voucher>>();
            response.setRowCount((long) vouchers.size());
            response.setResults(vouchers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<List<Voucher>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy voucher sắp hết hạn
    @GetMapping("/expiring")
    public ResponseEntity<Response<List<Voucher>>> getExpiringVouchers(@RequestParam(defaultValue = "7") int daysAhead) {
        try {
            List<Voucher> vouchers = voucherService.getExpiringVouchers(daysAhead);
            Response<List<Voucher>> response = new Response<List<Voucher>>();
            response.setRowCount((long) vouchers.size());
            response.setResults(vouchers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<List<Voucher>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Kiểm tra voucher có hợp lệ không
    @GetMapping("/validate/{voucherCode}")
    public ResponseEntity<Response<Boolean>> validateVoucher(@PathVariable String voucherCode) {
        try {
            boolean isValid = voucherService.isValidVoucher(voucherCode);
            return ResponseEntity.ok(new Response<Boolean>(isValid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<Boolean>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Tính toán giảm giá
    @PostMapping("/calculate-discount")
    public ResponseEntity<Response<BigDecimal>> calculateDiscount(@RequestParam String voucherCode,
                                                     @RequestParam BigDecimal orderAmount) {
        try {
            BigDecimal discount = voucherService.calculateDiscount(voucherCode, orderAmount);
            return ResponseEntity.ok(new Response<BigDecimal>(discount));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<BigDecimal>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Áp dụng voucher cho đơn hàng
    @PostMapping("/apply")
    public ResponseEntity<Response<String>> applyVoucher(@RequestParam String voucherCode,
                                                @RequestParam Long billId) {
        try {
            boolean success = voucherService.applyVoucher(voucherCode, billId);
            if (success) {
                return ResponseEntity.ok(new Response<String>("200", "Áp dụng voucher thành công"));
            } else {
                return ResponseEntity.badRequest().body(new Response<String>("400", "Không thể áp dụng voucher"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<String>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Tìm kiếm voucher theo tên
    @GetMapping("/search")
    public ResponseEntity<Response<List<Voucher>>> searchVouchers(@RequestParam String voucherName) {
        try {
            List<Voucher> vouchers = voucherService.searchVouchersByName(voucherName);
            return ResponseEntity.ok(new Response<List<Voucher>>((long) vouchers.size(), vouchers));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<List<Voucher>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy voucher theo loại giảm giá
    @GetMapping("/type/{discountType}")
    public ResponseEntity<Response<List<Voucher>>> getVouchersByType(@PathVariable Voucher.DiscountType discountType) {
        try {
            List<Voucher> vouchers = voucherService.getVouchersByDiscountType(discountType);
            Response<List<Voucher>> response = new Response<List<Voucher>>();
            response.setRowCount((long) vouchers.size());
            response.setResults(vouchers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<List<Voucher>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy voucher theo khoảng thời gian
    @GetMapping("/date-range")
    public ResponseEntity<Response<List<Voucher>>> getVouchersByDateRange(@RequestParam String startDate,
                                                          @RequestParam String endDate) {
        try {
            LocalDateTime start = LocalDateTime.parse(startDate);
            LocalDateTime end = LocalDateTime.parse(endDate);
            List<Voucher> vouchers = voucherService.getVouchersByDateRange(start, end);
            Response<List<Voucher>> response = new Response<List<Voucher>>();
            response.setRowCount((long) vouchers.size());
            response.setResults(vouchers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<List<Voucher>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Lấy thống kê voucher
    @GetMapping("/statistics")
    public ResponseEntity<Response<Map<String, Object>>> getVoucherStatistics() {
        try {
            long activeCount = voucherService.getActiveVoucherCount();
            long exhaustedCount = voucherService.getExhaustedVoucherCount();
            List<Voucher> topUsed = voucherService.getTopUsedVouchers(5);
            
            Map<String, Object> statistics = Map.of(
                "activeCount", activeCount, 
                "exhaustedCount", exhaustedCount, 
                "topUsed", topUsed
            );
            return ResponseEntity.ok(new Response<Map<String, Object>>(statistics));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<Map<String, Object>>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Tạo mã voucher mới
    @GetMapping("/generate-code")
    public ResponseEntity<Response<String>> generateVoucherCode() {
        try {
            String code = voucherService.generateVoucherCode();
            return ResponseEntity.ok(new Response<String>(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<String>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Kiểm tra mã voucher đã tồn tại chưa
    @GetMapping("/check-code/{voucherCode}")
    public ResponseEntity<Response<Boolean>> checkVoucherCodeExists(@PathVariable String voucherCode) {
        try {
            boolean exists = voucherService.isVoucherCodeExists(voucherCode);
            return ResponseEntity.ok(new Response<Boolean>(exists));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<Boolean>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    // Deactivate voucher hết hạn
    @PostMapping("/deactivate-expired")
    public ResponseEntity<Response<String>> deactivateExpiredVouchers() {
        try {
            voucherService.deactivateExpiredVouchers();
            return ResponseEntity.ok(new Response<String>("200", "Đã deactivate voucher hết hạn"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<String>("500", "Lỗi server: " + e.getMessage()));
        }
    }
} 