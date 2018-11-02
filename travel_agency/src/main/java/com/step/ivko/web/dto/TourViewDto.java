package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TourViewDto implements Serializable {
    private static final long serialVersionUID = -2864813180059389820L;
    private Integer id;
    private String type;
    private Double price;
    private Integer peopleCount;
    private String hotelType;
    private Double discount;
    private String country;
    private Date dateDeparture;
    private Date dateArrival;
    private boolean isRegistered;
    private boolean isHot;
    private boolean isPaidFor;
    private boolean isCanceled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean isPaidFor() {
        return isPaidFor;
    }

    public void setPaidFor(boolean paidFor) {
        isPaidFor = paidFor;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourViewDto that = (TourViewDto) o;
        return isRegistered == that.isRegistered &&
                isHot == that.isHot &&
                isPaidFor == that.isPaidFor &&
                isCanceled == that.isCanceled &&
                Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(price, that.price) &&
                Objects.equals(peopleCount, that.peopleCount) &&
                Objects.equals(hotelType, that.hotelType) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(country, that.country) &&
                Objects.equals(dateDeparture, that.dateDeparture) &&
                Objects.equals(dateArrival, that.dateArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price, peopleCount, hotelType, discount, country, dateDeparture, dateArrival,
                isRegistered, isHot, isPaidFor, isCanceled);
    }
}