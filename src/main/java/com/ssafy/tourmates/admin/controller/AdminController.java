package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.admin.admin.service.AdminQueryService;
import com.ssafy.tourmates.admin.controller.dto.admin.LoginAdmin;
import com.ssafy.tourmates.admin.controller.dto.admin.form.LoginAdminForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/intranet")
public class AdminController {

    private final AdminQueryService adminQueryService;

    @GetMapping
    public String loginAdmin(@ModelAttribute(name = "form") LoginAdminForm form) {
        return "adminLogin";
    }

    @PostMapping
    public String loginAdmin(@ModelAttribute LoginAdminForm form, HttpSession session) {
        LoginAdmin loginAdmin = adminQueryService.loginAdmin(form.getLoginId(), form.getLoginPw());
        log.debug("loginAdmin={}", loginAdmin);
        session.setAttribute("loginAdmin", loginAdmin);
        return "dashboard";
    }

}
