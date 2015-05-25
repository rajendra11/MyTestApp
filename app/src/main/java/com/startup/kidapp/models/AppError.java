package com.startup.kidapp.models;

/**
 * Created by rajendra on 5/24/15.
 */
public class AppError {

    public String code;
    public String error;
    public String developermessage;

    public AppError() {}

    /*
    public AppError(ServerError error) {
        this.code = error.code;
        this.error = error.error;
        this.developermessage = error.developermessage;
    }*/
}
