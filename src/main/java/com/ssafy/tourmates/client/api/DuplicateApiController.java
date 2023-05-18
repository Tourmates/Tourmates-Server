package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.member.service.DuplicateCheckService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Boolean checkLoginId() {
        return null;
    }

    @PostMapping("/email")
    public Boolean checkEmail() {
        return null;
    }

    @PostMapping("/tel")
    public Boolean checkTel() {
        return null;
    }

    @PostMapping("/nickname")
    public Boolean checkNickname() {
        return null;
    }
}
