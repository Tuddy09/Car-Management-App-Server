package com.mpp.mppbackend.Service;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Model.UserAccount;

import java.util.List;

public interface UserService {
    void addUser(User user, UserAccount useAccount);
    List<User> getAllUsers();
    User getUser(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);

    void addCarToUser(int userId, Car car);
    void removeCarFromUser(int carId);
    List<Car> getCarsByUserId(int userId);

    int getUserId(User user);


    int getCarId(Car car);

    int getPagesCount(UserAccount useAccount);

    Iterable<User> getPages(int page, UserAccount useAccount);
}
