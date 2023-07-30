package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DiaChi;
import com.fpoly.service.AddressService;
import com.fpoly.service.CustomerService;

@RestController
public class AddressRestController {
	@Autowired
	AddressService addressService;
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/rest/address")
	public ResponseEntity<Void> createAddress(@ModelAttribute("address") DiaChi dc){
		dc.setMaKhachHang(customerService.findByUser());
		addressService.luu(dc);
		return ResponseEntity.ok().build();
	}
}
