package com.ssafy.tourmates.client.api.dto.board.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddBoardCommentRequest {

  @NotBlank
  private String comment;
}
