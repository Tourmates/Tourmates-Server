package com.ssafy.tourmates.admin.admin.service.impl;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.admin.admin.repository.AdminRepository;
import com.ssafy.tourmates.admin.admin.service.AdminService;
import com.ssafy.tourmates.admin.admin.service.dto.AddAdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Long addAdmin(AddAdminDto dto) {
        Admin admin = Admin.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getName())
                .email(dto.getEmail())
                .tel(dto.getTel())
                .build();
        Admin savedAdmin = adminRepository.save(admin);
        return savedAdmin.getId();
    }
}
