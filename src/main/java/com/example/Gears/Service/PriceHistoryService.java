package com.example.Gears.Service;

import com.example.Gears.Entity.PriceHistory;
import java.util.List;

public interface PriceHistoryService {
    void savePriceHistory(PriceHistory priceHistory);
    List<PriceHistory> getPriceHistory(Long historyId);
    List<PriceHistory> getAllPriceHistory(); 
    List<PriceHistory> getPriceHistoryByProductName(Integer productName); 
}