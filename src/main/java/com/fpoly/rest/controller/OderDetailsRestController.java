package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.service.OrderDetailsService;

@RestController
@RequestMapping("/rest/order")
public class OderDetailsRestController {
	@Autowired
	OrderDetailsService orderDetailsService;
	
	@GetMapping("/details/{id}")
	public List<DonHangChiTiet> detailsOrder(@PathVariable("id") Integer id){
		return orderDetailsService.findByMaDonHang(id);
	}
}
