package com.fpoly.model;

import jakarta.persistence.*;

import org.hibernate.annotations.Nationalized;

@NamedQueries({
	@NamedQuery(name="findByTenHang", query = "select hkh from HangKhachHang hkh "
			+ "where hkh.tenHang like ?1")})


@Entity
@Table(name = "hang_khach_hang")
public class HangKhachHang {
    @Id
    @Column(name = "ma_hang", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nationalized
    @Column(name = "ten_hang", length = 50)
    private String tenHang;

    @Nationalized
    @Lob
    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "diem_toi_thieu", nullable = false)
    private Integer diemToiThieu;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getDiemToiThieu() {
        return diemToiThieu;
    }

    public void setDiemToiThieu(Integer diemToiThieu) {
        this.diemToiThieu = diemToiThieu;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

}