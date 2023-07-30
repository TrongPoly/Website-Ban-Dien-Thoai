package com.fpoly.service;

import java.util.List;

import com.fpoly.model.DiaChi;

public interface AddressService {

	List<DiaChi> FindByUser();

	DiaChi findById(Integer dchi);

	void luu(DiaChi diachi);

}
