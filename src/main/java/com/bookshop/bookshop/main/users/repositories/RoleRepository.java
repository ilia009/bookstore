package com.bookshop.bookshop.main.users.repositories;

import com.bookshop.bookshop.main.users.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
