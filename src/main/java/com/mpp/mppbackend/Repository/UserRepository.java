package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Model.UserAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>, CrudRepository<User, Integer>{
    User findByUsername(String username);
    int countByUserAccount(UserAccount userAccount);
    List<User> findAllByUserAccount(UserAccount userAccount, Pageable pageable);
}
