package com.module.usermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.usermgmt.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByBookId(long bookId);

}
