package com.fpoly.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.TaiKhoan;
import com.fpoly.repository.TaiKhoanRepository;
import com.fpoly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	TaiKhoanRepository taiKhoanRepository;
	@Override
	public TaiKhoan findByEmail(String username) {
		return taiKhoanRepository.findById(username).orElse(null);
	}
	@Override
	public void luu(TaiKhoan taiKhoan) {
			taiKhoanRepository.save(taiKhoan);
	}

}
