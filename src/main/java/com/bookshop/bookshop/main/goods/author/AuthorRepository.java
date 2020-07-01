package com.bookshop.bookshop.main.goods.author;

import com.bookshop.bookshop.main.goods.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {

}
