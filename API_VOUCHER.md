# Voucher API Documentation

## Tổng quan
Hệ thống Voucher cung cấp các API để quản lý mã giảm giá, bao gồm tạo, cập nhật, xóa và áp dụng voucher cho đơn hàng.

## Cấu trúc dữ liệu

### Voucher Entity
```json
{
  "voucherId": 1,
  "voucherCode": "WELCOME10",
  "voucherName": "Chào mừng khách hàng mới",
  "description": "Giảm 10% cho đơn hàng đầu tiên",
  "discountType": "PERCENTAGE",
  "discountValue": 10.00,
  "minOrderAmount": 100000,
  "maxDiscountAmount": 50000,
  "usageLimit": 100,
  "usedCount": 0,
  "startDate": "2024-01-01T00:00:00",
  "endDate": "2024-12-31T23:59:59",
  "isActive": true,
  "createdDate": "2024-01-01T00:00:00",
  "updatedDate": "2024-01-01T00:00:00"
}
```

### DiscountType Enum
- `PERCENTAGE`: Giảm theo phần trăm
- `FIXED_AMOUNT`: Giảm theo số tiền cố định

## API Endpoints

### 1. Tạo voucher mới
**POST** `/api/vouchers`

**Request Body:**
```json
{
  "voucherCode": "WELCOME10",
  "voucherName": "Chào mừng khách hàng mới",
  "description": "Giảm 10% cho đơn hàng đầu tiên",
  "discountType": "PERCENTAGE",
  "discountValue": 10.00,
  "minOrderAmount": 100000,
  "maxDiscountAmount": 50000,
  "usageLimit": 100,
  "startDate": "2024-01-01T00:00:00",
  "endDate": "2024-12-31T23:59:59",
  "isActive": true
}
```

**Response:**
```json
{
  "errorCode": null,
  "errorMessage": null,
  "result": {
    "voucherId": 1,
    "voucherCode": "WELCOME10",
    "voucherName": "Chào mừng khách hàng mới",
    "discountType": "PERCENTAGE",
    "discountValue": 10.00,
    "isActive": true
  }
}
```

### 2. Cập nhật voucher
**PUT** `/api/vouchers/{voucherId}`

**Request Body:** Tương tự như tạo voucher

### 3. Xóa voucher
**DELETE** `/api/vouchers/{voucherId}`

### 4. Lấy voucher theo ID
**GET** `/api/vouchers/{voucherId}`

### 5. Lấy voucher theo mã code
**GET** `/api/vouchers/code/{voucherCode}`

### 6. Lấy tất cả voucher
**GET** `/api/vouchers`

### 7. Lấy voucher đang active
**GET** `/api/vouchers/active`

### 8. Lấy voucher hợp lệ
**GET** `/api/vouchers/valid`

### 9. Lấy voucher sắp hết hạn
**GET** `/api/vouchers/expiring?daysAhead=7`

### 10. Kiểm tra voucher có hợp lệ không
**GET** `/api/vouchers/validate/{voucherCode}`

**Response:**
```json
{
  "errorCode": null,
  "errorMessage": null,
  "result": true
}
```

### 11. Tính toán giảm giá
**POST** `/api/vouchers/calculate-discount?voucherCode=WELCOME10&orderAmount=200000`

**Response:**
```json
{
  "errorCode": null,
  "errorMessage": null,
  "result": 20000.00
}
```

### 12. Áp dụng voucher cho đơn hàng
**POST** `/api/vouchers/apply?voucherCode=WELCOME10&billId=1`

**Response:**
```json
{
  "errorCode": null,
  "errorMessage": null,
  "result": "Áp dụng voucher thành công"
}
```

### 13. Tìm kiếm voucher theo tên
**GET** `/api/vouchers/search?voucherName=welcome`

### 14. Lấy voucher theo loại giảm giá
**GET** `/api/vouchers/type/PERCENTAGE`

### 15. Lấy voucher theo khoảng thời gian
**GET** `/api/vouchers/date-range?startDate=2024-01-01T00:00:00&endDate=2024-12-31T23:59:59`

### 16. Thống kê voucher
**GET** `/api/vouchers/statistics`

**Response:**
```json
{
  "errorCode": null,
  "errorMessage": null,
  "result": {
    "activeCount": 5,
    "exhaustedCount": 2,
    "topUsed": [
      {
        "voucherId": 1,
        "voucherCode": "WELCOME10",
        "usedCount": 50
      }
    ]
  }
}
```

