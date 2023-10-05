package edu.hw1;

public class Task1 {
    public static int minutesToSeconds(String time){
        String[] temp=time.split(":");
        if(temp.length==2){
            try{
                int minutes=Integer.parseInt(temp[0]);
                int seconds=Integer.parseInt(temp[1]);
                if(seconds<60){
                    return minutes*60+seconds;
                }
            }catch(NumberFormatException e){
                return -1;
            }

        }
        return -1;
    }
}
