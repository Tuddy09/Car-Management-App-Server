package com.mpp.mppbackend.Controller;

import com.mpp.mppbackend.Repository.Repository;
import org.springframework.web.bind.annotation.*;
import com.mpp.mppbackend.Model.Car;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controller {
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    @PostMapping("/addCar")
    public void addCar(@RequestParam String name, @RequestParam String type, @RequestParam String description) {
        int id = repository.getAllCars().getLast().getId() + 1;
        repository.addCar(new Car(id, name, type, description));
    }

    @PostMapping("/updateCar")
    public void updateCar(@RequestParam int id, @RequestParam String name, @RequestParam String type, @RequestParam String description) {
        repository.updateCar(new Car(id, name, type, description));
    }

    @PostMapping("/deleteCar")
    public void deleteCar(@RequestParam int id) {
        repository.deleteCar(id);
    }

    @GetMapping("/getAllCars")
    public Iterable<Car> getAllCars() {
        return repository.getAllCars();
    }

    @GetMapping("/getCar")
    public Car getCar(@RequestParam int id) {
        return repository.getCar(id);
    }


}
