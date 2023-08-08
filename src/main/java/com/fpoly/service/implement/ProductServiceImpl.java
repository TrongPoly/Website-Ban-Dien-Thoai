package com.fpoly.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.SanPham;
import com.fpoly.repository.SanPhamRepository;
import com.fpoly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	SanPhamRepository spRp;

	@Override
	public List<SanPham> findAll() {
		return spRp.findAll();
	}

	@Override
	public SanPham findById(Integer id) {
		return spRp.findById(id).get();
	}

	@Override
	public List<SanPham> findAllByNSX(String tenNsx) {
		return spRp.findAll().stream().filter(sv -> sv.getNhaSanXuat().getTenNsx().equals(tenNsx))
				.collect(Collectors.toList());
	}

	@Override
	public void luu(SanPham sp) {
		spRp.save(sp);
	}
	


}
