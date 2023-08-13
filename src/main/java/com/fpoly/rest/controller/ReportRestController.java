package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.ReportDoanhThu;
import com.fpoly.repository.DonHangChiTietRepository;

@RestController
@RequestMapping("/rest/report")
public class ReportRestController {
	@Autowired
	DonHangChiTietRepository donHangChiTietRepository;

	@GetMapping("/doanhThu")
	public List<ReportDoanhThu> doanhThu(@RequestParam("trangThai") Integer trangThai,
			@RequestParam("thang") Integer thang, @RequestParam("nam") Integer nam) {
		if (nam == 0 && thang == 0) {
			return donHangChiTietRepository.reportDoanhThuAll(trangThai);
		}
		if (thang == 0) {
			return donHangChiTietRepository.reportDoanhThuTheoNam(trangThai, nam);
		}

		if (trangThai == 2) {
			return donHangChiTietRepository.reportDangGiao(trangThai, thang, nam);
		}
		return donHangChiTietRepository.reportDoanhThu(trangThai, thang, nam);
	}

	@GetMapping("/getYear")
	public List<Integer> years() {
		return donHangChiTietRepository.getYear();
	}

}
