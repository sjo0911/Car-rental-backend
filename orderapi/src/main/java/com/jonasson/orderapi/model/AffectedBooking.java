package com.jonasson.orderapi.model;

public class AffectedBooking {
    private Long Id;
    private String newCarName;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNewCarName() {
        return newCarName;
    }

    public void setNewCarName(String newCarName) {
        this.newCarName = newCarName;
    }
}
