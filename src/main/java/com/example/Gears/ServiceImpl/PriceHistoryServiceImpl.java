package com.example.Gears.ServiceImpl;

import com.example.Gears.Entity.PriceHistory;
import com.example.Gears.Repository.PriceHistoryRepo;
import com.example.Gears.Service.PriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {

    @Autowired
    private PriceHistoryRepo priceHistoryRepo;

    @Override
    public void savePriceHistory(PriceHistory priceHistory) {
        priceHistoryRepo.save(priceHistory);
    }

    @Override
    public List<PriceHistory> getPriceHistory(Long productId) {
        return priceHistoryRepo.findByProductId(productId);
    }

    @Override
    public List<PriceHistory> getAllPriceHistory() {
        return priceHistoryRepo.findAll();
    }

    @Override
    public List<PriceHistory> getPriceHistoryByProductName(Integer productName) {
        return priceHistoryRepo.findByProductName(productName);
    }
}
