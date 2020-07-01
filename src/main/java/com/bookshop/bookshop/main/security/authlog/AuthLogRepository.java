package com.bookshop.bookshop.main.security.authlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthLogRepository extends JpaRepository<AuthLog, String> {

}
