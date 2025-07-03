-- Tạo bảng voucher
CREATE TABLE IF NOT EXISTS voucher (
    voucher_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    voucher_code VARCHAR(50) NOT NULL UNIQUE,
    voucher_name VARCHAR(255) NOT NULL,
    description TEXT,
    discount_type VARCHAR(20) NOT NULL,
    discount_value DECIMAL(10,2) NOT NULL,
    min_order_amount DECIMAL(10,2),
    max_discount_amount DECIMAL(10,2),
    usage_limit INT,
    used_count INT NOT NULL DEFAULT 0,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_voucher_code (voucher_code),
    INDEX idx_is_active (is_active),
    INDEX idx_start_date (start_date),
    INDEX idx_end_date (end_date),
    INDEX idx_discount_type (discount_type)
);

-- Thêm dữ liệu mẫu
INSERT INTO voucher (voucher_code, voucher_name, description, discount_type, discount_value, min_order_amount, max_discount_amount, usage_limit, start_date, end_date) VALUES
('WELCOME10', 'Chào mừng khách hàng mới', 'Giảm 10% cho đơn hàng đầu tiên', 'PERCENTAGE', 10.00, 100000, 50000, 100, '2024-01-01 00:00:00', '2024-12-31 23:59:59'),
('SAVE20K', 'Tiết kiệm 20k', 'Giảm 20,000 VNĐ cho đơn hàng từ 200k', 'FIXED_AMOUNT', 20000.00, 200000, NULL, 50, '2024-01-01 00:00:00', '2024-12-31 23:59:59'),
('SUMMER15', 'Khuyến mãi mùa hè', 'Giảm 15% cho tất cả sản phẩm', 'PERCENTAGE', 15.00, 150000, 100000, 200, '2024-06-01 00:00:00', '2024-08-31 23:59:59'),
('FLASH50K', 'Flash sale 50k', 'Giảm 50,000 VNĐ cho đơn hàng từ 500k', 'FIXED_AMOUNT', 50000.00, 500000, NULL, 30, '2024-01-01 00:00:00', '2024-12-31 23:59:59'),
('VIP25', 'Khách hàng VIP', 'Giảm 25% cho khách hàng VIP', 'PERCENTAGE', 25.00, 300000, 150000, 10, '2024-01-01 00:00:00', '2024-12-31 23:59:59'); 