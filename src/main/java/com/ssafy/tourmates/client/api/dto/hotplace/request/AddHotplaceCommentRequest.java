package com.ssafy.tourmates.client.api.dto.hotplace.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddHotplaceCommentRequest {

    @NotBlank
    private String comment;
}
