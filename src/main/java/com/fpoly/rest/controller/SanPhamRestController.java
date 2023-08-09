package com.fpoly.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.fpoly.model.SanPham;
import com.fpoly.repository.SanPhamRepository;
import com.fpoly.service.UploadService;

import jakarta.websocket.server.PathParam;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class SanPhamRestController {
	
	@Autowired
	UploadService uploadService;
	
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
	public SanPham post(@Validated @RequestBody SanPham sp,Errors errors, @PathParam("folder") MultipartFile folder) {
		
		 if(errors.hasErrors()) {
			
			 return sp;
		 }
		
		if (sp.getSoLuongTon() == 0) {
			sp.setTrangThai(false); // set trạng thái là hết hàng nếu số lượng tồn = 0
		} else {
			sp.setTrangThai(true);
		}

		/*
		 * String filename = folder.getOriginalFilename().toString(); String path =
		 * "E:\\Java6\\workspace\\Website-Ban-Dien-Thoai\\src\\main\\resources\\static\\img\\product\\"
		 * + filename; File savedFile = new File(path); try {
		 * folder.transferTo(savedFile); } catch (IllegalStateException e) {
		 * daosp.save(sp); e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * // Thiết lập đường dẫn cho sản phẩm sp.setAnhSanPham(filename);
		 */


		daosp.save(sp);
		return sp;
	}
		
	
	@PutMapping("/sanpham/{id}")
	public SanPham put(@PathVariable("id") Integer id, @RequestBody SanPham sp) {
		if (sp.getSoLuongTon() == 0) {
			sp.setTrangThai(false); // set trạng thái là hết hàng nếu số lượng tồn = 0
		} else {
			sp.setTrangThai(true);
		}
		
		daosp.findById(id).get();
		sp.setTenSanPham(sp.getTenSanPham());
		sp.setAnhSanPham(sp.getAnhSanPham());
		sp.setNhaSanXuat(sp.getNhaSanXuat());
		sp.setDonGia(sp.getDonGia());
		sp.setSoLuongTon(sp.getSoLuongTon());
		sp.setRam(sp.getRam());
		sp.setRom(sp.getRom());
		sp.setPin(sp.getPin());
		sp.setChip(sp.getChip());

		daosp.save(sp);
		return sp;
		
	}
	@DeleteMapping("/sanpham/{id}")
	public void DeleteId(@PathVariable("id") Integer id) {
		daosp.deleteById(id);
	}
	
	/*
	 * @GetMapping("/sanpham/page/{pageNumber}/{pageSize}") public List<SanPham>
	 * getAllProductsByPage(@PathVariable("pageNumber") Integer
	 * pageNumber, @PathVariable("pageSize") Integer pageSize) { Pageable pageable =
	 * PageRequest.of(pageNumber, pageSize); return
	 * daosp.findAll(pageable).getContent(); }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("/sanpham/brand/{brand}/{pageNumber}/{pageSize}") public
	 * List<SanPham> getAllProductsByBrandAndPage(@PathVariable("brand") String
	 * brand, @PathVariable("pageNumber") Integer
	 * pageNumber, @PathVariable("pageSize") Integer pageSize) { Pageable pageable =
	 * PageRequest.of(pageNumber, pageSize); return ((Slice<SanPham>)
	 * daosp.findByNhaSanXuat(brand, pageable)).getContent(); }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("/sanpham/search/{pageNumber}/{pageSize}/{name}") public
	 * List<SanPham> searchProductsByPage(@PathVariable("pageNumber") Integer
	 * pageNumber, @PathVariable("pageSize") Integer pageSize, @PathVariable("name")
	 * String name) { Pageable pageable = PageRequest.of(pageNumber, pageSize);
	 * return ((Slice<SanPham>) daosp.findByTenSanPham("%" + name + "%",
	 * pageable)).getContent(); }
	 */

}