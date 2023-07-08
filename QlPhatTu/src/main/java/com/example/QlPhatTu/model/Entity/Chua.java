package com.example.QlPhatTu.model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chua")
public class Chua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "tenchua")
    private String TenChua;

    @Column(name = "ngaythanhlap")
    private LocalDate NgayThanhLap;

    @Column(name = "diachi")
    private String DiaChi;

    @Column(name = "capnhap")
    private LocalDate CapNhap;

    @Column(name = "trutriid")
    private int TruTriId;
    @OneToMany(mappedBy = "chua")
    List<PhatTu> phatTuList;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTruTriId() {
        return TruTriId;
    }

    public void setTruTriId(int truTriId) {
        TruTriId = truTriId;
    }

    public String getTenChua() {
        return TenChua;
    }

    public void setTenChua(String tenChua) {
        TenChua = tenChua;
    }

    public LocalDate getNgayThanhLap() {
        return NgayThanhLap;
    }

    public void setNgayThanhLap(LocalDate ngayThanhLap) {
        NgayThanhLap = ngayThanhLap;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public LocalDate getCapNhap() {
        return CapNhap;
    }

    public void setCapNhap(LocalDate capNhap) {
        CapNhap = capNhap;
    }
}
