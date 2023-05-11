package com.ssafy.tourmates.client.hotplace;

import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import com.ssafy.tourmates.common.domain.UploadFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HotPlaceImage extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_image_id")
    private Long id;
    @Embedded
    private UploadFile uploadFile;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @Builder
    public HotPlaceImage(Long id, UploadFile uploadFile, HotPlace hotPlace) {
        this.id = id;
        this.uploadFile = uploadFile;
        this.hotPlace = hotPlace;
    }
}
