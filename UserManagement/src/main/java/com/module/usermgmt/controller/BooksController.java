package com.module.usermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.usermgmt.model.Book;
import com.module.usermgmt.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/getall")
	public List<Book> getAllBooks() {

		List<Book> libraryBooks = bookRepository.findAll();

		return libraryBooks;
	}

	@PostMapping("/edit")
	public ResponseEntity<Book> editBook(@RequestBody Book book) {

		Book _book = bookRepository.save(book);
		return new ResponseEntity<>(_book, HttpStatus.CREATED);

	}
}
