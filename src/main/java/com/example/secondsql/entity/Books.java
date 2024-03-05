package com.example.secondsql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOOKS_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bookName;

    private String title;

    private boolean live;

    private double price;

}
