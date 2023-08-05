package com.fpoly.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, Integer>{
	
	@Query(name = "findByTenKhachHang")
	List<KhachHang> findByTenKhachHang(String tenKhachHang,Pageable page);
}
