package com.fpoly.service;

import java.util.List;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.GioHang;

public interface CartService {

	GioHang findByNguoiMua();

	void luu(GioHang gh);


}
