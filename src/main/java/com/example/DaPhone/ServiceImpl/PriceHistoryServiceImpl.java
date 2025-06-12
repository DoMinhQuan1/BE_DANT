package com.example.DaPhone.ServiceImpl;

import com.example.DaPhone.Entity.PriceHistory;
import com.example.DaPhone.Repository.PriceHistoryRepo;
import com.example.DaPhone.Service.PriceHistoryService;
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
}
