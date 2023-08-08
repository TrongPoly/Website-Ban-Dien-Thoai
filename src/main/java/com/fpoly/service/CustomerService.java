package com.fpoly.service;

import com.fpoly.model.KhachHang;

public interface CustomerService {

	KhachHang findByUser();

	KhachHang findByMaKhach(Integer maKhach);

}
