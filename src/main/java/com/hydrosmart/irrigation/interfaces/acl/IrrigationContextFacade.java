package com.hydrosmart.irrigation.interfaces.acl;

import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationFrequency;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;
import com.hydrosmart.irrigation.domain.model.queries.GetIrrigationByIdQuery;
import com.hydrosmart.irrigation.domain.services.commandservices.IrrigationCommandService;
import com.hydrosmart.irrigation.domain.services.queryservices.IrrigationQueryService;
import org.springframework.stereotype.Service;

@Service
public class IrrigationContextFacade {
    private final IrrigationCommandService irrigationCommandService;
    private final IrrigationQueryService irrigationQueryService;

    public IrrigationContextFacade(IrrigationCommandService irrigationCommandService, IrrigationQueryService irrigationQueryService) {
        this.irrigationCommandService = irrigationCommandService;
        this.irrigationQueryService = irrigationQueryService;
    }

    public Long createIrrigation(IrrigationFrequency frequency, Float maxWaterUsage){
        var createIrrigationCommand = new CreateIrrigationCommand(maxWaterUsage);
        var result = irrigationCommandService.handle(createIrrigationCommand, frequency);
        if(result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public Irrigation fetchIrrigationById(Long id){
        var getIrrigationByIdQuery = new GetIrrigationByIdQuery(id);
        var result = irrigationQueryService.handle(getIrrigationByIdQuery);
        return result.orElse(null);
    }

    public boolean updateIrrigation(Long id, String status, IrrigationFrequency frequency, Float maxWaterUsage){
        var updateIrrigationCommand = new UpdateIrrigationCommand(id,maxWaterUsage);
        var foundStatus = IrrigationStatus.toStatusFromName(status);
        var result = irrigationCommandService.handle(updateIrrigationCommand, foundStatus, frequency);
        return result.isPresent();
    }
}