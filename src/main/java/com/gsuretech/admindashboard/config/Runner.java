package com.gsuretech.admindashboard.config;

import com.gsuretech.admindashboard.mapping.CustomMapper;
import com.gsuretech.admindashboard.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class Runner implements CommandLineRunner {

    private UserCredentialRepository userCredentialRepository;
    private CustomMapper customMapper;
    private PasswordEncoder passwordEncoder;

 /**
  * add password validation
  * lowercase, uppercase, numbers, symbols
  * passwordD12@
  * **/

    @Override
    public void run(String... args) throws Exception {
//        UserCredential superAdmin = UserCredential.builder()
//                .firstName("Abdul")
//                .lastName("Abubakar")
//                .email("geeee9023@gmail.com")
//                .password(passwordEncoder.encode("1234"))
//                .role(Role.ROLE_SUPER_ADMIN)
//                .team((Team.ENGINEER))
//                .build();
//
//        log.info("Creating super admin: {}",customMapper.mapUserCredentialToAdminDto(superAdmin));
//        userCredentialRepository.save(superAdmin);

    }
}
