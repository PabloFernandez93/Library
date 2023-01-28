package com.example.libraryjpa.repository;

import com.example.libraryjpa.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByPublishedBetween(Date from, Date to);

    List<Book> findByPrice(double price);

    List<Book> findByPublishedBefore(Date cap);

    Book findBookById(int id);

}
