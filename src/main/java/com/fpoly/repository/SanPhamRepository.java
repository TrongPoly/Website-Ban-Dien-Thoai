package com.fpoly.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fpoly.model.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer>{
	
	SanPham getByTenSanPham(String tenSanPham);
	
	@Query(name = "findByTenSanPham")
	List<SanPham> findByTenSanPham(String tenSanPham, Pageable page);
}
