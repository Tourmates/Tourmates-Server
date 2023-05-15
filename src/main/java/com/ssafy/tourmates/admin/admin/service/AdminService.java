package com.ssafy.tourmates.admin.admin.service;

import com.ssafy.tourmates.admin.admin.service.dto.AddAdminDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminService {

    Long addAdmin(AddAdminDto dto);
}
