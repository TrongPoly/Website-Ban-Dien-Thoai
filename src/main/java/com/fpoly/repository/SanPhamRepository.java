package com.fpoly.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fpoly.model.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer>{
	
	SanPham getByTenSanPham(String tenSanPham);
	
//	@Query(name = "findByTenSanPham")
//	List<SanPham> findByTenSanPham(String tenSanPham);
	
	@Query(name= "findByNhaSanXuat")
	List<SanPham> findByNhaSanXuat(String tenNhaSanXuat,Pageable page);

	
//	@Query("select p from SanPham p where p.trangThai = true and lower(p.tenSanPham) like lower(concat('%', ?1, '%'))")
	
	
	
	List<SanPham> findByTenSanPhamContaining(String keyword);
}
	