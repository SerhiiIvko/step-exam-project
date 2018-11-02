package com.step.ivko.converter;

import com.step.ivko.model.Tour;
import com.step.ivko.web.dto.TourCreateDto;
import com.step.ivko.web.dto.TourViewDto;

public class TourConverter {
    public TourViewDto asTourDto(Tour tour) {
        TourViewDto dto = new TourViewDto();
        dto.setId(tour.getId());
        dto.setType(tour.getType());
        dto.setPrice(tour.getPrice());
        dto.setPeopleCount(tour.getPeopleCount());
        dto.setHotelType(tour.getHotelType());
        dto.setCountry(tour.getCountry());
        dto.setDateDeparture(tour.getDateDeparture());
        dto.setDateArrival(tour.getDateArrival());
        dto.setDiscount(tour.getDiscount());
        dto.setRegistered(tour.isRegistered());
        dto.setHot(tour.isHot());
        dto.setPaidFor(tour.isPaidFor());
        dto.setCanceled(tour.isCanceled());
        return dto;
    }

    public Tour asTour(TourCreateDto tourCreateDto) {
        Tour tour = new Tour();
        tour.setId(tourCreateDto.getId());
        tour.setType(tourCreateDto.getType());
        tour.setPrice(tourCreateDto.getPrice());
        tour.setPeopleCount(tourCreateDto.getPeopleCount());
        tour.setHotelType(tourCreateDto.getHotelType());
        tour.setCountry(tourCreateDto.getCountry());
        tour.setDateDeparture(tourCreateDto.getDateDeparture());
        tour.setDateArrival(tourCreateDto.getDateArrival());
        tour.setDiscount(tourCreateDto.getDiscount());
        tour.setRegistered(tourCreateDto.isRegistered());
        tour.setHot(tourCreateDto.isHot());
        tour.setPaidFor(tourCreateDto.isPaidFor());
        tour.setCanceled(tourCreateDto.isCanceled());
        return tour;
    }
}