package com.step.ivko.dao;

import com.step.ivko.exception.ApplicationException;
import com.step.ivko.model.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl implements TourDao {
    private static final String TOUR_BY_ID_QUERY = "select * from tours where id = '%s'";
    private static final String ALL_TOURS_QUERY = "select * from tours";
    private static final String ERROR_MESSAGE_PATTERN = "Tour not found by %s: %s";

    @Override
    public Tour getById(Integer id) {
        return getTourByQuery(String.format(TOUR_BY_ID_QUERY, id),
                String.format(ERROR_MESSAGE_PATTERN, "id", id));
    }

    private Tour getTourByQuery(String query, String errorMessage) {
        Tour tour = null;
        try (Connection connection = retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tour = new Tour();
                tour.setId(resultSet.getInt("id"));
                tour.setType(resultSet.getString("type"));
                tour.setPrice(resultSet.getDouble("price"));
                tour.setPeopleCount(resultSet.getInt("peopleCount"));
                tour.setHotelType(resultSet.getString("hotelType"));
                tour.setCountry(resultSet.getString("country"));
                tour.setDateDeparture(resultSet.getDate("dateDeparture"));
                tour.setDateArrival(resultSet.getDate("dateArrival"));
                tour.setDiscount(resultSet.getDouble("discount"));
                tour.setRegistered(resultSet.getBoolean("registered"));
                tour.setHot(resultSet.getBoolean("hot"));
                tour.setPaidFor(resultSet.getBoolean("paidFor"));
                tour.setCanceled(resultSet.getBoolean("canceled"));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to load tour from DB", e);
        }
        if (tour == null) {
            throw new ApplicationException(errorMessage);
        }
        return tour;
    }

    @Override
    public List<Tour> getAll() {
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL_TOURS_QUERY);
            while (resultSet.next()) {
                Tour tour = new Tour();
                tour.setId(resultSet.getInt("id"));
                tour.setType(resultSet.getString("type"));
                tour.setPrice(resultSet.getDouble("price"));
                tour.setPeopleCount(resultSet.getInt("peopleCount"));
                tour.setHotelType(resultSet.getString("hotelType"));
                tour.setCountry(resultSet.getString("country"));
                tour.setDateDeparture(resultSet.getDate("dateDeparture"));
                tour.setDateArrival(resultSet.getDate("dateArrival"));
                tour.setDiscount(resultSet.getDouble("discount"));
                tour.setRegistered(resultSet.getBoolean("registered"));
                tour.setHot(resultSet.getBoolean("hot"));
                tour.setPaidFor(resultSet.getBoolean("paidFor"));
                tour.setCanceled(resultSet.getBoolean("canceled"));
                tours.add(tour);
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to load tour from DB", e);
        }
        return tours;
    }

    private static final String INSERT_TOUR_QUERY = "insert into tours (" +
            "type, " +
            "price, " +
            "peopleCount, " +
            "hotelType, " +
            "country, " +
            "dateDeparture, " +
            "dateArrival, " +
            "discount, " +
            "isRegistered, " +
            "isHot, " +
            "isPaidFor, " +
            "isCanceled) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Tour addTour(Tour tour) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_TOUR_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tour.getType());
            statement.setDouble(2, tour.getPrice());
            statement.setInt(3, tour.getPeopleCount());
            statement.setString(4, tour.getHotelType());
            statement.setString(5, tour.getCountry());
            statement.setDate(6, (Date) tour.getDateDeparture());
            statement.setDate(7, (Date) tour.getDateArrival());
            statement.setDouble(8, tour.getDiscount());
            statement.setBoolean(9, tour.isRegistered());
            statement.setBoolean(10, tour.isHot());
            statement.setBoolean(11, tour.isPaidFor());
            statement.setBoolean(12, tour.isCanceled());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tour.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to insert tour into DB", e);
        }
        return tour;
    }

    private static final String UPDATE_TOUR_QUERY =
            "update tours set type=?, " +
                    "price=?, " +
                    "peopleCount=?, " +
                    "hotelType=?, " +
                    "country=?, " +
                    "dateDeparture=?, " +
                    "dateArrival=?, " +
                    "discount=?," +
                    "isRegistered=?," +
                    "isHot=?," +
                    "isPaidFor=?," +
                    "isCanceled=?%s " +
                    "where id=?";

    @Override
    public Tour update(Tour tour) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(UPDATE_TOUR_QUERY))) {
            int parameterCounter = 1;
            statement.setString(parameterCounter++, tour.getType());
            statement.setDouble(parameterCounter++, tour.getPrice());
            statement.setInt(parameterCounter++, tour.getPeopleCount());
            statement.setString(parameterCounter++, tour.getHotelType());
            statement.setString(parameterCounter++, tour.getCountry());
            statement.setDate(parameterCounter++, (Date) tour.getDateDeparture());
            statement.setDate(parameterCounter++, (Date) tour.getDateArrival());
            statement.setDouble(parameterCounter++, tour.getDiscount());
            statement.setBoolean(parameterCounter++, tour.isRegistered());
            statement.setBoolean(parameterCounter++, tour.isHot());
            statement.setBoolean(parameterCounter++, tour.isPaidFor());
            statement.setBoolean(parameterCounter++, tour.isCanceled());
            statement.setInt(parameterCounter, tour.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationException("Failed to update tour", e);
        }
        return tour;
    }

    @Override
    public void update(List<Tour> tours) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(UPDATE_TOUR_QUERY, ""))) {
            for (Tour tour : tours) {
                statement.setString(1, tour.getType());
                statement.setDouble(2, tour.getPrice());
                statement.setInt(3, tour.getPeopleCount());
                statement.setString(4, tour.getHotelType());
                statement.setString(5, tour.getCountry());
                statement.setDate(6, (Date) tour.getDateDeparture());
                statement.setDate(7, (Date) tour.getDateArrival());
                statement.setDouble(8, tour.getDiscount());
                statement.setBoolean(9, tour.isRegistered());
                statement.setBoolean(10, tour.isHot());
                statement.setBoolean(11, tour.isPaidFor());
                statement.setBoolean(12, tour.isCanceled());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            throw new ApplicationException("Failed to update tours", e);
        }
    }

    private static final String DELETE_TOUR_QUERY = "delete from tours where id=?";

    @Override
    public void deleteTour(Tour tour) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TOUR_QUERY)) {
            statement.setInt(1, tour.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationException("Failed to delete tour", e);
        }
    }

    private Connection retrieveConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}