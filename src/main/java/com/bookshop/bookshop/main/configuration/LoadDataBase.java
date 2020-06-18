package com.bookshop.bookshop.main.configuration;

import com.bookshop.bookshop.main.entities.Book;
import com.bookshop.bookshop.main.repositories.BookRepository;
import java.util.ArrayList;
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
    CommandLineRunner initDatabase(BookRepository repository) { // service or repo

        return args -> {
            log.info("Preloading " + repository.saveAll(generateInitBooks(5)));
        };
    }

    private List<Book> generateInitBooks(int count) {
        return Stream.generate(Book::new).limit(count).collect(Collectors.toList());
    }

}