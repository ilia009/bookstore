package com.bookshop.bookshop.main.users.repositories;

import com.bookshop.bookshop.main.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
