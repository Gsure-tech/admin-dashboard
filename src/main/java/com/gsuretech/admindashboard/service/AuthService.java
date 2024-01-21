package com.gsuretech.admindashboard.service;


import com.gsuretech.admindashboard.config.JwtAuthenticationProvider;
import com.gsuretech.admindashboard.dto.AdminDto;
import com.gsuretech.admindashboard.dto.EmailDetails;
import com.gsuretech.admindashboard.dto.LoginDto;
import com.gsuretech.admindashboard.dto.ResponseDto;
import com.gsuretech.admindashboard.entity.UserCredential;
import com.gsuretech.admindashboard.mapping.CustomMapper;
import com.gsuretech.admindashboard.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialRepository userCredentialRepository;
    private final CustomMapper customMapper;
    private final EmailService emailService;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    public ResponseDto login(LoginDto loginDto){
        UserCredential userCredential = userCredentialRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        String token = jwtAuthenticationProvider.generateToken(authentication);
        AdminDto adminDetails = customMapper.mapUserCredentialToAdminDto(userCredential);
        EmailDetails loginAlert = EmailDetails.builder()
                .subject("You are Logged in!")
                .recipient(loginDto.getEmail())
                .messageBody("You logged into your account")
                .build();

        emailService.sendEmailAlert(loginAlert);

        return ResponseDto.builder()
                .adminDto(adminDetails)
                .token(token)
                .build();
    };
}
