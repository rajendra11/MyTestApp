package com.startup.kidapp.listeners;


import com.startup.kidapp.models.AppError;
import com.startup.kidapp.models.LoginRespone;

public interface LoginListener {

    public void onSuccess(LoginRespone respone);

    public void onError(AppError error);

}
