package com.example.QlPhatTu.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "daotrang")
public class DaoTrang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Max(0L)
    @Column(name = "noitochuc")
    private String NoiToChuc;

    @Column(name = "sothanhvienthamgia")
    private int SoThanhVienThamGia;

    @Column(name = "thoigiantochuc")
    private LocalDate ThoiGianToChuc;

    @Max(0L)
    @Column(name = "noidung")
    private String NoiDung;

    @Column(name = "daketthuc")
    private int DaKetThuc;

    @OneToMany(mappedBy = "daoTrang")
    List<PhatTuDaoTrang> phatTuDaoTranglist;

    @ManyToOne
    @JoinColumn(name = "nguoitrutriid")
    private PhatTu phatTu;

    @OneToMany(mappedBy = "daoTrangId")
    List<DonDangKy> donDangKyList;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNoiToChuc() {
        return NoiToChuc;
    }

    public void setNoiToChuc(String noiToChuc) {
        NoiToChuc = noiToChuc;
    }

    public int getSoThanhVienThamGia() {
        return SoThanhVienThamGia;
    }

    public void setSoThanhVienThamGia(int soThanhVienThamGia) {
        SoThanhVienThamGia = soThanhVienThamGia;
    }

    public LocalDate getThoiGianToChuc() {
        return ThoiGianToChuc;
    }

    public void setThoiGianToChuc(LocalDate thoiGianToChuc) {
        ThoiGianToChuc = thoiGianToChuc;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public int getDaKetThuc() {
        return DaKetThuc;
    }

    public void setDaKetThuc(int daKetThuc) {
        DaKetThuc = daKetThuc;
    }

    public PhatTu getPhatTu() {
        return phatTu;
    }

    public void setPhatTu(PhatTu phatTu) {
        this.phatTu = phatTu;
    }
}
