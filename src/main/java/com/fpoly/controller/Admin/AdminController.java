package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/admin/index")
	public String layout() {
		return "Admin/layout";
	}
	
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		
		return "Admin/admin";
	}

}
