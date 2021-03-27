package com.module.usermgmt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "user_books")
@IdClass(UserBookId.class)
public class UserBooks implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

	@JsonIgnore
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "issued_date")
    private Timestamp issuedDate;
    
    @Column(name = "returned_date")
    private Timestamp returnedDate;
    
    @Column(name = "book_count")
    private int bookCount;
    
    
    @Column(name = "book_fine")
    private BigDecimal bookFine;
}
