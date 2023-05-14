package com.ssafy.tourmates.client.controller.dto.tripPlan;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AddTripPlanRequest {

  @NotBlank
  @Size(max = 50)
  private String title;
}
