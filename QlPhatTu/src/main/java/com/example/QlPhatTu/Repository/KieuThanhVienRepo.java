package com.example.QlPhatTu.Repository;

import com.example.QlPhatTu.model.Entity.KieuThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuThanhVienRepo extends JpaRepository<KieuThanhVien,Integer> {
}
