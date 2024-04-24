package com.mpp.mppbackend.Service;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Repository.CarRepository;
import com.mpp.mppbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addCarToUser(int userId, Car car) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.addCar(car);
            userRepository.save(user);
        }
    }

    @Override
    public void removeCarFromUser(int carId) {
        //remove car from user
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            User user = car.getUser();
            user.removeCar(car);
            userRepository.save(user);
        }
        //delete car
        carRepository.deleteById(carId);
    }

    @Override
    public List<Car> getCarsByUserId(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getCars();
        }
        return null;
    }

    @Override
    public int getUserId(User user) {
        // TODO! Change this to something more efficient
        List<User> users = (List<User>) userRepository.findAll();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return u.getId();
            }
        }
        return -1;
    }

    @Override
    public int getCarId(Car car) {
        // TODO! Change this to something more efficient
        List<Car> cars = (List<Car>) carRepository.findAll();
        for (Car c : cars) {
            if (c.getName().equals(car.getName()) && c.getType().equals(car.getType()) && c.getDescription().equals(car.getDescription())) {
                return c.getId();
            }
        }
        return -1;
    }

    @Override
    public int getPagesCount() {
        long usersCount = userRepository.count();
        return (int) Math.ceil(usersCount / 50.0);
    }

    @Override
    public Iterable<User> getPages(int page) {
        Pageable pageable = PageRequest.of(page, 50);
        return userRepository.findAll(pageable).getContent();
    }
}
