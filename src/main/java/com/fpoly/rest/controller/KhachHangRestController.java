package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.KhachHang;
import com.fpoly.model.TaiKhoan;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.repository.TaiKhoanRepository;
import com.fpoly.service.SessionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class KhachHangRestController {

	@Autowired
	KhachHangRepository daokh;

	@Autowired
	SessionService sessionService;

	@Autowired
	TaiKhoanRepository tkdao;

	@GetMapping("/khachhang")
	public List<KhachHang> getAll(Model model) {
		return daokh.findAll();

	}

	@GetMapping("/khachhang/{id}")
	public KhachHang getOne(@PathVariable("id") Integer id) {
		return daokh.findById(id).get();
	}

	@PostMapping("/khachhang")
	public KhachHang post(@RequestBody KhachHang id) {
		daokh.save(id);
		return id;
	}

	@PutMapping("/khachhang/{id}")
	public KhachHang put(@PathVariable("id") Integer id, @RequestBody KhachHang kh) {
		daokh.save(kh);
		return kh;
	}
	
//	@PostMapping("/chanuser")
//    public ResponseEntity<String> blockUser(
//            @RequestParam("email") String email,
//            @RequestParam("trangThai") Boolean trangThai) {
//        TaiKhoan currentUser = sessionService.get("user");
//        if (currentUser != null && currentUser.getEmail().equals(email)) {
//            return new ResponseEntity<>("Bạn không thể chặn chính mình.", HttpStatus.BAD_REQUEST);
//        } else {
//            // Tiến hành chặn người dùng khác
//            TaiKhoan tk = tkdao.getById(email);
//            tk.setTrangThai(trangThai);
//            tkdao.save(tk);
//            if (!trangThai) {
//                return new ResponseEntity<>("Đã chặn người dùng thành công.", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Bỏ chặn người dùng thành công.", HttpStatus.OK);
//            }
//        }
//    }
	
	

	@DeleteMapping("/khachhang/{id}")
	public void delete(@PathVariable("id") Integer id) {
		daokh.deleteById(id);
	}

}
