package com.fpoly.service;

import java.util.List;

import com.fpoly.model.SanPham;

public interface ProductService {

	List<SanPham> findAll();

	SanPham findById(Integer id);

	List<SanPham> findAllByNSX(String tenNsx);

}
