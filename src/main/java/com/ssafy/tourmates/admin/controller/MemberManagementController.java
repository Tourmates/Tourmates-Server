package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.admin.controller.dto.member.response.MemberResponse;
import com.ssafy.tourmates.client.member.service.MemberQueryService;
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
@RequestMapping("/intranet/members")
public class MemberManagementController {

    private final MemberQueryService memberQueryService;

    @GetMapping
    public String memberList(Model model) {
        List<MemberResponse> members = memberQueryService.searchMembers();
        model.addAttribute("members", members);
        return "member";
    }
}
