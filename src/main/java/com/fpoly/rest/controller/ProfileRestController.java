package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.KhachHang;
import com.fpoly.model.TaiKhoan;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.service.AccountService;
import com.fpoly.service.AddressService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class ProfileRestController {
	
	
	
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	SessionService session;

	@Autowired
	AccountService accountService;
	
	@Autowired
	AddressService addressService;
	
	
	
	@GetMapping("/profile/getUser")
	public KhachHang findUser(){
		return customerService.findByUser();
	}
	@GetMapping("/profile/{id}")
	public KhachHang getOne(@PathVariable("id") Integer id) {
		return customerService.findByMaKhach(id);
	}
	@PostMapping("/profile")
	public KhachHang post(@RequestBody KhachHang kh) {
		customerService.luu(kh);	
		return kh;
	}

	@PutMapping("/profile/{id}")
	public KhachHang put(@PathVariable("id") Integer id, @RequestBody KhachHang kh) {	
		customerService.luu(kh);
		return kh;
	}
	
	
	
}
