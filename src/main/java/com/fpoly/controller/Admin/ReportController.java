package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {
	@RequestMapping("/report/doanhThu")
	public String DoanhThu(Model model) {
		return "report/doanhThu";
	}
	
	@RequestMapping("/report/SanPham")
	public String SanPham(Model model) {
		return "report/SanPham";
	}
}
