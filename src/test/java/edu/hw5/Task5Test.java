package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777","О777ОО177","Р666РР02"})
    void validatePlateNumber(String number) {
        Assertions.assertTrue(Task5.validatePlateNumber(number));
    }
    @ParameterizedTest
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77","А123ВЕ7777",""," "})
    void validatePlateNumberInvalid(String number) {
        Assertions.assertFalse(Task5.validatePlateNumber(number));
    }
}
