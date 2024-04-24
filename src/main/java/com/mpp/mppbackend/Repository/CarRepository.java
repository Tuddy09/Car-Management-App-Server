package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<Car, Integer>, CrudRepository<Car, Integer> {

}