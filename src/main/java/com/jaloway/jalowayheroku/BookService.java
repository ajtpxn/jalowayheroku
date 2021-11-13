package com.jaloway.jalowayheroku;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	BookRepo bookRepo;
	
	public List getSortedBookList() {
	List<Book> list = bookRepo.findAll();
	list.sort((left, right) -> left.getId() - right.getId());
	System.out.println(list);
	return list;
	}

}
