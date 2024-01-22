package com.gsuretech.admindashboard.service;


import com.gsuretech.admindashboard.dto.AdminDto;
import com.gsuretech.admindashboard.dto.InviteLinkRequest;
import com.gsuretech.admindashboard.entity.UserCredential;
import com.gsuretech.admindashboard.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AdminService {

    private UserCredentialRepository userCredentialRepository;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public ResponseEntity<AdminDto> sendInviteLinks(InviteLinkRequest request) {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Logged in user email: {}", loggedInUser);
        Optional<UserCredential> userCredentialExist = userCredentialRepository.findByEmail(request.getEmail());
        if (userCredentialExist.isPresent() && userCredentialExist.get().getInvitationLinkExpiry() != null) {
            if (userCredentialExist.get().getInvitationLinkExpiry().isBefore(LocalDateTime.now())) {
                userCredentialExist.get().setInvitationLinkExpiry(LocalDateTime.now().plusHours(24));
                userCredentialRepository.save(userCredentialExist.get());
                String encryptedEmail = passwordEncoder.encode(request.getEmail());
                String invitationLink = "https:://sandbox.zeliafinance.com/?email=" + encryptedEmail;
            }

        }
    }
