package be.bnp.kata.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class UnknownBookException extends Exception {

    private int bookId;
    
}
