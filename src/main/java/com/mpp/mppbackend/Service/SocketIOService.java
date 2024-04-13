package com.mpp.mppbackend.Service;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.mpp.mppbackend.Model.Car;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SocketIOService {
    private SocketIOServer server;
    private final CarService carService;

    public SocketIOService(CarService carService) {
        this.carService = carService;
    }

    @PostConstruct
    public void init() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        server = new SocketIOServer(config);
        server.start();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::sendNewCar, 1, 60, TimeUnit.HOURS);
    }

    @PreDestroy
    public void onDestroy() {
        server.stop();
    }

    private void sendNewCar() {
        Faker faker = new Faker();
        String carName = faker.vehicle().manufacturer();
        String carType = faker.vehicle().carType();
        String carDescription = faker.vehicle().doors() + " Doors";
        Car car = new Car();
        car.setName(carName);
        car.setType(carType);
        car.setDescription(carDescription);
        carService.addCar(car);
        server.getBroadcastOperations().sendEvent("newCar", car);
    }
}