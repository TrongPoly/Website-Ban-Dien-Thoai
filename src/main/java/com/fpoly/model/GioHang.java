package com.fpoly.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @Column(name = "ma_gio_hang", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_so_huu")
    private KhachHang nguoiSoHuu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KhachHang getNguoiSoHuu() {
        return nguoiSoHuu;
    }

    public void setNguoiSoHuu(KhachHang nguoiSoHuu) {
        this.nguoiSoHuu = nguoiSoHuu;
    }

}