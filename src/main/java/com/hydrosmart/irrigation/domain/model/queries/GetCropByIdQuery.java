package com.hydrosmart.irrigation.domain.model.queries;

public record GetCropByIdQuery(Long cropId) {
    public GetCropByIdQuery {
        if (cropId == null){
            throw new IllegalArgumentException("cropId cannot be null");
        }
        if (cropId < 0){
            throw new IllegalArgumentException("cropId cannot be negative");
        }
    }
}
