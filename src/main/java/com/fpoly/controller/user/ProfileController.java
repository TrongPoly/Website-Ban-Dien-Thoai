package com.fpoly.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
	@RequestMapping("/Profile")
	public String Profile() {
		return "/User/Profile";
	}

}
