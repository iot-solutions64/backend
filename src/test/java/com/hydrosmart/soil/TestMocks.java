package com.hydrosmart.soil;

import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;

public class TestMocks {
    public static Irrigation mockIrrigation() {
        Irrigation irrigation = new Irrigation();
        irrigation.setId(1L);
        return irrigation;
    }

    public static WaterTank mockWaterTank() {
        WaterTank waterTank = new WaterTank();
        waterTank.setId(1L);
        return waterTank;
    }
}
