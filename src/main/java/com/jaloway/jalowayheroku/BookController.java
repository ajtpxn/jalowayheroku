package com.jaloway.jalowayheroku;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@CrossOrigin({"*", "http://localhost:4200"})
public class BookController {
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	ObjectMapper mapper;

	int bookChunkSize = 3;
	
	@GetMapping("/")
	public ObjectNode index() {
		ObjectNode objectNode = mapper.createObjectNode();
		ArrayNode arrayNode = mapper.createArrayNode();
		List<Book> list = bookRepo.findAll();
		try {
			arrayNode = mapper.valueToTree(list);
			objectNode.putArray("Main Node").addAll(arrayNode);
		} catch (Exception e) {
			System.out.println(e.toString());
			objectNode.put("fail result", e.toString());
		}
		return objectNode;
	}
	
	@GetMapping("/{paginationNumber}")
	public ObjectNode getbook(@PathVariable int paginationNumber) {
		List<Book> list = bookRepo.findAll();
		int listSize = list.size();
		System.out.println("listSize: " + listSize);
		ObjectNode objectNode = mapper.createObjectNode();
		ArrayNode arrayNode = mapper.createArrayNode();
		int startingPoint = paginationNumber * bookChunkSize;
		System.out.println("startingPoint: " + startingPoint);
		int endingPoint = startingPoint + bookChunkSize;
		System.out.println("endingPoint: " + endingPoint);
		boolean hasMoreBooks = true;
		if (endingPoint >= listSize) {
			endingPoint = listSize;
			System.out.println("adjusted endingPoint: " + endingPoint);
			hasMoreBooks = false;
		}
		try {
			for (int i = startingPoint; i < endingPoint; i++) {
				ObjectNode thisBookNode = mapper.valueToTree(list.get(i));
				arrayNode.add(thisBookNode);
			}
			objectNode.putArray("Main Node").addAll(arrayNode);
			JsonNode hasMoreBooksObject = mapper.convertValue(hasMoreBooks, JsonNode.class);
			objectNode.set("hasMoreBooks", hasMoreBooksObject);
		} catch (Exception e) {
			System.out.println(e.toString());
			objectNode.put("fail result", e.toString());
		}
		return objectNode;
	}
		
	@PostMapping("/")
	public ObjectNode post(@RequestBody String bookAsString) {
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			JsonNode node = mapper.readTree(bookAsString);
			String authorString = node.get("author").asText();
			Book book = new Book();
			book.setAuthor(authorString);
			System.out.println(authorString);
			String titleString = node.get("title").asText();
			book.setTitle(titleString);
			System.out.println(titleString);
			JsonNode arrNode = node.get("paragraphs");
			book.setParagraphs(arrNode.toString());
			System.out.println(arrNode.toString());
			bookRepo.saveAndFlush(book);
			objectNode = mapper.convertValue(book, ObjectNode.class);
		} catch (Exception e) {
			System.out.println(e.toString());
			objectNode.put("fail result", e.toString());
		}
		return objectNode;
	}
	
	@PatchMapping(path = "/{id}")
	public ObjectNode patch(@PathVariable int id, @RequestBody Book newBook) {
		ObjectNode objectNode = mapper.createObjectNode();
		Optional<Book> optional = bookRepo.findById(id);
		Book updateBook = new Book();
		if (optional.isPresent()) {
			updateBook = optional.get();
			try {
				updateBook.setAuthor(newBook.getAuthor());
				updateBook.setTitle(newBook.getTitle());
				updateBook.setParagraphs(newBook.getParagraphs());
				bookRepo.saveAndFlush(updateBook);
				objectNode = mapper.convertValue(updateBook, ObjectNode.class);
			} catch (Exception e) {
				System.out.println(e.toString());
				objectNode.put("fail result", e.toString());
			}
		}
		else {
			objectNode.put("fail result", "book not found");
		}
		return objectNode;
	}
	
	@DeleteMapping("/{id}")
	public ObjectNode delete(@PathVariable int id) {
		System.out.println("Endpoint Reached. Delete Mapping. Id: " + id);
		ObjectNode returnObjectNode = mapper.createObjectNode();
		try {
			bookRepo.deleteById(id);
			returnObjectNode.put("deleted", id);
		} catch (Exception e) {
			System.out.println(e.toString());
			returnObjectNode.put("fail result", e.toString());
		}
		return returnObjectNode;
	}

}