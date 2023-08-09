package com.fpoly.rest.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DonHang;
import com.fpoly.model.DonHangActivity;
import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.GioHang;
import com.fpoly.service.DonHangActivityService;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/order")
public class OderDetailsRestController {
	@Autowired
	OrderDetailsService orderDetailsService;
	@Autowired
	OrderService orderService;
	@Autowired 
	DonHangActivityService donHangActivityService;
	
	
	@GetMapping("/get")
	public List<DonHang> getOrder(){
		return orderService.findByUser();
	}
	@GetMapping("/details/{id}")
	public List<DonHangChiTiet> detailsOrder(@PathVariable("id") Integer id){
		return orderDetailsService.findByMaDonHang(id);
	}
	
	@GetMapping("/activity/{orderId}")
	public List<DonHangActivity> getActivity(@PathVariable("orderId") Integer id){
		return donHangActivityService.findByOrderId(id);
	}
	@PutMapping("/requestCancel/{orderId}")
	public void cancel(@PathVariable("orderId") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		donHang.setTrangThai(5);
		orderService.luu(donHang);
		Instant ngayCapNhat = Instant.now();
		DonHangActivity donHangActivity = new DonHangActivity(donHang, 5, ngayCapNhat, 1);
		donHangActivityService.luu(donHangActivity);
		
	}
	@PutMapping("/requestOrderSuccess/{orderId}")
	public void successOrderByUser(@PathVariable("orderId") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		Instant ngayCapNhat = Instant.now();
		DonHangActivity donHangActivity = new DonHangActivity(donHang, 7, ngayCapNhat, 1);
		donHangActivityService.luu(donHangActivity);
		
	}
}
