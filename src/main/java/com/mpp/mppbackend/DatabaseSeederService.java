package com.mpp.mppbackend;

import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class DatabaseSeederService {

    @Autowired
    private UserRepository userRepository;

    public void seedDatabase() {
        try {
            System.out.println("Seeding database...");
            int cores = Runtime.getRuntime().availableProcessors();
            System.out.println("Number of processor cores: " + cores);
            ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(cores);
            for (int i = 0; i < 100000; i++) {
                int finalI = i;
                executorService.execute(() -> {
                    User user = new User();
                    user.setUsername("user" + finalI);
                    user.setPassword("password" + finalI);
                    for (int j = 0; j < 10000; j++) {
                        Car car = new Car();
                        car.setName("car" + j);
                        car.setType("type" + j);
                        car.setDescription("description" + j);
                        user.addCar(car);
                    }
                    userRepository.save(user);
                    if (finalI % 1000 == 0)
                        System.out.println("User " + finalI + " saved.");
                });
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}