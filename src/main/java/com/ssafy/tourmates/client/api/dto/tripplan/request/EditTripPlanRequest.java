package com.ssafy.tourmates.client.api.dto.tripplan.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditTripPlanRequest {

    @NotBlank
    @Size(max = 50)
    private String title;
}
