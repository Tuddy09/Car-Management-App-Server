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
    public void addCarToUser(int userId, int carId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.addCar(carRepository.findById(carId).orElse(null));
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
}
