package com.kryvapust.articlesblog.Exception;

public class UserDontHaveRightsException extends RuntimeException {
    public UserDontHaveRightsException(String message){
        super(message);
    }
}
