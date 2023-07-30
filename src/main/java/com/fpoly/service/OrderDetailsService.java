package com.fpoly.service;

import java.util.List;

import com.fpoly.model.DonHangChiTiet;

public interface OrderDetailsService {


	List<DonHangChiTiet> findByMaDonHang(Integer id);

	void luu(DonHangChiTiet dhct);


}
