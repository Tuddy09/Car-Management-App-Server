package com.mpp.mppbackend.Service;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;
    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car getCar(int id) {
        if (carRepository.findById(id).isPresent()) {
            return carRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void updateCar(int id, Car car) {
        if (carRepository.findById(id).isPresent()) {
            car.setUser(carRepository.findById(id).get().getUser());
            carRepository.save(car);
        }
    }

    @Override
    public void deleteCar(int id) {
        if (carRepository.findById(id).isPresent()) {
            carRepository.deleteById(id);
        }
    }

    @Override
    public User getUserOfCar(int id) {
        if (carRepository.findById(id).isPresent()) {
            return carRepository.findById(id).get().getUser();
        }
        return null;
    }
}
