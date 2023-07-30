package com.fpoly.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class InvoiceController {
	
	@RequestMapping("/invoice")
	public String invoice() {
		return "Admin/invoiceManagement";
	}
	
}
