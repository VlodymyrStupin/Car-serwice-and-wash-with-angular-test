package com.stupin.carService.domain.dto;

public class Validator {
    private static volatile Validator instance = null;

    private Validator(){
        if (instance != null){
            throw new RuntimeException("Not allowed create this instance with word 'new'. Use getInstance() method");
        }
    }
    public static Validator getInstance(){
        if (instance == null){
            synchronized (Validator.class){
                if (instance == null){
                    instance = new Validator();
                }
            }
        }
        return instance;
    }
}
