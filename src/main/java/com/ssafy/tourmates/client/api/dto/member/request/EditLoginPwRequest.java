package com.ssafy.tourmates.client.api.dto.member.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EditLoginPwRequest {

    @NotBlank
    private String currentLoginPw;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String newLoginPw;
    @NotBlank
    private String checkedNewLoginPw;
}
