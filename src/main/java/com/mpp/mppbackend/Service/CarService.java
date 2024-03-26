package com.mpp.mppbackend.Service;



import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final Repository repository;

    public CarService(Repository repository) {
        this.repository = repository;
    }

    public void addCar(Car car) {
        repository.addCar(car);
    }

    public void updateCar(int id, Car car) {
        repository.updateCar(id, car.getName(), car.getType(), car.getDescription());
    }

    public void deleteCar(int id) {
        repository.deleteCar(id);
    }

    public Iterable<Car> getAllCars() {
        return repository.getAllCars();
    }

    public Car getCar(int id) {
        return repository.getCar(id);
    }
}
