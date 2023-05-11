package com.ssafy.tourmates.admin.attraction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Gugun {

    @Id
    @Column(name = "gugun_code")
    private Integer code;
    @Column(name = "gugun_name", unique = true, nullable = false, length = 30)
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    @Builder
    public Gugun(Integer code, String name, Sido sido) {
        this.code = code;
        this.name = name;
        this.sido = sido;
    }
}
