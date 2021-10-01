package be.bnp.kata.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class BookOrder {

    private int bookId;
    private int quantity; 
    
}
