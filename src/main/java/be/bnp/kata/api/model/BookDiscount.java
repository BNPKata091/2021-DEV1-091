package be.bnp.kata.api.model;

import lombok.Getter;

@Getter
public enum BookDiscount {

    TWO_BOOKS(2, 5),
    THREE_BOOKS(3, 10),
    FOUR_BOOKS(4, 20),
    FIVE_BOOKS(5, 25);

    private int numberOfBooks;
    private int discountInPercent;

    BookDiscount(int numberOfBooks, int discountInPercent) {
        this.numberOfBooks = numberOfBooks;
        this.discountInPercent = discountInPercent;
    }
    
}
