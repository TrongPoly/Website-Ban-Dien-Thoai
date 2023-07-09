package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HangKhachHangController {
	
	

	@RequestMapping("/customerRankTable")
	public String Table(Model model) {
		return "Admin/customerRankTabled";
	}
	
	@RequestMapping("/customerRank")
	public String HangKhachHang(Model model) {
		
		return "Admin/customerRanked";
	}
}
