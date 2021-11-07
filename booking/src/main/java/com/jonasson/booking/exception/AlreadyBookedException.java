package com.jonasson.booking.exception;

public class AlreadyBookedException extends Exception {
    public AlreadyBookedException(String errMsg){
        super(errMsg);
    }
}
