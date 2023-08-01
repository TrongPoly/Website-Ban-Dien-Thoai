package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.model.DonHangActivity;
import com.fpoly.model.GioHang;
import com.fpoly.repository.DonHangActivityRepository;
import com.fpoly.service.DonHangActivityService;

@Service
public class DonHangActivityServiceImpl implements DonHangActivityService{
	@Autowired
	DonHangActivityRepository donHangActivityRepository;
	
	@Override
	public void luu(DonHangActivity activity) {
		donHangActivityRepository.save(activity);
	}

	@Override
	public List<DonHangActivity> findByOrderId(Integer id) {
		return donHangActivityRepository.findAll().stream().filter(dh -> dh.getMaDonHang().getId().equals(id)).toList();
	}

	@Override
	public List<DonHangActivity> findAll() {
		return donHangActivityRepository.findAll();
	}

	@Override
	public List<DonHangActivity> findByChecked() {
		return donHangActivityRepository.findAll().stream().filter(dh -> dh.getChecked()!=2).toList();
	}

	@Override
	public DonHangActivity findById(Integer id) {
		return donHangActivityRepository.findById(id).get();
	}

	

	

}
