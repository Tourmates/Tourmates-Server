package com.ssafy.tourmates.admin.attraction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Sido {

    @Id
    @Column(name = "sido_code")
    private int code;
    @Column(name = "sido_name", unique = true, nullable = false, length = 30)
    public String name;

    @Builder
    public Sido(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
