package com.ssafy.tourmates.client.friend.service.impl;

import com.ssafy.tourmates.client.friend.Friend;
import com.ssafy.tourmates.client.friend.repository.FriendRepository;
import com.ssafy.tourmates.client.friend.service.FriendService;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.service.dto.AddFriendDto;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final MemberValidator memberValidator;
    private final FriendRepository friendRepository;

    @Override
    public Long registerFriend(String loginId, AddFriendDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);

        Friend friend = Friend.builder()
                .memberId(findMember.getId())
                .targetId(dto.getTargetId())
                .build();

        Friend savedFriend = friendRepository.save(friend);
        return savedFriend.getId();
    }
}
