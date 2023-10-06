package edu.hw1;

public final class Task5 {
    private Task5() {

    }

    public static boolean isPalindromeDescendant(int number) {
        String num = String.valueOf(number);
        while (num.length() > 1) {
            boolean flag = true;
            for (int i = 0; i < num.length() / 2; i++) {
                if (num.charAt(i) != num.charAt(num.length() - i - 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i + 1 < num.length(); i += 2) {
                    int a = Character.getNumericValue(num.charAt(i));
                    int b = Character.getNumericValue(num.charAt(i + 1));
                    sb.append(a + b);

                }
                if (num.length() % 2 != 0) {
                    sb.append(num.charAt(num.length() - 1));
                }
                num = sb.toString();
            }
        }
        return false;
    }
}
