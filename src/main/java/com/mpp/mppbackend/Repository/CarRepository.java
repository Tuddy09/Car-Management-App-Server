package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {

}