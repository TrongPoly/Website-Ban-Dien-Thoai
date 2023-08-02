package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fpoly.model.KhachHang;
import com.fpoly.repository.KhachHangRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class KhachHangRestController {

	@Autowired
	KhachHangRepository daokh;
	
	@GetMapping("/rest/khachhang")
	public List<KhachHang> getAll(Model model){
		return daokh.findAll();
		
	}
	
	@GetMapping("/rest/khachhang/{id}")
	public KhachHang getOne(@PathVariable("id") Integer id) {
		return daokh.findById(id).get();
	}
	
	@PostMapping("/rest/khachhang")
	public KhachHang post(@RequestBody KhachHang id) {
		daokh.save(id);
		return id;
	}
	@PutMapping("/rest/khachhang/{id}")
	public KhachHang put(@PathVariable("id") Integer id, @RequestBody KhachHang kh) {
		daokh.save(kh);
		return kh;
	}
	@DeleteMapping("/rest/khachhang/{id}")
	public void delete(@PathVariable("id") Integer id) {
		daokh.deleteById(id);
	}
	
}
