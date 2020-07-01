package com.bookshop.bookshop.main.goods.author;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    @Override
    public void createAuthor(Author author) {
    authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);

    }

    @Override
    public Optional<Author> findAuthor(String id) {
        return authorRepository.findById(id);
    }

}
