package com.example.QlPhatTu.persistence;


import com.example.QlPhatTu.model.Entity.Role;
import com.example.QlPhatTu.model.Entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleName(RoleName roleName);


}
