package com.ssafy.tourmates.client.controller.dto.member.request;

import com.ssafy.tourmates.client.member.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class JoinMemberRequest {

    @NotBlank
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String loginId;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String loginPw;
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[가-힣]*$")
    private String name;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(min = 13, max = 13)
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String tel;
    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{4}.\\d{2}.\\d{2}$")
    private String birth;
    @NotBlank
    private Gender gender;
    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]*$")
    private String nickname;
}
