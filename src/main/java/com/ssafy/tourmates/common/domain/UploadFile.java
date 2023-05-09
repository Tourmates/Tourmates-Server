package com.ssafy.tourmates.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    @Builder
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}