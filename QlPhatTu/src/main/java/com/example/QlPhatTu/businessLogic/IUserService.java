package com.example.QlPhatTu.businessLogic;


import com.example.QlPhatTu.dto.LoginDto;
import com.example.QlPhatTu.dto.RegisterDto;
import com.example.QlPhatTu.model.Entity.Role;
import com.example.QlPhatTu.model.Entity.User;
import org.springframework.http.ResponseEntity;





public interface IUserService {
   //ResponseEntity<?> register (RegisterDto registerDto);
   //  ResponseEntity<BearerToken> authenticate(LoginDto loginDto);

   ResponseEntity<?> authenticate(LoginDto loginDto);
   ResponseEntity<?> register (RegisterDto registerDto);
   Role saveRole(Role role);

   User saverUser (User user) ;
}
