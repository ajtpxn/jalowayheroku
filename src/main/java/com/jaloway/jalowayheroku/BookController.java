package com.jaloway.jalowayheroku;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class BookController {
	
	@Autowired
	BookRepo bookRepo;

	int bookChunkSize = 3;
	
	@GetMapping("/")
	public String index() {
		System.out.println("Endpoint Reached. Index mapping.");
		List<Book> list = bookRepo.findAll();
		for (Book book : list) {
			System.out.println("id: " + book.getId());
			System.out.println("author: " + book.getAuthor());
			System.out.println("title: " + book.getTitle());
			System.out.println("paragraphs: " + book.getParagraphs());
		}
		return "Greetings from Novlr! Index mapping.";
	}
	
	@GetMapping("/{pageNumber}")
	public String getbook(@PathVariable int id) {
		System.out.println("Endpoint Reached. Get mapping.");
		Book book = bookRepo.getById(id);
		return "Greetings from Novlr! Get mapping.";
	}
		
	@PostMapping("/")
	public String post(@RequestBody Book book) {
		System.out.println("Endpoint Reached. Post mapping.");
		System.out.println("author: " + book.getAuthor());
		System.out.println("title: " + book.getTitle());
		bookRepo.saveAndFlush(book);
		return "Greetings from Novlr! Post mapping.";
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
	public String patch(@PathVariable int id, @RequestBody Book book) {
		System.out.println("Endpoint Reached. Patch mapping. Id: " + id);
		bookRepo.getById(id);
		bookRepo.saveAndFlush(book);
		return "Greetings from Novlr! Patch mapping. Id: " + id;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		System.out.println("Endpoint Reached. Delete Mapping. Id: " + id);
		bookRepo.deleteById(id);
		return "Greetings from Novlr! Deleted book: " + id;
	}

}