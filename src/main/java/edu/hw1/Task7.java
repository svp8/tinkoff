package edu.hw1;

public class Task7 {
    public int rotateRight(int num, int target) {
        String binary = Integer.toBinaryString(num);
        while(target!=0){
           String temp=binary.substring(0,binary.length()-1);
            temp=binary.substring(binary.length()-1)+temp;
            binary=temp;
            target--;
        }
        return Integer.parseInt(binary, 2);
    }

    public int rotateLeft(int num, int target) {
        String binary = Integer.toBinaryString(num);
        System.out.println(binary);

        while(target!=0){
            String temp=binary.substring(1);
            System.out.println(temp);
            temp=temp+binary.charAt(0);
            System.out.println(temp);
            binary=temp;
            target--;
        }
        return Integer.parseInt(binary, 2);
    }
}
