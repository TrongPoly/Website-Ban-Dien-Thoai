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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.model.NhaSanXuat;
import com.fpoly.model.SanPham;
import com.fpoly.repository.NhaSanXuatRepository;
import com.fpoly.repository.SanPhamRepository;
import com.fpoly.service.ProductService;
import com.fpoly.service.UploadService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/rest")
public class SanPhamRestController {

	@Autowired
	ProductService prodcutService;
	
	@Autowired
	UploadService uploadService;
	@Autowired
	NhaSanXuatRepository nsxRepository;

	@Autowired
	SanPhamRepository daosp;

	@GetMapping("/sanpham")
	public ResponseEntity<List<SanPham>> getAll(Model model) {
		return ResponseEntity.ok(daosp.findAll());
	}

	@GetMapping("/sanpham/{id}")
	public ResponseEntity<SanPham> getOne(@PathVariable("id") Integer id) {
		if (!daosp.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(daosp.findById(id).get());
	}

	@PostMapping("/sanpham")
	public SanPham post(@RequestBody SanPham sp, @PathParam("folder") MultipartFile folder) {
		daosp.save(sp);
		return sp;
	}

	@PutMapping("/sanpham/{id}")
	public ResponseEntity<SanPham> put( @RequestParam("nhaSX") Integer nsx,@PathVariable("id") Integer id, @RequestBody SanPham sp) {

		if (!daosp.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		NhaSanXuat nhaSX =	nsxRepository.findById(nsx).get();
		sp.setNhaSanXuat(nhaSX);
		System.out.println(sp.getTenSanPham());
		System.out.println(sp.getNhaSanXuat().getTenNsx());

		daosp.save(sp);
		return ResponseEntity.ok(sp);

	}

	@DeleteMapping("/sanpham/{id}")
	public ResponseEntity<Void> DeleteId(@PathVariable("id") Integer id) {

		if (!daosp.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		daosp.deleteById(id);
		return ResponseEntity.ok().build();

	}
	@GetMapping("/sanpham/search")
	public List<SanPham> searchProductByName(@RequestParam("keyword") String keyword){
		return prodcutService.searchByName(keyword);
	}

}
