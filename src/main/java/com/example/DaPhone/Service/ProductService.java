//package com.example.DaPhone.Service;
//
//import com.example.DaPhone.Entity.PriceHistory;
//import com.example.DaPhone.Entity.Product;
//import com.example.DaPhone.Repository.PriceHistoryRepo;
//import com.example.DaPhone.Repository.ProductRepo;
//import com.example.DaPhone.Request.ProductRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public interface ProductService {
//
//    @Autowired
//    ProductRepo productRepo = null;
//
//    @Autowired
//    PriceHistoryRepo priceHistoryRepo = null;
//
//    Page<Product> findProduct(ProductRequest productParam, Pageable pageable);
//
//    Product findById(Long id);
//
//    void deleteProduct(Long id);
//
//    ByteArrayInputStream exportExcelProduct(ProductRequest productParam) throws IOException;
//
//    List<Long> getSatisticBrand();
//
//    List<Long> getSatisticCategory();
//
//    /**
//     * Lưu sản phẩm, đồng thời lưu lịch sử sửa giá nếu giá bị thay đổi.
//     */
//    default Product saveProduct(Product product) {
//        Product existingProduct = productRepo.findById(product.getProductID()).orElse(null);
//
//        if (existingProduct != null && existingProduct.getProductPrice() != product.getProductPrice()) {
//            // Lưu lịch sử sửa giá
//            PriceHistory priceHistory = new PriceHistory();
//            priceHistory.setProduct(existingProduct);
//            priceHistory.setOldPrice(existingProduct.getProductPrice());
//            priceHistory.setNewPrice(product.getProductPrice());
//            priceHistory.setUpdatedBy("Admin"); // Thay bằng thông tin người dùng hiện tại
//            priceHistory.setUpdatedAt(LocalDateTime.now());
//            priceHistoryRepo.save(priceHistory);
//        }
//
//        return productRepo.save(product);
//    }
//
//    /**
//     * Lấy lịch sử sửa giá của một sản phẩm cụ thể.
//     */
//    default List<PriceHistory> getPriceHistory(Long productId) {
//        Product product = productRepo.findById(productId).orElse(null);
//        return product != null ? priceHistoryRepo.findByProduct(product) : Collections.emptyList();
//    }
//}
package com.example.DaPhone.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.DaPhone.Entity.Product;
import com.example.DaPhone.Request.ProductRequest;
@Service
public interface ProductService {
	public Page<Product> findProduct(ProductRequest productParam, Pageable pageable);
	public Product findById(Long id);
	public Product saveProduct(Product product);
	public void deleteProduct(Long id);
	public ByteArrayInputStream exportExcelProduct(ProductRequest productParam) throws IOException;
	public List<Long> getSatisticBrand();
	public List<Long> getSatisticCategory();
}

