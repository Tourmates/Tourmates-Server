package com.ssafy.tourmates.hotplace.service.dto;

import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.common.domain.UploadFile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AddHotPlaceDto {

    private Integer contentId;
    private ContentType tag;
    private String title;
    private String content;
    private String visitedDate;
    private List<UploadFile> uploadFiles;

    @Builder
    public AddHotPlaceDto(Integer contentId, ContentType tag, String title, String content, String visitedDate, List<UploadFile> uploadFiles) {
        this.contentId = contentId;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.visitedDate = visitedDate;
        this.uploadFiles = uploadFiles;
    }
}
