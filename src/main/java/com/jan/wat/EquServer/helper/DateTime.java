package com.jan.wat.EquServer.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

    static int yearInt;
    static byte year;
    static byte month;
    static byte day;
    static byte hour;
    static byte minute;
    static byte second;
    private static Date date;
    private static Calendar calendar = Calendar.getInstance();
//    public static void initNow(){
//        if(date == null){
//            date = new Date();
//        }
//        date.setTime(System.currentTimeMillis());
//    }

    public static Date DateNow(){
        if(date == null){
            date = new Date();
        }
        date.setTime(System.currentTimeMillis());
        return date;
    }

    public static Date DateNow(long current){
        if(date == null){
            date = new Date();
        }
        date.setTime(current);
        return date;
    }


    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public static void Now(){
//        initNow();
        yearInt = calendar.get(Calendar.YEAR);
        String year2 = (String)String.valueOf(yearInt).subSequence(2,4);
        year = (byte) Byte.parseByte(year2);
        month = (byte) calendar.get(Calendar.MONTH);
        day = (byte) calendar.get(Calendar.DAY_OF_MONTH);
        hour = (byte) calendar.get(Calendar.HOUR_OF_DAY);
        minute = (byte) calendar.get(Calendar.MINUTE);
        second = (byte) calendar.get(Calendar.SECOND);
    }



    public static SimpleDateFormat getFormat() {
        return format;
    }

    public static int getYearInt() {
        return yearInt;
    }

    public static byte getYear() {
        return year;
    }

    public static byte getMonth() {
        return month;
    }

    public static byte getDay() {
        return day;
    }

    public static byte getHour() {
        return hour;
    }

    public static byte getMinute() {
        return minute;
    }

    public static byte getSecond() {
        return second;
    }

    public static Date getDate() {
        return date;
    }

    public static Calendar getCalendar() {
        return calendar;
    }

    public static String getMonthStr(Date time){
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        return c.get(Calendar.YEAR)+String.format("%02d",c.get(Calendar.MONTH));
    }

    public static String getMonthNow(){
//        initNow();
        return String.valueOf(calendar.get(Calendar.MONTH));

    }

}
