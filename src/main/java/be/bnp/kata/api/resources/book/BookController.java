package be.bnp.kata.api.resources.book;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import be.bnp.kata.api.exceptions.UnknownBookException;
import be.bnp.kata.api.resources.book.request.BookBasketRequest;
import be.bnp.kata.api.resources.book.response.BookBasketResponse;
import be.bnp.kata.api.services.book.BookService;

@Slf4j
@RestController
@RequestMapping("/books")
public final class BookController {

	@Autowired
	private BookService bookService;

    @PostMapping("/cost")
	public BookBasketResponse calculateBasketCost(@RequestBody BookBasketRequest basket) {
		try {
			return new BookBasketResponse(bookService.calculateBasketCost(basket));
		} catch (UnknownBookException e) {
			log.error("Unknown book ID in basket: {}", e.getBookId());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown book ID in basket", e);
		}
	}

}