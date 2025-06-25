package com.example.Gears.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gears.Entity.Bill;
import com.example.Gears.Entity.BillDetail;
@Repository
public interface BillDetailRepo extends JpaRepository<BillDetail, Long>{
	public List<BillDetail> findByBill(Bill bill);
}
