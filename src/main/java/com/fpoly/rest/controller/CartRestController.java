package com.fpoly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.model.GioHang;
import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.GioHangChiTietId;
import com.fpoly.model.SanPham;
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
	public void addToCart(@PathVariable("productId") Integer productId, @RequestParam("soLuong") Optional<Integer> soLuong) {
		SanPham sanPham = product.findById(productId);
		GioHang gioHang = cart.findByNguoiMua();

		GioHangChiTietId ghctId = new GioHangChiTietId(gioHang.getId(), productId);
		GioHangChiTiet ghct = cartDetailsService.findByMaGioHangVaMaSanPham(gioHang.getId(), productId);
		if (ghct != null) {
			ghct.setSoLuong(ghct.getSoLuong() + soLuong.orElse(1));
			cartDetailsService.luu(ghct);
		} else {
			ghct = new GioHangChiTiet(ghctId, gioHang, sanPham, soLuong.orElse(1), true);
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

}
