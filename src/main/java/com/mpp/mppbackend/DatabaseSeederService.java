package com.mpp.mppbackend;

import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DatabaseSeederService {

    @Autowired
    private UserRepository userRepository;

    public void seedDatabase() {
        try {
            System.out.println("Seeding database...");
            int cores = Runtime.getRuntime().availableProcessors();
            ExecutorService executorService = Executors.newFixedThreadPool(cores);
            for (int i = 0; i < 100000; i++) {
                int finalI = i;
                executorService.submit(() -> {
                    User user = new User();
                    user.setId(finalI);
                    user.setUsername("user" + finalI);
                    user.setPassword("password" + finalI);
                    for (int j = 0; j < 10000; j++) {
                        Car car = new Car();
                        car.setId(j);
                        car.setUser(user);
                        car.setType("type" + j);
                        car.setDescription("description" + j);
                        user.addCar(car);
                    }
                    userRepository.save(user);
                    // print progress
                    if (finalI % 1000 == 0) {
                        System.out.println("Progress: " + finalI);
                    }
                });
            }
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                //wait
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}