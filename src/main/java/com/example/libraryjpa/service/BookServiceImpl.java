package com.example.libraryjpa.service;

import com.example.libraryjpa.entity.Book;
import com.example.libraryjpa.exception.TechnicalException;
import com.example.libraryjpa.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Die Methode nimmt eine id entgegen und gibt das Objekt Book (aus
    // der Datenbank) mit der entsprechen id zurück. Falls kein Book mit der id existiert,
    // gibt sie ein neues “leeres” Book zurück

    @Override
    public Book findBookById(int id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    //Add book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    //Add book
    /*
    public void createBook(Book book) {
    if (!bookRepository.findById(book.getId()).isPresent()) {
            bookRepository.save(book);
        }
     */

    //Update book
    public Book updateBook (Book book) {
        if (!bookRepository.findById(book.getId()).isPresent()) {
            throw new TechnicalException("Book with given Id does not exist!", null, HttpStatus.NOT_FOUND );
        }
        bookRepository.save(book);
        return book;
    }

    //Delete book
    public String deleteBookById(int id) {
        bookRepository.deleteById(id);
        return "Book got deleted";
    }


    public List<Book> findAllBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).map(Book::new).collect(Collectors.toList());
    }


    //saveAll?
    /*
    public List<Book> saveAllBooks() {
        return StreamSupport.stream(bookRepository.saveAll(List<Book>).spliterator(), false).map(List<Book>::new).collect(Collectors.toList());
    }

     */


    public List<Book> findByPrice(double price) {
        return bookRepository.findByPrice(price);
    }


    public List<Book> findByPublishedBetween(Date from, Date to) {
        return bookRepository.findByPublishedBetween(from, to);
    }


    public List<Book> findByPublishedBefore(Date cap) {
        return bookRepository.findByPublishedBefore(cap);
    }



}

