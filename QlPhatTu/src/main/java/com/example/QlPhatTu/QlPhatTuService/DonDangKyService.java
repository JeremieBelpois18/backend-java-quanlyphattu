package com.example.QlPhatTu.QlPhatTuService;

import com.example.QlPhatTu.QlPhatTuService.InterfaceService.IdonDangKyServiec;
import com.example.QlPhatTu.Repository.*;
import com.example.QlPhatTu.model.Entity.DaoTrang;
import com.example.QlPhatTu.model.Entity.DonDangKy;
import com.example.QlPhatTu.model.Entity.PhatTu;
import com.example.QlPhatTu.model.Entity.PhatTuDaoTrang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DonDangKyService implements IdonDangKyServiec {
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private DonDangKyRepo donDangKyRepo;

    @Autowired
    private PhatTuDaoTrangRepo phatTuDaoTrangRepo;

    @Autowired
    private ChuaRepo chuaRepo;

    @Autowired
    private DaoTrangRepo daoTrangRepo;
    @Autowired
    private KieuThanhVienRepo kieuThanhVienRepo;

    //Tạo đơn đăng Ký
    @Override
    public ResponseEntity<String> createDonDangKy(String ten, String phapDanh, String sdt, String email, int daoTrangId) {
        DonDangKy donDangKy = new DonDangKy();
        Optional<PhatTu> phatTu = phatTuRepo.findByTPSE(ten, phapDanh, sdt, email);
        Optional<DaoTrang> daoTrang = daoTrangRepo.findById(daoTrangId);
        // kiểm tra thông tin phật tử và đơn đã đăng ký hay chưa
        if (daoTrang.isPresent() && phatTu.isPresent() && phatTuRepo.existsByPhatTuId(phatTu.get().getId()) == 1 && phatTu.get().getDaHoanTuc() == 0) {
            donDangKy.setPhatTuId(phatTu.get());
            donDangKy.setTrangThaiDon(0);
            donDangKy.setDaoTrangId(daoTrang.get());
            donDangKy.setNgayGuiDon(LocalDate.now());
            donDangKyRepo.save(donDangKy);
            return ResponseEntity.ok("Chờ Duyệt");
        }
        return ResponseEntity.badRequest().body(" Thông tin không đúng hoặc phật tử đã hoàn tục Hoặc dơn đa tồn tại");
    }


    //Quản Lý dơn đang ký
    @Override
    public ResponseEntity<?> QuanLyDon(int id, int trangThai, String nguoiXuLy,String lyDoKhongThamGia) {
        Optional<DonDangKy> getDonId = donDangKyRepo.findById(id);
        PhatTuDaoTrang phatTuDaoTrang = new PhatTuDaoTrang();
        if (getDonId.isEmpty()) {
            return ResponseEntity.badRequest().body("Đơn không tồn tại");
        }
        getDonId.get().setTrangThaiDon(trangThai);
        getDonId.get().setNgayXuLy(LocalDate.now());
        getDonId.get().setNguoiXuLy(nguoiXuLy);
        donDangKyRepo.save(getDonId.get());
        if(getDonId.get().getTrangThaiDon() == 0){
            phatTuDaoTrang.setPhatTu(getDonId.get().getPhatTuId());
            phatTuDaoTrang.setDaoTrang(getDonId.get().getDaoTrangId());
            phatTuDaoTrang.setLyDoKhongThamGia(lyDoKhongThamGia);
            phatTuDaoTrang.setDaThamGia(0);
            phatTuDaoTrangRepo.save(phatTuDaoTrang);
        }else {
            phatTuDaoTrang.setPhatTu(getDonId.get().getPhatTuId());
            phatTuDaoTrang.setDaoTrang(getDonId.get().getDaoTrangId());
            phatTuDaoTrang.setLyDoKhongThamGia("không");
            phatTuDaoTrang.setDaThamGia(1);
            phatTuDaoTrangRepo.save(phatTuDaoTrang);
        }
        return ResponseEntity.of(getDonId);
    }

}
