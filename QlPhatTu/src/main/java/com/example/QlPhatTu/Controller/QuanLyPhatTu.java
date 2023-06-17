package com.example.QlPhatTu.Controller;

import com.example.QlPhatTu.QlPhatTuService.QlPhatTuService;
import com.example.QlPhatTu.entity.Chua;
import com.example.QlPhatTu.entity.DaoTrang;
import com.example.QlPhatTu.entity.PhatTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(value = "*",allowedHeaders = "*")
@RequestMapping("api/version1.0")
public class QuanLyPhatTu {

    @Autowired
    private QlPhatTuService phatTuService;

    @GetMapping("getallphattu")
    public Page<PhatTu> getPhatTu(@RequestParam(defaultValue = "0")int numberPage,
                                  @RequestParam(defaultValue = "5")int sizePage){
        return phatTuService.getPhatTu(numberPage,sizePage);
    }

    @PostMapping("themphattu")
    public ResponseEntity<String> ThemPhatTu(@RequestBody PhatTu phatTu){
        return phatTuService.addPhatTu(phatTu);
    }

    @DeleteMapping("xoaphattu")
    public ResponseEntity<String> deletePhatTu(int phatTuId){
        return phatTuService.DeletePhatTu(phatTuId);
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
        return phatTuService.createDonDangKy(ten, phapDanh, sdt, email,daoTrangId,nguoiSuLy);
    }

    @PostMapping("dangnhap")
    public ResponseEntity<String> dangNhap(String email,String password){
        return phatTuService.dangNhap(email, password);
    }

    @PostMapping("doimatkhau")
    public ResponseEntity<String> doiMatKhau(String email,String newpassword,String oldpass){
        return phatTuService.doiMatKhau(email, newpassword,oldpass);
    }

    @PostMapping("quanlydon")
    public ResponseEntity<?> quanLyDon(int id,int trangThai,int nguoiSuLy){
        return phatTuService.QuanLyDon(id, trangThai,nguoiSuLy);
    }

    @GetMapping("getallchua")
    public Page<Chua> getAllChua(@RequestParam(defaultValue = "0")int numberPage,
                                 @RequestParam(defaultValue = "5")int sizePage){
        return phatTuService.getAllChua(numberPage,sizePage);
    }

    @GetMapping("themchua")
    public ResponseEntity<?> createChua(@RequestBody Chua chua){
        return phatTuService.createChua(chua);
    }
    @DeleteMapping("xoathongtinchua")
    public ResponseEntity<?> xoaChua(int id){
        return phatTuService.deleteChua(id);
    }
    @PostMapping("suachua")
    public ResponseEntity<?> editChua(int id, String tenChua, LocalDate ngayThanhLap, String diaChi){
        return phatTuService.editChua(id, tenChua, ngayThanhLap, diaChi);
    }

    @PostMapping("taodaotrang")
    public ResponseEntity<?> createDaoTrang(String noiToChuc,LocalDate thoiGianToChuc,String noidung,int daKetThuc,int idTruTri,int idDaoTrang){
        return phatTuService.createDaoTrang(noiToChuc, thoiGianToChuc, noidung, daKetThuc,idTruTri,idDaoTrang);
    }

    @GetMapping("capnhapdaotrang")
    public ResponseEntity<?> updateDaoTrang(int daoTrangId){
        return phatTuService.updateDaoTrang(daoTrangId);
    }

    @PostMapping("suadaotrang")
    public ResponseEntity<?> suaDaoTrang(DaoTrang daoTrang){
        return phatTuService.editDaoTrang(daoTrang);
    }

    @GetMapping("timdaotrang")
    public Page<DaoTrang> timDaoTrang(String daoTrangId,String noiToChuc,String daKetThuc,String thoiGian,
                                      @RequestParam(defaultValue = "0")int numberPage,
                                      @RequestParam(defaultValue = "5")int sizePage){
        return phatTuService.timDaoTrang(daoTrangId,noiToChuc,daKetThuc,thoiGian,numberPage,sizePage);
    }

    @DeleteMapping("xoadaotrang")
    public ResponseEntity<?> xoaDaoTrang(int id){
        return phatTuService.xoaDaoTrang(id);
    }

    @GetMapping("inphattuthamgiadaotrang")
    public Page<PhatTu> inPhatTuThamGiaDaoTrang(@RequestParam(defaultValue = "0")int numberPage,
                                                        @RequestParam(defaultValue = "5")int sizePage){
        return phatTuService.inPhatTuDaoTrang(numberPage,sizePage);
    }


    @GetMapping("taophattudaotrang")
    public ResponseEntity<?> creatPhatTuDaoTrang(int phatTuId,int daoTrangId,String lyDoKhongThamGia){
        return phatTuService.createPhatTuDaoTrang(phatTuId, daoTrangId, lyDoKhongThamGia);
    }

    @DeleteMapping("xoaphattudaotrang")
    public ResponseEntity<?> deletePhatTuDaoTrang(int phatTuDaoTrangId){
        return phatTuService.deletePhatTuDaoTrang(phatTuDaoTrangId);
    }
}
