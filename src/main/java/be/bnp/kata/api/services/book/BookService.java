package be.bnp.kata.api.services.book;

import org.springframework.stereotype.Service;

import be.bnp.kata.api.exceptions.UnknownBookException;
import be.bnp.kata.api.model.BookDiscount;
import be.bnp.kata.api.model.BookOrder;
import be.bnp.kata.api.model.Constants;
import be.bnp.kata.api.model.SoftwareBook;
import be.bnp.kata.api.resources.book.request.BookBasketRequest;

@Service
public final class BookService {

    public double calculateBasketCost(BookBasketRequest basket) throws UnknownBookException {
        double totalCost = 0;

        if (basket.getBooks() != null) {
            System.out.println("basket not empty");

            int[] numberOfEachBook = getNumberOfEachBook(basket);
            
            boolean emptyBasket = false;
            
            while (!emptyBasket) {
                
                // get a set of as many different books as possible and remove them from the basket
                int numberOfDifferentBooks = getNumberOfDifferentBooksInRemainingBasket(numberOfEachBook);
                
                // calculate the cost of this set and add it to the total
                if (numberOfDifferentBooks > 0) {
                    totalCost += getCostofSet(numberOfDifferentBooks);
                } else { // if no book was found, the basket is empty
                    emptyBasket = true;
                }
                
            }
        }

        return totalCost;
    }

    private int[] getNumberOfEachBook(BookBasketRequest basket) throws UnknownBookException {
        int[] numberOfEachBook = new int[SoftwareBook.values().length];

        for (BookOrder bookOrder : basket.getBooks()) {
            SoftwareBook book = SoftwareBook.getBookById(bookOrder.getBookId());
            numberOfEachBook[book.ordinal()] = bookOrder.getQuantity();
        }
        
        return numberOfEachBook;
    }

    private int getNumberOfDifferentBooksInRemainingBasket(int[] numberOfEachBook) {
        int numberOfDifferentBooks = 0;

        for (int i = 0; i < numberOfEachBook.length; i++) {
            if (numberOfEachBook[i] > 0) {
                ++numberOfDifferentBooks;
                --numberOfEachBook[i];
            }                
        }

        return numberOfDifferentBooks;
    }

    private double getCostofSet(int numberOfDifferentBooks) {
        double costBeforeDiscount = Constants.BOOK_PRICE * numberOfDifferentBooks;

        int discountInPercent = BookDiscount.getDiscountByNumberOfBooks(numberOfDifferentBooks);

        double costWithDiscount = costBeforeDiscount - (costBeforeDiscount / 100 * discountInPercent);

        return costWithDiscount;
    }

}
