package com.ssafy.tourmates.client.api.dto.hotplace.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditHotPlaceCommentRequest {

    @NotBlank
    private String content;
}
