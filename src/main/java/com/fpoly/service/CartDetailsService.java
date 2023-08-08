package com.fpoly.service;

import java.util.List;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.GioHangChiTietId;

public interface CartDetailsService {

	List<GioHangChiTiet> findByGioHang(Integer gioHang);

	GioHangChiTiet findByMaGioHangVaMaSanPham(Integer gh, Integer sp);

	void luu(GioHangChiTiet gioHangChiTiet);

	void xoaSP(GioHangChiTietId gioHangChiTietId);

	void xoaSanPham(GioHangChiTiet gioHangChiTiet);

	List<GioHangChiTiet> timTatCa();




}
