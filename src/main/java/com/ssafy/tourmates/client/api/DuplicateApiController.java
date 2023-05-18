package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.member.request.CheckEmailRequest;
import com.ssafy.tourmates.client.api.dto.member.request.CheckLoginIdRequest;
import com.ssafy.tourmates.client.api.dto.member.request.CheckNicknameRequest;
import com.ssafy.tourmates.client.member.service.DuplicateCheckService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/check")
@Api(tags = {"중복검사"})
public class DuplicateApiController {

    private final DuplicateCheckService duplicateCheckService;

    @PostMapping("/loginId")
    public Boolean checkLoginId(@RequestBody CheckLoginIdRequest request) {
        return duplicateCheckService.existLoginId(request.getLoginId());
    }

    @PostMapping("/email")
    public Boolean checkEmail(@RequestBody CheckEmailRequest request) {
        return duplicateCheckService.existEmail(request.getEmail());
    }

    @PostMapping("/tel")
    public Boolean checkTel() {
        return null;
    }

    @PostMapping("/nickname")
    public Boolean checkNickname(@RequestBody CheckNicknameRequest request) {
        return duplicateCheckService.existNickname(request.getNickname());
    }
}
