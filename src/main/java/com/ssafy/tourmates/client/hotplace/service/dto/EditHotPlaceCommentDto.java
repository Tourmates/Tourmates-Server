package com.ssafy.tourmates.client.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditHotPlaceCommentDto {

    private String content;

    @Builder
    public EditHotPlaceCommentDto(String content){
        this.content = content;
    }
}
