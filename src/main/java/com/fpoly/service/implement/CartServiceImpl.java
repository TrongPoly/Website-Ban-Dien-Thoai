package com.fpoly.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.GioHang;
import com.fpoly.repository.GioHangRepository;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	GioHangRepository cart;
	@Autowired
	SessionService session;
	@Autowired
	CustomerService customer;
	
	@Override
	public GioHang findByUser() {
		GioHang gioHang = cart.findAll().stream()
				.filter(gh -> gh.getNguoiSoHuu().getEmail().getEmail().equals(session.get("user").getEmail()))
				.findFirst().get();
		
		return gioHang;
	}

	@Override
	public void luu(GioHang gh) {
		cart.save(gh);
	}

}
