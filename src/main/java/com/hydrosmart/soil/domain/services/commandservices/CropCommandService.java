package com.hydrosmart.soil.domain.services.commandservices;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;

import java.util.Optional;

public interface CropCommandService {
    Optional<Crop> handle(CreateCropCommand command);
}
