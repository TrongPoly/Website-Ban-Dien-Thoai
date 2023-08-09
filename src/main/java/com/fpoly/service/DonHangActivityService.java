package com.fpoly.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

import com.fpoly.model.DonHangActivity;
import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.GioHang;

public interface DonHangActivityService {

	void luu(DonHangActivity activity);

	List<DonHangActivity> findByOrderId(Integer id);


	List<DonHangActivity> findByChecked();

	DonHangActivity findById(Integer id);



}
