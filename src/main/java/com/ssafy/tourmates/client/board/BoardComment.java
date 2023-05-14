package com.ssafy.tourmates.client.board;

import static javax.persistence.FetchType.LAZY;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardComment extends TimeBaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "board_comment_id")
  private Long id;
  @Column(nullable = false, length = 50)
  private String content;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  @Builder
  public BoardComment(Long id, String content, Member member, Board board){
    this.id = id;
    this.content = content;
    this.member = member;
  }

  //==비즈니스 로직==//
  public void changeBoardComment(String content){
    this.content = content;
  }


}
