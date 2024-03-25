package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.Car;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class MemoryRepository implements Repository {
    private final List<Car> cars = new ArrayList<>();

    public MemoryRepository() {
        cars.add(new Car(1, "Opel", "Combi", "5 seats and 2 doors."));
        cars.add(new Car(2, "Renault", "Hatchback", "5 seats and 4 doors."));
        cars.add(new Car(3, "Mercedes", "Coupe", "2 seats and 2 doors."));
        cars.add(new Car(4, "BMW", "Sedan", "5 seats and 4 doors."));
        cars.add(new Car(5, "Audi", "SUV", "5 seats and 4 doors."));
        cars.add(new Car(6, "Volkswagen", "Combi", "5 seats and 2 doors."));
    }
    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCar(int id) {
        return cars.stream().filter(car -> car.getId() == id).findFirst().orElse(null);
    }


    @Override
    public void addCar(Car car) {
        cars.add(car);
    }

    @Override
    public void updateCar(Car car) {
        Car existingCar = getCar(car.getId());
        if (existingCar != null) {
            existingCar.setName(car.getName());
            existingCar.setType(car.getType());
            existingCar.setDescription(car.getDescription());
        }
    }

    @Override
    public void deleteCar(int id) {
        cars.removeIf(car -> car.getId() == id);
    }
}