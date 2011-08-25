package org.iitk.brihaspatisync.util;

/*@(#)ServerUtil.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import java.util.Vector;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Calendar;

import java.util.List;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspatisync.om.Lecture;
import org.iitk.brihaspatisync.om.LecturePeer;
import org.iitk.brihaspatisync.ReflectorStatusManager;
/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind pal </a>
 */


public class ReflectorHandler {

	private javax.servlet.ServletContext context=null;	
	private static ReflectorHandler obj=null;

   	public static ReflectorHandler getController() {
        	if(obj==null)
           		obj=new ReflectorHandler();
        	return obj;
   	}
	
	public void LectureHandler(javax.servlet.ServletContext context1) {
		if(context==null)
			this.context=context1;
		List l=getAllSessionList();
		java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat("HH:mm");
                Date now = new Date();
                String systemTime = sdfTime.format(now);
		String systemdate=getCurrentDate("");
		String system_time_split[]=systemTime.split(":");
		java.text.SimpleDateFormat dateformatYYYYMMDD = new java.text.SimpleDateFormat("yyyyMMdd");
		if(l!=null) {
			for(int i=0;i<l.size();i++) {
				Lecture element=(Lecture)(l.get(i));
	                        String lectid=Integer.toString(element.getLectureid());
				Date lectDate=element.getSessiondate();
				String lecturdate = new String( dateformatYYYYMMDD.format(lectDate));
				String lectTime=element.getSessiontime();
				if(Integer.parseInt(lecturdate)<Integer.parseInt(systemdate)) {
					java.io.File filepath=new java.io.File(context.getRealPath(lectid+".xml"));
					if(filepath.exists()){
                                       		try { 
							ReflectorStatusManager.getController().removeLoad_and_Sessionid_Peer(lectid);
                                        		filepath.delete();
						}catch(Exception e){}
					}
				} else if(Integer.parseInt(lecturdate)==Integer.parseInt(systemdate)) {
        	        	       	String lectDuration=element.getDuration();
					String lecture_times_split[]=lectTime.split(":");
					String durationtime[]=lectDuration.split(":");	
					
					int total_Lecture_hour=Integer.parseInt(lecture_times_split[0]);
					int lecture_minute=total_Lecture_hour*60+Integer.parseInt(lecture_times_split[1]);
					int end_lecture_minute=((total_Lecture_hour+Integer.parseInt(durationtime[0]))*60)+Integer.parseInt(lecture_times_split[1]);
					int system_hour=Integer.parseInt(system_time_split[0]);
					int system_minute=system_hour*60+Integer.parseInt(system_time_split[1]);
					if(end_lecture_minute <= system_minute) {
						java.io.File filepath=new java.io.File(context.getRealPath(lectid+".xml"));
						if(filepath.exists()){
							try {
								ReflectorStatusManager.getController().removeLoad_and_Sessionid_Peer(lectid);
								filepath.delete();
							}catch(Exception e){}
						}
					}
				}
			}
		}
	}

	/****************  get Current Date **************/
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
                catch(Exception ex) {
                        System.out.println("Error in Expiry Util");
                }
                return(cdate);
        }
	/** getting the  day or time difference between the sessionDate and SystemDate. */
	
	/*
	public String getSystemDateTime() {
		java.text.SimpleDateFormat sdfDate = new java.text.SimpleDateFormat("yyyy/MM/dd");
                java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat("HH:mm");
                Date now = new Date();
                String strDate = sdfDate.format(now);
                String strTime = sdfTime.format(now);
                System.out.println("Date: " + strDate);
                System.out.println("Time: " + strTime); 
                return "date"+strDate+" "+strTime;
	}
	*/
	public List getAllSessionList(){
               	List l=null;
                try {
                        Criteria crit=new Criteria();
                        l=LecturePeer.doSelect(crit);
                }catch(Exception e){ServerLog.getController().Log("Error Log in Lecture select "+e.getMessage());}
             	return l;
        }	
		
}//end of class	
