package com.jaloway.jalowayheroku;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String author;
	
	private String title;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
//	@JsonIgnoreProperties("book")
	private String paragraphs;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(String paragraphs) {
		this.paragraphs = paragraphs;
	}

	public int getId() {
		return id;
	}

}
