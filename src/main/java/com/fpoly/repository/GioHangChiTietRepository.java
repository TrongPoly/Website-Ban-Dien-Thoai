package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.GioHangChiTiet;
import com.fpoly.model.GioHangChiTietId;



public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,GioHangChiTietId>{
	@Query("select ghct from GioHangChiTiet ghct where ghct.maGioHang.id = ?1 and ghct.maSanPham.id=?2")
	GioHangChiTiet findByMaGHvaMaSP(Integer maGH, Integer maSP);
	
	


}
