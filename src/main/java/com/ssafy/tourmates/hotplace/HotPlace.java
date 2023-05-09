package com.ssafy.tourmates.hotplace;

import com.ssafy.tourmates.attraction.AttractionInfo;
import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import com.ssafy.tourmates.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HotPlace extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ContentType tag;
    @Column(nullable = false, length = 50)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int hit;
    @Column(nullable = false, length = 10)
    private String visitedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceImage> images = new ArrayList<>();

    @Builder
    public HotPlace(Long id, ContentType tag, String title, String content, int hit, String visitedDate, Member member, AttractionInfo attractionInfo, List<HotPlaceImage> images) {
        this.id = id;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.member = member;
        this.attractionInfo = attractionInfo;
        this.images = images;
    }
}
