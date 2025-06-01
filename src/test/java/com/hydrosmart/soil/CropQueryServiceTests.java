package com.hydrosmart.soil;

import com.hydrosmart.soil.application.internal.queryservices.CropQueryServiceImpl;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.queries.GetAllCropsByUserIdQuery;
import com.hydrosmart.soil.domain.model.queries.GetCropByIdQuery;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.CropRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CropQueryServiceTests {
    private CropRepository cropRepository;
    private CropQueryServiceImpl cropQueryService;

    @BeforeEach
    public void setUp() {
        cropRepository = mock(CropRepository.class);
        cropQueryService = new CropQueryServiceImpl(cropRepository);
    }

    @Test
    public void testGetCropByIdQuerySuccess() {
        Long cropId = 1L;
        Crop mockCrop = new Crop();
        when(cropRepository.findById(cropId)).thenReturn(Optional.of(mockCrop));

        var query = new GetCropByIdQuery(cropId);
        Optional<Crop> result = cropQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockCrop, result.get());
        verify(cropRepository, times(1)).findById(cropId);
    }

    @Test
    public void testGetCropByIdQueryNotFound() {
        Long cropId = 99L;
        when(cropRepository.findById(cropId)).thenReturn(Optional.empty());

        var query = new GetCropByIdQuery(cropId);
        Optional<Crop> result = cropQueryService.handle(query);

        assertTrue(result.isEmpty());
        verify(cropRepository, times(1)).findById(cropId);
    }

    @Test
    public void testGetAllCropsByUserIdQuerySuccess() {
        Long userId = 1L;
        List<Crop> mockCrops = List.of(new Crop(), new Crop());
        when(cropRepository.findAllByUserId(userId)).thenReturn(mockCrops);

        var query = new GetAllCropsByUserIdQuery(userId);
        List<Crop> result = cropQueryService.handle(query);

        assertEquals(2, result.size());
        verify(cropRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    public void testGetCropByIdQueryNullId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GetCropByIdQuery(null);
        });
        assertEquals("cropId cannot be null", exception.getMessage());
    }

    @Test
    public void testGetCropByIdQueryNegativeId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GetCropByIdQuery(-1L);
        });
        assertEquals("cropId cannot be negative", exception.getMessage());
    }

    @Test
    public void testGetAllCropsByUserIdQueryNullId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GetAllCropsByUserIdQuery(null);
        });
        assertEquals("userId cannot be null", exception.getMessage());
    }

    @Test
    public void testGetAllCropsByUserIdQueryNegativeId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GetAllCropsByUserIdQuery(-1L);
        });
        assertEquals("userId cannot be negative", exception.getMessage());
    }
}
