package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatConsumer {

    public Info processStat(StatsCollector statsCollector,String name){
        List<Double> data=new ArrayList<>();
        for(Stat s:statsCollector.getStats()){
            if(s.name().equals(name)){
                for (double d:s.data()){
                    data.add(d);
                }
            }
        }
        double max=data.stream().max(Double::compareTo).get();
        double min=data.stream().min(Double::compareTo).get();
        double sum= data.stream().mapToDouble(Double::doubleValue).sum();
        double avg=sum/data.size();
        return new Info(max,avg,sum,min);
    }
    record Info(double max,double avg,double sum,double min){

    }
}
