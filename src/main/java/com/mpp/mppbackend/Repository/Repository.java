package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.Car;

import java.util.List;

public interface Repository {
    List<Car> getAllCars();

    Car getCar(int id);

    void addCar(Car car);

    void updateCar(int id, String name, String type, String description);

    void deleteCar(int id);
}