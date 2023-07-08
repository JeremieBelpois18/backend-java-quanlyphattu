package com.example.QlPhatTu.Controller;


import com.example.QlPhatTu.QlPhatTuService.*;
import com.example.QlPhatTu.businessLogic.IUserService;
import com.example.QlPhatTu.dto.LoginDto;
import com.example.QlPhatTu.dto.RegisterDto;
import com.example.QlPhatTu.model.Entity.Chua;
import com.example.QlPhatTu.model.Entity.DaoTrang;
import com.example.QlPhatTu.model.Entity.PhatTu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {



    private final IUserService iUserService ;

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

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto)
    { return  iUserService.register(registerDto);}

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto)
    { return  iUserService.authenticate(loginDto);}


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


    @PostMapping("dondangky")
    public ResponseEntity<String> createDonDangKy(String ten,String phapDanh,String sdt,String email,int daoTrangId,int nguoiSuLy){
        return donDangKyService.createDonDangKy(ten, phapDanh, sdt, email,daoTrangId);
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
