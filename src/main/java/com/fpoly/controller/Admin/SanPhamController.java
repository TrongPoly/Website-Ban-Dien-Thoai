package com.fpoly.controller.Admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.model.SanPham;
import com.fpoly.repository.SanPhamRepository;
import com.fpoly.service.OptionServiceNhaSanXuat;
import com.fpoly.service.UploadFileService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Controller
@RequestMapping("/admin")
public class SanPhamController {
	@Autowired
	UploadFileService uploadFileService;
	@Autowired
	SanPhamRepository daosp;
	@Autowired
	public OptionServiceNhaSanXuat optionService;
	
	@RequestMapping("/product")
	public String form(Model model) {
		
		Map<Integer, String> options = optionService.getAllOptions();
		SanPham sp = new SanPham();
		model.addAttribute("sanpham", sp);
		var sps = daosp.findAll();
		model.addAttribute("sps",sps);
		model.addAttribute("options", options);
		return "Admin/productadd";
	}
	@GetMapping("/productTable")
	public String TableProduct( Model model, @RequestParam("p") Optional<Integer> p) {
		var numberOfRecords = daosp.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("id").descending());
		model.addAttribute("currIndex", p.orElse(0));

		var sps = daosp.findAll(sort);
		model.addAttribute("sps", sps);
		
		
		return "Admin/productTabled";
	}
	@GetMapping("/product/page")
	public String paginate(@ModelAttribute("sanpham") SanPham sp, Model model, @RequestParam("p") Optional<Integer> p) {
		return this.TableProduct(model, p);
	}
	
	@PostMapping("/product/save")
	public String save(@Validated @ModelAttribute("sanpham") SanPham sp,
			Errors errors, @RequestParam("file") MultipartFile file,Model model) {
		 if(errors.hasErrors()) {
			 Map<Integer, String> options = optionService.getAllOptions();
			 model.addAttribute("options", options);
			 return "Admin/productadd";
		 }
		 if (sp.getSoLuongTon() == 0) {
				sp.setTrangThai(false); // set trạng thái là hết hàng nếu số lượng tồn = 0
			} else {
				sp.setTrangThai(true);
			}
			// Lưu tệp vào thư mục
			String filename = file.getOriginalFilename().toString();
			String path = "E:\\Java6\\workspace\\Website-Ban-Dien-Thoai\\src\\main\\resources\\static\\img\\product\\"
					+ filename;
			File savedFile = new File(path);
			try {
				file.transferTo(savedFile);
			} catch (IllegalStateException e) {
				daosp.save(sp);
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Thiết lập đường dẫn cho sản phẩm
			sp.setAnhSanPham(filename);

		
		daosp.save(sp);
		
		return "redirect:/admin/productTable";
	}
	
	@RequestMapping( value = "/product/delete/{id}")
	public String deleteId(@PathVariable("id") Integer id) {
		daosp.deleteById(id);
		return "redirect:/admin/productTable";
	}
	
	
	
	@RequestMapping("/product/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		SanPham sp = daosp.findById(id).get();
		model.addAttribute("sanpham", sp);
		Map<Integer, String> options = optionService.getAllOptions();
		model.addAttribute("options", options);
		List<SanPham> sps = daosp.findAll();
		model.addAttribute("sps", sps);
		

		return "Admin/productadd";
	}
	
	@RequestMapping("/product/search")
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
		var numberOfRecords = daosp.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(pageNo, 5, Sort.by("id").descending());
		model.addAttribute("currIndex", pageNo);

		var sps = daosp.findByTenSanPham("%" + name.orElse("") + "%", sort);
		model.addAttribute("sps", sps);

		return "Admin/productTabled";
	}
}
