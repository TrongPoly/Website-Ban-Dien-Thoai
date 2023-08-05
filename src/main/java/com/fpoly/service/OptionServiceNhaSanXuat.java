package com.fpoly.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.NhaSanXuat;
import com.fpoly.repository.NhaSanXuatRepository;

@Service
public class OptionServiceNhaSanXuat {
	@Autowired
	public NhaSanXuatRepository NhaSanXuatdao;
	
	public Map<Integer, String> getAllOptions(){
		List<NhaSanXuat> nhasanxuats = NhaSanXuatdao.findAll();
		Map<Integer, String> sanphamMap = new HashMap<>();
		for(NhaSanXuat nhasanxuat : nhasanxuats) {
			sanphamMap.put(nhasanxuat.getId(), nhasanxuat.getTenNsx());
		}
		
		return sanphamMap;
	}
	public NhaSanXuat getOptionByKey(String key) {
		List<NhaSanXuat> nhasanxuats = NhaSanXuatdao.findbykeywords(key);
		return nhasanxuats.isEmpty() ? null : nhasanxuats.get(0);
	}
	
}
