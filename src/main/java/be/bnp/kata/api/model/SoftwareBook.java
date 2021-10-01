package be.bnp.kata.api.model;

import lombok.Getter;

@Getter
public enum SoftwareBook {

    CLEAN_CODE(0, "Clean Code", "Robert Martin", 2008),
    THE_CLEAN_CODER(1, "The Clean Coder", "Robert Martin", 2011),
    CLEAN_ARCHITECTURE(2, "Clean Architecture", "Robert Martin", 2017),
    TEST_DRIVEN_DEVELOPMENT(3, "Test-Driven Development By Example", "Kent Beck", 2003),
    WORKING_WITH_LEGACY_CODE(4, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
    
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

}
