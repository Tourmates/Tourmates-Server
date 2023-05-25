package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.member.request.AddFriendRequest;
import com.ssafy.tourmates.client.friend.service.FriendService;
import com.ssafy.tourmates.client.member.service.dto.AddFriendDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/friends")
@Api(tags = {"친구"})
public class FriendController {

    private final FriendService friendService;

    @ApiOperation(value = "친구 등록")
    @PostMapping("/register")
    public Long registerFriend(@Valid @RequestBody AddFriendRequest request){

        String loginId = SecurityUtil.getCurrentLoginId();

        AddFriendDto dto = AddFriendDto.builder()
                .targetLoginId(request.getTargetLoginId())
                .build();

        Long savedFriendId = friendService.registerFriend(loginId, dto);

        return savedFriendId;
    }
}
