package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<Car, Integer>, CrudRepository<Car, Integer> {
    Page<Car> findCarByUserId(int userId, Pageable pageable);

    int countByUserId(int userId);

    Car findCarByNameAndUser(String name, User user);
}