package com.fpoly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GioHangChiTietId implements Serializable {
    private static final long serialVersionUID = 8640178315417884397L;
    @Column(name = "ma_gio_hang", nullable = false)
    private Integer maGioHang;

    @Column(name = "ma_san_pham", nullable = false)
    private Integer maSanPham;

    public Integer getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(Integer maGioHang) {
        this.maGioHang = maGioHang;
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
        GioHangChiTietId entity = (GioHangChiTietId) o;
        return Objects.equals(this.maGioHang, entity.maGioHang) &&
                Objects.equals(this.maSanPham, entity.maSanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGioHang, maSanPham);
    }

}