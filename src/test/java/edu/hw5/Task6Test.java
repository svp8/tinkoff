package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @ParameterizedTest
    @CsvSource({"achfdbaabgabcaabg,abc", "assf//daasda,f//d", "tEst,E", "artborc,rt",
        "\\\\Q\\\\Eeee\\\\E,\\\\Q\\\\Eeee\\\\E"})
    void isSubString(String S, String T) {
        Assertions.assertTrue(Task6.isSubString(S, T));
    }

    @ParameterizedTest
    @CsvSource({"achfdbaabgacaabg,abc", "tEst,E t", "artborc,abc", "artborc,", "assfadaasda,f*d"})
    void notSubString(String S, String T) {
        Assertions.assertFalse(Task6.isSubString(S, T));
    }
}
