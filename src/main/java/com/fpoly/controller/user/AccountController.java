package com.fpoly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.GioHang;
import com.fpoly.model.TaiKhoan;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AccountController {
	@Autowired
	SessionService session;
	@Autowired
	CartService cart;
	@Autowired
	CustomerService customer;

	@GetMapping("/login/form")
	public String formLogin() {
		return "User/login";
	}

	@GetMapping("/login/success")
	public String doLogin(Model model) {

		return "redirect:/home/index";
	}

	@RequestMapping("/logoff/success")
	public String doLogout() {
		session.remove("user");
		return "redirect:/auth/login/form";
	}

	@RequestMapping("/login/error")
	public String loginError() {
		return "redirect:/auth/login/form";
	}

	@RequestMapping("/access/denied")
	public String denied() {
		return "redirect:/home/index";
	}

}
