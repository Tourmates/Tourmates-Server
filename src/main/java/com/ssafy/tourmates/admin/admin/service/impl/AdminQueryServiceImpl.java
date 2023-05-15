package com.ssafy.tourmates.admin.admin.service.impl;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.admin.admin.repository.AdminRepository;
import com.ssafy.tourmates.admin.admin.service.AdminQueryService;
import com.ssafy.tourmates.admin.controller.dto.admin.LoginAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AdminQueryServiceImpl implements AdminQueryService {

    private final AdminRepository adminRepository;

    @Override
    public LoginAdmin loginAdmin(String loginId, String loginPw) {
        Admin findLoginAdmin = adminRepository.findByLoginIdAndLoginPw(loginId, loginPw)
                .orElseThrow(NoSuchElementException::new);
        return LoginAdmin.builder()
                .loginId(findLoginAdmin.getLoginId())
                .name(findLoginAdmin.getName())
                .build();
    }
}
