package com.example.QlPhatTu.model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "phattu")
public class PhatTu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "ho")
    private String Ho;


    @Column(name = "tendem")
    private String TenDem;


    @Column(name = "ten")
    private String Ten;

    @Column(name = "phapdanh")
    private String PhapDanh;


    @Column(name = "anhchup")
    private String AnhChup;


    @Column(name = "sodienthoai")
    private String SoDienThoai;


    @Column(name = "email")
    private String email;


    @Column(name = "matkhau")
    private String MatKhau;
    @Column(name = "ngaysinh")
    private LocalDate NgaySinh;

    @Column(name = "ngayxuatgia")
    private LocalDate NgayXuatGia;

    @Column(name = "dahoantuc")
    private int DaHoanTuc;

    @Column(name = "ngayhoantuc")
    private LocalDate NgayHoanTuc;

    @Column(name = "gioitinh")
    private int GioiTinh;

    @Column(name = "ngaycapnhap")
    private LocalDate NgayCapNhap;


    @ManyToOne
    @JoinColumn(name = "kieuthanhvienid")
    private KieuThanhVien kieuThanhVien;

    @ManyToOne
    @JoinColumn(name = "chuaid")
    private Chua chua;

    @OneToMany(mappedBy = "phatTu")
    List<PhatTuDaoTrang> phatTuDaoTrangList;


    @OneToMany(mappedBy = "phatTuId")
    List<DonDangKy> donDangKyList;


    @OneToMany(mappedBy = "phatTu")
    List<DaoTrang> daoTrangList;


    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String ho) {
        Ho = ho;
    }

    public String getTenDem() {
        return TenDem;
    }

    public void setTenDem(String tenDem) {
        TenDem = tenDem;
    }

    public String getPhapDanh() {
        return PhapDanh;
    }

    public void setPhapDanh(String phapDanh) {
        PhapDanh = phapDanh;
    }

    public String getAnhChup() {
        return AnhChup;
    }

    public void setAnhChup(String anhChup) {
        AnhChup = anhChup;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public LocalDate getNgayXuatGia() {
        return NgayXuatGia;
    }

    public void setNgayXuatGia(LocalDate ngayXuatGia) {
        NgayXuatGia = ngayXuatGia;
    }

    public int getDaHoanTuc() {
        return DaHoanTuc;
    }

    public void setDaHoanTuc(int daHoanTuc) {
        DaHoanTuc = daHoanTuc;
    }

    public LocalDate getNgayHoanTuc() {
        return NgayHoanTuc;
    }

    public void setNgayHoanTuc(LocalDate ngayHoanTuc) {
        NgayHoanTuc = ngayHoanTuc;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public LocalDate getNgayCapNhap() {
        return NgayCapNhap;
    }

    public void setNgayCapNhap(LocalDate ngayCapNhap) {
        NgayCapNhap = ngayCapNhap;
    }

    public KieuThanhVien getKieuThanhVien() {
        return kieuThanhVien;
    }

    public void setKieuThanhVien(KieuThanhVien kieuThanhVien) {
        this.kieuThanhVien = kieuThanhVien;
    }


    public Chua getChua() {
        return chua;
    }

    public void setChua(Chua chua) {
        this.chua = chua;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
