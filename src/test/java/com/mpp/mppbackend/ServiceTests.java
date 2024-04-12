package com.mpp.mppbackend;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Service.CarService;
import com.mpp.mppbackend.Service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @InjectMocks
    private CarService carService = new CarServiceImpl();

    @BeforeEach
    public void setUp() {
        carService = new CarServiceImpl();
    }

    @Test
    public void addCarTest() {
        Car car = new Car();
        car.setName("Toyota");
        car.setType("SUV");
        car.setDescription("5 seats and 4 doors.");
        carService.addCar(car);
        assert (carService.getCar(car.getId()).getName().equals("Toyota"));
    }

    @Test
    public void updateCarTest() {
        Car car = carService.getAllCars().get(0);
        Car car1 = new Car();
        car1.setName("Toyota");
        car1.setType("SUV");
        car1.setDescription("5 seats and 4 doors.");
        carService.updateCar(car.getId(), car1);
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
