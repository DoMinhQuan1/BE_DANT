package com.example.DaPhone.Repository;

import com.example.DaPhone.Entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepo extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findByProductId(Long productId);
}
