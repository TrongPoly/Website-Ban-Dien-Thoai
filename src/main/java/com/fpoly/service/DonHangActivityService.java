package com.fpoly.service;

import java.util.List;

import com.fpoly.model.DonHangActivity;

public interface DonHangActivityService {

	void luu(DonHangActivity activity);

	List<DonHangActivity> findByOrderId(Integer id);


	List<DonHangActivity> findByChecked();

	DonHangActivity findById(Integer id);



}
