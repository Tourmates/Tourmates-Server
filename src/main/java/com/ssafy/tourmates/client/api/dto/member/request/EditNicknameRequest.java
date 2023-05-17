package com.ssafy.tourmates.client.api.dto.member.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EditNicknameRequest {

    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]*$")
    private String newNickname;

}
