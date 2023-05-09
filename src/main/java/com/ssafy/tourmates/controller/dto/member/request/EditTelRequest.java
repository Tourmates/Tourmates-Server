package com.ssafy.tourmates.controller.dto.member.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EditTelRequest {

    @NotBlank
    @Size(min = 13, max = 13)
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String newTel;
}
