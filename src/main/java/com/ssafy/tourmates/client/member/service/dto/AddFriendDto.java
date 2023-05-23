package com.ssafy.tourmates.client.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddFriendDto {

    private String targetLoginId;

    @Builder
    public AddFriendDto(String targetLoginId){
        this.targetLoginId = targetLoginId;
    }
}
