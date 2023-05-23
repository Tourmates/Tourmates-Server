package com.ssafy.tourmates.client.friend.service;

import com.ssafy.tourmates.client.member.service.dto.AddFriendDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FriendService {

    Long registerFriend(String loginId, AddFriendDto dto);
}
