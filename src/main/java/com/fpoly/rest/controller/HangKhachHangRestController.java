package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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


@RestController
@RequestMapping("/rest")
public class HangKhachHangRestController {

	@Autowired
	HangKhachHangRepository daohkh;
	
	@GetMapping("/hangkhachhang")
	public ResponseEntity<List<HangKhachHang>> getAll(Model model){
		return ResponseEntity.ok(daohkh.findAll());
	}
	

	@GetMapping("/hangkhachhang/{id}")
	public ResponseEntity<HangKhachHang> getOne(@PathVariable("id") Integer id) {
		
		if(!daohkh.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(daohkh.findById(id).get());
	}
	
	@PostMapping("/hangkhachhang")
	public HangKhachHang post(@RequestBody HangKhachHang hkh){

		daohkh.save(hkh);
		return hkh;
	}

	@PutMapping("/hangkhachhang/{id}")
	public ResponseEntity<HangKhachHang> put(@PathVariable("id") Integer id,@RequestBody HangKhachHang hkh){
		
		if(!daohkh.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		
		daohkh.save(hkh);
		return ResponseEntity.ok(hkh);
	}
	@DeleteMapping("/hangkhachhang/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		
		if(!daohkh.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		daohkh.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
