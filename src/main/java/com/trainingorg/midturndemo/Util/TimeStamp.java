package com.trainingorg.midturndemo.Util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public String getInstance(){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar= Calendar.getInstance();
        return weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }

    public int getWeek(Date StartTime){
        Calendar calendar=Calendar.getInstance();
        Date EndTime=new Date();
        calendar.setTime(StartTime);
        long time1=calendar.getTimeInMillis();
        calendar.setTime(EndTime);
        long time2=calendar.getTimeInMillis();
        long interval=time2-time1;
        double days= Double.parseDouble(String.valueOf(interval));
        return new Double(days/7).intValue();
    }
}
