package com.mpp.mppbackend.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        cars.add(car);
        car.setUser(this);
    }

    public void removeCar(Car car) {
        cars.remove(car);
        car.setUser(null);
    }

    public void removeAllCars() {
        for (Car car : cars) {
            car.setUser(null);
        }
        cars.clear();
    }

    public void updateCar(Car car) {
        for (Car c : cars) {
            if (c.getId() == car.getId()) {
                c.setName(car.getName());
                c.setType(car.getType());
                c.setDescription(car.getDescription());
            }
        }
    }

    public Car getCar(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public boolean hasCar(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
