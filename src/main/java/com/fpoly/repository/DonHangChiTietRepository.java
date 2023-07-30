package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.DonHangChiTietId;

public interface DonHangChiTietRepository extends JpaRepository<DonHangChiTiet, DonHangChiTietId>{

}
