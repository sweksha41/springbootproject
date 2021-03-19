package com.module.usermgmt.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_books")
public class UserBooks {

	@Column(name = "user_id")
    private long userId;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "issued_date")
    private Timestamp issuedDate;
    
    @Column(name = "returned_date")
    private Timestamp returnedDate;
    
    @Column(name = "book_count")
    private int bookCount;
    
    
    @Column(name = "book_fine")
    private BigDecimal bookFine;
}
