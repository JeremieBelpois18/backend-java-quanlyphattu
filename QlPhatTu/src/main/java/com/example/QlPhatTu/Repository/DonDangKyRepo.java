package com.example.QlPhatTu.Repository;

import com.example.QlPhatTu.model.Entity.DonDangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonDangKyRepo extends JpaRepository<DonDangKy,Integer> {


}
