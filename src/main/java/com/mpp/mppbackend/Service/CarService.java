package com.mpp.mppbackend.Service;


import com.mpp.mppbackend.Model.Car;

import java.util.List;

public interface CarService {
    void addCar(Car car);

    List<Car> getAllCars();

    Car getCar(int id);

    void updateCar(int id, Car car);

    void deleteCar(int id);


    int getPagesCount(int userId);

    Iterable<Car> getPages(int page, int userId);
}
