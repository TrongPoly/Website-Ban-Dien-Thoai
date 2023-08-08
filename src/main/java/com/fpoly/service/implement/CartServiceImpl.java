package com.fpoly.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.GioHang;
import com.fpoly.model.KhachHang;
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
	public GioHang findByNguoiMua() {
		GioHang gioHang = cart.findAll().stream()
				.filter(gh -> gh.getNguoiSoHuu().getEmail().getEmail().equals(session.get("user").getEmail()))
				.findFirst().get();
		KhachHang khachHang = customer.findByUser();
		if(gioHang==null) {
			GioHang gioHang1 = new GioHang(khachHang);
			cart.save(gioHang1);
			return gioHang1;			
		}
		return gioHang;
	}

	@Override
	public void luu(GioHang gh) {
		cart.save(gh);
	}

}
