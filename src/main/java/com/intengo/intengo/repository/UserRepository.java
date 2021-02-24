package com.intengo.intengo.repository;

import com.intengo.intengo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);
}
