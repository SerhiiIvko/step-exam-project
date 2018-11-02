package com.step.ivko.dao;

import com.step.ivko.model.Tour;

import java.util.List;

public interface TourDao {
    Tour getById(Integer id);

    List<Tour> getAll();

    Tour addTour(Tour tour);

    Tour update(Tour tour);

    void update(List<Tour> tours);

    void deleteTour(Tour tour);
}