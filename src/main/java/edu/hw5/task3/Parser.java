package edu.hw5.task3;

import java.time.LocalDate;

public interface Parser {
    boolean canParse(String date);

    LocalDate parse(String date);

}
