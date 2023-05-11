package com.ssafy.tourmates.client.controller.dto.member.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditEmailRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String newEmail;

}
