package com.hydrosmart.soil.interfaces.rest.controllers;

import com.hydrosmart.shared.constants.AppConstants;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.domain.model.queries.GetAllCropsByUserIdQuery;
import com.hydrosmart.soil.domain.model.queries.GetCropByIdQuery;
import com.hydrosmart.soil.domain.services.commandservices.CropCommandService;
import com.hydrosmart.soil.domain.services.queryservices.CropQueryService;
import com.hydrosmart.soil.interfaces.rest.resources.CropReferenceResource;
import com.hydrosmart.soil.interfaces.rest.transform.CropReferenceResourceFromEntityAssembler;
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

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService){
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<CropReferenceResource> getCropById(@PathVariable Long cropId){
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        return crop.map(value -> ResponseEntity.ok(CropReferenceResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CropReferenceResource>> getAllCropsByUserId(@PathVariable Long userId){
        var getAllCropsByUserIdQuery = new GetAllCropsByUserIdQuery(userId);
        var cropList = cropQueryService.handle(getAllCropsByUserIdQuery);
        var cropResourceList = cropList.stream().map(CropReferenceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cropResourceList);
    }
    /*
    @PostMapping("/{userId}")
    public ResponseEntity<CropReferenceResource> createCrop(@PathVariable Long userId){
        var createCropCommand = new CreateCropCommand(userId);
    }
    */
}
