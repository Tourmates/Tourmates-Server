package com.ssafy.tourmates.client.api.dto.tripPlan;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AddTripPlanRequest {

    @NotBlank
    @Size(max = 50)
    private String title;
    private List<Integer> contentIds;
}
