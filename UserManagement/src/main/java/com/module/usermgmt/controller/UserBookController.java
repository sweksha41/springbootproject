package com.module.usermgmt.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.module.usermgmt.model.Book;
import com.module.usermgmt.model.User;
import com.module.usermgmt.model.UserBooks;
import com.module.usermgmt.repository.UserBookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userbooks")
public class UserBookController {
	@Autowired
	UserBookRepository userBookRepository;
	
	@PutMapping("/generatefine")
	public ResponseEntity<UserBooks> generateFine(@RequestBody String userBooks) {
		try {
			JsonNode userBookNode = new ObjectMapper().readTree(userBooks);
			
			BigDecimal fine = calculateFine(userBookNode);
			
			Long bookId = userBookNode.get("bookId").asLong();
			Long userId = userBookNode.at("/user/userId").asLong();
			
			User user = new User();
			user.setUserId(userId);
			
			Book book = new Book();
			book.setBookId(bookId);
			
			UserBooks savedUserBooks = userBookRepository.findByUserAndBook(user, book);
			savedUserBooks.setBookFine(fine);
			
			userBookRepository.save(savedUserBooks);
			
			return new ResponseEntity<>(savedUserBooks, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/returnbook")
	public ResponseEntity<UserBooks> returnBook(@RequestBody String userBooks) {
		try {
			JsonNode userBookNode = new ObjectMapper().readTree(userBooks);
			
			//BigDecimal fine = calculateFine(userBookNode);
			
			Long bookId = userBookNode.get("bookId").asLong();
			Long userId = userBookNode.at("/user/userId").asLong();
			
			User user = new User();
			user.setUserId(userId);
			
			Book book = new Book();
			book.setBookId(bookId);
			
			UserBooks savedUserBooks = userBookRepository.findByUserAndBook(user, book);
			
			
			
			savedUserBooks.setReturnedDate(new Timestamp(System.currentTimeMillis()));
			
			userBookRepository.save(savedUserBooks);
			
			return new ResponseEntity<>(savedUserBooks, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	private BigDecimal calculateFine(JsonNode userBookNode) {
		BigDecimal fine = new BigDecimal(0);
		
		Long bookId = userBookNode.get("bookId").asLong();
		ArrayNode allUserBooksNode = (ArrayNode) userBookNode.at("/user/userBooks");
		
		String issuedDateStr = userBookNode.get("issuedDate").asText();
		issuedDateStr = issuedDateStr.substring(0, 10);
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate bookIssuedDate = LocalDate.parse(issuedDateStr, dateformatter);
		
		ZoneId zonedId = ZoneId.of( "Asia/Kolkata" );
		LocalDate today = LocalDate.now( zonedId );
		
		long days = Duration.between(bookIssuedDate.atStartOfDay(), today.atStartOfDay()).toDays();
		if(days > 21)
		{
			JsonNode bookDataJsonNode = null;
			for(JsonNode bookNode : allUserBooksNode)
			{
				if(bookNode.at("/book/bookId").asLong() == bookId)
				{
					bookDataJsonNode = bookNode;
					break;
				}
			}
			if(bookDataJsonNode != null)
			{
				BigDecimal bookPrice = new BigDecimal(bookDataJsonNode.at("/book/bookPrice").asText());
				fine = bookPrice.multiply(new BigDecimal(0.02)).multiply(new BigDecimal(days));
			}
		}
		return fine;
	}
}
