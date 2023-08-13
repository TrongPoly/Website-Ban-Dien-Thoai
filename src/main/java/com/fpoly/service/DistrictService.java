package com.fpoly.service;

import java.util.List;

import com.fpoly.model.District;
public interface DistrictService {

	List<District> loadAll();

	List<District> loadQuan(String idTinh);


}
