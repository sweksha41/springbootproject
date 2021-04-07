package com.module.usermgmt.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/getavailable")
	public List<Book> getAvailableBooks() {

		List<Book> libraryBooks = getAllBooks();

		List<Book> availableBooks = libraryBooks.stream().filter(book -> book.getAvailableCount() > 0)
				.collect(Collectors.toList());

		return availableBooks;
	}

	@PutMapping("/edit")
	public ResponseEntity<Book> editBook(@RequestBody Book book) {

		Book savedBook = bookRepository.findByBookId(book.getBookId());
		savedBook.setAvailableCount(book.getAvailableCount());
		savedBook.setBookPrice(book.getBookPrice());

		Book _book = bookRepository.save(savedBook);
		return new ResponseEntity<>(_book, HttpStatus.CREATED);

	}
}
