package edu.hw1;

public class Task4 {
    public static String fixString(String str){
        StringBuilder sb=new StringBuilder();
        int i=1;
        if(str.length()==1) return str;
        while(i<str.length()){
            sb.append(str.charAt(i));
            sb.append(str.charAt(i-1));
            i+=2;
        }
        if(str.length()%2!=0){
            sb.append(str.charAt(i-1));
        }
        return sb.toString();

    }
}
