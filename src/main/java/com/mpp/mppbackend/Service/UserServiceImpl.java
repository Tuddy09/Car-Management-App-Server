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
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userRepository.save(userToUpdate);
        }
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
        return userRepository.findByUsername(user.getUsername()).getId();
    }

    @Override
    public int getCarId(Car car) {
        return carRepository.findCarByNameAndUser(car.getName(), car.getUser()).getId();
    }

    @Override
    public int getPagesCount() {
        long usersCount = userRepository.count();
        return (int) Math.ceil(usersCount / 50.0) - 1;
    }

    @Override
    public Iterable<User> getPages(int page) {
        Pageable pageable = PageRequest.of(page, 50);
        return userRepository.findAll(pageable).getContent();
    }
}
