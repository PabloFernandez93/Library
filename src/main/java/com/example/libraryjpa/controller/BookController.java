package com.example.libraryjpa.controller;

import com.example.libraryjpa.entity.Book;
import com.example.libraryjpa.exception.TechnicalException;
import com.example.libraryjpa.service.BookServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;




@RestController
@RequestMapping("/api/book")
public class BookController {

    private BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }


    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookServiceImpl.findBookById(id);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookServiceImpl.findAllBooks();
    }

    @PostMapping("/addbook")
    public Book addBook (@RequestBody Book book) {
        return bookServiceImpl.createBook(book);
    }

    /*
    @PostMapping("/addbooks")
    public List<Book> addBooks (@RequestBody List<Book> books) {
        return bookServiceImpl.createBooks(books);
    }

     */

    @PutMapping("/updatebook")
    public Book updateBook (@RequestBody Book book) {
        return bookServiceImpl.updateBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookServiceImpl.deleteBookById(id);
    }

    @GetMapping("/findbyprice/{price}")
    public List<Book> findByPrice(@PathVariable double price) {
        return bookServiceImpl.findByPrice(price);
    }

    @GetMapping("/findbypublished")
    public List<Book> findByPublishedBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        System.out.println("From " + from + "    To " + to);
        return bookServiceImpl.findByPublishedBetween(from, to);
    }

    @ExceptionHandler({TechnicalException.class})
    public ResponseEntity<String> handleException(TechnicalException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @GetMapping("/findbypublishedbefore")
    List<Book> findByPublishedBefore(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date cap) {
        return bookServiceImpl.findByPublishedBefore(cap);
    }

}

