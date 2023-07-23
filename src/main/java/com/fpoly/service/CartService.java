package com.fpoly.service;

import com.fpoly.model.GioHang;

public interface CartService {

	GioHang findByUser();

	void luu(GioHang gh);

}
