package com.ssafy.tourmates.client.tripPlan.service.impl;

import com.ssafy.tourmates.client.api.dto.tripplan.request.TripPlanRequest;
import com.ssafy.tourmates.client.api.dto.tripplan.response.PlanAttractionResponse;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanOptimizeService;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.PlanAttractionDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

@Service
public class TripPlanOptimizeServiceImpl implements TripPlanOptimizeService {

    static int n, statusFullBit, INF = 987654321;
    static double[][] w;
    static double[][] dp;
    static List<Position> tripPositionList = new ArrayList<>();


    @Override
    public List<PlanAttractionResponse> optimize(PlanAttractionDto dto) {

        List<TripPlanRequest> plans = dto.getPlans();
        dp = new double[n][n << 1];  //이전 도착 마을에 대한 저장 공간이 있어야 함

        for (int i = 0; i < plans.size(); i++) {
            for (int j = i + 1; j < plans.size(); j++) {
                double latitude_diff = tripPositionList.get(i).latitude - tripPositionList.get(j).latitude;
                double longitude_diff = tripPositionList.get(i).longitude - tripPositionList.get(j).longitude;

                double diff = sqrt(latitude_diff * latitude_diff + longitude_diff * longitude_diff);
                w[i][i] = 0; //갈 수 없는 경우는 0
                w[i][j] = diff;
                w[j][i] = diff;
            }
        }


        return null;
    }

    public static void dfs(int now, int visited) {
        if (visited == ((1 << n) - 1)) {
            if (w[now][0] == 0) {
                return;
            }
//            int compare = dp[now][visited];
        }
    }

    static class Position {
        private double latitude;
        private double longitude;

        public Position(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
