package com.fpoly.controller.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.DiaChi;
import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.TaiKhoan;
import com.fpoly.service.CartDetailsService;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.AddressService;
import com.fpoly.service.SessionService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	SessionService session;
	@Autowired
	CartDetailsService cartDetailsService;
	@Autowired
	AddressService addressService;
	@Autowired
	CustomerService customer;

	@RequestMapping("/index")
	public String index(Model model) {
		List<DiaChi> listDC = addressService.FindByUser();
		model.addAttribute("listDC", listDC);
		GioHang gioHang = cartService.findByUser();

		List<GioHangChiTiet> listGD = cartDetailsService.findByGioHang(gioHang);

		model.addAttribute("listGH", listGD);
		long total = listGD.stream().mapToLong(ghct -> ghct.getSoLuong()
				* Long.parseLong(ghct.getMaSanPham().getDonGia().stripTrailingZeros().toPlainString())).sum();
		model.addAttribute("total", total);
		return "User/cart";
	}
}
