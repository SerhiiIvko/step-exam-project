package com.step.ivko.service;

import com.step.ivko.converter.TourConverter;
import com.step.ivko.dao.TourDao;
import com.step.ivko.dao.TourDaoImpl;
import com.step.ivko.model.Tour;
import com.step.ivko.web.dto.TourCreateDto;
import com.step.ivko.web.dto.TourViewDto;

import java.util.List;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService {
    private TourDao tourDao = new TourDaoImpl();
    private TourConverter tourConverter = new TourConverter();

    @Override
    public TourViewDto addTour(TourCreateDto createDto) {
        Tour tour = tourConverter.asTour(createDto);
        tour = tourDao.addTour(tour);
        return tourConverter.asTourDto(tour);
    }

    @Override
    public List<TourViewDto> getAllTours() {
        List<Tour> tours = tourDao.getAll();
        return tours
                .stream()
                .map(tour -> tourConverter.asTourDto(tour))
                .collect(Collectors.toList());
    }

    @Override
    public TourViewDto getTourById(Integer id) {
        Tour tour = tourDao.getById(id);
        return tourConverter.asTourDto(tour);
    }

    @Override
    public TourViewDto updateTour(TourCreateDto createDto) {
        Tour tour = tourConverter.asTour(createDto);
        tour = tourDao.addTour(tour);
        return tourConverter.asTourDto(tour);
    }

    @Override
    public void updateTours(List<TourCreateDto> createDtoList) {
        List<Tour> tours = createDtoList
                .stream()
                .map(tourCreateDto -> tourConverter.asTour(tourCreateDto))
                .collect(Collectors.toList());
        tourDao.update(tours);
    }

    @Override
    public void deleteTour(TourCreateDto createDto) {

    }
}
