package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {	
	@RequestMapping("/index")
	public String admin(Model model) {
		return "Admin/admin";
	}
	@RequestMapping("/customer")
	public String khachHang() {
		return "Admin/customered";
	}
	

}
