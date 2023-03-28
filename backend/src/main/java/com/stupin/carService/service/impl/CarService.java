package com.stupin.carService.service.impl;

import com.stupin.carService.converter.CarConverter;
import com.stupin.carService.domain.dao.CarDao;
import com.stupin.carService.domain.dto.Car;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.stupin.carService.converter.CarConverter.carToDto;
import static com.stupin.carService.converter.UserConverter.toDao;
import static com.stupin.carService.converter.UserConverter.toDto;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getByVinCode(String id) {
        return CarConverter.carToDto(carRepository.findByVinCode(id));
    }

    public List<Car> getByUserId(Integer id) {
        List<Car> cars = new ArrayList<>();
        carRepository.findByUserId(id).forEach(it -> cars.add(new Car(it.getId(),
                it.getBrand(), it.getModel(), it.getOdometer(), it.getProductionYear(), toDto(it.getUserDao()))));
        return cars;
    }


    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(it -> cars.add(new Car(it.getId(),
                it.getBrand(), it.getModel(), it.getOdometer(), it.getProductionYear(), toDto(it.getUserDao()))));
        return cars;
    }

    public Car save(Car car) {
        CarDao dao;
        if (car.getId() != null && carRepository.existsById(car.getId())) {
            dao = carRepository.findById(car.getId()).get();
            dao.setBrand(car.getBrand());
            dao.setModel(car.getModel());
            dao.setOdometer(car.getOdometer());
            dao.setProductionYear(car.getProductionYear());
            dao.setUserDao(toDao(car.getUser()));
        } else {
            dao = CarConverter.carToDao(car);
        }
        return carToDto(carRepository.save(dao));
    }
    @Transactional
    public void delete(String id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        }
    }
}
