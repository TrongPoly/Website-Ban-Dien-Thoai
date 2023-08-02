package com.fpoly.controller.Admin;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.repository.KhachHangRepository;
import com.fpoly.repository.TaiKhoanRepository;
import com.fpoly.service.OptionServiceHangKhachHang;
import com.fpoly.service.SessionService;
import com.fpoly.model.KhachHang;
import com.fpoly.model.TaiKhoan;

@Controller
@RequestMapping("/admin")
public class KhachHangController {

	@Autowired
	KhachHangRepository khdao;
	
	@Autowired
	public OptionServiceHangKhachHang optionService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	TaiKhoanRepository tkdao;

	
	@RequestMapping("/customerTable")
	public String Table(Model model, @RequestParam("p") Optional<Integer> p) {
		var numberOfRecords = khdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("id").ascending());
		model.addAttribute("currIndex", p.orElse(0));
		var khs = khdao.findAll(sort);
		model.addAttribute("khs", khs);// buộc lên bảng
		return "/Admin/customerTabled";
	}

	@GetMapping("/customer")
	public String KhachHang(Model model) {
		
		Map<Integer, String> options = optionService.getAllOptions();
		KhachHang kh = new KhachHang();
		model.addAttribute("khachhang", kh);
		List<KhachHang> khs = khdao.findAll();
		model.addAttribute("khs", khs);
		model.addAttribute("options", options);
		
		return "Admin/customered";
	}
	
	
	@RequestMapping(value =  "/customer/save", method = {RequestMethod.GET, RequestMethod.POST})
	public String save(@Validated @ModelAttribute("khachhang") KhachHang kh,Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			Map<Integer, String> options = optionService.getAllOptions();
			model.addAttribute("options", options);
			return "/Admin/customered";
		}
		
		khdao.save(kh);
		return "redirect:/admin/customerTable";
	}
	@RequestMapping("/chanuser")
	public String blockUser(Model model, @RequestParam("email") String email,
			@RequestParam("trangThai") Boolean trangThai) {
		TaiKhoan currentUser = sessionService.get("user");
		if (currentUser != null && currentUser.getEmail().equals(email)) {
			model.addAttribute("msg", "Bạn không thể chặn chính mình.");
		} else {
			// Tiến hành chặn người dùng khác
			TaiKhoan tk = tkdao.getById(email);
			tk.setTrangThai(trangThai);
			tkdao.save(tk);
			if (!trangThai) {
				model.addAttribute("msg", "Đã chặn người dùng thành công.");
			} else {
				model.addAttribute("msg", "Bỏ chặn người dùng thành công.");
			}
		}
		return "forward:/admin/customerTable";
	}
	@RequestMapping("/customer/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Map<Integer, String> options = optionService.getAllOptions();
		KhachHang kh = khdao.findById(id).get();
		model.addAttribute("khachhang", kh);
		List<KhachHang> khs = khdao.findAll();
		model.addAttribute("khs", khs);
		model.addAttribute("options", options);
		return "Admin/customered";
	}
	@GetMapping("/customer/page")
	public String paginate(@ModelAttribute("khachhang") KhachHang kh, Model model,
			@RequestParam("p") Optional<Integer> p) {
		return this.Table(model,p);
	}
	@RequestMapping(value = "/customer/delete/{id}")
	public String deleteId(@PathVariable("id") Integer id) {
		khdao.deleteById(id);
		return "redirect:/admin/customerTabled";
	}
	@RequestMapping("/customer/search")
	public String searchName(Model model, @RequestParam("name") Optional<String> name,
			@RequestParam(name = "page", defaultValue = "0") Integer pageNo) {

//		Pageable pageable = PageRequest.of(pageNo, 4);
//		
//		int totalPages = pageable.getPageSize();
//		
//		
//		
//		model.addAttribute("totalPages", totalPages);
//		
//		model.addAttribute("currenIdenx", pageNo);
		var numberOfRecords = khdao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(pageNo, 5, Sort.by("id").ascending());
		model.addAttribute("currIndex", pageNo);

		var khs = khdao.findByTenKhachHang("%" + name.orElse("") + "%", sort);
		model.addAttribute("khs", khs);

		return "/Admin/customerTabled";
	}
}
