package be.bnp.kata.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class BookOrder {

    private int bookId;
    private int quantity; 
    
}
