package com.bookshop.bookshop.main.service;

import com.bookshop.bookshop.main.entities.Book;
import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    void saveBook(Book book);

    void deleteBook(String id);

    void saveListOfBooks(List<Book> listOfBooks);

    Book findBook(String id);

    List<Book> findAllBooks();


}
