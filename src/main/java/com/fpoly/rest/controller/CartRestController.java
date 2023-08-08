package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.GioHangChiTietId;
import com.fpoly.model.SanPham;
import com.fpoly.model.TaiKhoan;
import com.fpoly.service.CartDetailsService;
import com.fpoly.service.CartService;
import com.fpoly.service.CustomerService;
import com.fpoly.service.ProductService;
import com.fpoly.service.SessionService;

@RestController
@RequestMapping("/rest/cart")
public class CartRestController {
	@Autowired
	CartDetailsService cartDetailsService;
	@Autowired
	ProductService product;
	@Autowired
	SessionService session;
	@Autowired
	CartService cart;
	@Autowired
	CustomerService customer;

	@GetMapping("/get")
	public List<GioHangChiTiet> getDonHangChiTiet() {
		GioHang gioHang = cart.findByNguoiMua();
		return cartDetailsService.findByGioHang(gioHang.getId());
	}

	@PostMapping("/add/{productId}")
	public void addToCart(@PathVariable("productId") Integer productId) {
		SanPham sanPham = product.findById(productId);
		GioHang gioHang = cart.findByNguoiMua();

		GioHangChiTietId ghctId = new GioHangChiTietId(gioHang.getId(), productId);
		GioHangChiTiet ghct = cartDetailsService.findByMaGioHangVaMaSanPham(gioHang.getId(), productId);
		if (ghct != null) {
			ghct.setSoLuong(ghct.getSoLuong() + 1);
			cartDetailsService.luu(ghct);
		} else {
			ghct = new GioHangChiTiet(ghctId, gioHang, sanPham, 1, true);
			cartDetailsService.luu(ghct);
		}
	}

	@PutMapping("/update/{cartId}/{productId}")
	public ResponseEntity<GioHangChiTiet> updateSoLuong(@PathVariable("productId") Integer productId,
			@PathVariable("cartId") Integer cartId, @RequestParam("soLuong") Integer soLuong, @RequestParam("chonMua") Boolean chonMua) {
		GioHangChiTiet ghct = cartDetailsService.findByMaGioHangVaMaSanPham(cartId, productId);
		if(soLuong<=0 || ghct.getMaSanPham().getSoLuongTon()<soLuong) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		ghct.setSoLuong(soLuong);
		ghct.setChonMua(chonMua);
		cartDetailsService.luu(ghct);
		return ResponseEntity.ok(ghct);
	}
	
	@DeleteMapping("/delete/{cartId}/{productId}")
	public void deleteProductInCart(@PathVariable("cartId") Integer cartId, @PathVariable("productId") Integer productId) {
		GioHangChiTiet ghct = cartDetailsService.findByMaGioHangVaMaSanPham(cartId, productId);
		cartDetailsService.xoaSanPham(ghct);
	}

//	@PostMapping("/rest/cart")
//	public ResponseEntity<GioHangChiTiet> addToCart(@RequestParam("productId") Integer id) {
//		TaiKhoan user = session.get("user");
//		if (user == null)
//			return ResponseEntity.badRequest().build();
//		
//		GioHang gh = cart.findByNguoiMua();
//		SanPham sp = product.findById(id);
//		
//		GioHangChiTiet productExist = cartDetailsService.findByMaGioHangVaMaSanPham(gh.getId(), sp.getId());
//		// SP Chưa có trong giỏ hàng
//		if (productExist == null) {
//			themVaoGH(gh.getId(), sp.getId(), gh, sp);
//		} else {
//			int sl = productExist.getSoLuong() + 1;
//			productExist.setSoLuong(sl);
//			cartDetailsService.luu(productExist);
//		}
//		
//		return ResponseEntity.ok().build();
//	}
//
//	@DeleteMapping("/rest/cart")
//	public ResponseEntity<Void> deleteProduct(@RequestParam("productId") Integer productId,
//			@RequestParam("cartId") Integer cartId) {
//		GioHangChiTiet ghct = cartDetailsService.findByMaGioHangVaMaSanPham(cartId, productId);
//		cartDetailsService.xoaSanPham(ghct);
//		return ResponseEntity.ok().build();
//	}
//
//	@PutMapping("/rest/cart")
//	public ResponseEntity<Void> updateCart(@RequestParam("soLuongSP") Integer soLuong,
//			@RequestParam("productId") Integer productId, @RequestParam("cartId") Integer cartId) {
//		GioHangChiTiet ghct = cartDetailsService.findByMaGioHangVaMaSanPham(cartId, productId);
//		 if (soLuong <= 0) {
//			soLuong = 1;
//		}
//		ghct.setSoLuong(soLuong);
//		cartDetailsService.luu(ghct);
//		return ResponseEntity.ok().build();
//	}
//
//	@PutMapping("/rest/cart/checked")
//	public ResponseEntity<Void> CheckProduct(@RequestParam("productCheck") Boolean check,
//			@RequestParam("productId") Integer productId, @RequestParam("cartId") Integer cartId) {
//		GioHangChiTiet ghct = cartDetailsService.findByMaGioHangVaMaSanPham(cartId, productId);
//		ghct.setChonMua(check);
//		cartDetailsService.luu(ghct);
//		return ResponseEntity.ok().build();
//	}
//
//	private GioHangChiTiet themVaoGH(int maGH, int maSP, GioHang gh, SanPham sp) {
//		GioHangChiTietId ghctId = new GioHangChiTietId();
//		ghctId.setMaGioHang(maGH);
//		ghctId.setMaSanPham(maSP);
//		GioHangChiTiet ghct = new GioHangChiTiet(ghctId, gh, sp, 1, true);
//		cartDetailsService.luu(ghct);
//		return ghct;
//	}
}
