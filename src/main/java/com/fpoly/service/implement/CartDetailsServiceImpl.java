package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.GioHangChiTietId;
import com.fpoly.repository.GioHangChiTietRepository;
import com.fpoly.service.CartDetailsService;

@Service
public class CartDetailsServiceImpl implements CartDetailsService{
	@Autowired
	GioHangChiTietRepository ghct;
	
	@Override
	public List<GioHangChiTiet> findByGioHang(GioHang gioHang) {
		 return ghct.findAll().stream().filter(ghct -> ghct.getMaGioHang().getId().equals(gioHang.getId())).toList();
	}

	@Override
	public GioHangChiTiet findByMaGioHangVaMaSanPham(Integer gh, Integer sp) {
		return ghct.findByMaGHvaMaSP(gh, sp);
	}

	@Override
	public void luu(GioHangChiTiet gioHangChiTiet) {
		ghct.save(gioHangChiTiet);		
	}

	@Override
	public void xoaSP(GioHangChiTiet gioHangChiTiet) {
		ghct.delete(gioHangChiTiet);
	}

	



}
