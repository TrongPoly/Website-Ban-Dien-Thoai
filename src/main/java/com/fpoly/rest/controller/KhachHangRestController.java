package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.KhachHang;
import com.fpoly.model.TaiKhoan;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.repository.TaiKhoanRepository;
import com.fpoly.service.AccountService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

@RestController
@RequestMapping("/rest")
public class KhachHangRestController {

	@Autowired
	KhachHangRepository daokh;
	
	@Autowired
	TaiKhoanRepository taiKhoanRepository;

	@Autowired
	SessionService sessionService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/khachhang")
	public ResponseEntity<List<KhachHang>> getAll(Model model) {
		return ResponseEntity.ok(daokh.findAll());

	}

	@GetMapping("/khachhang/{id}")
	public ResponseEntity<KhachHang> getOne(@PathVariable("id") Integer id) {
		
		if(!daokh.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(daokh.findById(id).get());
	}

	@PostMapping("/khachhang")
	public KhachHang post(@RequestBody KhachHang kh) {
		daokh.save(kh);		
		return kh;
	}

	@PutMapping("/khachhang/{id}")
	public KhachHang put(@PathVariable("id") Integer id, @RequestBody KhachHang kh) {
		
		
		daokh.save(kh);
		return kh;
	}
	@PutMapping("/khachhang/chan/{id}")
	public  ResponseEntity<KhachHang> chan(@PathVariable("id") Integer id) {
		TaiKhoan taiKhoan = accountService.findByEmail(daokh.findById(id).get().getEmail().getEmail());
		taiKhoan.setTrangThai(false);
		accountService.luu(taiKhoan);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/khachhang/boChan/{id}")
	public  ResponseEntity<KhachHang> boChan(@PathVariable("id") Integer id) {
		TaiKhoan taiKhoan = accountService.findByEmail(daokh.findById(id).get().getEmail().getEmail());
		taiKhoan.setTrangThai(true);
		accountService.luu(taiKhoan);
		return ResponseEntity.ok().build();
	}
	

	@GetMapping("/khachhang/search")
	public List<KhachHang> searchCustomerByName(@RequestParam("keyword") String keyword){
		return customerService.searchByName(keyword);
	}
	


}
