package be.bnp.kata.api.resources.book.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import be.bnp.kata.api.model.BookOrder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class BookBasketRequest {

    List<BookOrder> books;
    
}
