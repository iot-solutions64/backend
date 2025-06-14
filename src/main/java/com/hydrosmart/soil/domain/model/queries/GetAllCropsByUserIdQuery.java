package com.hydrosmart.soil.domain.model.queries;

public record GetAllCropsByUserIdQuery(Long userId) {
    public GetAllCropsByUserIdQuery {
        if (userId == null){
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (userId < 0){
            throw new IllegalArgumentException("userId cannot be negative");
        }
    }
}
