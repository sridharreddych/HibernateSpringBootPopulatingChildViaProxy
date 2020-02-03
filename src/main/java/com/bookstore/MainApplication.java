package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {            
            bookstoreService.addBookToAuthor();
        };
    }
}

/*
 * 
 * How To Populate a Child-Side Parent Association via Proxy

Description: A Hibernate proxy can be useful when a child entity can be persisted with a reference to its parent (@ManyToOne or @OneToOne association). In such cases, fetching the parent entity from the database (execute the SELECT statement) is a performance penalty and a pointless action, because Hibernate can set the underlying foreign key value for an uninitialized proxy.

Key points:

rely on EntityManager#getReference()
in Spring, use JpaRepository#getOne() -> used in this example
in Hibernate, use load()
assume two entities, Author and Book, involved in a unidirectional @ManyToOne association (Author is the parent-side)
we fetch the author via a proxy (this will not trigger a SELECT), we create a new book, we set the proxy as the author for this book and we save the book (this will trigger an INSERT in the book table)
Output example:

the console output will reveal that only an INSERT is triggered, and no SELECT
 * 
 * 
 */
