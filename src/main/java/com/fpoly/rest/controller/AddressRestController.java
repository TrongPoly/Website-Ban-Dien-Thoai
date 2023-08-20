package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DiaChi;
import com.fpoly.model.District;
import com.fpoly.model.Province;
import com.fpoly.model.Ward;
import com.fpoly.service.AddressService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.DistrictService;
import com.fpoly.service.ProvinceService;
import com.fpoly.service.WardsService;

@RestController
@RequestMapping("/rest")
public class AddressRestController {
	@Autowired
	AddressService addressService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProvinceService provinceService;
	@Autowired
	DistrictService districtService;
	@Autowired
	WardsService wardsService;
	
	@GetMapping("/address/getProvince")
	public List<Province> getTinh(){
		return provinceService.loadAll();
	}
	@GetMapping("/address/getDistrict/{idTinh}")
	public List<District> getQuan(@PathVariable("idTinh") String idTinh){
		return districtService.loadQuan(idTinh);
	}
	@GetMapping("/address/getWards/{idQuan}")
	public List<Ward> getPhuong(@PathVariable("idQuan") String idQuan){
		return wardsService.loadPhuong(idQuan);
	}
	
	@GetMapping("/address/getAll")
	public List<DiaChi> getAll(){
		return addressService.FindByUser();
	}
	
	@PostMapping("/address/create")
	public ResponseEntity<DiaChi> createAddress( @RequestBody DiaChi diachi){
		diachi.setMaKhachHang(customerService.findByUser());
		diachi.setTrangThai(true);
		addressService.luu(diachi);
		return ResponseEntity.ok(diachi);
	}
	@PutMapping("/address/delete/{idAddress}")
	public void DeleteAddress(@PathVariable("idAddress") Integer idAddress) {
		DiaChi diaChi = addressService.findById(idAddress);
		diaChi.setTrangThai(false);
		addressService.luu(diaChi);
	}
	
}
