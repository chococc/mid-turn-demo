package com.trainingorg.demo.Util;

import java.util.List;

public class SQLMath {

    public float calculator(int type, List<Float> number) {
        return 0;
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