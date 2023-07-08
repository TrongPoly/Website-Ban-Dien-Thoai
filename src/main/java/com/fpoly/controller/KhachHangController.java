package com.fpoly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KhachHangController {

	@RequestMapping("/admin/customerTable")
	public String Table(Model model) {
		return "Admin/customerTabled";
	}

	@RequestMapping("/admin/customer")
	public String KhachHang(Model model) {
		return "/Admin/customered";
	}
}
