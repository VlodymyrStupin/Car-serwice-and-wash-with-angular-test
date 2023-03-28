package com.stupin.carService.converter;

import com.stupin.carService.domain.dao.CarDao;

import com.stupin.carService.domain.dto.Car;

import static com.stupin.carService.converter.UserConverter.toDto;
import static com.stupin.carService.converter.UserConverter.toDao;
public class CarConverter {
    public static Car carToDto(CarDao carDao) {
        return new Car(
                carDao.getId(),
                carDao.getBrand(),
                carDao.getModel(),
                carDao.getOdometer(),
                carDao.getProductionYear(),
                toDto(carDao.getUserDao()));

    }

    public static CarDao carToDao(Car car) {
        return new CarDao(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getOdometer(),
                car.getProductionYear(),
                toDao(car.getUser()));
    }
}
