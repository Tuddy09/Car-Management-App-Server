package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>, CrudRepository<User, Integer>{
}
