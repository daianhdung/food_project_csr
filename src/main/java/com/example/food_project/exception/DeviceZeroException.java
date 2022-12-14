package com.example.food_project.exception;

public class DeviceZeroException extends RuntimeException{

    public DeviceZeroException(){
        super();
    }

    public DeviceZeroException(String message){
        super(message);
    }

}
