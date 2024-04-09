package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.Car;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class MemoryRepository implements Repository {
    private final List<Car> cars = new ArrayList<>();

    public MemoryRepository() {
        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker();
            String carName = faker.vehicle().manufacturer();
            String carType = faker.vehicle().carType();
            String carDescription = faker.vehicle().doors() + " Doors";
            cars.add(new Car(carName, carType, carDescription));
        }
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