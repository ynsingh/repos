package org.iitk.brihaspatisync.util;

/*@(#)ServerUtil.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008, 2013 All Rights Reserved.
 */


import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.Calendar;
import java.util.StringTokenizer;

import java.util.List;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspatisync.om.Lecture;
import org.iitk.brihaspatisync.om.LecturePeer;

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Arvind Pal </a> // modifyed 2012 
 */


public class ServerUtil {
	
	/** Generate a session Key */
    	public static int generateSessionKey() {
       		int key=new Random().nextInt();
         	if(key<=0) {
                	do{
                       		key=new Random().nextInt();
                       	} while(key<=0);
             	}
        	return key;
    	}

	/****************  get Corent Date **************/
	public static String getCurrentDate(String delimiter)
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
			
                } catch(Exception ex) { ServerLog.log("Error getCurrentDate in ServerUtil class "+e.getMessage()); }
                return(cdate.trim());
        }
	/****************  get Corent Date **************/	
	/** getting the  day or time difference between the sessionDate and SystemDate. */

	public static int getDifferenceOfDay(Date sessionDate,String sessionTime) {

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
               	}
                if(numDays>=1)
                	return 1;
                else
                	return 0;
  	}
	
	public static String getSystemDateTime() {
		try {
			java.text.SimpleDateFormat sdfDate = new java.text.SimpleDateFormat("yyyy/MM/dd");
        	        java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat("HH:mm");
                	Date now = new Date();
        	        String strTime = sdfTime.format(now);
        	        return "date"+getCurrentDate("/")+" "+strTime;
		}catch(Exception e){return "UnSuccessfull"; }
	}
	
	public static String getAVStatus(String lect_id) {
                String str="";
                try{
                        Criteria forav = new Criteria();
                        forav.add(LecturePeer.LECTUREID,lect_id);
                        List u = LecturePeer.doSelect(forav);
                        for(int i=0;i<u.size();i++)
                        {
                                Lecture element=(Lecture)(u.get(i));
                                String str1=(element.getForvideo());
                                String str2=(element.getForaudio());
                                str =",A="+str2+",V="+str1;
                        }
                } catch(Exception e) { ServerLog.log("Error in selection of course"+e); }
                return str;
        }
		
	public static String getSessionList(String courseName){
               	String message="";
                try {
                        Criteria crit=new Criteria();
                        crit.add(LecturePeer.GROUP_NAME,courseName);
                        List l=LecturePeer.doSelect(crit);
                        for(int i=0;i<l.size();i++) {
                                Lecture element=(Lecture)(l.get(i));
                                String lectid=encrypt(Integer.toString(element.getLectureid()));
                                String lectUserName=encrypt(element.getUrlname());
                                String lectCouseName=encrypt(element.getGroupName());
	                        String lectName=encrypt(element.getLecturename());
                                String lectInfo=encrypt(element.getLectureinfo().trim());
                                String lectNo=encrypt(element.getPhoneno());
                                String lectVedio=encrypt(element.getForvideo());
                                String lectAudio=encrypt(element.getForaudio());
                                String lectWhiteBoard=encrypt(element.getForwhiteboard());
                                String lectDate=encrypt(element.getSessiondate().toString());
                                String lectTime=encrypt(element.getSessiontime());
                                String lectDuration=encrypt(element.getDuration());
                                String repeattime=encrypt(element.getRepeatlec());
                                String fortime=encrypt(element.getFortime());
				
				String mail=encrypt(element.getMailNotification());
                                message=message+"$$"+lectid+","+lectCouseName+","+lectName+","+lectInfo+","+lectUserName+","+lectNo+","+lectVedio+","+lectAudio+","+lectWhiteBoard+","+lectDate+","+lectTime+","+lectDuration+","+repeattime+","+fortime+","+mail;
			}
			ServerLog.log("getSessionList in ServerUtil class  "+message);
                }catch(Exception e){ ServerLog.log("Error getSessionList in ServerUtil class "+e.getMessage());}
             	return message;
        }

	public static String getLectureInfo(String lecture_id){
                String message="";
                try {
                        Criteria crit=new Criteria();
                        crit.add(LecturePeer.LECTUREID,Integer.parseInt(lecture_id));
                        List l=LecturePeer.doSelect(crit);
                        for(int i=0;i<l.size();i++) {
                                Lecture element=(Lecture)(l.get(i));
                                String lectid=encrypt(Integer.toString(element.getLectureid()));
                                String lectCouseName=encrypt(element.getGroupName());
                                String lectName=encrypt(element.getLecturename());
                                String lectInfo=encrypt(element.getLectureinfo().trim());
				String lectUserName=encrypt(element.getUrlname());
                                String lectNo=encrypt(element.getPhoneno());
                                String lectVedio=encrypt(element.getForvideo());
                                String lectAudio=encrypt(element.getForaudio());
                                String lectWhiteBoard=encrypt(element.getForwhiteboard());
                                String lectDate=encrypt(element.getSessiondate().toString());
                                String lectTime=encrypt(element.getSessiontime());
                                String lectDuration=encrypt(element.getDuration());
                                String repeattime=encrypt(element.getRepeatlec());
                                String fortime=encrypt(element.getFortime());
                                String mail=encrypt(element.getMailNotification());
                                message=lectid+","+lectCouseName+","+lectName+","+lectInfo+","+lectUserName+","+lectNo+","+lectVedio+","+lectAudio+","+lectWhiteBoard+","+lectDate+","+lectTime+","+lectDuration+","+repeattime+","+fortime+","+mail;
				ServerLog.log("getLectureInfo in ServerUtil class  "+message);
                        }
                }catch(Exception e) { ServerLog.log("Error Log in Lecture select "+e.getMessage());}
                return message;
        }	
	
	private static String encrypt(String Data) throws Exception {
                byte[] encVal = Data.getBytes();
                String encryptedValue = new sun.misc.BASE64Encoder().encode(encVal);
                return encryptedValue;
        }

}//end of class	
