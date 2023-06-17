package com.example.QlPhatTu.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dondangky")
public class DonDangKy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "trangthaidon")
    private int TrangThaiDon;

    @Column(name = "ngayguidon")
    private LocalDate NgayGuiDon;

    @Column(name = "ngayxuly")
    private LocalDate NgayXuLy;

    @Column(name = "nguoisulyid")
    private int NguoiSuLyId;

    @ManyToOne
    @JoinColumn(name = "phattuid")
    private PhatTu phatTuId;

    @ManyToOne
    @JoinColumn(name = "daotrangid")
    private DaoTrang daoTrangId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTrangThaiDon() {
        return TrangThaiDon;
    }

    public void setTrangThaiDon(int trangThaiDon) {
        TrangThaiDon = trangThaiDon;
    }

    public LocalDate getNgayGuiDon() {
        return NgayGuiDon;
    }

    public void setNgayGuiDon(LocalDate ngayGuiDon) {
        NgayGuiDon = ngayGuiDon;
    }

    public LocalDate getNgayXuLy() {
        return NgayXuLy;
    }

    public void setNgayXuLy(LocalDate ngayXuLy) {
        NgayXuLy = ngayXuLy;
    }

    public int getNguoiSuLyId() {
        return NguoiSuLyId;
    }

    public void setNguoiSuLyId(int nguoiSuLyId) {
        NguoiSuLyId = nguoiSuLyId;
    }

    public PhatTu getPhatTuId() {
        return phatTuId;
    }

    public void setPhatTuId(PhatTu phatTuId) {
        this.phatTuId = phatTuId;
    }

    public DaoTrang getDaoTrangId() {
        return daoTrangId;
    }

    public void setDaoTrangId(DaoTrang daoTrangId) {
        this.daoTrangId = daoTrangId;
    }
}
