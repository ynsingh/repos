package org.iitk.brihaspatisync.util;

/*@(#)ServerUtil.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008, 2013 ,2014 All Rights Reserved.
 */

import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.util.StringTokenizer;

import java.util.List;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspatisync.om.Lecture;
import org.iitk.brihaspatisync.om.LecturePeer;
import org.iitk.brihaspatisync.om.UrlConection;
import org.iitk.brihaspatisync.om.UrlConectionPeer;
import org.iitk.brihaspatisync.ReflectorStatusManager;

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind pal </a>
 */


public class ReflectorHandler {

	private static ReflectorHandler obj=null;

   	public static ReflectorHandler getController() {
        	if(obj==null)
           		obj=new ReflectorHandler();
        	return obj;
   	}
	
	/**
	 *  This method is used to remove sessionid.xml file  and delete entry from reflector.xml according to the  end of the durataion of  session id
	 *  This method  is called in ProcessRequest.java 
	 **/	
	
	public void LectureHandler(javax.servlet.ServletContext context) {
		List sessionlist=getAllSessionList();
		java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat("HH:mm");
                Date now = new Date();
                String systemTime = sdfTime.format(now);
		String systemdate=getCurrentDate("");
		String system_time_split[]=systemTime.split(":");
		java.text.SimpleDateFormat dateformatYYYYMMDD = new java.text.SimpleDateFormat("yyyyMMdd");
		if(sessionlist!=null) {
			for(int i=0;i<sessionlist.size();i++) {
				Lecture element=(Lecture)(sessionlist.get(i));
	                        String lectid=Integer.toString(element.getLectureid());
				Date lectDate=element.getSessiondate();
				String lectTime=element.getSessiontime();
				String lectDuration=element.getDuration();
				
				String lecturdate = new String( dateformatYYYYMMDD.format(lectDate));
				String lecture_times_split[]=lectTime.split(":");
				int start_Lecture_hour=Integer.parseInt(lecture_times_split[0]);
                                String durationtime[]=lectDuration.split(":");	
				int duratiinhour=Integer.parseInt(durationtime[0]);
				int total_session_hour=start_Lecture_hour+duratiinhour;
				int session_date=Integer.parseInt(lecturdate);
				int system_cur_date=Integer.parseInt(systemdate);
				if(total_session_hour>24) {
					total_session_hour=total_session_hour-24;
					session_date=session_date+1;
				}
				
				int total_session_minute=total_session_hour*60+Integer.parseInt(lecture_times_split[1]);	
				int total_system_minute=(Integer.parseInt(system_time_split[0]))*60+Integer.parseInt(system_time_split[1]);				

				/** compare serverdate and sessiondate */
				if(session_date <system_cur_date) {
					removeExpiryEntry(lectid,context);
				}else if((session_date ==system_cur_date) && (total_session_minute<total_system_minute)) {
					removeExpiryEntry(lectid,context);
				}
			}
		}
	}

	/** 
 	 * This method is used to remove the entry from UrlConection table accroding to session_id
 	 * and delete file session_id.xml 
 	 * and remove entry from ReflectorStatusManager accroding to session_id
 	 **/  	
	private void removeExpiryEntry(String lectid,javax.servlet.ServletContext context ){
                try {
//                        ReflectorStatusManager.removeLoad_and_Sessionid_Peer(lectid);
                        Criteria crit=new Criteria();
                        crit.add(UrlConectionPeer.LECTUREID,Integer.parseInt(lectid));
                        UrlConectionPeer.doDelete(crit);
  //                      ReflectorStatusManager.removeLoad_and_Sessionid_Peer(lectid);
			java.io.File filepath=new java.io.File(context.getRealPath(lectid+".xml"));
                        if(filepath.exists())
                        	filepath.delete();
           	} catch(Exception e){ ServerLog.log(" Exceptio in ReflectorHandler.java "+e.getMessage()); }
	}

	/** 
 	 * This method is used to get curent system date ant time .
 	 */ 
	public String getCurrentDate(String delimiter)
        {
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
                                return current_year+delimiter+current_month+delimiter+current_day;
                        else
                                return current_year+current_month+current_day;
                }
                catch(Exception ex) { System.out.println("Error in Expiry Util"); }
                return null;
        }
	
	/**
 	 * This method is used to get difference between the sessionDate and SystemDate. 
	 */
	
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

	/**
 	 * This is used to getting all session list from LECTURE table .
 	 */	
	public List getAllSessionList(){
               	List l=null;
                try {
                        Criteria crit=new Criteria();
                        l=LecturePeer.doSelect(crit);
                } catch(Exception e) { ServerLog.log("Exception  in Lecture select "+e.getMessage());}
             	return l;
        }	
		
}//end of class	
