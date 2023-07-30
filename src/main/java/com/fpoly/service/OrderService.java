package com.fpoly.service;

import java.util.List;

import com.fpoly.model.DonHang;

public interface OrderService {

	List<DonHang> findByUser();

	void luu(DonHang donHang);

}
