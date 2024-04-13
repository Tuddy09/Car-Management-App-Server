package com.mpp.mppbackend.Service;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Repository.CarRepository;
import com.mpp.mppbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void removeCarFromUser(int userId, int carId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.removeCar(carRepository.findById(carId).orElse(null));
            userRepository.save(user);
        }
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
    public boolean login(User user) {
        List<User> users = (List<User>) userRepository.findAll();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        userRepository.save(user);
        return false;
    }

    @Override
    public int getUserId(User user) {
        List<User> users = (List<User>) userRepository.findAll();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return u.getId();
            }
        }
        return -1;
    }
}