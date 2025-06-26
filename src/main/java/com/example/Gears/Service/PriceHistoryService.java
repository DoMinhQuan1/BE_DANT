package com.example.Gears.Service;

import com.example.Gears.Entity.PriceHistory;

import java.util.List;

public interface PriceHistoryService {
    void savePriceHistory(PriceHistory priceHistory);
    List<PriceHistory> getPriceHistory(Long productId);
}
