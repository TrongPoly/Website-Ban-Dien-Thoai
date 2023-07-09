package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SanPhamController {
	
	@RequestMapping("/admin/productTable")
	public String TableProduct(Model model) {
		return "Admin/productTabled";
	}
	
	@RequestMapping("/admin/product")
	public String form(Model model) {
		return "Admin/productadd";
	}
}
