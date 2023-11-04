package edu.hw5;

import java.util.regex.Pattern;

public final class Task7 {
    private Task7() {

    }

    public static boolean symbolsWith3length(String input) {
        Pattern pattern1 = Pattern.compile("^[0,1]{2,}0$");
        return pattern1.matcher(input).matches();

    }

    public static boolean between1and3(String input) {
        Pattern pattern3 = Pattern.compile("^[0,1]{1,3}$");
        return pattern3.matcher(input).matches();

    }

    public static boolean sameStartEnd(String input) {
        Pattern pattern2 = Pattern.compile("^(?<start>[0,1])([0,1]*(\\k<start>))?$");
        return pattern2.matcher(input).matches();

    }
}
