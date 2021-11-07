package com.jonasson.booking.dto;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class BookingDto {
    private Long Id;
    @NotNull(message = "Booking need a customer id!")
    private Long customerId;
    @NotNull(message = "Car has not been selected")
    private Long carId;
    @NotNull(message = "Date to rent from has not been selected")
    private Date fromDate;
    @NotNull(message = "Date to rent to has not been selected")
    private Date toDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
