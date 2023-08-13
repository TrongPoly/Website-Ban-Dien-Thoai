package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Ward;
import com.fpoly.repository.WardsRepository;
import com.fpoly.service.WardsService;

@Service
public class WardsServiceImpl implements WardsService{

	@Autowired
	WardsRepository wardsRepository;
	@Override
	public List<Ward> loadAll() {
		return wardsRepository.findAll();
	}

	@Override
	public List<Ward> loadPhuong( String idQuan) {
		return wardsRepository.findAll().stream().filter(p -> p.getDistrict().getTenQuan().equals(idQuan) ).toList();
	}

}
