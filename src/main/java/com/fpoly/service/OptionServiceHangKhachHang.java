package com.fpoly.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.HangKhachHang;
import com.fpoly.repository.HangKhachHangRepository;

@Service
public class OptionServiceHangKhachHang {
	
	@Autowired
	public HangKhachHangRepository hkhdao;
	
	public Map<Integer, String> getAllOptions(){
		List<HangKhachHang> hangkhachhangs = hkhdao.findAll();
		Map<Integer, String> khachhangMap = new HashMap<>();
		for(HangKhachHang hangkhachhang : hangkhachhangs) {
			khachhangMap.put(hangkhachhang.getId(), hangkhachhang.getTenHang());
		}
		return khachhangMap;
	}
	
	public HangKhachHang getOptionByKey(String key) {
		List<HangKhachHang> hangkhachhangs = hkhdao.findbykeywords(key);
		return hangkhachhangs.isEmpty() ? null : hangkhachhangs.get(0);
	}
}
