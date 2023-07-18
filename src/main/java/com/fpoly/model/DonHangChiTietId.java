package com.fpoly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DonHangChiTietId implements Serializable {
    private static final long serialVersionUID = 3800633230937416308L;
    @Column(name = "ma_don_hang", nullable = false)
    private Integer maDonHang;

    @Column(name = "ma_san_pham", nullable = false)
    private Integer maSanPham;

    public Integer getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Integer maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DonHangChiTietId entity = (DonHangChiTietId) o;
        return Objects.equals(this.maSanPham, entity.maSanPham) &&
                Objects.equals(this.maDonHang, entity.maDonHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSanPham, maDonHang);
    }

}