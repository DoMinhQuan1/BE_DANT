package com.example.Gears.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Gears.Entity.Bill;

import java.util.List;

@Repository
public interface BillRepo  extends JpaRepository<Bill, Long>{
	Page<Bill> findAll(Specification<Bill> specification, Pageable pageable);
	
	@Query(value = "SELECT bill_id, total, payment, address, date, name, phone, status, user_id FROM bill", nativeQuery = true)
	List<Object[]> findAllBillData();
}
