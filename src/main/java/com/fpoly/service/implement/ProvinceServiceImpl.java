package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Province;
import com.fpoly.repository.ProvinceRepository;
import com.fpoly.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService{
	@Autowired
	ProvinceRepository provinceRepository;
	@Override
	public List<Province> loadAll() {
		return provinceRepository.findAll();
	}

}
