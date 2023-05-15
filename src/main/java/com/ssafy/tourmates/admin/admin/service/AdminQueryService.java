package com.ssafy.tourmates.admin.admin.service;

import com.ssafy.tourmates.admin.controller.dto.admin.LoginAdmin;

public interface AdminQueryService {

    LoginAdmin loginAdmin(String loginId, String loginPw);
}
