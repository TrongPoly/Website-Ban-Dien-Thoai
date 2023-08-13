package com.fpoly.service;

import java.util.List;

import com.fpoly.model.Ward;

public interface WardsService {

	List<Ward> loadAll();

	List<Ward> loadPhuong(String idQuan);


}
