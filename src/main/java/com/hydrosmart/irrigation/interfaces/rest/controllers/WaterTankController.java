package com.hydrosmart.irrigation.interfaces.rest.controllers;

import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTanksByUserId;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankCommandService;
import com.hydrosmart.irrigation.domain.services.queryservices.WaterTankQueryService;
import com.hydrosmart.irrigation.interfaces.rest.resources.CreateWaterTankResource;
import com.hydrosmart.irrigation.interfaces.rest.resources.WaterTankResource;
import com.hydrosmart.irrigation.interfaces.rest.transform.CreateWaterTankCommandFromResourceAssembler;
import com.hydrosmart.irrigation.interfaces.rest.transform.WaterTankResourceFromEntityAssembler;
import com.hydrosmart.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/water-tanks")
@Tag(name = "Water Tank Controller", description = "This controller allows the management of the water tanks")
public class WaterTankController {
    private final WaterTankCommandService waterTankCommandService;
    private final WaterTankQueryService waterTankQueryService;


    public WaterTankController(WaterTankCommandService waterTankCommandService, WaterTankQueryService waterTankQueryService) {
        this.waterTankCommandService = waterTankCommandService;
        this.waterTankQueryService = waterTankQueryService;
    }

    @PostMapping
    public ResponseEntity<WaterTankResource> createWaterTank(CreateWaterTankResource resource){
        var createWaterTankCommand = CreateWaterTankCommandFromResourceAssembler.toCommandFromResource(resource);
        var waterTank = waterTankCommandService.handle(createWaterTankCommand);
        if(waterTank.isEmpty()) return ResponseEntity.badRequest().build();
        var waterTankResource = WaterTankResourceFromEntityAssembler.toResourceFromEntity(waterTank.get());
        return ResponseEntity.ok(waterTankResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterTankResource> getWaterTankById(@PathVariable Long id){
        var getWaterTankByIdQuery = new GetWaterTankByIdQuery(id);
        var waterTank = waterTankQueryService.handle(getWaterTankByIdQuery);
        if(waterTank.isEmpty()) return ResponseEntity.badRequest().build();
        var waterTankResource = WaterTankResourceFromEntityAssembler.toResourceFromEntity(waterTank.get());
        return ResponseEntity.ok(waterTankResource);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<WaterTankResource>> getWaterTanksByUserId(@PathVariable Long userId){
        var getWaterTanksByUserIdQuery = new GetWaterTanksByUserId(userId);
        var waterTankList = waterTankQueryService.handle(getWaterTanksByUserIdQuery);
        if(waterTankList.isEmpty()) return ResponseEntity.badRequest().build();
        var waterTankListResource = waterTankList
                .stream()
                .map(WaterTankResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(waterTankListResource);
    }
}
