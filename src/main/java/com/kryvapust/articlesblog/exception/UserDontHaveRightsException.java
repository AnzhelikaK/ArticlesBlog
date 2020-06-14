package com.kryvapust.articlesblog.exception;

public class UserDontHaveRightsException extends RuntimeException {
    public UserDontHaveRightsException(String message){
        super(message);
    }
}
