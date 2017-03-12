package com.example.lusen.bihuplus.httputils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lusen on 2017/2/17.
 */

public class Date_split {
    private String dataBase;
    private int year;
    private int month;
    private int day;
    public Date_split(String dataBase){
        this.dataBase = dataBase;
    }

    public void split(String dataBase){
        this.dataBase = dataBase;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = dateFormat.parse(this.dataBase);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH) + 1;
            this.day = calendar.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public String add(int num){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = dateFormat.parse(this.dataBase);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date newDate2 = new Date(date.getTime() + num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateOk = simpleDateFormat.format(newDate2);
        this.dataBase = dateOk;
        return this.dataBase;
    }
    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
}
