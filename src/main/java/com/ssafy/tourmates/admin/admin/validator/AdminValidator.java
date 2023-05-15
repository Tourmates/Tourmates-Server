package com.ssafy.tourmates.admin.admin.validator;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.admin.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class AdminValidator {

    private final AdminRepository adminRepository;

    public Admin findByLoginId(String loginId) {
        return adminRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);
    }
}
