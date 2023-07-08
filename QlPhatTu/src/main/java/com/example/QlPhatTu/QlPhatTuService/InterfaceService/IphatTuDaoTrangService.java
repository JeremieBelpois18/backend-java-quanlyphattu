package com.example.QlPhatTu.QlPhatTuService.InterfaceService;

import com.example.QlPhatTu.model.Entity.PhatTu;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IphatTuDaoTrangService {
    //tạo phat tu dao trang
    ResponseEntity<?> createPhatTuDaoTrang(int phatTuId, int daoTrangId, String lyDoKhongThamGia);

    //xoa phattudaotrang
    ResponseEntity<?> deletePhatTuDaoTrang(int phatTuDaoTrangId);

    // in những phật tử đã tham gia đạo trang
    Page<PhatTu> inPhatTuDaoTrang(int numbberPage, int sizePage);
}
