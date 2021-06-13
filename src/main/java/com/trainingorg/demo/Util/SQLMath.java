package com.trainingorg.demo.Util;

import java.util.ArrayList;
import java.util.List;

public class SQLMath {

    public Float calculator(int type, List<Float> number) {
        switch(type){
            case 0:return number.get(0);
            case 1:return sum(number);
            case 2:return average(number);
            case 3:return proportion(number);
        }
        return null;
    }

    public float calculateLong(int type, List<Long> number) {
        List<Float> numbers = new ArrayList<>();
        for(Long aNumber:number){
            String s=(aNumber.toString());
            numbers.add(Float.parseFloat(s));
        }
        return calculator(type,numbers);
    }

    public float calculateInteger(int type, List<Integer> number) {
        List<Float> numbers = new ArrayList<>();
        for(Integer aNumber:number){
            numbers.add((float)aNumber);
        }
        return calculator(type,numbers);
    }

    public float sum(List<Float> number){
        float sum=0;
        for (Float aFloat : number) {
            sum += aFloat;
        }
        return sum;
    }

    public float average(List<Float> number){
        return sum(number)/number.size();
    }

    public float proportion(List<Float> number){
        return number.get(1)/number.get(0);
    }
}