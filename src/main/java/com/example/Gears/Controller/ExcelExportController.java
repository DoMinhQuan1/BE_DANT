package com.example.Gears.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gears.Entity.Product;
import com.example.Gears.Entity.Bill;
import com.example.Gears.Entity.User;
import com.example.Gears.Entity.Voucher;
import com.example.Gears.Service.ExcelExportService;
import com.example.Gears.Repository.ProductRepo;
import com.example.Gears.Repository.BillRepo;
import com.example.Gears.Repository.UserRepo;
import com.example.Gears.Repository.VoucherRepo;

@RestController
@RequestMapping("/api/export")
public class ExcelExportController {

    @Autowired
    private ExcelExportService excelExportService;
    
    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private BillRepo billRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private VoucherRepo voucherRepo;

    @GetMapping("/products/excel")
    public ResponseEntity<InputStreamResource> exportProductsToExcel() {
        try {
            List<Product> products = productRepo.findAll();
            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=products.xlsx");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            
            InputStreamResource resource = new InputStreamResource(
                excelExportService.exportProductsToExcel(products)
            );
            
            return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
                
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/bills/excel")
    public ResponseEntity<InputStreamResource> exportBillsToExcel() {
        try {
            List<Object[]> billData = billRepo.findAllBillData();
            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=bills.xlsx");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            
            InputStreamResource resource = new InputStreamResource(
                excelExportService.exportBillsToExcel(billData)
            );
            
            return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
                
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/users/excel")
    public ResponseEntity<InputStreamResource> exportUsersToExcel() {
        try {
            List<User> users = userRepo.findAll();
            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=users.xlsx");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            
            InputStreamResource resource = new InputStreamResource(
                excelExportService.exportUsersToExcel(users)
            );
            
            return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
                
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/test/bills")
    public ResponseEntity<String> testBills() {
        try {
            List<Object[]> billData = billRepo.findAllBillData();
            return ResponseEntity.ok("Found " + billData.size() + " bills");
        } catch (Exception e) {
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/test/users")
    public ResponseEntity<String> testUsers() {
        try {
            List<User> users = userRepo.findAll();
            return ResponseEntity.ok("Found " + users.size() + " users");
        } catch (Exception e) {
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/vouchers/excel")
    public ResponseEntity<InputStreamResource> exportVouchersToExcel() {
        try {
            List<Voucher> vouchers = voucherRepo.findAll();
            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=vouchers.xlsx");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            
            InputStreamResource resource = new InputStreamResource(
                excelExportService.exportVouchersToExcel(vouchers)
            );
            
            return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
                
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/test/vouchers")
    public ResponseEntity<String> testVouchers() {
        try {
            List<Voucher> vouchers = voucherRepo.findAll();
            return ResponseEntity.ok("Found " + vouchers.size() + " vouchers");
        } catch (Exception e) {
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }
} 