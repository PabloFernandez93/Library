package com.example.libraryjpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;
    @Column
    private Date published;
    @Column
    private int page_count;
    @Column
    private double price;

    public Book(Book book) {
    }

    public Book() {

    }

}
