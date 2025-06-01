package com.hydrosmart.irrigation.interfaces.rest.controllers;

import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankStatusCommand;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTanksByUserId;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankCommandService;
import com.hydrosmart.irrigation.domain.services.queryservices.WaterTankQueryService;
import com.hydrosmart.irrigation.interfaces.rest.resources.CreateWaterTankResource;
import com.hydrosmart.irrigation.interfaces.rest.resources.PatchWaterTankNameResource;
import com.hydrosmart.irrigation.interfaces.rest.resources.PatchWaterTankWaterAmountRemainingResource;
import com.hydrosmart.irrigation.interfaces.rest.resources.WaterTankResource;
import com.hydrosmart.irrigation.interfaces.rest.transform.CreateWaterTankCommandFromResourceAssembler;
import com.hydrosmart.irrigation.interfaces.rest.transform.PatchWaterTankNameCommandFromResourceAssembler;
import com.hydrosmart.irrigation.interfaces.rest.transform.PatchWaterTankWaterAmountRemainingCommandFromResourceAssembler;
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
    public ResponseEntity<WaterTankResource> createWaterTank(@RequestBody CreateWaterTankResource resource){
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

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<WaterTankResource> updateStatus(@PathVariable Long id, @PathVariable WaterTankStatusList status){
        //Since the status is an enum, it is not necessary to use an assembler class. Instead, create the class here
        var patchWaterTankStatusCommand = new PatchWaterTankStatusCommand(id,status);
        var waterTank = waterTankCommandService.handle(patchWaterTankStatusCommand);
        if(waterTank.isEmpty()) return ResponseEntity.badRequest().build();
        var waterTankResource = WaterTankResourceFromEntityAssembler.toResourceFromEntity(waterTank.get());
        return ResponseEntity.ok(waterTankResource);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<WaterTankResource> updateName(@RequestBody PatchWaterTankNameResource resource){
        var patchWaterTankNameCommand = PatchWaterTankNameCommandFromResourceAssembler.toCommandFromResource(resource);
        var waterTank = waterTankCommandService.handle(patchWaterTankNameCommand);
        if(waterTank.isEmpty()) return ResponseEntity.badRequest().build();
        var waterTankResource = WaterTankResourceFromEntityAssembler.toResourceFromEntity(waterTank.get());
        return ResponseEntity.ok(waterTankResource);
    }

    @PatchMapping("/{id}/water-remaining")
    public ResponseEntity<WaterTankResource> updateWaterRemaining(@RequestBody PatchWaterTankWaterAmountRemainingResource resource){
        var patchWaterTankWaterAmountRemainingCommand = PatchWaterTankWaterAmountRemainingCommandFromResourceAssembler.toCommandFromResource(resource);
        var waterTank = waterTankCommandService.handle(patchWaterTankWaterAmountRemainingCommand);
        if(waterTank.isEmpty()) return ResponseEntity.badRequest().build();
        var waterTankResource = WaterTankResourceFromEntityAssembler.toResourceFromEntity(waterTank.get());
        return ResponseEntity.ok(waterTankResource);
    }
}
