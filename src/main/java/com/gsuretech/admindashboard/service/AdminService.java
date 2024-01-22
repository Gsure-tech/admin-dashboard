package com.gsuretech.admindashboard.service;


import com.gsuretech.admindashboard.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AdminService {

    private UserCredentialRepository userCredentialRepository;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public ResponseEntity<CustomResponse> sendInviteLinks(InviteLinkRquest request){

    }
}
