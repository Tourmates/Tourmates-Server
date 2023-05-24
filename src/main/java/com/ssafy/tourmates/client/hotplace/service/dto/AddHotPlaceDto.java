package com.ssafy.tourmates.client.hotplace.service.dto;

import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.common.domain.UploadFile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AddHotPlaceDto {

    private Integer contentId;
    private List<String> tagNames;
    private String title;
    private String content;
    private String visitedDate;
    private List<UploadFile> uploadFiles;

    @Builder
    public AddHotPlaceDto(Integer contentId, List<String> tagNames, String title, String content, String visitedDate, List<UploadFile> uploadFiles) {
        this.contentId = contentId;
        this.tagNames = tagNames;
        this.title = title;
        this.content = content;
        this.visitedDate = visitedDate;
        this.uploadFiles = uploadFiles;
    }
}
