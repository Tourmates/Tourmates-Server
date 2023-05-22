package com.ssafy.tourmates.client.api.dto.hotplace.request;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class EditHotPlaceRequest {

    @NotNull
    private ContentType tag;
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    @Size(min = 10, max = 10)
    private String visitedDate;
//    @NotNull
    private List<MultipartFile> files;
}
