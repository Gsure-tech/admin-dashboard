package com.gsuretech.admindashboard.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_user")
public class UserCredential implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
