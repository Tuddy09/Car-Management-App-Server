package com.mpp.mppbackend.Service;


import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Model.User;

import java.util.List;

public interface CarService {
    void addCar(Car car);
    List<Car> getAllCars();
    Car getCar(int id);
    void updateCar(int id, Car car);
    void deleteCar(int id);
    User getUserOfCar(int id);
}
