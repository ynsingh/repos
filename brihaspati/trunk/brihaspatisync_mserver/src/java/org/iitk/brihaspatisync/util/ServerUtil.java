package org.iitk.brihaspatisync.util;

/*@(#)ServerUtil.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import java.util.Random;
import java.util.Vector;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */


public class ServerUtil{
	
	private static ServerUtil obj=null;

   	public static ServerUtil getController() {
        	if(obj==null)
           		obj=new ServerUtil();
        	return obj;
   	}

	/** Generate a session Key */

    	public int generateSessionKey() {
       		int key=new Random().nextInt();
         		if(key<=0) {
                     		do{
                       			key=new Random().nextInt();
                       		} while(key<=0);
                    	}
        	return key;
    	}
	/****************  get Corent Date **************/
	public String getCurrentDate(String delimiter)
        {
                String cdate="";
                try{
                        Calendar calendar=Calendar.getInstance();

                        int curr_day=calendar.get(calendar.DATE);
                        int curr_month=calendar.get(calendar.MONTH)+1;
                        int curr_year=calendar.get(calendar.YEAR);
                        String current_day=Integer.toString(curr_day);
                        String current_month=Integer.toString(curr_month);
                        String current_year=Integer.toString(curr_year);
                        if(curr_month<10)
                                current_month="0"+current_month;
                        if(curr_day<10)
                                current_day="0"+current_day;
                        if(!delimiter.equals(""))
                                cdate=current_year+delimiter+current_month+delimiter+current_day;
                        else
                                cdate=current_year+current_month+current_day;
                }
                catch(Exception ex)
                {
                        System.out.println("Error in Expiry Util");
                }
                return(cdate);
        }
	/****************  get Corent Date **************/	
	

	public int getDifferenceOfDay(Date sessionDate,String sessionTime) {

		ServerLog.getController().Log("Session Data = "+sessionDate+" Session time = "+sessionTime);
        	StringTokenizer str=new StringTokenizer(sessionTime,":");
              	int hours=0,minutes=0;
             	while(str.hasMoreTokens()) {
                	hours=Integer.parseInt(str.nextToken());
                  	minutes=Integer.parseInt(str.nextToken());
             	}
      		long numDays=0;
      		Date cloneOfsessionDate=new Date(1900+sessionDate.getYear(),1+sessionDate.getMonth(),sessionDate.getDate(),hours,minutes,0);
       		Date systemDate=new Date();
       		Date cloneOfsystemDate=new Date(1900+systemDate.getYear(),1+systemDate.getMonth(),systemDate.getDate(),systemDate.getHours(),systemDate.getMinutes(),0);

               	long sessionIntime = cloneOfsessionDate.getTime();
               	long systemIntime  = cloneOfsystemDate.getTime();
               	long MS_PER_DAY = 86400000;
               	if(sessionIntime<systemIntime) {
                	numDays = Math.abs(systemIntime-sessionIntime) / MS_PER_DAY;
	      //        System.out.println("No of Days=>"+numDays);
               	}
                if(numDays>=1)
                	return 1;
                else
                	return 0;
  	}
}//end of class	
