package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.HangKhachHang;

public interface HangKhachHangRepository extends JpaRepository<HangKhachHang, Integer>{
	@Query("select o from HangKhachHang o where o.id like ?1")
	List<HangKhachHang> findbykeywords(String key);
	
}
