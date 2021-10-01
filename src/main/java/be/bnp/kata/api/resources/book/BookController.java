package be.bnp.kata.api.resources.book;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.bnp.kata.api.resources.book.request.BookBasketRequest;
import be.bnp.kata.api.resources.book.response.BookBasketResponse;

@RestController
@RequestMapping("/books")
public final class BookController {

    @PostMapping("/cost")
	public BookBasketResponse calculateBasketCost(BookBasketRequest basket) {
		return new BookBasketResponse(0);
	}

}