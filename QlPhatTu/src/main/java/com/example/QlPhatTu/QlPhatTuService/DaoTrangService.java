package com.example.QlPhatTu.QlPhatTuService;

import com.example.QlPhatTu.QlPhatTuService.InterfaceService.IdaoTrangService;
import com.example.QlPhatTu.Repository.DaoTrangRepo;
import com.example.QlPhatTu.Repository.PhatTuRepo;
import com.example.QlPhatTu.model.Entity.DaoTrang;
import com.example.QlPhatTu.model.Entity.PhatTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DaoTrangService implements IdaoTrangService {

    @Autowired
    private PhatTuRepo phatTuRepo;
    @Autowired
    private DaoTrangRepo daoTrangRepo;
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
//    private KieuThanhVienRepo kieuThanhVienRepo;

    //Thêm event đạo trang
    @Override
    public ResponseEntity<?> createDaoTrang(String noiToChuc, LocalDate thoiGianToChuc, String noidung, int daKetThuc, int idtruTri, int idDaoTrang){
        if(noiToChuc != null || thoiGianToChuc != null || noidung != null && daKetThuc > 0 && idtruTri > 0 && idDaoTrang > 0){
            Optional<PhatTu> opPhatTu = phatTuRepo.findById(idtruTri);
            if(opPhatTu.isEmpty()){
                return ResponseEntity.ok("Tru Tri không tồn tại");
            }
            DaoTrang daoTrang = new DaoTrang();
            daoTrang.setNoiToChuc(noiToChuc);
            daoTrang.setThoiGianToChuc(thoiGianToChuc);
            daoTrang.setNoiDung(noidung);
            daoTrang.setPhatTu(opPhatTu.get());
            daoTrang.setDaKetThuc(daKetThuc);
            daoTrangRepo.save(daoTrang);
            return ResponseEntity.ok("Tạo buổi đạo trang thanh công");
        }
        return ResponseEntity.ok("Tru tri , dao trang và đã kết thúc chưa đúng");
    }
    //cập nhập buổi đạo trang
    @Override
    public ResponseEntity<?> updateDaoTrang(int daoTrangId){
        Optional<DaoTrang> daoTrang = daoTrangRepo.findById(daoTrangId);
        if(daoTrang.isEmpty()){
            return ResponseEntity.ok("Buổi đạo trang đã kết thúc hoặc không tồn tại");
        }
        daoTrang.get().setSoThanhVienThamGia(phatTuRepo.countnumberThamGia(daoTrangId));
        daoTrangRepo.save(daoTrang.get());
        return ResponseEntity.of(daoTrang);
    }

    //sua thông tin buổi đạo trang
    @Override
    public ResponseEntity<?> editDaoTrang(DaoTrang daoTrang){
        if(daoTrangRepo.existsById(daoTrang.getId())){
            daoTrangRepo.save(daoTrang);
            return ResponseEntity.ok("Thay đổi thành công");
        }
        return ResponseEntity.badRequest().body("Thông tin đạo trang không tồn tại");
    }


    //tìm đạo trang
    @Override
    public Page<DaoTrang> timDaoTrang(String daoTrangId, String noiToChuc, String daKetThuc, String thoiGian, int numberPage, int sizePage){
        PageRequest pageRequest = PageRequest.of(numberPage,sizePage);
        if(daoTrangId ==null && noiToChuc == null && daKetThuc == null && thoiGian == null){
            return new PageImpl<>(daoTrangRepo.findAll(),pageRequest,daoTrangRepo.findAll().size());
        }
        List<DaoTrang> daoTrangList =  daoTrangRepo.findDaoTrang(daoTrangId,noiToChuc, thoiGian,daKetThuc);
        return new PageImpl<>(daoTrangList,pageRequest,daoTrangList.size());
    }
    //xoa dạo trang
    @Override
    public ResponseEntity<?> xoaDaoTrang(int id) {
        Optional<DaoTrang> daoTrangOptional = daoTrangRepo.findById(id);

        if (daoTrangOptional.isPresent()) {
            DaoTrang daoTrang = daoTrangOptional.get();
            daoTrang.setPhatTu(null);
            daoTrangRepo.save(daoTrang);
            daoTrangRepo.deleteById(id);
            return ResponseEntity.ok("Xoá thành công");
        } else {
            return ResponseEntity.badRequest().body("Thông tin không tồn tại");
        }
    }
}
