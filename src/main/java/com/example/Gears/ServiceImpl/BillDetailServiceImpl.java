package com.example.Gears.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Gears.Entity.Bill;
import com.example.Gears.Entity.BillDetail;
import com.example.Gears.Repository.BillDetailRepo;
import com.example.Gears.Service.BillDetailService;
@Service
public class BillDetailServiceImpl implements BillDetailService{
	
	@Autowired
	private BillDetailRepo billDetailRepo; 

	@Override
	public List<BillDetail> findByBill(Long id){
		Bill bill = new Bill();
		bill.setBillID(id);
		return billDetailRepo.findByBill(bill);
	}
	
	@Override
	public BillDetail saveBillDetail(BillDetail billDetail) {
		return billDetailRepo.save(billDetail);
	}
}
