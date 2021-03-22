package com.module.usermgmt.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "book_id")
    private long bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;
    
    @Column(name = "available_count")
    private int availableCount;
    
    @Column(name = "book_price")
    private BigDecimal bookPrice;
    
  //  @OneToMany(mappedBy = "books")
    //private List<UserBooks> userBooks;
}
