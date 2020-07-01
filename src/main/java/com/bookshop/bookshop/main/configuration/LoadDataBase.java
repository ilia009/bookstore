package com.bookshop.bookshop.main.configuration;

import com.bookshop.bookshop.main.goods.book.Book;
import com.bookshop.bookshop.main.goods.book.BookRepository;
import com.bookshop.bookshop.main.users.model.Role;
import com.bookshop.bookshop.main.users.model.Status;
import com.bookshop.bookshop.main.users.model.User;
import com.bookshop.bookshop.main.users.repositories.RoleRepository;
import com.bookshop.bookshop.main.users.repositories.UserRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, UserRepository userRepository,
            RoleRepository roleRepository) {
        return args -> {
            loadUsers(userRepository);
     loadRoles(roleRepository);
            loadBooks(bookRepository);
        };
    }

    private void loadUsers(UserRepository userRepository) {
        userRepository
                .save(new User(1L, new Date(), new Date(), Status.ACTIVE, "illia", "Illia", "Illia", "email@email",
                        "$2a$10$w/yJre.7JpyZRQ0WOGwFPOMRd09fjREOC6e598uLxUcTJbImCRCCG", Collections.singletonList(new Role(1L,new Date(), new Date(), Status.ACTIVE,"ADMIN"))));
        userRepository
                .save(new User(2L, new Date(), new Date(), Status.ACTIVE, "oleg", "oleg", "oleginko", "email@email",
                        "$2a$10$LuxmqLIb5NNJeaU3M3d/NeSO0zoDhqxLQw4B2Ra7xV31r5v2qEvi.", Collections.singletonList(new Role(2L,new Date(), new Date(), Status.ACTIVE,"USER"))));

        List<User> users = userRepository.findAll();
        log.info("Users was preloaded: {}", users.size());
        users.forEach(user -> log.info("User Id and username: {}, {}", user.getId(), user.getUsername()));
    }

    private void loadRoles(RoleRepository roleRepository) {
        roleRepository
                .save(new Role(1L,new Date(), new Date(), Status.ACTIVE,"ADMIN"));
        roleRepository
                .save(new Role(2L,new Date(), new Date(), Status.ACTIVE,"ADMIN"));

        List<Role> roles = roleRepository.findAll();
        log.info("Roles was preloaded: {}", roles.size());
    }

    private void loadBooks(BookRepository bookRepository) {
        bookRepository.save(new Book("1L", "Master & Margarita", 220));
        bookRepository.save(new Book("2L", "Criticism of Pure Reason", 214));
        bookRepository.save(new Book("3L", "Sovereign", 432));
        bookRepository.save(new Book("4L", "Red Hat", 12));
        bookRepository.save(new Book("5L", "Autobiorafy",120 ));

        List<Book> books = bookRepository.findAll();
        log.info("Books was preloaded: {}", books.size());
    }

}