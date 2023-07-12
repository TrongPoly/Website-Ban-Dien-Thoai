package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class InvoiceController {
	
	@RequestMapping("/invoice")
	public String invoice(Model model) {
		return "Admin/invoiceManagement";
	}
}
