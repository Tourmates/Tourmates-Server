package com.ssafy.tourmates.client.api.dto.member.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddFriendRequest {

    @NotBlank
    private String targetLoginId;
}
