package com.gsuretech.admindashboard.mapping;


import com.gsuretech.admindashboard.dto.AdminDto;
import com.gsuretech.admindashboard.entity.UserCredential;
import org.springframework.stereotype.Component;

@Component
public class CustomMapper {

    public AdminDto mapUserCredentialToAdminDto(UserCredential userCredential){
        return  AdminDto.builder()
                .id(userCredential.getId())
                .firstname(userCredential.getFirstName())
                .lastName(userCredential.getLastName())
                .email(userCredential.getEmail())
                .team(userCredential.getTeam().toString())
                .role(userCredential.getRole().toString())
                .build();
    }
}

