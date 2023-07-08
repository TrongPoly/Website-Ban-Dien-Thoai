package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HangKhachHangController {
	
	

	@RequestMapping("/admin/customerRankTable")
	public String Table(Model model) {
		return "Admin/customerRankTabled";
	}
	
	@RequestMapping("/admin/customerRank")
	public String HangKhachHang(Model model) {
		
		return "Admin/customerRanked";
	}
}
