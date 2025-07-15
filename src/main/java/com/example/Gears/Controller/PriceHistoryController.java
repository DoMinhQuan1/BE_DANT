package com.example.Gears.Controller;

import com.example.Gears.Entity.PriceHistory;
import com.example.Gears.Model.Response;
import com.example.Gears.Service.PriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricehistories")
@CrossOrigin(origins = "*")
public class PriceHistoryController {
    
    @Autowired
    private PriceHistoryService priceHistoryService;
    
    @GetMapping
    public ResponseEntity<Response<List<PriceHistory>>> getAllPriceHistory() {
        try {
            List<PriceHistory> priceHistories = priceHistoryService.getAllPriceHistory();
            Response<List<PriceHistory>> response = new Response<>();
            response.setRowCount((long) priceHistories.size());
            response.setResults(priceHistories);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>("500", "Lỗi server: " + e.getMessage()));
        }
    }
    
    @GetMapping("/product/{productName}")
    public ResponseEntity<Response<List<PriceHistory>>> getPriceHistoryByProducName(@PathVariable Integer productName) {
        try {
            List<PriceHistory> priceHistories = priceHistoryService.getPriceHistoryByProductName(productName);
            Response<List<PriceHistory>> response = new Response<>();
            response.setRowCount((long) priceHistories.size());
            response.setResults(priceHistories);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>("500", "Lỗi server: " + e.getMessage()));
        }
    }
}