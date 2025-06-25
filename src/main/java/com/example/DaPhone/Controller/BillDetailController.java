package com.example.Gears.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gears.Entity.BillDetail;
import com.example.Gears.Model.Response;
import com.example.Gears.Service.BillDetailService;
@RestController
@RequestMapping(path = "/api/bill-detail")
public class BillDetailController {
	@Autowired
	private BillDetailService billDetailService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<BillDetail>> getBillDetail(@PathVariable(name = "id") Long id) {
		List<BillDetail> billDetails = billDetailService.findByBill(id);
		Long count = (long) billDetails.size();
		return new ResponseEntity<Response<BillDetail>>(new Response<BillDetail>(count, billDetails), HttpStatus.OK);
	}
}
