package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.admin.controller.dto.admin.LoginAdmin;
import com.ssafy.tourmates.admin.controller.dto.notice.form.AddNoticeForm;
import com.ssafy.tourmates.admin.controller.dto.notice.response.AdminNoticeResponse;
import com.ssafy.tourmates.admin.notice.Notice;
import com.ssafy.tourmates.admin.notice.repository.NoticeRepository;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
import com.ssafy.tourmates.admin.notice.service.NoticeService;
import com.ssafy.tourmates.admin.notice.service.dto.AddNoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/intranet/notices")
public class NoticeController {

    // TODO: 2023/05/16 세부 수정
    //관리자 권한: 수정, 삭제, 조회, 복사
    private final NoticeService noticeService;
    private final NoticeQueryService noticeQueryService;

    @GetMapping
    public String noticeList(
            @ModelAttribute("addForm") AddNoticeForm form,
            Model model) {
        List<AdminNoticeResponse> notices = noticeQueryService.searchAdminNotices();
        model.addAttribute("notices", notices);
        return "noticeList";
    }

    @PostMapping("/register")
    public String registerNotice(
            @ModelAttribute AddNoticeForm form,
            @SessionAttribute(name = "loginAdmin") LoginAdmin loginAdmin
            ) {
        AddNoticeDto dto = AddNoticeDto.builder()
                .pin(form.getPin() == null ? "0" : "1")
                .title(form.getTitle())
                .content(form.getContent())
                .build();

        Long noticeId = noticeService.registerNotice(loginAdmin.getLoginId(), dto);
        log.debug("noticeId={}", noticeId);
        return "redirect:/intranet/notices";
    }
}
