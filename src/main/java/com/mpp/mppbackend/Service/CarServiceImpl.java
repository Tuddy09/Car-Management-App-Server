package com.mpp.mppbackend.Service;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
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
        carRepository.deleteById(id);
    }

    @Override
    public int getPagesCount(int userId) {
        return (int) Math.ceil(carRepository.countByUserId(userId) / 50.0) - 1;
    }

    @Override
    public Iterable<Car> getPages(int page, int userId) {
        Pageable pageable = PageRequest.of(page, 50);
        return carRepository.findCarByUserId(userId, pageable).getContent();
    }


}
