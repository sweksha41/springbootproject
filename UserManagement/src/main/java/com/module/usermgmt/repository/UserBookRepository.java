package com.module.usermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.usermgmt.model.Book;
import com.module.usermgmt.model.User;
import com.module.usermgmt.model.UserBooks;

@Repository
public interface UserBookRepository extends JpaRepository<UserBooks, Long> {

	UserBooks findByUserAndBook(User userId, Book bookId);

}
