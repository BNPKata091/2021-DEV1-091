package be.bnp.kata.api.resources.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.bnp.kata.api.resources.book.request.BookBasketRequest;
import be.bnp.kata.api.resources.book.response.BookBasketResponse;
import be.bnp.kata.api.services.book.BookService;

@RestController
@RequestMapping("/books")
public final class BookController {

	@Autowired
	private BookService bookService;

    @PostMapping("/cost")
	public BookBasketResponse calculateBasketCost(BookBasketRequest basket) {
		return new BookBasketResponse(bookService.calculateBasketCost(basket));
	}

}