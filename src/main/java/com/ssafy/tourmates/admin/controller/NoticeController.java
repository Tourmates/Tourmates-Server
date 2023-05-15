package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.admin.notice.Notice;
import com.ssafy.tourmates.admin.notice.repository.NoticeRepository;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/intranet/notices")
public class NoticeController {

    // TODO: 2023/05/16 세부 수정
    //관리자 권한: 등록, 수정, 삭제, 조회, 복사
    private final NoticeRepository noticeRepository;
    private final NoticeQueryService noticeQueryService;

    @GetMapping
    public String noticeList(Model model) {
        List<Notice> notices = noticeRepository.findAll();
        model.addAttribute("notices", notices);
        return "noticeList";
    }
}
