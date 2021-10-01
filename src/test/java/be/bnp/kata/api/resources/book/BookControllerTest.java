package be.bnp.kata.api.resources.book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import be.bnp.kata.api.exceptions.UnknownBookException;
import be.bnp.kata.api.model.BookDiscount;
import be.bnp.kata.api.model.BookOrder;
import be.bnp.kata.api.model.Constants;
import be.bnp.kata.api.resources.book.request.BookBasketRequest;
import be.bnp.kata.api.resources.book.response.BookBasketResponse;

@WebMvcTest(BookController.class)
final class BookControllerTest {

	@Autowired
	private MockMvc mvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void calculateBasketCost_for_one_book() throws Exception {

		BookBasketRequest basket = createBasketWithOneBook();
		String jsonRequest = basketToJsonRequest(basket);

		int expectedCost = Constants.BOOK_PRICE;
		String expectedJsonResponse = expectedCostToJsonResponse(expectedCost);

		mvc.perform(post("/books/cost")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonResponse));
	}

	@Test
	public void calculateBasketCost_for_two_different_books() throws Exception {

		BookBasketRequest basket = createBasketWithTwoDifferentBooks();
		String jsonRequest = basketToJsonRequest(basket);

		int expectedCost = calculateCostForDifferentBooksOnly(BookDiscount.TWO_BOOKS);
		String expectedJsonResponse = expectedCostToJsonResponse(expectedCost);

		mvc.perform(post("/books/cost")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonResponse));
	}

	@Test
	public void calculateBasketCost_for_three_different_books() throws Exception {

		BookBasketRequest basket = createBasketWithThreeDifferentBooks();
		String jsonRequest = basketToJsonRequest(basket);

		int expectedCost = calculateCostForDifferentBooksOnly(BookDiscount.THREE_BOOKS);
		String expectedJsonResponse = expectedCostToJsonResponse(expectedCost);

		mvc.perform(post("/books/cost")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonResponse));
	}

	@Test
	public void calculateBasketCost_for_four_different_books() throws Exception {

		BookBasketRequest basket = createBasketWithFourDifferentBooks();
		String jsonRequest = basketToJsonRequest(basket);

		int expectedCost = calculateCostForDifferentBooksOnly(BookDiscount.FOUR_BOOKS);
		String expectedJsonResponse = expectedCostToJsonResponse(expectedCost);

		mvc.perform(post("/books/cost")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonResponse));
	}

	@Test
	public void calculateBasketCost_for_five_different_books() throws Exception {

		BookBasketRequest basket = createBasketWithFiveDifferentBooks();
		String jsonRequest = basketToJsonRequest(basket);

		int expectedCost = calculateCostForDifferentBooksOnly(BookDiscount.FIVE_BOOKS);
		String expectedJsonResponse = expectedCostToJsonResponse(expectedCost);

		mvc.perform(post("/books/cost")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonResponse));
	}

	@Test
	public void calculateBasketCost_for_twice_the_same_book() throws Exception {

		BookBasketRequest basket = createBasketWithTwiceTheSameBook();
		String jsonRequest = basketToJsonRequest(basket);

		int expectedCost = Constants.BOOK_PRICE * 2;
		String expectedJsonResponse = expectedCostToJsonResponse(expectedCost);

		mvc.perform(post("/books/cost")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonResponse));
	}

	@Test
	public void calculateBasketCost_for_a_mix_of_books() throws Exception {

		BookBasketRequest basket = createBasketWithAMixOfBooks();
		String jsonRequest = basketToJsonRequest(basket);

		int expectedCost = calculateCostForTheMixOfBooks();
		String expectedJsonResponse = expectedCostToJsonResponse(expectedCost);

		mvc.perform(post("/books/cost")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJsonResponse));
	}

	@Test
	public void calculateBasketCost_for_an_unknown_book_throws_an_exception() throws Exception {

		BookBasketRequest basket = createBasketWithUnknownBook();

		String json = basketToJsonRequest(basket);

		Assertions.assertThrows(UnknownBookException.class, 
			() -> mvc.perform(post("/books/cost")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)));
	}

	private BookBasketRequest createBasketWithOneBook() {
		return createBookBasketRequest(new int[]{1});
	}

	private BookBasketRequest createBasketWithTwoDifferentBooks() {
		return createBookBasketRequest(new int[]{1, 1});
	}

	private BookBasketRequest createBasketWithThreeDifferentBooks() {
		return createBookBasketRequest(new int[]{1, 1, 1});
	}

	private BookBasketRequest createBasketWithFourDifferentBooks() {
		return createBookBasketRequest(new int[]{1, 1, 1, 1});
	}

	private BookBasketRequest createBasketWithFiveDifferentBooks() {
		return createBookBasketRequest(new int[]{1, 1, 1, 1, 1});
	}

	private BookBasketRequest createBasketWithTwiceTheSameBook() {
		return createBookBasketRequest(new int[]{2});
	}

	private BookBasketRequest createBasketWithAMixOfBooks() {
		// beware that if you change the content of this basket, 
		// you'll need to adapt calculateCostForTheMixOfBooks() accordingly
		return createBookBasketRequest(new int[]{1, 0, 2, 0, 3});
	}

	private BookBasketRequest createBasketWithUnknownBook() {
		return createBookBasketRequest(new int[]{0, 0, 0, 0, 0, 1});
	}
	
	private BookBasketRequest createBookBasketRequest(int... numberOfEachBook) {
		List<BookOrder> books = new ArrayList<>();

		for (int i = 0; i < numberOfEachBook.length; i++) {
			BookOrder bookOrder = new BookOrder(i, numberOfEachBook[i]);
			books.add(bookOrder);
		}

		return new BookBasketRequest(books);
	}

	private String basketToJsonRequest(BookBasketRequest basket) throws JsonProcessingException{
		return objectMapper.writeValueAsString(basket);
	}

	private int calculateCostForDifferentBooksOnly(BookDiscount bookDiscount) {
		int totalCostBeforeDiscount = Constants.BOOK_PRICE * bookDiscount.getNumberOfBooks();
		int costWithDiscount = totalCostBeforeDiscount - (totalCostBeforeDiscount / 100 * bookDiscount.getDiscountInPercent());
		return costWithDiscount;
	}

	private int calculateCostForTheMixOfBooks() {
		return calculateCostForDifferentBooksOnly(BookDiscount.THREE_BOOKS)
			+ calculateCostForDifferentBooksOnly(BookDiscount.TWO_BOOKS)
			+ Constants.BOOK_PRICE;
	}

	private String expectedCostToJsonResponse(int expectedCost) throws JsonProcessingException{
		BookBasketResponse expectedResponse = new BookBasketResponse(expectedCost);
		return objectMapper.writeValueAsString(expectedResponse);
	}

}