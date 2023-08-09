package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.GioHang;
import com.fpoly.model.KhachHang;
import com.fpoly.model.TaiKhoan;
import com.fpoly.repository.PhanQuyenRepository;
import com.fpoly.service.AccountService;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;

@RestController
@RequestMapping("/rest/auth")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	PhanQuyenRepository phanQuyenRepository;
	@Autowired
	CustomerService customerService;
	@Autowired
	CartService cartService;

	@PostMapping("/register")
	public ResponseEntity<TaiKhoan> regiser(@RequestBody TaiKhoan taiKhoan) {		
		TaiKhoan tk =accountService.findByEmail(taiKhoan.getEmail());
		if (tk != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		taiKhoan.setTrangThai(true);
		taiKhoan.setPhanQuyen(phanQuyenRepository.findById(2).get());
		accountService.luu(taiKhoan);
		
		KhachHang khachHang = new KhachHang(taiKhoan);
		customerService.luu(khachHang);
		
		GioHang gioHang = new GioHang(khachHang);
		cartService.luu(gioHang);
		
		return ResponseEntity.ok().build();
	}
}
