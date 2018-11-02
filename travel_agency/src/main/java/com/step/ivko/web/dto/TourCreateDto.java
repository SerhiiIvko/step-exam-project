package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TourCreateDto implements Serializable {
    private static final long serialVersionUID = -7194240611151195207L;
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

    public TourCreateDto(String type, Double price, Integer peopleCount, String hotelType, String country,
                         Date dateDeparture, Date dateArrival, Double discount, Boolean isRegistered,
                         Boolean isHot, Boolean isPaidFor, Boolean isCanceled) {
        this(null, type, price, peopleCount, hotelType, country, dateDeparture,
                dateArrival, discount, isRegistered, isHot, isPaidFor, isCanceled);
    }

    public TourCreateDto(Integer id, String type, Double price, Integer peopleCount, String hotelType, String country,
                         Date dateDeparture, Date dateArrival, Double discount, Boolean isRegistered,
                         Boolean isHot, Boolean isPaidFor, Boolean isCanceled) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.peopleCount = peopleCount;
        this.hotelType = hotelType;
        this.country = country;
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.discount = discount;
        this.isRegistered = isRegistered;
        this.isHot = isHot;
        this.isPaidFor = isPaidFor;
        this.isCanceled = isCanceled;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public boolean isHot() {
        return isHot;
    }

    public boolean isPaidFor() {
        return isPaidFor;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public String getHotelType() {
        return hotelType;
    }

    public Double getDiscount() {
        return discount;
    }

    public String getCountry() {
        return country;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TourCreateDto)) return false;
        TourCreateDto that = (TourCreateDto) o;
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