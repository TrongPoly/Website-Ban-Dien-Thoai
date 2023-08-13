package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.DonHangChiTiet;
import com.fpoly.model.DonHangChiTietId;
import com.fpoly.model.ReportDoanhThu;

public interface DonHangChiTietRepository extends JpaRepository<DonHangChiTiet, DonHangChiTietId> {
	@Query("SELECT new com.fpoly.model.ReportDoanhThu(dhct.maSanPham.tenSanPham, SUM(dhct.soLuong), SUM(dhct.soLuong * dhct.donGia)) "
			+ "FROM DonHangChiTiet dhct " + "WHERE dhct.maDonHang.trangThai = ?1 "
			+ "AND MONTH(dhct.maDonHang.ngayThanhToan) = ?2 " + "AND YEAR(dhct.maDonHang.ngayThanhToan) = ?3 "
			+ "GROUP BY dhct.maSanPham.tenSanPham")
	List<ReportDoanhThu> reportDoanhThu(Integer trangThai, Integer month, Integer year);
	@Query("SELECT new com.fpoly.model.ReportDoanhThu(dhct.maSanPham.tenSanPham, SUM(dhct.soLuong), SUM(dhct.soLuong * dhct.donGia)) "
			+ "FROM DonHangChiTiet dhct " + "WHERE dhct.maDonHang.trangThai = ?1 "
			+ "AND MONTH(dhct.maDonHang.ngayLap) = ?2 " + "AND YEAR(dhct.maDonHang.ngayLap) = ?3 "
			+ "GROUP BY dhct.maSanPham.tenSanPham")
	List<ReportDoanhThu> reportDangGiao(Integer trangThai, Integer month, Integer year);

	@Query("SELECT new com.fpoly.model.ReportDoanhThu(dhct.maSanPham.tenSanPham, SUM(dhct.soLuong), SUM(dhct.soLuong * dhct.donGia)) "
			+ "FROM DonHangChiTiet dhct " + "WHERE dhct.maDonHang.trangThai = ?1 "
			+ "AND YEAR(dhct.maDonHang.ngayLap) = ?2 "
			+ "GROUP BY dhct.maSanPham.tenSanPham")
	List<ReportDoanhThu> reportDoanhThuTheoNam(Integer trangThai, Integer year);
	
	@Query("SELECT new com.fpoly.model.ReportDoanhThu(dhct.maSanPham.tenSanPham, SUM(dhct.soLuong), SUM(dhct.soLuong * dhct.donGia)) "
			+ "FROM DonHangChiTiet dhct " + "WHERE dhct.maDonHang.trangThai = ?1 "
			+ "GROUP BY dhct.maSanPham.tenSanPham")
	List<ReportDoanhThu> reportDoanhThuAll(Integer trangThai);

	@Query("select distinct YEAR(dh.ngayThanhToan) from DonHang dh where dh.ngayThanhToan is not null")
	List<Integer> getYear();
}
