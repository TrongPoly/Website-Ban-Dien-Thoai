package com.fpoly.service;

import java.util.List;

import com.fpoly.model.DonHang;
import com.fpoly.model.ReportDoanhThu;

public interface OrderService {

	List<DonHang> findByUser();

	void luu(DonHang donHang);

	DonHang findByMaDonHang(Integer id);

	List<DonHang> findAll();

	void xoa(DonHang donHang);


}
