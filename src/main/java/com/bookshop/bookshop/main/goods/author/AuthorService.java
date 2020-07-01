package com.bookshop.bookshop.main.goods.author;

import java.util.Optional;

public interface AuthorService {

    void createAuthor(Author author);

    void deleteAuthor(String id);

    Optional<Author> findAuthor(String id);

}