### 17. Tạo mã voucher tự động
**GET** `/api/vouchers/generate-code`

**Response:**
```json
{
  "errorCode": null,
  "errorMessage": null,
  "result": "ABC12345"
}
```

### 18. Kiểm tra mã voucher đã tồn tại
**GET** `/api/vouchers/check-code/{voucherCode}`

### 19. Deactivate voucher hết hạn
**POST** `/api/vouchers/deactivate-expired`

## Quy tắc tính toán giảm giá

### 1. Voucher loại PERCENTAGE
- Giảm giá = (Giá trị đơn hàng × Phần trăm giảm) / 100
- Nếu có maxDiscountAmount: Giảm giá = min(Giảm giá tính được, maxDiscountAmount)
- Nếu có minOrderAmount: Chỉ áp dụng khi đơn hàng ≥ minOrderAmount

### 2. Voucher loại FIXED_AMOUNT
- Giảm giá = Giá trị cố định
- Nếu có minOrderAmount: Chỉ áp dụng khi đơn hàng ≥ minOrderAmount

### 3. Điều kiện áp dụng voucher
- Voucher phải active (isActive = true)
- Thời gian hiện tại phải trong khoảng startDate và endDate
- Số lần sử dụng chưa đạt giới hạn (usedCount < usageLimit)
- Giá trị đơn hàng ≥ minOrderAmount (nếu có)

## Ví dụ sử dụng

### Tạo voucher giảm 10%
```bash
curl -X POST http://localhost:8082/api/vouchers \
  -H "Content-Type: application/json" \
  -d '{
    "voucherCode": "SAVE10",
    "voucherName": "Tiết kiệm 10%",
    "discountType": "PERCENTAGE",
    "discountValue": 10.00,
    "minOrderAmount": 100000,
    "maxDiscountAmount": 50000,
    "startDate": "2024-01-01T00:00:00",
    "endDate": "2024-12-31T23:59:59"
  }'
```

### Tính toán giảm giá
```bash
curl -X POST "http://localhost:8082/api/vouchers/calculate-discount?voucherCode=SAVE10&orderAmount=200000"
```

### Áp dụng voucher
```bash
curl -X POST "http://localhost:8082/api/vouchers/apply?voucherCode=SAVE10&billId=1"
```

## Lỗi thường gặp

### 1. Voucher không tồn tại
```json
{
  "errorCode": "400",
  "errorMessage": "Voucher không tồn tại"
}
```

### 2. Voucher đã hết hạn
```json
{
  "errorCode": "400",
  "errorMessage": "Voucher đã hết hạn"
}
```

### 3. Voucher đã đạt giới hạn sử dụng
```json
{
  "errorCode": "400",
  "errorMessage": "Voucher đã đạt giới hạn sử dụng"
}
```

### 4. Đơn hàng không đủ điều kiện
```json
{
  "errorCode": "400",
  "errorMessage": "Đơn hàng không đủ điều kiện áp dụng voucher"
}
```

## Tính năng nâng cao

### 1. Tự động deactivate voucher hết hạn
- Chạy định kỳ để deactivate các voucher đã hết hạn
- Có thể gọi API `/api/vouchers/deactivate-expired` để thực hiện thủ công

### 2. Tạo mã voucher tự động
- API `/api/vouchers/generate-code` tạo mã 8 ký tự ngẫu nhiên
- Đảm bảo mã không trùng lặp

### 3. Thống kê sử dụng
- Theo dõi số lượng voucher active
- Thống kê voucher đã sử dụng hết
- Danh sách voucher được sử dụng nhiều nhất

## Cài đặt và triển khai

### 1. Tạo bảng database
Chạy file `voucher_schema.sql` để tạo bảng và dữ liệu mẫu.

### 2. Cấu hình application.properties
```properties
# Cấu hình JPA cho voucher
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Khởi động ứng dụng
```bash
mvn spring-boot:run
```

## Bảo mật

- Tất cả API đều có validation đầu vào
- Kiểm tra tính hợp lệ của voucher trước khi áp dụng
- Logging đầy đủ cho việc audit
- Rate limiting cho API tạo voucher

## Monitoring

- Theo dõi số lượng voucher được tạo/sử dụng
- Alert khi voucher sắp hết hạn
- Thống kê hiệu quả của từng loại voucher 