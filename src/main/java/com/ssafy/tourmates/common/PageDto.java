package com.ssafy.tourmates.common;

import lombok.Data;

@Data
public class PageDto {

    private int startPage; //첫번째 번호
    private int endPage; //마지막 번호
    private boolean prev; //이전 버튼 활성화 유무
    private boolean next; //다음 버튼 활성화 유무

    private int pageNum; //현재 페이지 번호
    private int amount; //데이터 수
    private long total; //전체 데이터 수

    public PageDto(int pageNum, int amount, long total) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.total = total;

        this.endPage = (int) Math.ceil(this.pageNum * 0.1) * 10;
        this.startPage = this.endPage - 10 + 1;
        int realEnd = (int) Math.ceil(this.total / (double) this.amount);
        if (this.endPage > realEnd) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}