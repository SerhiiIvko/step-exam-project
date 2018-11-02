package com.step.ivko.model;

import java.util.Date;
import java.util.Objects;

public class Tour {
    private Hotel hotel;
    private Boolean isRegistered;
    private Boolean isHot;
    private Boolean isPaidFor;
    private Boolean isCanceled;
    private Integer id;
    private String type;
    private Double price;
    private Integer peopleCount;
    private String hotelType;
    private Double discount;
    private String country;
    private Date dateDeparture;
    private Date dateArrival;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Boolean isHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public Boolean isPaidFor() {
        return isPaidFor;
    }

    public void setPaidFor(Boolean paidFor) {
        isPaidFor = paidFor;
    }

    public Boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;
        Tour tour = (Tour) o;
        return hotel == tour.hotel &&
                Objects.equals(isRegistered, tour.isRegistered) &&
                Objects.equals(isHot, tour.isHot) &&
                Objects.equals(isPaidFor, tour.isPaidFor) &&
                Objects.equals(isCanceled, tour.isCanceled) &&
                Objects.equals(id, tour.id) &&
                Objects.equals(type, tour.type) &&
                Objects.equals(price, tour.price) &&
                Objects.equals(peopleCount, tour.peopleCount) &&
                Objects.equals(hotelType, tour.hotelType) &&
                Objects.equals(discount, tour.discount) &&
                Objects.equals(country, tour.country) &&
                Objects.equals(dateDeparture, tour.dateDeparture) &&
                Objects.equals(dateArrival, tour.dateArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotel, isRegistered, isHot, isPaidFor, isCanceled, id, type, price, peopleCount, hotelType,
                discount, country, dateDeparture, dateArrival);
    }
}