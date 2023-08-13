package com.fpoly.service;

import java.util.List;

import com.fpoly.model.KhachHang;

public interface CustomerService {

	KhachHang findByUser();

	KhachHang findByMaKhach(Integer maKhach);

	void luu(KhachHang khachHang);

	List<KhachHang> findAll();

	List<KhachHang> searchByName(String keyword);

}
