package com.mpp.mppbackend;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Repository.MemoryRepository;
import com.mpp.mppbackend.Repository.Repository;
import com.mpp.mppbackend.Service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    @Mock
    private Repository repository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    public void setUp() {
        repository = new MemoryRepository();
        carService = new CarService(repository);
    }

    @Test
    public void addCarTest() {
        carService.addCar(new Car("Toyota", "SUV", "5 seats and 4 doors."));
        assert (carService.getAllCars().size() == 7);
    }

    @Test
    public void updateCarTest() {
        Car car = carService.getAllCars().get(0);
        carService.updateCar(car.getId(), new Car("Toyota", "SUV", "5 seats and 4 doors."));
        assert (carService.getCar(car.getId()).getName().equals("Toyota"));
    }

    @Test
    public void deleteCarTest() {
        carService.deleteCar(carService.getAllCars().get(0).getId());
        assert (carService.getAllCars().size() == 5);
    }

    @Test
    public void getCarTest() {
        Car car = carService.getAllCars().get(0);
        assert (carService.getCar(car.getId()).getName().equals(car.getName()));
    }

    @Test
    public void getAllCarsTest() {
        assert (carService.getAllCars().size() == 6);
    }

    @Test
    public void initTest() {
        assert (carService.getAllCars().size() == 6);
    }

}
