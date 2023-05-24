package com.ssafy.tourmates.client.hashtag;

import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Hashtag extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hashtag_id")
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private String tagName;

    @Builder
    public Hashtag(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }
}
