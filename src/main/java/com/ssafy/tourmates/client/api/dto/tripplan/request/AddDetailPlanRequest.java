package com.ssafy.tourmates.client.api.dto.tripplan.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AddDetailPlanRequest {

    @NotNull
    @Size(min = 1)
    List<Integer> contentIds;
}
