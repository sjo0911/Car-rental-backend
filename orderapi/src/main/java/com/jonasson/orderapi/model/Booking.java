package com.jonasson.orderapi.model;

import java.sql.Date;

public class Booking {

        private Long Id;
        private Long customerId;
        private Long carId;
        private Date fromDate;
        private Date toDate;
        private boolean active;

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

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
}
