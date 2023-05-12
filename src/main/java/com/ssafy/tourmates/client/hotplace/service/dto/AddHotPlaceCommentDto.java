package com.ssafy.tourmates.client.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddHotPlaceCommentDto {

    private String content;

    @Builder
    public AddHotPlaceCommentDto(String content){
        this.content = content;
    }
}
