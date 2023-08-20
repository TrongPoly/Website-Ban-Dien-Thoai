package com.fpoly.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.SessionService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/auth")
public class AccountController {
	@Autowired
	SessionService session;
	@Autowired
	CartService cart;
	@Autowired
	CustomerService customer;
	@Autowired
	ServletContext context;

	@GetMapping("/login/form")
	public String formLogin() {
		return "User/login";
	}

	@GetMapping("/register/form")
	public String formRegis() {
		return "User/register";
	}

	@GetMapping("/login/success")
	public String doLogin() {
		if(session.get("user").getPhanQuyen().getId()==1) {
			return "redirect:/admin/invoice";
		}
		return "redirect:/home/index";
	}

	@RequestMapping("/logoff/success")
	public String doLogout(Model model) {
		session.remove("user");
		context.setAttribute("msg", "Đã đăng xuất!");
		return "redirect:/auth/login/form";
	}

	@RequestMapping("/login/error")
	public String loginError() {
		context.setAttribute("msg", "Sai tài khoản hoặc mật khẩu!");
		return "redirect:/auth/login/form";
	}

	@RequestMapping("/login/blocked")
	public String isBlocked() {
		context.setAttribute("msg", "Tài khoản đã bị khóa!");
		return "redirect:/auth/login/form";
	}

	@RequestMapping("/access/denied")
	public String denied() {
		return "User/ErrorPage";
	}

}
