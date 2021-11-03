//package com.jaloway.jalowayheroku;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class Paragraph {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="book_id")
//	private Book book;
//	
//	@Column(name="paragraph_text")
//	private String paragraphText;
//
//	public Book getBook() {
//		return book;
//	}
//
//	public void setBook(Book book) {
//		this.book = book;
//	}
//
//	public String getParagraphText() {
//		return paragraphText;
//	}
//
//	public void setParagraphText(String paragraphText) {
//		this.paragraphText = paragraphText;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//
//	
//}
