package com.ssafy.tourmates.common.domain;

public enum ContentType {
    ATTRACTION(12),
    CULTURAL(14),
    FESTIVAL(15),
    TRAVEL(25),
    LEPORTS(28),
    ACCOMMODATION(32),
    SHOPPING(38),
    RESTAURANT(39);

    private final Integer key;

    ContentType(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }
}
