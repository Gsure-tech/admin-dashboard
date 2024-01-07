package com.gsuretech.admindashboard.dto;

import com.gsuretech.admindashboard.entity.Role;
import com.gsuretech.admindashboard.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto {
    private Long id;
    private String firstname;
    private String lastName;
    private String email;
    private String role;
    private String team;
}
