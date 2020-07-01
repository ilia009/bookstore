package com.bookshop.bookshop.main.goods.book;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        log.info("save book:{}", book);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void saveListOfBooks(List<Book> listOfBooks) {
        log.info("save {} books", listOfBooks.size());
        bookRepository.saveAll(listOfBooks);
    }

    @Override
    public Book findBook(String id) {
        return bookRepository.getOne(id);
    }


}
