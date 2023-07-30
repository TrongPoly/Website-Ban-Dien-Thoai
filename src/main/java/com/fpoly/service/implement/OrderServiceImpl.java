package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.DonHang;
import com.fpoly.repository.DonHangRepository;
import com.fpoly.service.OrderService;
import com.fpoly.service.SessionService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	DonHangRepository donHangRepository;
	@Autowired
	SessionService session;

	@Override
	public List<DonHang> findByUser() {
		return donHangRepository.findAll().stream()
				.filter(dh -> dh.getNguoiMua().getEmail().getEmail().equals(session.get("user").getEmail())).toList();
	}

	@Override
	public void luu(DonHang donHang) {
		donHangRepository.save(donHang);
	}

}
