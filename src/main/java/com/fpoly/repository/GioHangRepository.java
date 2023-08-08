package com.fpoly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.GioHang;
import com.fpoly.model.KhachHang;

public interface GioHangRepository extends JpaRepository<GioHang, Integer>{


}
