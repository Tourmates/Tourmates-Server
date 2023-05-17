package com.ssafy.tourmates.client.api.dto.board.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AddBoardCommentRequest {

  @NotBlank
  @Size(max = 100)
  private String comment;
}
