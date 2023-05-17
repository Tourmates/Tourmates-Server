package com.ssafy.tourmates.client.api.dto.board.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddBoardRequest {

    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String content;
}
