package com.example.Gears.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Gears.Entity.Bill;
import com.example.Gears.Request.BillRequest;

@Service
public interface BillService {
	public Page<Bill> findBill(BillRequest billParam, Pageable pageable);
	public List<Long> statisticBillByWeek();
	public ByteArrayInputStream exportExcel(BillRequest billParam) throws IOException;
	public Bill saveBill(Bill bill);
	public void cancelBill(Bill bill);
	public void deleteBill(Long id);
	public Bill paymentBill(Bill bill);
}
