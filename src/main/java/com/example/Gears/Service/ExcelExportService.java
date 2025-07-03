package com.example.Gears.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.Gears.Entity.Product;
import com.example.Gears.Entity.Bill;
import com.example.Gears.Entity.User;
import com.example.Gears.Entity.Voucher;

@Service
public class ExcelExportService {

    public ByteArrayInputStream exportProductsToExcel(List<Product> products) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            
            Sheet sheet = workbook.createSheet("Products");
            
            // Tạo style cho header
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            // Tạo header
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Tên sản phẩm", "Giá", "Giá nhập", "Số lượng", "Đã bán", "Giá thị trường", "Mô tả", "Kích thước", "Kết nối", "Thương hiệu", "Danh mục"};
            
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Thêm dữ liệu
            int rowIdx = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowIdx++);
                
                row.createCell(0).setCellValue(product.getProductID());
                row.createCell(1).setCellValue(product.getProductName());
                row.createCell(2).setCellValue(product.getProductPrice());
                row.createCell(3).setCellValue(product.getProductImportPrice());
                row.createCell(4).setCellValue(product.getProductQuantily());
                row.createCell(5).setCellValue(product.getProductQuantitySold());
                row.createCell(6).setCellValue(product.getProductMarketprice());
                row.createCell(7).setCellValue(product.getProductDescription());
                row.createCell(8).setCellValue(product.getProductDimensions());
                row.createCell(9).setCellValue(product.getProductConnection());
                row.createCell(10).setCellValue(product.getBrand() != null ? product.getBrand().getBrandName() : "");
                row.createCell(11).setCellValue(product.getCategory() != null ? product.getCategory().getCategoryName() : "");
            }
            
            // Tự động điều chỉnh độ rộng cột
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
    
    public ByteArrayInputStream exportBillsToExcel(List<Object[]> billData) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            System.out.println("Processing " + billData.size() + " bills");
            
            Sheet sheet = workbook.createSheet("Bills");
            
            // Tạo style cho header
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            // Tạo header
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Ngày tạo", "Tổng tiền", "Trạng thái", "Địa chỉ", "Số điện thoại", "Ghi chú", "Khách hàng"};
            
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Thêm dữ liệu
            int rowIdx = 1;
            for (Object[] billRow : billData) {
                try {
                    Row row = sheet.createRow(rowIdx++);
                    
                    row.createCell(0).setCellValue(billRow[0] != null ? billRow[0].toString() : "");
                    row.createCell(1).setCellValue(billRow[4] != null ? billRow[4].toString() : "");
                    row.createCell(2).setCellValue(billRow[1] != null ? billRow[1].toString() : "");
                    row.createCell(3).setCellValue(billRow[7] != null ? billRow[7].toString() : "");
                    row.createCell(4).setCellValue(billRow[3] != null ? billRow[3].toString() : "");
                    row.createCell(5).setCellValue(billRow[6] != null ? billRow[6].toString() : "");
                    row.createCell(6).setCellValue(billRow[5] != null ? billRow[5].toString() : "");
                    row.createCell(7).setCellValue("User ID: " + (billRow[8] != null ? billRow[8].toString() : "N/A"));
                } catch (Exception e) {
                    System.out.println("Error processing bill row: " + e.getMessage());
                }
            }
            
            // Tự động điều chỉnh độ rộng cột
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
    
    public ByteArrayInputStream exportUsersToExcel(List<User> users) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            
            Sheet sheet = workbook.createSheet("Users");
            
            // Tạo style cho header
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            // Tạo header
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Tên đăng nhập", "Email", "Số điện thoại", "Vai trò"};
            
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Thêm dữ liệu
            int rowIdx = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowIdx++);
                
                row.createCell(0).setCellValue(user.getUserID());
                row.createCell(1).setCellValue(user.getUserName());
                row.createCell(2).setCellValue(user.getUserEmail());
                row.createCell(3).setCellValue(user.getUserPhone());
                row.createCell(4).setCellValue(user.isUserRole() ? "Admin" : "User");
            }
            
            // Tự động điều chỉnh độ rộng cột
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
    
    public ByteArrayInputStream exportVouchersToExcel(List<Voucher> vouchers) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            
            Sheet sheet = workbook.createSheet("Vouchers");
            
            // Tạo style cho header
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            // Tạo header
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Mã voucher", "Tên voucher", "Mô tả", "Loại giảm giá", "Giá trị giảm", 
                               "Giá trị đơn hàng tối thiểu", "Giảm tối đa", "Giới hạn sử dụng", "Đã sử dụng", 
                               "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Ngày tạo"};
            
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Thêm dữ liệu
            int rowIdx = 1;
            for (Voucher voucher : vouchers) {
                Row row = sheet.createRow(rowIdx++);
                
                row.createCell(0).setCellValue(voucher.getVoucherId() != null ? voucher.getVoucherId() : 0);
                row.createCell(1).setCellValue(voucher.getVoucherCode() != null ? voucher.getVoucherCode() : "");
                row.createCell(2).setCellValue(voucher.getVoucherName() != null ? voucher.getVoucherName() : "");
                row.createCell(3).setCellValue(voucher.getDescription() != null ? voucher.getDescription() : "");
                row.createCell(4).setCellValue(voucher.getDiscountType() != null ? voucher.getDiscountType().toString() : "");
                row.createCell(5).setCellValue(voucher.getDiscountValue() != null ? voucher.getDiscountValue().doubleValue() : 0.0);
                row.createCell(6).setCellValue(voucher.getMinOrderAmount() != null ? voucher.getMinOrderAmount().doubleValue() : 0.0);
                row.createCell(7).setCellValue(voucher.getMaxDiscountAmount() != null ? voucher.getMaxDiscountAmount().doubleValue() : 0.0);
                row.createCell(8).setCellValue(voucher.getUsageLimit() != null ? voucher.getUsageLimit() : 0);
                row.createCell(9).setCellValue(voucher.getUsedCount() != null ? voucher.getUsedCount() : 0);
                row.createCell(10).setCellValue(voucher.getStartDate() != null ? voucher.getStartDate().toString() : "");
                row.createCell(11).setCellValue(voucher.getEndDate() != null ? voucher.getEndDate().toString() : "");
                row.createCell(12).setCellValue(voucher.getIsActive() != null && voucher.getIsActive() ? "Active" : "Inactive");
                row.createCell(13).setCellValue(voucher.getCreatedDate() != null ? voucher.getCreatedDate().toString() : "");
            }
            
            // Tự động điều chỉnh độ rộng cột
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
} 