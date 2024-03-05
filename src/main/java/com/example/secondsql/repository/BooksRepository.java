package com.example.secondsql.repository;

import com.example.secondsql.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {

}
