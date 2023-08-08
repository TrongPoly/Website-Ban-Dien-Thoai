package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.GioHangChiTietId;
import com.fpoly.repository.GioHangChiTietRepository;
import com.fpoly.service.CartDetailsService;
import com.fpoly.service.SessionService;

@Service
public class CartDetailsServiceImpl implements CartDetailsService{
	@Autowired
	GioHangChiTietRepository ghct;
	@Autowired
	SessionService session;
	@Override
	public List<GioHangChiTiet> findByGioHang(Integer gioHang) {
		return ghct.findAll().stream().filter(ghct -> ghct.getMaGioHang().getId().equals(gioHang)).toList();
		 
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
	public void xoaSP(GioHangChiTietId gioHangChiTietId) {
		ghct.deleteById(gioHangChiTietId);
	}

	@Override
	public void xoaSanPham(GioHangChiTiet gioHangChiTiet) {
		ghct.delete(gioHangChiTiet);
	}

	@Override
	public List<GioHangChiTiet> timTatCa() {
		return ghct.findAll();
	}
	


	



}
