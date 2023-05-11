package com.ssafy.tourmates.client.hotplace;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HotplaceComment {

    @Id
    @GeneratedValue
    @Column(name = "hot_place_comment_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotplace;

    @Builder
    public HotplaceComment(Long id, String content){
        this.id = id;
        this.content = content;
    }

    //==비즈니스 로직==//
    public void changeHotplaceComment(String content){
        this.content = content;
    }
}
