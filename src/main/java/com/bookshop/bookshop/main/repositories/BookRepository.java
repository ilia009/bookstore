package com.bookshop.bookshop.main.repositories;

import com.bookshop.bookshop.main.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
