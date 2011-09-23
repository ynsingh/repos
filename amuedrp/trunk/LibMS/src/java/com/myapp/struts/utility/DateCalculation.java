/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author EdRP-05
 */
public class DateCalculation {
     public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";

    public static long getDifference(String from_date,String to_date){
     Calendar ca1 = Calendar.getInstance();
            Calendar ca2 = Calendar.getInstance();
            Calendar ca3 = Calendar.getInstance();


            /*extract month day and year from now()*/

            String date=from_date;
            String year=date.substring(0,date.indexOf("-"));


            String month=date.substring(date.indexOf("-")+1,date.indexOf("-", date.indexOf("-")+1));


            String day=date.substring(date.lastIndexOf("-")+1,date.length());


System.out.println(year+"-"+month+"-"+day);
            long day1 = ca1.get(Calendar.DATE)-Integer.parseInt(day);
            long month1 = (ca1.get(Calendar.MONTH)+1)-Integer.parseInt(month);
            long year1 = ca1.get(Calendar.YEAR)-Integer.parseInt(year);

            /**************************/
            ca2.set(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day));

             /*extract month day and year from now()*/
             date=to_date;
             year=date.substring(0,date.indexOf("-"));


            month=date.substring(date.indexOf("-")+1,date.indexOf("-", date.indexOf("-")+1));


            day=date.substring(date.lastIndexOf("-")+1,date.length());


            ca3.set(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day));

            long milliseconds1 = ca2.getTimeInMillis();
            long milliseconds2 = ca3.getTimeInMillis();
            long diff =  milliseconds1-milliseconds2 ;
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.println("Days"+diffDays);
            return diffDays;
    }
    public static String now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    return sdf.format(cal.getTime());
  }
 public static String dateTime() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(cal.getTime());
  }
}
