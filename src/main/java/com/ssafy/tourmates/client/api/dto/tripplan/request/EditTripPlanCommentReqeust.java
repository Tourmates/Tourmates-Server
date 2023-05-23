package com.ssafy.tourmates.client.api.dto.tripplan.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditTripPlanCommentReqeust {

    @NotBlank
    private String content;
}
