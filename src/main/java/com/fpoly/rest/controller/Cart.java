package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.service.CartDetailsService;
import com.fpoly.service.CartService;

@RestController
public class Cart {
@Autowired CartService cartService;
@Autowired CartDetailsService cartDetailsService;

	@GetMapping("/rest/cart/index")
	public List<GioHangChiTiet> getGioHang(){
		GioHang gh= cartService.findByUser();
		List<GioHangChiTiet> ghct = cartDetailsService.findByGioHang(gh.getId());
		return ghct;
	}
}
