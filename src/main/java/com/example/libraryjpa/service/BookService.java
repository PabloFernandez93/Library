package com.example.libraryjpa.service;

import com.example.libraryjpa.entity.Book;

public interface BookService {

    Book findBookById(int id);
}
