package com.ssafy.tourmates.admin.admin.service;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.admin.admin.repository.AdminRepository;
import com.ssafy.tourmates.admin.admin.service.dto.AddAdminDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminServiceTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;

    @Test
    @DisplayName("관리자 등록")
    void addAdmin() {
        //given
        AddAdminDto dto = AddAdminDto.builder()
                .loginId("admin")
                .loginPw("admin1234@")
                .name("김싸피")
                .email("admin@ssafy.com")
                .tel("010-9999-9999")
                .build();

        //when
        Long adminId = adminService.addAdmin(dto);

        //then
        Optional<Admin> findAdmin = adminRepository.findById(adminId);
        assertThat(findAdmin).isPresent();
    }
}