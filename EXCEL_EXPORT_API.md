# API Export Excel - Hướng dẫn sử dụng

## Tổng quan
Hệ thống cung cấp các API để export dữ liệu ra file Excel (.xlsx) cho các entity chính trong hệ thống.

## Các API có sẵn

### 1. Export Products (Sản phẩm)
**Endpoint:** `GET /api/export/products/excel`

**Mô tả:** Export tất cả sản phẩm ra file Excel

**Headers:**
- Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
- Content-Disposition: attachment; filename=products.xlsx

**Các cột trong Excel:**
- ID
- Tên sản phẩm
- Giá
- Giá nhập
- Số lượng
- Đã bán
- Giá thị trường
- Mô tả
- Kích thước
- Kết nối
- Thương hiệu
- Danh mục

**Ví dụ sử dụng:**
```bash
curl -X GET http://localhost:8082/api/export/products/excel -o products.xlsx
```

### 2. Export Bills (Hóa đơn)
**Endpoint:** `GET /api/export/bills/excel`

**Mô tả:** Export tất cả hóa đơn ra file Excel

**Headers:**
- Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
- Content-Disposition: attachment; filename=bills.xlsx

**Các cột trong Excel:**
- ID
- Ngày tạo
- Tổng tiền
- Trạng thái
- Địa chỉ
- Số điện thoại
- Ghi chú
- Khách hàng

**Ví dụ sử dụng:**
```bash
curl -X GET http://localhost:8082/api/export/bills/excel -o bills.xlsx
```

### 3. Export Users (Người dùng)
**Endpoint:** `GET /api/export/users/excel`

**Mô tả:** Export tất cả người dùng ra file Excel

**Headers:**
- Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
- Content-Disposition: attachment; filename=users.xlsx

**Các cột trong Excel:**
- ID
- Tên đăng nhập
- Email
- Số điện thoại
- Vai trò

**Ví dụ sử dụng:**
```bash
curl -X GET http://localhost:8082/api/export/users/excel -o users.xlsx
```

## API Test

### Test Bills
**Endpoint:** `GET /api/export/test/bills`

**Mô tả:** Kiểm tra số lượng bills có thể export

**Ví dụ sử dụng:**
```bash
curl http://localhost:8082/api/export/test/bills
```

### Test Users
**Endpoint:** `GET /api/export/test/users`

**Mô tả:** Kiểm tra số lượng users có thể export

**Ví dụ sử dụng:**
```bash
curl http://localhost:8082/api/export/test/users
```

## Tính năng

### 1. Styling Excel
- Header có màu nền và font chữ đậm
- Tự động điều chỉnh độ rộng cột
- Màu sắc khác nhau cho từng loại dữ liệu:
  - Products: Xanh dương
  - Bills: Xanh lá
  - Users: Cam

### 2. Null Safety
- Xử lý dữ liệu null an toàn
- Hiển thị "N/A" hoặc chuỗi rỗng cho dữ liệu không có

### 3. Error Handling
- Xử lý lỗi database gracefully
- Log lỗi chi tiết để debug
- Trả về HTTP 500 khi có lỗi

## Cấu trúc Code

### Files chính:
1. `ExcelExportService.java` - Service xử lý logic export Excel
2. `ExcelExportController.java` - Controller cung cấp REST API
3. `BillRepo.java` - Repository với native query để tránh lazy loading issues

### Dependencies:
- Apache POI 3.17 (đã có trong pom.xml)
- Spring Boot 2.5.4
- MySQL 8.0

## Lưu ý kỹ thuật

### 1. Performance
- Sử dụng native query cho Bills để tránh lazy loading
- Xử lý dữ liệu theo batch để tránh memory issues
- Tự động đóng workbook sau khi tạo xong

### 2. Security
- API không yêu cầu authentication (có thể thêm sau)
- Validate input data
- Sanitize output data

### 3. Compatibility
- Excel format: .xlsx (Office 2007+)
- Encoding: UTF-8
- Compatible với Excel, LibreOffice, Google Sheets

## Troubleshooting

### Lỗi thường gặp:

1. **404 Not Found**
   - Kiểm tra ứng dụng đã start chưa
   - Kiểm tra endpoint URL

2. **500 Internal Server Error**
   - Kiểm tra database connection
   - Kiểm tra log để xem lỗi chi tiết

3. **File Excel bị hỏng**
   - Kiểm tra Apache POI version
   - Kiểm tra memory usage

### Debug:
```bash
# Kiểm tra ứng dụng đang chạy
curl http://localhost:8082/api/product/test

# Kiểm tra số lượng dữ liệu
curl http://localhost:8082/api/export/test/bills
curl http://localhost:8082/api/export/test/users
```

## Mở rộng

Để thêm export cho entity mới:

1. Thêm method trong `ExcelExportService`
2. Thêm endpoint trong `ExcelExportController`
3. Thêm repository method nếu cần
4. Test và document

## Kết luận

Hệ thống export Excel đã được implement hoàn chỉnh với:
- ✅ 3 API export chính (Products, Bills, Users)
- ✅ Styling và formatting đẹp
- ✅ Error handling robust
- ✅ Performance optimized
- ✅ Documentation đầy đủ 