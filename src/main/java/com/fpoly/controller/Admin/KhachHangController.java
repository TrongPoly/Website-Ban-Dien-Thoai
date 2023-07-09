package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class KhachHangController {

	@RequestMapping("/customerTable")
	public String Table(Model model) {
		return "Admin/customerTabled";
	}

	@RequestMapping("/customer")
	public String KhachHang(Model model) {
		return "/Admin/customered";
	}
}
