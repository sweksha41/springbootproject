package com.module.usermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
