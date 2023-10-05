package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Task6 {
    public static int countK(int num,int count){
        if (num==6174) {
            return count;
        }
        int temp = num;
        ArrayList<Integer> array = new ArrayList<Integer>();
        do{
            array.add(temp % 10);
            temp /= 10;
        } while  (temp > 0);
        Collections.sort(array);
        ArrayList<Integer> array2=new ArrayList<Integer>(array);
        array2.sort(Collections.reverseOrder());
        int num1=0;
        int num2=0;
        for (int i = array.size()-1; i >=0; i--) {
            num1+=array.get(i)*Math.pow(10,i);
            num2+=array2.get(i)*Math.pow(10,i);
        }
        int result=num1-num2;
        return countK(result,count+1);
    }
}
