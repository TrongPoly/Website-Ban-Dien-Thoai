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

import com.fpoly.model.HangKhachHang;
import com.fpoly.repository.HangKhachHangRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class HangKhachHangRestController {

	@Autowired
	HangKhachHangRepository daohkh;
	
	@GetMapping("/hangkhachhang")
	public List<HangKhachHang> getAll(Model model){
		return daohkh.findAll();
	}
	

	@GetMapping("/hangkhachhang/{id}")
	public HangKhachHang getOne(@PathVariable("id") Integer id) {
		return daohkh.findById(id).get();
	}
	
	@PostMapping("/hangkhachhang")
	public HangKhachHang post(@RequestBody HangKhachHang hkh){
		daohkh.save(hkh);
		return hkh;
	}

	@PutMapping("/hangkhachhang/{id}")
	public HangKhachHang put(@RequestBody HangKhachHang hkh){
		daohkh.save(hkh);
		return hkh;
	}
	@DeleteMapping("/hangkhachhang/{id}")
	public void delete(@PathVariable("id") Integer id) {
		daohkh.deleteById(id);
	}
}
