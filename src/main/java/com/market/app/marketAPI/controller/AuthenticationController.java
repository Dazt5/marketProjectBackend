package com.market.app.marketAPI.controller;

import com.market.app.marketAPI.dto.LoginRequestDTO;
import com.market.app.marketAPI.dto.LoginResponseDTO;
import com.market.app.marketAPI.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
         return authService.login(loginRequest);
    }
}
