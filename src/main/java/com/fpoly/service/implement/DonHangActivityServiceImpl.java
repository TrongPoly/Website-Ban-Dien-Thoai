package com.fpoly.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fpoly.model.DonHangActivity;
import com.fpoly.model.GioHang;
import com.fpoly.repository.DonHangActivityRepository;
import com.fpoly.service.DonHangActivityService;

@Service
public class DonHangActivityServiceImpl implements DonHangActivityService {
	@Autowired
	DonHangActivityRepository donHangActivityRepository;

	@Override
	public void luu(DonHangActivity activity) {
		donHangActivityRepository.save(activity);
	}

	@Override
	public List<DonHangActivity> findByOrderId(Integer id) {
		Sort sort = Sort.by(Sort.Direction.DESC, "ngayCapNhat");
		return donHangActivityRepository.findAll(sort).stream().filter(dh -> dh.getMaDonHang().getId().equals(id))
				.toList();
	}

	@Override
	public List<DonHangActivity> findByChecked() {
		Sort sort = Sort.by(Sort.Direction.DESC, "ngayCapNhat");
		return donHangActivityRepository.findAll(sort).stream().filter(dh -> dh.getChecked() != 2).toList();
	}

	@Override
	public DonHangActivity findById(Integer id) {
		return donHangActivityRepository.findById(id).get();
	}

}
