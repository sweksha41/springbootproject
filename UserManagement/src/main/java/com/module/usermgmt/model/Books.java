package com.module.usermgmt.model;

import java.math.BigDecimal;

import javax.persistence.Column;

public class Books {

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
}
