package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.District;
import com.fpoly.repository.DistrictRepository;
import com.fpoly.repository.WardsRepository;
import com.fpoly.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService{
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	WardsRepository  wardsRepository;
	@Override
	public List<District> loadAll() {
		return districtRepository.findAll();
	}

	@Override
	public List<District> loadQuan(String idTinh) {
		return districtRepository.findAll().stream().filter(quan -> quan.getProvince().getTenTinh().equals(idTinh)).toList();
	}

	

}
