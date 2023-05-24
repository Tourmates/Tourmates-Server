package com.ssafy.tourmates.client.hotplace;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.client.hashtag.Hashtag;
import com.ssafy.tourmates.client.hashtag.HotPlaceHashtag;
import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import com.ssafy.tourmates.common.domain.UploadFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.client.member.Active.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HotPlace extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int hit;
    @Column(nullable = false, length = 10)
    private String visitedDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceImage> images = new ArrayList<>();
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceComment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceLike> likes = new ArrayList<>();
    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceHashtag> hashtags = new ArrayList<>();

    @Builder
    public HotPlace(Long id, String title, String content, int hit, String visitedDate, Active active, Member member, AttractionInfo attractionInfo, List<HotPlaceImage> images, List<HotPlaceComment> comments, List<HotPlaceLike> likes, List<HotPlaceHashtag> hashtags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.active = active;
        this.member = member;
        this.attractionInfo = attractionInfo;
        this.images = images;
        this.comments = comments;
        this.likes = likes;
        this.hashtags = hashtags;
    }

    @Builder
    public static HotPlace createHotPlace(String title, String content, String visitedDate, Member member, Integer contentId, List<UploadFile> uploadFiles, List<Long> hashtagIds) {
        HotPlace hotPlace = HotPlace.builder()
                .title(title)
                .content(content)
                .visitedDate(visitedDate)
                .active(ACTIVE)
                .member(member)
                .attractionInfo(AttractionInfo.builder().id(contentId).build())
                .build();

        List<HotPlaceImage> images = new ArrayList<>();
        for (UploadFile uploadFile : uploadFiles) {
            images.add(HotPlaceImage.builder()
                    .uploadFile(uploadFile)
                    .hotPlace(hotPlace)
                    .build());
        }

        List<HotPlaceHashtag> hashtags = new ArrayList<>();
        for (Long hashtagId : hashtagIds) {
            hashtags.add(HotPlaceHashtag.builder()
                    .hotPlace(hotPlace)
                    .hashtag(Hashtag.builder().id(hashtagId).build())
                    .build());
        }

        hotPlace.images = images;
        hotPlace.hashtags = hashtags;
        return hotPlace;
    }

    //== 비즈니스 로직 ==//
    public void changeHotPlace(String title, String content, String visitedDate, List<HotPlaceImage> images) {
        this.title = title;
        this.content = content;
        this.visitedDate = visitedDate;
        this.images.clear();
        this.images = images;
    }

    public void increaseHit() {
        this.hit += 1;
    }

    public void deActive() {
        this.active = DEACTIVE;
    }
}
