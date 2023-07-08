package com.example.QlPhatTu.QlPhatTuService;

import com.example.QlPhatTu.QlPhatTuService.InterfaceService.IkieuThanhVienService;
import com.example.QlPhatTu.Repository.KieuThanhVienRepo;
import com.example.QlPhatTu.model.Entity.KieuThanhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KieuThanhVienService implements IkieuThanhVienService {

//    @Autowired
//    private PhatTuRepo phatTuRepo;
//    @Autowired
//    private DonDangKyRepo donDangKyRepo;
//
//    @Autowired
//    private PhatTuDaoTrangRepo phatTuDaoTrangRepo;
//
//    @Autowired
//    private ChuaRepo chuaRepo;
//
//    @Autowired
//    private DaoTrangRepo daoTrangRepo;
    @Autowired
    private KieuThanhVienRepo kieuThanhVienRepo;

    //tạo chức danh thành viên
    @Override
    public ResponseEntity<?> createKieuThanhVien(KieuThanhVien kieuThanhVien){
        kieuThanhVienRepo.save(kieuThanhVien);
        return ResponseEntity.ok("tạo thành công");
    }
}
