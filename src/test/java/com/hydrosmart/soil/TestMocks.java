package com.hydrosmart.soil;


import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;

public class TestMocks {
    public static Irrigation mockIrrigation() {
        Irrigation irrigation = new Irrigation();
        irrigation.setId(1L);
        irrigation.setMaxWaterUsage(100.0f);
        return irrigation;
    }

    public static WaterTank mockWaterTank() {
        WaterTank waterTank = new WaterTank();
        waterTank.setId(1L);
        waterTank.setMaxWaterCapacity(1000.0f);
        return waterTank;
    }
}