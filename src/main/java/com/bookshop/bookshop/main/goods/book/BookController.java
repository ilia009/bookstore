package com.bookshop.bookshop.main.goods.book;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Book> get(@PathVariable("id") String bookId) {
        if (bookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Book book = this.bookService.findBook(bookId);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book) {

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.bookService.saveBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book, UriComponentsBuilder builder) {

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.bookService.saveBook(book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") String id) {
        Book book = this.bookService.findBook(id);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.bookService.deleteBook(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("list")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = this.bookService.findAllBooks();

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
