package com.jaloway.jalowayheroku;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		System.out.println("Endpoint Reached.");
		return "Greetings from Novlr!";
	}

}