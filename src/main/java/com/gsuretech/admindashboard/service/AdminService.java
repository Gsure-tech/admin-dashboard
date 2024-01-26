package com.gsuretech.admindashboard.service;


import com.gsuretech.admindashboard.dto.AdminDto;
import com.gsuretech.admindashboard.dto.CustomResponse;
import com.gsuretech.admindashboard.dto.EmailDetails;
import com.gsuretech.admindashboard.dto.InviteLinkRequest;
import com.gsuretech.admindashboard.entity.Role;
import com.gsuretech.admindashboard.entity.Team;
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

    public ResponseEntity<CustomResponse> sendInviteLinks(InviteLinkRequest request) {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Logged in user email: {}", loggedInUser);
        Optional<UserCredential> userCredentialExist = userCredentialRepository.findByEmail(request.getEmail());
        if (userCredentialExist.isPresent() && userCredentialExist.get().getInvitationLinkExpiry() != null) {
            if (userCredentialExist.get().getInvitationLinkExpiry().isBefore(LocalDateTime.now())) {
                userCredentialExist.get().setInvitationLinkExpiry(LocalDateTime.now().plusHours(24));
                userCredentialRepository.save(userCredentialExist.get());
                String encryptedEmail = passwordEncoder.encode(request.getEmail());
                String invitationLink = "https:://sandbox.zeliafinance.com/?email=" + encryptedEmail;

                EmailDetails adminInvite = EmailDetails.builder()
                        .subject("ADMIN INVITE")
                        .recipient(userCredentialExist.get().getEmail())
                        .messageBody("You have been invited to join your team on " +
                                "QuickPay admin dashboard. Click the link below to join")
                        .build();
                emailService.sendEmailAlert(adminInvite);

                return ResponseEntity.ok().body(CustomResponse
                        .builder()
                                .responseCode("200")
                                .responseMessage("Invitation Link has been sent successfully")
                        .build());
            }
            if(userCredentialExist.get().getInvitationLinkExpiry().isAfter(LocalDateTime.now())){
                return ResponseEntity.badRequest().body(CustomResponse.builder()
                                .responseCode("400")
                                .responseMessage("The user has a pending acceptance of a previous invitation link")
                        .build());
            }
            if(userCredentialExist.get().isInviteAccepted()){
                return ResponseEntity.badRequest().body(CustomResponse.builder()
                                .responseCode("400")
                                .responseMessage("This user has already accepted an invitation link")
                        .build());
            }
            if(request.getRole().equalsIgnoreCase("ROLE_USER")){
                return ResponseEntity.badRequest().body(CustomResponse.builder()
                                .responseCode("400")
                                .responseMessage("User role cannot be applied here")
                        .build());

            }
            UserCredential userCredential = UserCredential.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .team(Team.valueOf(request.getTeam()))
                    .role(Role.valueOf(request.getRole()))
                    .createdBy(loggedInUser)
                    .modifiedBy(loggedInUser)
                    .inviteAccepted(false)
                    .invitationLinkExpiry(LocalDateTime.now().plusHours(24))
                    .build();

            UserCredential savedAdminCredential = userCredentialRepository.save(userCredential);
            log.info("Saved user: {}", modelMapper.map(savedAdminCredential, AdminDto.class));
            String encryptedEmail = passwordEncoder.encode(savedAdminCredential.getEmail());
            String invitationLink = "http://wwww.localhost:5511/?email=" + encryptedEmail;

            EmailDetails adminInvite = EmailDetails.builder()
                    .subject("ADMIN INVITE")
                    .recipient(userCredentialExist.get().getEmail())
                    .messageBody("You have been invited to join your team on " +
                            "QuickPay admin dashboard. Click the link below to join")
                    .build();
            emailService.sendEmailAlert(adminInvite);
            return ResponseEntity.ok(CustomResponse.builder()
                        .responseCode("200")
                        .responseMessage("SUCCESS")
                .build());
        }

    }
}