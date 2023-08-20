package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.KhachHang;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	SessionService session;
	@Autowired
	KhachHangRepository khachHang;

	@Override
	public KhachHang findByUser() {
		return khachHang.findAll().stream()
				.filter(kh -> kh.getEmail().getEmail().equals(session.get("user").getEmail())).findFirst().get();
	}

	@Override
	public KhachHang findByMaKhach(Integer maKhach) {
		return khachHang.findById(maKhach).get();
	}

	@Override
	public void luu(KhachHang kh) {
		khachHang.save(kh);
	}
	
	@Override
	public List<KhachHang> findAll(){
		return khachHang.findAll();
	}
	
	@Override
	public List<KhachHang> searchByName(String keyword){
		return khachHang.findByTenKhachHangContaining(keyword);
		
		
	}
}
