package com.trainingorg.midturndemo.Util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamp {

    Timestamp timestamp;

    public Timestamp getNowTimestamp(){
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestamp = Timestamp.valueOf(simpleDate.format(date));
        return timestamp;
    }

    public Timestamp getTimestamp(String string){
        SimpleDateFormat simpleDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = simpleDate.parse(string+" 00:00:00");
            timestamp=Timestamp.valueOf(simpleDate.format(date));
        }catch (Exception e){
            e.printStackTrace();
            return timestamp;
        }
         return timestamp;
    }
}
