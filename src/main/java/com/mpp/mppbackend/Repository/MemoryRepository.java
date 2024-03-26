package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.Car;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class MemoryRepository implements Repository {
    private final List<Car> cars = new ArrayList<>();

    public MemoryRepository() {
        cars.add(new Car("Opel", "Combi", "5 seats and 2 doors."));
        cars.add(new Car("Renault", "Hatchback", "5 seats and 4 doors."));
        cars.add(new Car("Mercedes", "Coupe", "2 seats and 2 doors."));
        cars.add(new Car( "BMW", "Sedan", "5 seats and 4 doors."));
        cars.add(new Car("Audi", "SUV", "5 seats and 4 doors."));
        cars.add(new Car("Volkswagen", "Combi", "5 seats and 2 doors."));
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
    public void updateCar(int id, String name, String type, String description) {
        cars.stream().filter(car -> car.getId() == id).findFirst().ifPresent(car -> {
            car.setName(name);
            car.setType(type);
            car.setDescription(description);
        });
    }

    @Override
    public void deleteCar(int id) {
        cars.removeIf(car -> car.getId() == id);
    }
}