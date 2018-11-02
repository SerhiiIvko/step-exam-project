package com.step.ivko.service;

import com.step.ivko.web.dto.TourCreateDto;
import com.step.ivko.web.dto.TourViewDto;

import java.util.List;

public interface TourService {
    TourViewDto addTour(TourCreateDto createDto);
    List<TourViewDto> getAllTours();
    TourViewDto getTourById(Integer id);
    TourViewDto updateTour(TourCreateDto createDto);
    void updateTours(List<TourCreateDto> createDtoList);
    void deleteTour(TourCreateDto createDto);
}