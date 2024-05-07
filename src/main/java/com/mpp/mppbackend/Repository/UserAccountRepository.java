package com.mpp.mppbackend.Repository;

import com.mpp.mppbackend.Model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
