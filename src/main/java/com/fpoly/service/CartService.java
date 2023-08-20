package com.fpoly.service;

import com.fpoly.model.GioHang;

public interface CartService {

	GioHang findByNguoiMua();

	void luu(GioHang gh);


}
