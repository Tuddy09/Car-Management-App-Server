package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
