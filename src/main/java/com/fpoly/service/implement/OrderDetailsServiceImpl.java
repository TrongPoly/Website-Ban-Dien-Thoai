package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.repository.DonHangChiTietRepository;
import com.fpoly.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	DonHangChiTietRepository donHangChiTietRepository;

	@Override
	public List<DonHangChiTiet> findByMaDonHang(Integer id) {

		return donHangChiTietRepository.findAll().stream().filter(dhct -> dhct.getMaDonHang().getId().equals(id))
				.toList();
	}

	@Override
	public void luu(DonHangChiTiet dhct) {
		donHangChiTietRepository.save(dhct);
	}

	

}
