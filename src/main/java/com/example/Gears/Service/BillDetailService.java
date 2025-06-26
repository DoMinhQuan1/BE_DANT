package com.example.Gears.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Gears.Entity.BillDetail;
@Service
public interface BillDetailService {
	public List<BillDetail> findByBill(Long id);
	public BillDetail saveBillDetail(BillDetail billDetail);
}
