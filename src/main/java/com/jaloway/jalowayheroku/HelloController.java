package com.jaloway.jalowayheroku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	BookRepo bookRepo;

	int bookChunkSize = 3;
	
	@GetMapping("/{pageNumber}")
	public String index(@PathVariable int pageNumber) {
		System.out.println("Endpoint Reached. Get mapping. Page number: " + pageNumber);
		return "Greetings from Novlr! Get mapping. Page number: " + pageNumber;
	}
		
	@PostMapping("/addbook")
	public String post(@RequestBody Book book) {
		System.out.println("Endpoint Reached. Post mapping.");
		System.out.println("author: " + book.getAuthor());
		System.out.println("title: " + book.getTitle());
		bookRepo.saveAndFlush(book);
		return "Greetings from Novlr! Post mapping.";
	}
	
	@PatchMapping("/{id}")
	public String patch(@PathVariable int id) {
		System.out.println("Endpoint Reached. Patch mapping. Id: " + id);
		return "Greetings from Novlr! Patch mapping. Id: " + id;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		System.out.println("Endpoint Reached. Delete Mapping. Id: " + id);
		return "Greetings from Novlr! Delete Mapping. Id: " + id;
	}

}