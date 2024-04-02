package com.mpp.mppbackend;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Repository.MemoryRepository;
import com.mpp.mppbackend.Repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepositoryTests {
    private Repository repository;

    @BeforeEach
    public void setUp() {
        repository = new MemoryRepository();
    }

    @Test
    public void initTest() {
        assert (repository.getAllCars().size() == 6);
    }

    @Test
    public void addCarTest() {
        repository.addCar(new Car("Toyota", "SUV", "5 seats and 4 doors."));
        assert (repository.getAllCars().size() == 7);
    }

    @Test
    public void updateCarTest() {
        Car car = repository.getAllCars().get(0);
        repository.updateCar(car.getId(), "Toyota", "SUV", "5 seats and 4 doors.");
        assert (repository.getCar(car.getId()).getName().equals("Toyota"));
    }

    @Test
    public void deleteCarTest() {

        repository.deleteCar(repository.getAllCars().get(0).getId());
        assert (repository.getAllCars().size() == 5);
    }

    @Test
    public void getCarTest() {
        Car car = repository.getAllCars().get(0);
        assert (repository.getCar(car.getId()).getName().equals(car.getName()));
    }

    @Test
    public void getAllCarsTest() {
        assert (repository.getAllCars().size() == 6);
    }
}
