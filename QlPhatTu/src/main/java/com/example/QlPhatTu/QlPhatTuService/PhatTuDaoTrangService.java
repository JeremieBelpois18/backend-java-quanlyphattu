package com.example.QlPhatTu.QlPhatTuService;

import com.example.QlPhatTu.QlPhatTuService.InterfaceService.IphatTuDaoTrangService;
import com.example.QlPhatTu.Repository.DaoTrangRepo;
import com.example.QlPhatTu.Repository.DonDangKyRepo;
import com.example.QlPhatTu.Repository.PhatTuDaoTrangRepo;
import com.example.QlPhatTu.Repository.PhatTuRepo;
import com.example.QlPhatTu.model.Entity.DaoTrang;
import com.example.QlPhatTu.model.Entity.PhatTu;
import com.example.QlPhatTu.model.Entity.PhatTuDaoTrang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhatTuDaoTrangService implements IphatTuDaoTrangService {
    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private DonDangKyRepo donDangKyRepo;

    @Autowired
    private PhatTuDaoTrangRepo phatTuDaoTrangRepo;

    @Autowired
    private DaoTrangRepo daoTrangRepo;


    //tạo phat tu dao trang
    @Override
    public ResponseEntity<?> createPhatTuDaoTrang(int phatTuId, int daoTrangId, String lyDoKhongThamGia){
        Optional<DaoTrang> opDaoTrang = daoTrangRepo.findById(daoTrangId);
        Optional<PhatTu> opPhatTu = phatTuRepo.findById(phatTuId);
        PhatTuDaoTrang phatTuDaoTrang = new PhatTuDaoTrang();
        if(opPhatTu.isPresent() && opDaoTrang.isPresent() ){
            if(phatTuRepo.findTrangThaiDon(opPhatTu.get().getId()) == 1 && lyDoKhongThamGia == null) {
                phatTuDaoTrang.setPhatTu(opPhatTu.get());
                phatTuDaoTrang.setDaoTrang(opDaoTrang.get());
                phatTuDaoTrang.setLyDoKhongThamGia("0");
                phatTuDaoTrang.setDaThamGia(1);
                phatTuDaoTrangRepo.save(phatTuDaoTrang);
                return ResponseEntity.ok("thêm thành công");
            }
            phatTuDaoTrang.setPhatTu(opPhatTu.get());
            phatTuDaoTrang.setDaoTrang(opDaoTrang.get());
            phatTuDaoTrang.setLyDoKhongThamGia(lyDoKhongThamGia);
            phatTuDaoTrang.setDaThamGia(0);
            phatTuDaoTrangRepo.save(phatTuDaoTrang);
            return ResponseEntity.ok("thêm thành công");

        }
        return ResponseEntity.ok("Phật tu hoặc thông tin đạo trang không tồn tại");
    }

    //xoa phattudaotrang
    @Override
    public ResponseEntity<?> deletePhatTuDaoTrang(int phatTuDaoTrangId){
        if(phatTuDaoTrangRepo.existsById(phatTuDaoTrangId)){
            phatTuDaoTrangRepo.deleteById(phatTuDaoTrangId);
        }
        return ResponseEntity.ok("Phat tu dao trang không tồn tại");
    }




    // in những phật tử đã tham gia đạo trang
    @Override
    public Page<PhatTu> inPhatTuDaoTrang(int numbberPage, int sizePage) {
        List<PhatTu> phatTuList = phatTuRepo.findPhatTuDaoTrang();
        PageRequest pageable = PageRequest.of(numbberPage,sizePage);
        return new PageImpl<>(phatTuList,pageable,phatTuList.size());
    }
}
