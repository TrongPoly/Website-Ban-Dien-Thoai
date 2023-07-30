package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DonHang;
import com.fpoly.model.DonHangChiTiet;
import com.fpoly.service.OrderDetailsService;
import com.fpoly.service.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/rest/order")
public class OderMgmtController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailsService orderDetailsService;
	
	@GetMapping("/index")
	public List<DonHang> getAll(){
		return orderService.findAll();
	}
	@GetMapping("/details/{id}")
	public List<DonHangChiTiet> detailsOrder(@PathVariable("id") Integer id){
		return orderDetailsService.findByMaDonHang(id);
	}
	@PutMapping("/cancelOrder/{id}")
	public void cancelOrder(@PathVariable("id") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		donHang.setTrangThai(4);
		orderService.luu(donHang);
	}
	@PutMapping("/delivery/{id}")
	public void delivery(@PathVariable("id") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		donHang.setTrangThai(2);
		orderService.luu(donHang);
	}
	@PutMapping("/successfulDelivery/{id}")
	public void success(@PathVariable("id") Integer id) {
		DonHang donHang = orderService.findByMaDonHang(id);
		donHang.setTrangThai(3);
		orderService.luu(donHang);
	}
}
