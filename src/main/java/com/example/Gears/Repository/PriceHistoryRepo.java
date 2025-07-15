package com.example.Gears.Repository;

import com.example.Gears.Entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepo extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findByProductId(Long productId);

    List<PriceHistory> findByProductName(Integer productName);
}
