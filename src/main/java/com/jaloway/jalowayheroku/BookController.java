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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({"*", "http://localhost:4200"})
public class BookController {
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	ObjectMapper mapper;
	
	int bookChunkSize = 20;
	
	List<Book> sortedBookList;
	
	@Autowired
	private void sortSortedBookList() {
	System.out.println("Sorting...");
	List<Book> list = bookRepo.findAll();
	list.sort((left, right) -> left.getId() - right.getId());
	sortedBookList = list;
	}
	
	private ObjectNode paraStringToArrayNode(ObjectNode bookNode) throws JsonProcessingException {
		if (bookNode.has("paragraphs")) {
			String paraString = bookNode.get("paragraphs").asText();
			if (paraString != null) {
				ArrayNode paraArrayNode = mapper.readValue(paraString, ArrayNode.class);
				if (paraArrayNode != null) {
					bookNode.remove("paragraphs");
					bookNode.putArray("paragraphs").addAll(paraArrayNode);
				}
			}
		}
		return bookNode;
	}
	
	private Book stringToBook(String bookAsString) throws JsonProcessingException {
		JsonNode node = mapper.readTree(bookAsString);
		Book book = new Book();
		String authorString = new String();
		if (node.has("author")) {
			authorString = node.get("author").asText();
			book.setAuthor(authorString);
		}
		if (node.has("title")) {
			String titleString = node.get("title").asText();
			book.setTitle(titleString);
		}
		if (node.has("paragraphs")) {
			JsonNode arrNode = node.get("paragraphs");
			book.setParagraphs(arrNode.toString());
		}
		return book;
	}
	
	@GetMapping("/")
	public ObjectNode index() {
		int listSize = sortedBookList.size();
		ObjectNode objectNode = mapper.createObjectNode();
		ArrayNode arrayNode = mapper.createArrayNode();
		try {
			for (int i = 0; i < listSize; i++) {
				ObjectNode thisBookNode = mapper.valueToTree(sortedBookList.get(i));
				thisBookNode = paraStringToArrayNode(thisBookNode);
				arrayNode.add(thisBookNode);
			}
			objectNode.putArray("books").addAll(arrayNode);
		} catch (Exception e) {
			e.printStackTrace();
			objectNode.put("fail result", e.toString());
		}
		return objectNode;
	}
	
	@GetMapping("/{paginationNumber}")
	public ObjectNode getbook(@PathVariable int paginationNumber) {
		int listSize = sortedBookList.size();
		ObjectNode objectNode = mapper.createObjectNode();
		ArrayNode arrayNode = mapper.createArrayNode();
		int startingPoint = paginationNumber * bookChunkSize;
		int endingPoint = startingPoint + bookChunkSize;
		boolean hasMoreBooks = true;
		if (endingPoint >= listSize) {
			endingPoint = listSize;
			hasMoreBooks = false;
		}
		try {
			for (int i = startingPoint; i < endingPoint; i++) {
				ObjectNode thisBookNode = mapper.valueToTree(sortedBookList.get(i));
				thisBookNode = paraStringToArrayNode(thisBookNode);
				arrayNode.add(thisBookNode);
			}
			objectNode.putArray("books").addAll(arrayNode);
			JsonNode hasMoreBooksObject = mapper.convertValue(hasMoreBooks, JsonNode.class);
			objectNode.set("hasMoreBooks", hasMoreBooksObject);
		} catch (Exception e) {
			e.printStackTrace();
			objectNode.put("fail result", e.toString());
		}
		return objectNode;
	}
		
	@PostMapping("/")
	public ObjectNode post(@RequestBody String bookAsString) {
		ObjectNode objectNode = mapper.createObjectNode();
		try {
			Book book = stringToBook(bookAsString);
			bookRepo.saveAndFlush(book);
			objectNode = mapper.convertValue(book, ObjectNode.class);
			objectNode = paraStringToArrayNode(objectNode);
		} catch (Exception e) {
			e.printStackTrace();
			objectNode.put("fail result", e.toString());
		}
		sortSortedBookList();
		return objectNode;
	}
	
	
	@PatchMapping(path = "/{id}")
	public ObjectNode patch(@PathVariable int id, @RequestBody String newBookAsString) {
		ObjectNode objectNode = mapper.createObjectNode();
		Optional<Book> optional = bookRepo.findById(id);
		Book updateBook = new Book();
		if (optional.isPresent()) {
			updateBook = optional.get();
			try {
				Book newBook = stringToBook(newBookAsString);
				if (newBook.getAuthor() != null) {
					updateBook.setAuthor(newBook.getAuthor());
				}
				if (newBook.getTitle() != null) {
					updateBook.setTitle(newBook.getTitle());
				}
				if (newBook.getParagraphs() != null) {
					updateBook.setParagraphs(newBook.getParagraphs());
				}
				bookRepo.saveAndFlush(updateBook);
				objectNode = mapper.convertValue(updateBook, ObjectNode.class);
				objectNode = paraStringToArrayNode(objectNode);
			} catch (Exception e) {
				e.printStackTrace();
				objectNode.put("fail result", e.toString());
			}
		}
		else {
			objectNode.put("fail result", "book not found");
		}
		sortSortedBookList();
		return objectNode;
	}
	
	@DeleteMapping("/{id}")
	public ObjectNode delete(@PathVariable int id) {
		ObjectNode returnObjectNode = mapper.createObjectNode();
		try {
			bookRepo.deleteById(id);
			returnObjectNode.put("deleted", id);
		} catch (Exception e) {
			e.printStackTrace();
			returnObjectNode.put("fail result", e.toString());
		}
		sortSortedBookList();
		return returnObjectNode;
	}

}