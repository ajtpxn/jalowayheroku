package com.jaloway.jalowayheroku;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	int bookChunkSize = 3;
	
	@GetMapping("/{pageNumber}")
	public String index(@PathVariable int pageNumber) {
		System.out.println("Endpoint Reached. Get mapping. Page number: " + pageNumber);
		return "Greetings from Novlr! Get mapping. Page number: " + pageNumber;
	}
		
	@PostMapping("/")
	public String post() {
		System.out.println("Endpoint Reached. Post mapping.");
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