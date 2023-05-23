package com.ssafy.tourmates.client.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddFriendDto {

    private Long targetId;

    @Builder
    public AddFriendDto(Long targetId){
        this.targetId = targetId;
    }
}
