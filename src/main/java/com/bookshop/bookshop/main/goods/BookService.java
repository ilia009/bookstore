package com.bookshop.bookshop.main.goods;

import java.util.List;

public interface BookService {

    void saveBook(Book book);

    void deleteBook(String id);

    void saveListOfBooks(List<Book> listOfBooks);

    Book findBook(String id);

    List<Book> findAllBooks();


}
