package com.gsuretech.admindashboard.controller;

import com.gsuretech.admindashboard.dto.LoginDto;
import com.gsuretech.admindashboard.dto.ResponseDto;
import com.gsuretech.admindashboard.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor

public class AuthController {
    private final AuthService authService;


    @PostMapping("login")
    public ResponseDto login(@RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }
}
