package com.fpoly.service;

import java.util.List;

import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;

public interface CartDetailsService {

	List<GioHangChiTiet> findByGioHang(GioHang gioHang);

	GioHangChiTiet findByMaGioHangVaMaSanPham(Integer gh, Integer sp);

	void luu(GioHangChiTiet gioHangChiTiet);

	void xoaSP(GioHangChiTiet gioHangChiTiet);


}
