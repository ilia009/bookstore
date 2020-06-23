package com.bookshop.bookshop.main.configuration;

import com.bookshop.bookshop.main.goods.Book;
import com.bookshop.bookshop.main.goods.BookRepository;
import com.bookshop.bookshop.main.users.model.Role;
import com.bookshop.bookshop.main.users.model.User;
import com.bookshop.bookshop.main.users.repositories.RoleRepository;
import com.bookshop.bookshop.main.users.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, UserRepository userRepository, RoleRepository roleRepository) {

        return args -> {
            log.info("Preloading books" + bookRepository.saveAll(Stream.generate(Book::new).limit(5).collect(Collectors.toList())));
            log.info("Preloading users" + userRepository.saveAll(Stream.generate(User::new).limit(1).collect(Collectors.toList())));
            log.info("Preloading roles for user" + roleRepository.saveAll(Stream.generate(Role::new).limit(1).collect(Collectors.toList())));
        };
    }
}