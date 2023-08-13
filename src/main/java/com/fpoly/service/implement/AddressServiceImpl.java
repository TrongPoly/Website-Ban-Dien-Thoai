package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.DiaChi;
import com.fpoly.repository.DiaChiRepository;
import com.fpoly.service.AddressService;
import com.fpoly.service.SessionService;

@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	DiaChiRepository diaChi;
	@Autowired
	SessionService session;
	
	@Override
	public List<DiaChi> FindByUser() {
		return diaChi.findAll().stream().filter(dc -> dc.getMaKhachHang().getEmail().getEmail().equals(session.get("user").getEmail())).toList();
	}

	@Override
	public DiaChi findById(Integer dchi) {
		return diaChi.findById(dchi).get();
	}

	@Override
	public void luu(DiaChi diachi) {
		diaChi.save(diachi);
	}
	@Override
	public void xoa(DiaChi diachi) {
		diaChi.deleteById(diachi);
	}
	
	

}
