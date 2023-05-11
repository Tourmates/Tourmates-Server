package com.ssafy.tourmates.client.controller.dto.board.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditBoardRequest {

    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String content;
}
