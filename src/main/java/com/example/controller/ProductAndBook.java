package com.example.controller;


import com.example.secondsql.entity.Books;
import com.example.secondsql.repository.BooksRepository;
import com.example.sql.entity.Product;
import com.example.sql.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/multidb")
public class ProductAndBook {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger=  LoggerFactory.getLogger(ProductAndBook.class);

    @PostMapping("/product")
    public ResponseEntity<Product> saveOrderDetails(@RequestBody Product product){

        Product entity = modelMapper.map(product, Product.class);
        return ResponseEntity.ok(productRepository.save(entity));
    }

    @PostMapping("/books")
    public ResponseEntity<Books> saveOrderDetails(@RequestBody Books books){

        Books entity = modelMapper.map(books, Books.class);
        return ResponseEntity.ok(booksRepository.save(entity));
    }

    @GetMapping("/product")
    public List<Product> getOrders(){
        logger.info("This is Example file are one");
        return productRepository.findAll();
    }

}
