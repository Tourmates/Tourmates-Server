package com.ssafy.tourmates.client.hashtag;

import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HotPlaceHashtag extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_hashtag_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hashtag_ids")
    private Hashtag hashtag;

    @Builder
    public HotPlaceHashtag(Long id, HotPlace hotPlace, Hashtag hashtag) {
        this.id = id;
        this.hotPlace = hotPlace;
        this.hashtag = hashtag;
    }
}
