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

import com.fpoly.model.SanPham;
import com.fpoly.repository.SanPhamRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/admin/rest")
public class SanPhamRestController {
	

	
	@Autowired
	SanPhamRepository daosp;
	
	@GetMapping("/sanpham")
	public List<SanPham> getAll (Model model){
		return daosp.findAll();
	}
	
	@GetMapping("/sanpham/{id}")
	public SanPham getOnt(@PathVariable("id") Integer id) {
		return daosp.findById(id).get();
	}
	
	@PostMapping("/sanpham")
	public SanPham post(@RequestBody SanPham sp) {
		daosp.save(sp);
		return sp;
		
	}
	@PutMapping("/sanpham/{id}")
	public SanPham put(@PathVariable("id") Integer id, @RequestBody SanPham sp) {
		daosp.save(sp);
		return sp;
		
	}
	@DeleteMapping("/sanpham/{id}")
	public void DeleteId(@PathVariable("id") Integer id) {
		daosp.deleteById(id);
	}
	
	
	

}
