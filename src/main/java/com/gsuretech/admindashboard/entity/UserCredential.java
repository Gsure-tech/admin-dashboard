package com.gsuretech.admindashboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_user")
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private LocalDate dateOBirth;
    private String email;
    private String password;
    private String phoneNumber;
    private String mobileNumber;
    private String whatsAppNumber;
    private String gender;
    private String bvn;
    private String bvnVerifyStatus;
    private String nin;
    private String ninStatus;
    private String pin;
    @Column(unique = true)
    private String walletId;
    private String vAccountNumber;
    private String customerRef;
    private String accountStatus;
    private double accountBalance;
    private double availableBalance;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String passwordResetToken;
    private LocalDate tokenExpiryDate;
    private String emailVerifyStatus;
    private String deviceIp;
    private String liveLocation;
    private String modifiedBy;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Team team;
    private String referralCode;
    private String referredBy;
    private String hashedPassword;
    private String otp;
    private String referenceId;
    private LocalDateTime otpExpiryDate;
    private String maritalStatus;
    private Integer profileSetupLevel;
    private String address;
    private String securityQuestion;
    private String securityAnswer;
    private String imagePath;
    private String imageFileName;
    private int failedPinAttempts;
    private LocalDateTime lockoutTimeStamp;
    private String createdBy;
    private LocalDateTime invitationLinkExpiry;
    private boolean inviteAccepted;
    private LocalDateTime lastLoggedIn;
    @Column(name = "password_error_count")
    private int passwordErrorCount;
}
