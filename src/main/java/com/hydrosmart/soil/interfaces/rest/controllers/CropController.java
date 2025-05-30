package com.hydrosmart.soil.interfaces.rest.controllers;

import com.hydrosmart.shared.constants.AppConstants;
import com.hydrosmart.soil.application.internal.orchestrators.CropOrchestrator;
import com.hydrosmart.soil.domain.model.queries.GetAllCropsByUserIdQuery;
import com.hydrosmart.soil.domain.model.queries.GetCropByIdQuery;
import com.hydrosmart.soil.domain.services.commandservices.CropCommandService;
import com.hydrosmart.soil.domain.services.commandservices.HumidityCommandService;
import com.hydrosmart.soil.domain.services.commandservices.TemperatureCommandService;
import com.hydrosmart.soil.domain.services.queryservices.CropQueryService;
import com.hydrosmart.soil.interfaces.rest.resources.*;
import com.hydrosmart.soil.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/crop")
@Tag(name = "Crop Controller", description = "This controller allows the management of the crops")
public class CropController {
    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;
    private final TemperatureCommandService temperatureCommandService;
    private final HumidityCommandService humidityCommandService;
    private final CropOrchestrator cropOrchestrator;

    public CropController(
            CropCommandService cropCommandService,
            CropQueryService cropQueryService,
            TemperatureCommandService temperatureCommandService,
            HumidityCommandService humidityCommandService,
            CropOrchestrator cropOrchestrator
    ){
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
        this.temperatureCommandService = temperatureCommandService;
        this.humidityCommandService = humidityCommandService;
        this.cropOrchestrator = cropOrchestrator;
    }

    @PostMapping
    public ResponseEntity<CropReferenceResource> createCrop(@RequestBody CreateCropResource resource){
        var crop = cropOrchestrator.handle(resource);
        return crop
                .map(value -> ResponseEntity.ok(CropReferenceResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{cropId}/reference")
    public ResponseEntity<CropReferenceResource> getCropById(@PathVariable Long cropId){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if(crop.isEmpty()) return ResponseEntity.notFound().build();
        return crop.map(value -> ResponseEntity.ok(CropReferenceResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{cropId}/light")
    public ResponseEntity<CropLightResource> getLightCropById(@PathVariable Long cropId){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if(crop.isEmpty()) return ResponseEntity.notFound().build();
        return crop.map(value -> ResponseEntity.ok(CropLightResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{cropId}/detailed")
    public ResponseEntity<CropDetailedResource> getDetailedCropById(@PathVariable Long cropId){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if(crop.isEmpty()) return ResponseEntity.notFound().build();
        return crop.map(value -> ResponseEntity.ok(CropDetailedResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CropReferenceResource>> getAllCropsByUserId(@PathVariable Long userId){
        var getAllCropsByUserIdQuery = new GetAllCropsByUserIdQuery(userId);
        var cropList = cropQueryService.handle(getAllCropsByUserIdQuery);
        var cropResourceList = cropList.stream().map(CropReferenceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cropResourceList);
    }

    @PutMapping("/{cropId}/temperature")
    public ResponseEntity<TemperatureResource> updateCropTemperature(@PathVariable Long cropId, @RequestBody UpdateTemperatureResource resource){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if(crop.isEmpty()) return ResponseEntity.notFound().build();
        var temperatureId = crop.get().getTemperature().getId();
        var updateTemperatureCommand = UpdateTemperatureCommandFromResourceAssembler.toCommandFromResource(resource, temperatureId);
        var temperature = temperatureCommandService.handle(updateTemperatureCommand);
        if(temperature.isEmpty()) return ResponseEntity.notFound().build();
        var temperatureResource = TemperatureResourceFromEntityAssembler.toResourceFromEntity(temperature.get());
        return ResponseEntity.ok(temperatureResource);
    }

    @PutMapping("/{cropId}/humidity")
    public ResponseEntity<HumidityResource> updateCropHumidity(@PathVariable Long cropId, @RequestBody UpdateHumidityResource resource){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if(crop.isEmpty()) return ResponseEntity.notFound().build();
        var humidityId = crop.get().getHumidity().getId();
        var updateHumidityCommand = UpdateHumidityCommandFromResourceAssembler.toCommandFromResource(resource, humidityId);
        var humidity = humidityCommandService.handle(updateHumidityCommand);
        if(humidity.isEmpty()) return ResponseEntity.notFound().build();
        var humidityResource = HumidityResourceFromEntityAssembler.toResourceFromEntity(humidity.get());
        return ResponseEntity.ok(humidityResource);
    }

    @PatchMapping("{cropId}/temperature")
    public ResponseEntity<String> patchTemperature(@PathVariable Long cropId, @RequestBody PatchTemperatureResource resource){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if(crop.isEmpty()) return ResponseEntity.notFound().build();
        var temperatureId = crop.get().getTemperature().getId();
        var patchTemperatureCommand = PatchTemperatureCommandFromResourceAssembler.toCommandFromResource(resource, temperatureId);
        temperatureCommandService.handle(patchTemperatureCommand);
        return ResponseEntity.ok("Temperature updated");
    }

    @PatchMapping("/{cropId}/humidity")
    public ResponseEntity<String> patchHumidity(@PathVariable Long cropId, @RequestBody PatchHumidityResource resource){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if(crop.isEmpty()) return ResponseEntity.notFound().build();
        var humidityId = crop.get().getHumidity().getId();
        var patchHumidityCommand = PatchHumidityCommandFromResourceAssembler.toCommandFromResource(resource, humidityId);
        humidityCommandService.handle(patchHumidityCommand);
        return ResponseEntity.ok("Humidity updated");
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<String> deleteCrop(@PathVariable Long cropId) {
        cropCommandService.deleteById(cropId);
        String message = "Crop " + cropId + " deleted";
        return ResponseEntity.ok(message);
    }

}
