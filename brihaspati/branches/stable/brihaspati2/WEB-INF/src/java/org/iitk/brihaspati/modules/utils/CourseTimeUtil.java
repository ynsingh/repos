 package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) CourseTimeUtil.java
 *  Copyright (c) 2011 ETRG,IIT Kanpur 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 */
import java.util.*;
import java.text.*;
import java.lang.*;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.Collections;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.CourseTimePeer;
import org.iitk.brihaspati.om.CourseTime;
import org.iitk.brihaspati.om.CourseTimedayPeer;
import org.iitk.brihaspati.om.CourseTimeday;
import org.apache.turbine.util.RunData;
import org.iitk.brihaspati.om.TurbineGroupPeer;
import org.iitk.brihaspati.om.TurbineGroup;
import org.apache.turbine.services.session.TurbineSession;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.CourseMonthPeer;
import org.iitk.brihaspati.om.CourseMonth;
//import com.workingdogs.village.Record;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
//package org.kodejava.example.util;
import org.apache.commons.lang.StringUtils; 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 */
public class CourseTimeUtil
{
	 /*
	  *entry id from Course_Time table.
	  *we get current user's entry_id from course_time table.
	  *by which this thing is clear user use which course in this Login.
	  */
	   public static int getentryid(int uid)
        {
                int entryid=0;
                try{
                        Criteria crit=new Criteria();
                        Vector vec=new Vector();
                        crit.add(CourseTimePeer.USER_ID,uid);
                        List v=CourseTimePeer.doSelect(crit);	
			if(v.size()!=0){
				 
                        	for(int p=0;p<v.size();p++){
                                	        CourseTime element=(CourseTime)v.get(p);
                                        	int eid=element.getEntryId();
                                        	vec.add(eid);
                        	}
                        Object obm = Collections.max(vec);
                        String eid1 = obm.toString();
                        entryid=Integer.parseInt(eid1);
			}
			else
			entryid=v.size();
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in Method getentryid -----"+ex); }
                return entryid;
        }

	       /*
		*get primary id of user currently use in course from COURSE_TIME table.
		*/
		public static int getCTid(int entryid)
                {
                        int ctid=0;

                        try{
                                Criteria crit=new Criteria();
                                crit.add(CourseTimePeer.ENTRY_ID,entryid);
                                List v=CourseTimePeer.doSelect(crit);
                                for(int p=0;p<v.size();p++){
                                       CourseTime element=(CourseTime)v.get(p);
					int status=element.getStatus();
					if(status==1)
						ctid=element.getCtId();
                                }

                        }catch(Exception e){
                                ErrorDumpUtil.ErrorLog("Error CourseTimeUtil in getCTid Method------"+e);}
                                return ctid;
                }
		/*
		 *this method for get CT_ID on CourseLogin Action if user agion login in a Course 
		 *wihout Logout from brihaspati.
		 * here courseid get from parameter and entry id from Usage_Details Table
		 */
		public static int getACid(int entryid,String courseid)
		{
			int ctid=0;
		
			try{
				Criteria crit=new Criteria();
                        	Vector vec=new Vector();
                        	crit.add(CourseTimePeer.ENTRY_ID,entryid);
				crit.add(CourseTimePeer.COURSE_ID,courseid);
                        	List v=CourseTimePeer.doSelect(crit);
                                CourseTime element=(CourseTime)v.get(0);
                                ctid=element.getCtId();

			}catch(Exception e){
				ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil getACid Method------"+e);}
				return ctid;
		}
		/*
		 *method For time calculating for a User's current Login in a Course.
		 *get ENTRY_ID from Course_Time Table on the behalf of userid.
		 *get C_ID on the behalf of ENTRY_ID from Course_Time Table.
		 *get User's Login Time in current Course on the behalf of C_ID.
		 * get Current Date Time and subtract both dates.
		 * get result in hours min form in a String.
		 */
                public static String getCloginTime(int userid)
                {
                String ctime=null;
                try{
			Date date=new Date();
			int eid=getentryid(userid);
                        Criteria crit=new Criteria();
			crit.add(CourseTimePeer.ENTRY_ID,eid);
                        List v=CourseTimePeer.doSelect(crit);
			for(int i=0;i<v.size();i++)
			{
				CourseTime element=(CourseTime)v.get(i);
				int status=element.getStatus();
				if(status==1)
				{
					Integer coursetime =element.getCourseTime();
					long time1 = coursetime.longValue();
					long diffHours = time1/(60*60*1000);
					long diffHour = coursetime%(60*60*1000);
					long diffMin=diffHour/(60*1000);
					ctime=diffHours+" hours"+" "+diffMin+" min";
				}
			}
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil getCloginTime Method------"+ex);}
                 return ctime;
        }
	/*
	 *method For time calculating for a User's current Login at every page on Course area.
	 *get ENTRY_ID from Course_Time Table on the behalf of USER_ID.
	 *get C_ID on the behalf of ENTRY_ID from COURSE_TIME Table.
	 *Take Login Time In Course on the behalf of C_ID.
	 *subtract from current Date .
	 *add the diff with STINE in DataBase and send in database.
	 */
	 public static void getCalculation(int userid)
        {
                try{
                        Date date=new Date();
                        int eid=getentryid(userid);
                        Criteria crit=new Criteria();
                        crit.add(CourseTimePeer.ENTRY_ID,eid);
                        List v=CourseTimePeer.doSelect(crit);
                        for(int i=0;i<v.size();i++){
                                CourseTime element=(CourseTime)v.get(i);
                                int status=element.getStatus();
                                if(status==1){
                                        int ctid=element.getCtId();
                                        String courseid=element.getCourseId();
                                        Date priviousDate=element.getCloginDate();
                                        Integer ctime=element.getCourseTime();
                                        long ctime1 = ctime.longValue();
                                        long diff1=date.getTime()-priviousDate.getTime();
                                        long diff=diff1+ctime1;
                                        String priDate=ModuleTimeUtil.getDateformate(priviousDate);
                                        String TodayDate=ModuleTimeUtil.getDateformate(date);
                                        Criteria cr=new Criteria();
                                        if(priDate.equals(TodayDate)){
                                        cr.add(CourseTimePeer.CT_ID,ctid);
                                        cr.and(CourseTimePeer.CLOGIN_DATE,date);
                                        cr.add(CourseTimePeer.COURSE_TIME,diff);
                                        CourseTimePeer.doUpdate(cr);
                                        }else
                                        {
					cr.add(CourseTimePeer.CT_ID,ctid);
                                        cr.add(CourseTimePeer.STATUS,"0");
                                        CourseTimePeer.doUpdate(cr);
                                        cr.add(CourseTimePeer.ENTRY_ID,eid);
                                        cr.add(CourseTimePeer.USER_ID,userid);
                                        cr.add(CourseTimePeer.COURSE_ID,courseid);
                                        cr.add(CourseTimePeer.CLOGIN_DATE,date);
                                        cr.add(CourseTimePeer.COURSE_TIME,"00");
                                        cr.add(CourseTimePeer.COUNT_COURSELOGIN,"1");
                                        cr.add(CourseTimePeer.STATUS,"1");
                                        CourseTimePeer.doInsert(cr);
                                        }
                                }
                        }
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil getCalculation Method-------"+ex);}
        }

	/*
	 *Method for getting list of allActive Users of this Group who are currently 
	 *use this CourseArea. 
         *This method use in GUI in CourseHome.
	 */
	public static Vector getCourseActiveList(int gid,String groupName)
	{
		 Vector userList=new Vector();
		try{
			/*getting gruop name of current gid */
			//String groupname=getGroupName(gid);
                        Vector Activelist=new Vector();
			/*get All Active User in a Vector*/
                        Collection au=TurbineSession.getActiveUsers();
			Iterator it=au.iterator();
                        while(it.hasNext()){
                                String ss=it.next().toString();
                                String ss1=(ss.substring(0,(ss.length()-3)));
                                int uid=UserUtil.getUID(ss1);
                                Activelist.add(uid);
                        }
			/*get List of All user in A Group bY gid*/
			Criteria crit=null;
			crit=new Criteria();
			crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
			/*getList of All Users*/
                        List v=TurbineUserGroupRolePeer.doSelect(crit);
			/*take value from List one by one*/
			for(int p=0;p<v.size();p++){
                                     TurbineUserGroupRole el=(TurbineUserGroupRole)v.get(p);
				/*take User_Id*/
                                int uid=el.getUserId();
				/*if(this user belongs this group or not){yes*/
				if(Activelist.contains(uid)){
				/*getEntry_ID of this User From COURSE_TIME table*/
				int eid=getentryid(uid);
				crit=new Criteria();
				/*take status of user on the behalf of ENTRY_ID and
				  GROUP_NAME*/
				crit.add(CourseTimePeer.ENTRY_ID,eid);
				crit.add(CourseTimePeer.COURSE_ID,groupName);
				List v1=CourseTimePeer.doSelect(crit);
					CourseTime element=(CourseTime)v1.get(0);
					int status=element.getStatus();
					/*if(status==1){true*/
					  if(status==1){
						/*user currently in this GROUP*/
						/*time in this CourseArea*/
						 String ct=CourseTimeUtil.getCloginTime(uid);
						/*take LoginName of User*/
						String LoginName=UserUtil.getLoginName(uid);
						/*take Login Name and Time in a String for showing 
					          on browser*/
						 String cname=LoginName+" ("+ct+" )";
						userList.add(cname);
					}
				}
			}
		} catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in Method getCourseUid------"+ex); }
                 return userList;
		}
	/*
	 *Method for Calculating time User Spend in a Course in a day.
	 */
	 public static String totalCourseTime(int userid,String courseid,String date)
	{
		String TimeandLogin=null;
		try{
			 Criteria crit=new Criteria();
			crit.add(CourseTimePeer.USER_ID,userid);
			crit.add(CourseTimePeer.COURSE_ID,courseid);
			crit.add("COURSE_TIME","CLOGIN_DATE",(Object) (date+"%"),crit.LIKE);
			List v=CourseTimePeer.doSelect(crit);
			int totalTime=0;
			int totalLogin=0;
				for(int p=0;p<v.size();p++){
					CourseTime element=(CourseTime)v.get(p);
					//Date clogindate=element.getCloginDate();
					//String de = ModuleTimeUtil.getDateformate(clogindate);
					//if(de.equals(date)){					
					int te=element.getCourseTime();
					int log=element.getCountCourselogin();
					totalTime=te+totalTime;
					totalLogin=log+totalLogin;
					}
				//}
				TimeandLogin=totalTime+"@"+totalLogin;
			
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in TotalCourseTime Method-----"+ex);}
		 return TimeandLogin;
	}

	/*
	 *Method for delete CousreTime Table EveryDay.
	 */
	public static void deleteDayEntry(int userid,String courseid,String date){
		try{
			Criteria crit=new Criteria();
                        crit.add(CourseTimePeer.USER_ID,userid);
                        crit.add(CourseTimePeer.COURSE_ID,courseid);
			List v=CourseTimePeer.doSelect(crit);
			for(int p=0;p<v.size();p++){
                                        CourseTime element=(CourseTime)v.get(p);
					Date clogindate=element.getCloginDate();	
					String de =ModuleTimeUtil.getDateformate(clogindate);
					if(de.equals(date))
					CourseTimePeer.doDelete(crit);
			}
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil Total deleteDayEntry Method-----"+ex);}
	}
	 public static void CourseDay()
        {
                try{
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DATE, -1);
                        Date yesterday = calendar.getTime();
                        String date=ModuleTimeUtil.getDateformate(yesterday);
                        Vector vec=new Vector();
                        Criteria crit=new Criteria();
                        crit.setDistinct();
                        crit.addGroupByColumn(CourseTimePeer.USER_ID);
                        crit.addGroupByColumn(CourseTimePeer.COURSE_ID);
                        List v=CourseTimePeer.doSelect(crit);
                        for(int p=0;p<v.size();p++){
                                CourseTime element=(CourseTime)v.get(p);
                                int userid=element.getUserId();
                                String courseid=element.getCourseId();
                                String TimeandLogin=totalCourseTime(userid,courseid,date);
                                String [] Stringsplit=TimeandLogin.split("@");
                                int totalTime=Integer.parseInt(Stringsplit[0]);
                                int totalLogins=Integer.parseInt(Stringsplit[1]);
                                deleteDayEntry(userid,courseid,date);
                                String date1=date+" "+"00:00:00";
                                        Criteria cr=new Criteria();
                                        cr.add(CourseTimedayPeer.USER_ID,userid);
                                        cr.add(CourseTimedayPeer.COURSE_ID,courseid);
                                        cr.add(CourseTimedayPeer.PRIVIOUS_DATE,date1);
                                        cr.add(CourseTimedayPeer.COURSE_TIMEDAY,totalTime);
                                        cr.add(CourseTimedayPeer.COUNT_LOGINDAY,totalLogins);
                                        CourseTimedayPeer.doInsert(cr);
                                 }

                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil Method CourseDay-------------"+ex);}
        }

	/*
	 *Method for Delete Entry from CourseTimeDay table before 31 days.
	 */
	public static void deleteSameDateEntry()
	{
		try{
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, -31);
			String priviousDate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-" + now.get(Calendar.DATE);
			 DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			 Date priviousDate1=formatter.parse(priviousDate);
			String priviousDate2=(formatter.format(priviousDate1));
			Criteria crit=new Criteria();
			List v=CourseTimedayPeer.doSelect(crit);
			for(int p=0;p<v.size();p++){
				CourseTimeday element=(CourseTimeday)v.get(p);
				Date date1=element.getPriviousDate();
				String date=ModuleTimeUtil.getDateformate(date1);
				if(priviousDate2.equals(date)){
					int ctdid=element.getCtdId();
					crit=new Criteria();
					crit.add(CourseTimedayPeer.CTD_ID,ctdid);
					CourseTimedayPeer.doDelete(crit);
					//ErrorDumpUtil.ErrorLog("-------Delete Today date Entry------------");
				}
			}
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error CourseTimeUtil in deleteSameDateEntry-----"+ex);}
	}

        /* 
	 *functions for time calculation of every user at a particular month in a course
	 */

         public static String  CourseMonthTime(int uid,String courseid)
        {
		String monthTimeandLogin=null;
                //int time=0;
                try{
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			Calendar now = Calendar.getInstance();
			int Currentmonth=Integer.parseInt(Integer.toString(now.get(Calendar.MONTH)));
			if (Currentmonth==0)
                                Currentmonth=12;
			int time=0;
			int NoofLogins=0;
			int time2=0;
                        Criteria crit=new Criteria();
                        crit.add(CourseTimedayPeer.USER_ID,uid);
                        crit.add(CourseTimedayPeer.COURSE_ID,courseid);
                        List v1=CourseTimedayPeer.doSelect(crit);
                        for(int q=0;q<v1.size();q++){
                                CourseTimeday el=(CourseTimeday)v1.get(q);
				Date de=el.getPriviousDate();
				int month2=Integer.parseInt(sdf.format(de));
				if(Currentmonth==month2){
                             	  	 int time1=el.getCourseTimeday();
                               		 time2=time2+time1;
					int logins=el.getCountLoginday();
					NoofLogins=NoofLogins+logins;

				}
                        }
			time=time2/(60*1000);
			monthTimeandLogin=time+"@"+NoofLogins;
                }catch(Exception ex){
                ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in Method CoMonthTime-----"+ex);
                }
                 return monthTimeandLogin;
        }
	

        //}

	public static String getmonthYear(int month)
        {
                String mYear=null;
                try{
                        Calendar cal = Calendar.getInstance();
                        int year=(cal.get(Calendar.YEAR));
                        if(month==1)
                                mYear="1"+"-"+year;
                        else if(month==2)
                                mYear="2"+"-"+year;
                        else if(month==3)
                                mYear="3"+"-"+year;
                        else if(month==4)
                                mYear="4"+"-"+year;
                        else if(month==5)
                                mYear="5"+"-"+year;
                        else if(month==6)
                                 mYear="6"+"-"+year;
                        else if(month==7)
                                mYear="7"+"-"+year;
                        else if(month==8)
                                mYear="8"+"-"+year;
                        else if(month==9)
                                mYear="9"+"-"+year;
                        else if(month==10)
                                 mYear="10"+"-"+year;
                        else if(month==11)
                                mYear="11"+"-"+year;
                        else if(month==0){
                                year=year-1;
                                mYear="12"+"-"+year;
                        }

                        
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil getmonthYear Method------"+ex);}
                return mYear; 
      }

	public static void UpdateCourseMonth()
	{
		try{
			Vector vec=new Vector();
			Calendar cal = Calendar.getInstance();
			//int tdate=cal.get(Calendar.DAY_OF_MONTH);
			//if(tdate==1)
			//{
			deleteSameMonthEntry();
			int month=cal.get(Calendar.MONTH);
			String yearAndMonth=getmonthYear(month);
				Criteria crit=new Criteria();
				crit.setDistinct();
				crit.addGroupByColumn(CourseTimedayPeer.USER_ID);
				crit.addGroupByColumn(CourseTimedayPeer.COURSE_ID);
				List v=CourseTimedayPeer.doSelect(crit);
				for(int p=0;p<v.size();p++){
					CourseTimeday element=(CourseTimeday)v.get(p);
					int userid=element.getUserId();
					String courseid=element.getCourseId();
						String time=CourseMonthTime(userid,courseid);
						String[] splittime=time.split("@");
						int totalTime=Integer.parseInt(splittime[0]);
						int totalLogins=Integer.parseInt(splittime[1]);
						crit=new Criteria();
						crit.add(CourseMonthPeer.USER_ID,userid);
						crit.add(CourseMonthPeer.COURSE_ID,courseid);
						crit.add(CourseMonthPeer.MONTH_YEAR,yearAndMonth);
						crit.add(CourseMonthPeer.COURSE_TIMEMONTH,totalTime);
						crit.add(CourseMonthPeer.COUNT_LOGINMONTH,totalLogins);
						CourseMonthPeer.doInsert(crit);
					}
				
				//}
			//}
			//deleteSameDateEntry();
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in Method UpdateCourseMonth()-----"+ex);}
	}
	/*
	 *Method for deleting same month entry of privious year
	 */
	public static void deleteSameMonthEntry()
	{
		try{
			Calendar cal = Calendar.getInstance();
			int month=cal.get(Calendar.MONTH);
			if(month==0)
				month=12;
			int year=cal.get(Calendar.YEAR)-1;
			String yearMonth=month+"-"+year;
			Criteria crit=new Criteria();
			 List v=CourseMonthPeer.doSelect(crit);
                        for(int p=0;p<v.size();p++){
                                CourseMonth element=(CourseMonth)v.get(p);
                                String month1=element.getMonthYear();
				if(yearMonth.equals(month1)){
					int id=element.getId();
					crit=new Criteria();
					crit.add(CourseMonthPeer.ID,id);
					CourseMonthPeer.doDelete(crit);
				}
			}
			
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in deleteSameMonthEntry-----"+ex);}	
	}
	
        /**method for changing the status of a user in database if he stop to use 
         *a Course area this method is use in Index.java,Indexhome.java,
         *myLogout.java 
         */
        public static int getchangeStatus(int eid)
        {
                int status=0;
                try{
                        //int status=0;
                        int ctid=getCTid(eid);
                        Criteria crit=new Criteria();
                        crit.add(CourseTimePeer.CT_ID,ctid);
                        crit.add(CourseTimePeer.STATUS,"0");
                        CourseTimePeer.doUpdate(crit);
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in Method getchangeStatus-----"+ex); }
                 return status;
}
 	/*
         *method for getting LoginTime of Current User by the help of USER_ID to check a User use
         *any module after enter in a CourseHome or not .
         *get ENTRY_ID on the Behalf of USER_ID from COURSE_TIME Table;
         *get C_ID by ENTRY _ID from COURSE_TIME table.
         *get Value in a list on the behalf of C_ID from COURSE_TIME table.
         */
        public static Date getDatetime(int userid)
        {
                Date recenttime=null;
                try{
                        int eid=getentryid(userid);
                        Criteria crit=crit=new Criteria();
                        crit.add(CourseTimePeer.ENTRY_ID,eid);
                        List v=CourseTimePeer.doSelect(crit);
                        for(int i=0;i<v.size();i++){
                        CourseTime element=(CourseTime)v.get(i);
                        int status=element.getStatus();
                        if(status==1)
                        recenttime=element.getCloginDate();
                        }
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in Method getDatetime------"+ex);}
                return recenttime;
        }

//}
	
 	public static String getLastweekTime(int uid,String courseid)
	{
		String WeekTime=null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			Date todaydate=cal.getTime();
			String todate=sdf.format(todaydate);
			int time=0;
			for(int i=0;i<=7;i++){
				cal.setTime(sdf.parse(todate));
				cal.add(Calendar.DATE,-i);
				String priviousdate = sdf.format(cal.getTime());
				if(priviousdate.equals(todate))
				{
					String timelogin=CourseTimeUtil.totalCourseTime(uid,courseid,todate);
					time=Integer.parseInt(StringUtils.substringBefore(timelogin,"@"));
				}
				Criteria crit=new Criteria();
                        	crit.add(CourseTimedayPeer.USER_ID,uid);
                        	crit.add(CourseTimedayPeer.COURSE_ID,courseid);
                       		crit.add(CourseTimedayPeer.PRIVIOUS_DATE,priviousdate);
				List v=CourseTimedayPeer.doSelect(crit);
				if(v.size()!=0)
				{
					CourseTimeday el=(CourseTimeday)v.get(0);
					int time1=el.getCourseTimeday();
					time=time+time1;
							
					
				}
			
			}
			int Hours = time/(60 * 60 * 1000);
                        int Hour = time%(60 * 60 * 1000);
			int Mins=Hour/(60*1000);
			int Min=Hour%(60*1000);
			int sec=Min/1000;
			WeekTime=Hours+"h:"+Mins+"m:"+sec+"s";
			
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in Method getLastweekTime------"+ex);}
		return WeekTime;
	}

}
