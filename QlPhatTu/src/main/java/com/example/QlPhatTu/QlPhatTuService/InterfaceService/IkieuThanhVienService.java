package com.example.QlPhatTu.QlPhatTuService.InterfaceService;

import com.example.QlPhatTu.model.Entity.KieuThanhVien;
import org.springframework.http.ResponseEntity;

public interface IkieuThanhVienService {
    //tạo chức danh thành viên
    ResponseEntity<?> createKieuThanhVien(KieuThanhVien kieuThanhVien);
}
