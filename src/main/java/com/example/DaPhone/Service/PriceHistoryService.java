package com.example.DaPhone.Service;

import com.example.DaPhone.Entity.PriceHistory;

import java.util.List;

public interface PriceHistoryService {
    void savePriceHistory(PriceHistory priceHistory);
    List<PriceHistory> getPriceHistory(Long productId);
}
