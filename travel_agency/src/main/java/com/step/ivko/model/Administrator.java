package com.step.ivko.model;

import java.io.Serializable;
import java.util.Date;

public class Administrator extends Manager implements Serializable {
    private static final long serialVersionUID = -673949647895942253L;
//    private Integer id;
//    private String name;
//    private String surname;
//    private Integer age;
//    private String email;
//    private String password;
//    private Boolean isAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Boolean isAdmin() {
//        return isAdmin;
//    }
//
//    public void setAdmin(Boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }

    @Override
    public boolean markTourAsHot() {
        return false;
    }

    @Override
    public boolean markTourAsPaid() {
        return false;
    }

    @Override
    public boolean markTourAsCanceled() {
        return false;
    }

    @Override
    public int setDiscount() {
        return 0;
    }

    public void blockUser(Customer customer) {
        customer.isBlocked();
    }

    public void unblockUser(Customer customer) {
        customer.isBlocked();
    }

    public Tour addTour() {
        return null;
    }

    public void deleteTour(Tour tour) {

    }

    public Tour changeTourInfo(String type, Double price, Integer peopleCount, String hotelType, String country,
                               Date dateDeparture, Date dateArrival, Double discount, Boolean isRegistered,
                               Boolean isHot, Boolean isPaidFor, Boolean isCanceled) {
        Tour tour = new Tour();
        tour.setType(type);
        tour.setPrice(price);
        tour.setPeopleCount(peopleCount);
        tour.setHotelType(hotelType);
        tour.setCountry(country);
        tour.setDateDeparture(dateDeparture);
        tour.setDateArrival(dateArrival);
        tour.setDiscount(discount);
        tour.setRegistered(isRegistered);
        tour.setHot(isHot);
        tour.setPaidFor(isPaidFor);
        tour.setCanceled(isCanceled);
        return tour;
    }
}