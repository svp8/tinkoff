package edu.hw5;

public final class Task5 {
    private Task5() {

    }

    public static boolean validatePlateNumber(String number) {
        return number != null && number.matches("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
    }
}
