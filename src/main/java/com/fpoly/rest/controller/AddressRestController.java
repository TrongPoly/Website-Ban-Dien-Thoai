package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DiaChi;
import com.fpoly.service.AddressService;
import com.fpoly.service.CustomerService;

@RestController
@RequestMapping("/rest")
public class AddressRestController {
	@Autowired
	AddressService addressService;
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/address/getAll")
	public List<DiaChi> getAll(){
		return addressService.FindByUser();
	}
	@PostMapping("/address/create")
	public DiaChi createAddress( @RequestBody DiaChi diachi){
		diachi.setMaKhachHang(customerService.findByUser());
		addressService.luu(diachi);
		return diachi;
	}
	@PutMapping("/address/{id}")
	public DiaChi UpdateAddress( @RequestBody DiaChi diachi) {
		diachi.setMaKhachHang(customerService.findByUser());
		addressService.luu(diachi);
		return diachi;
	}
	/*
	 * @GetMapping("/address") public List<DiaChi> findUser() {
	 * 
	 * return addressService.FindByUser(); }
	 */
	
	
	
//	@PostMapping("/rest/address")
//	public ResponseEntity<Void> createAddress(@ModelAttribute("address") DiaChi dc){
//		dc.setMaKhachHang(customerService.findByUser());
//		addressService.luu(dc);
//		return ResponseEntity.ok().build();
//	}
}
