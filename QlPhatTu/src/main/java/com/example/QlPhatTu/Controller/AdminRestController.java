package com.example.QlPhatTu.Controller;


import com.example.QlPhatTu.QlPhatTuService.*;
import com.example.QlPhatTu.model.Entity.Chua;
import com.example.QlPhatTu.model.Entity.DaoTrang;
import com.example.QlPhatTu.model.Entity.PhatTu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {

    @Autowired
    private QlPhatTuService phatTuService;

    @Autowired
    private PhatTuDaoTrangService phatTuDaoTrangService;

    @Autowired
    private DaoTrangService daoTrangService;

    @Autowired
    private DonDangKyService donDangKyService;

    @Autowired
    private KieuThanhVienService kieuThanhVienService;

    @Autowired
    private ChuaService chuaService;

    @GetMapping("getallphattu")
    public Page<PhatTu> getPhatTu(@RequestParam(defaultValue = "0")int numberPage,
                                  @RequestParam(defaultValue = "5")int sizePage){
        return phatTuService.getPhatTu(numberPage,sizePage);
    }

    @PostMapping("themphattu")
    public ResponseEntity<String> ThemPhatTu(@RequestBody PhatTu phatTu){
        return phatTuService.addPhatTu(phatTu);
    }


    @GetMapping("timkiemphattu")
    public Page<PhatTu> timKiem(String ten, String phapDanh, String gioiTinh, String hoanTuc,@RequestParam(defaultValue = "0")int numberPage,
                                @RequestParam(defaultValue = "5")int sizePage){
        return phatTuService.findPhatTu(ten,phapDanh,gioiTinh,hoanTuc,numberPage,sizePage);
    }

    @PostMapping("suaphattu")
    public ResponseEntity<String> suaphantu(@RequestBody PhatTu phatTu){
        return phatTuService.editPhatTu(phatTu);
    }

    @PutMapping("dondangky")
    public ResponseEntity<String> createDonDangKy(String ten,String phapDanh,String sdt,String email,int daoTrangId,int nguoiSuLy){
        return donDangKyService.createDonDangKy(ten, phapDanh, sdt, email,daoTrangId);
    }
    /////
    @PostMapping("quanlydon")
    public ResponseEntity<?> quanLyDon(int id,int trangThai,String nguoiXuLy,String lyDoKhongThamGia){
        return donDangKyService.QuanLyDon(id, trangThai,nguoiXuLy, lyDoKhongThamGia);
    }

    @GetMapping("getallchua")
    public Page<Chua> getAllChua(@RequestParam(defaultValue = "0")int numberPage,
                                 @RequestParam(defaultValue = "5")int sizePage){
        return chuaService.getAllChua(numberPage,sizePage);
    }

    @GetMapping("themchua")
    public ResponseEntity<?> createChua(@RequestBody Chua chua){
        return chuaService.createChua(chua);
    }

    @PostMapping("suachua")
    public ResponseEntity<?> editChua(int id, String tenChua, LocalDate ngayThanhLap, String diaChi){
        return chuaService.editChua(id, tenChua, ngayThanhLap, diaChi);
    }

    @PostMapping("taodaotrang")
    public ResponseEntity<?> createDaoTrang(String noiToChuc,LocalDate thoiGianToChuc,String noidung,int daKetThuc,int idTruTri,int idDaoTrang){
        return daoTrangService.createDaoTrang(noiToChuc, thoiGianToChuc, noidung, daKetThuc,idTruTri,idDaoTrang);
    }

    @GetMapping("capnhapdaotrang")
    public ResponseEntity<?> updateDaoTrang(int daoTrangId){
        return daoTrangService.updateDaoTrang(daoTrangId);
    }

    @PostMapping("suadaotrang")
    public ResponseEntity<?> suaDaoTrang(DaoTrang daoTrang){
        return daoTrangService.editDaoTrang(daoTrang);
    }

    @GetMapping("timdaotrang")
    public Page<DaoTrang> timDaoTrang(String daoTrangId,String noiToChuc,String daKetThuc,String thoiGian,
                                      @RequestParam(defaultValue = "0")int numberPage,
                                      @RequestParam(defaultValue = "5")int sizePage){
        return daoTrangService.timDaoTrang(daoTrangId,noiToChuc,daKetThuc,thoiGian,numberPage,sizePage);
    }



    @GetMapping("inphattuthamgiadaotrang")
    public Page<PhatTu> inPhatTuThamGiaDaoTrang(@RequestParam(defaultValue = "0")int numberPage,
                                                @RequestParam(defaultValue = "5")int sizePage){
        return phatTuDaoTrangService.inPhatTuDaoTrang(numberPage,sizePage);
    }


    @GetMapping("taophattudaotrang")
    public ResponseEntity<?> creatPhatTuDaoTrang(int phatTuId,int daoTrangId,String lyDoKhongThamGia){
        return phatTuDaoTrangService.createPhatTuDaoTrang(phatTuId, daoTrangId, lyDoKhongThamGia);
    }



}
