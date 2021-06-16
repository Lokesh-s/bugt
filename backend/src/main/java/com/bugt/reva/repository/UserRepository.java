package com.bugt.reva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bugt.reva.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
