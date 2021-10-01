package be.bnp.kata.api.model;

import lombok.Getter;

import be.bnp.kata.api.exceptions.UnknownBookException;

@Getter
public enum SoftwareBook {

    CLEAN_CODE(23, "Clean Code", "Robert Martin", 2008),
    THE_CLEAN_CODER(425, "The Clean Coder", "Robert Martin", 2011),
    CLEAN_ARCHITECTURE(284, "Clean Architecture", "Robert Martin", 2017),
    TEST_DRIVEN_DEVELOPMENT(393, "Test-Driven Development By Example", "Kent Beck", 2003),
    WORKING_WITH_LEGACY_CODE(447, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
    
    private int id;
    private String title;
    private String author;
    private int year;

    SoftwareBook(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public static SoftwareBook getBookById(int id) throws UnknownBookException {
        for (SoftwareBook book : SoftwareBook.values()) {
            if (book.id == id) return book;
        }
        throw new UnknownBookException();
    }

}
