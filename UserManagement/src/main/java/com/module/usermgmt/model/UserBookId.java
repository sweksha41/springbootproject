package com.module.usermgmt.model;

import java.io.Serializable;

public class UserBookId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public Long getBook() {
		return book;
	}
	public void setBook(Long book) {
		this.book = book;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	private Long book;
	private Long user;
}
