package com.gsuretech.admindashboard.dto;


import com.gsuretech.admindashboard.entity.Role;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteLinkRequest {
    private String email;
    private String password;
    private Role role;
}
