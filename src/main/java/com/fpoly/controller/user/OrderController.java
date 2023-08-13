package com.fpoly.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

	@RequestMapping("/index")
	public String index(Model model) {
//		List<DonHang> listDH = orderService.findByUser();
//		model.addAttribute("listDH", listDH);
		return "User/invoice";
	}

//	@RequestMapping("/details/{id}")
//	public String details(Model model, @PathVariable("id") Integer id) {
//		List<DonHangChiTiet> listDHCT = orderDetails.findByMaDonHang(id);
//		model.addAttribute("listDHCT", listDHCT);
//		DonHang donHang = orderService.findByMaDonHang(id);
//		model.addAttribute("trangThai",donHang.getTrangThai());
//		
//		long total = listDHCT.stream().mapToLong(
//				dhct -> dhct.getSoLuong() * Long.parseLong(dhct.getDonGia().stripTrailingZeros().toPlainString()))
//				.sum();
//		model.addAttribute("total",total);
//		return "User/invoiceDetails";
//	}
}
