package edu.hw3.task1;

public final class Task1 {

    public static final int Z = 122;
    public static final int A = 97;
    public static final int N = 110;
    public static final int M = 109;

    private Task1() {
    }

    public static String atbash(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            boolean isUpperCase = Character.isUpperCase(letter);
            letter = Character.toLowerCase(letter);
            int num = letter;
            if (num >= A && num < N) { //Интервал от 97 до 110
                num = (N - num) + M;
            } else if (num >= N && num <= Z) {  //Интервал от 110 до 122
                num = N - (num - M);
            }
            //Остальные символы не меняем
            if (isUpperCase) {
                stringBuilder.append(Character.toUpperCase((char) num));
            } else {
                stringBuilder.append((char) num);
            }
        }
        return stringBuilder.toString();
    }

}
