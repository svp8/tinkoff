package edu.hw1;

public final class Task1 {
    private Task1() {

    }

    private static final int SECONDS_IN_MINUTE = 60;

    public static int minutesToSeconds(String time) {
        String[] temp = time.split(":");
        if (temp.length == 2) {
            try {
                int minutes = Integer.parseInt(temp[0]);
                int seconds = Integer.parseInt(temp[1]);
                if (minutes < 0 || seconds < 0) {
                    return -1;
                }
                if (seconds < SECONDS_IN_MINUTE) {
                    return minutes * SECONDS_IN_MINUTE + seconds;
                }
            } catch (NumberFormatException e) {
                return -1;
            }

        }
        return -1;
    }
}
