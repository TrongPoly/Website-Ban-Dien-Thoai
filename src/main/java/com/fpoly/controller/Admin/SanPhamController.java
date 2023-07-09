package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SanPhamController {
	
	@RequestMapping("/productTable")
	public String TableProduct(Model model) {
		return "Admin/productTabled";
	}
	
	@RequestMapping("/product")
	public String form(Model model) {
		return "Admin/productadd";
	}
}
