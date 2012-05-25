 package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) ModuleTimeUtil.java
 *  Copyright (c) 2010 ETRG,IIT Kanpur 
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
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Collections;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.CourseTimePeer;
import org.iitk.brihaspati.om.CourseTime;
import org.iitk.brihaspati.om.ModuleTimePeer;
import org.iitk.brihaspati.om.ModuleTime;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.util.RunData;

/**
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 */
public class ModuleTimeUtil
{
	/*
	 *Methof for take dateformate from Date.
	 */
	public static String getDateformate(Date date)
	{
		String formatedDate=null;
		try{
  			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 			 formatedDate=sdf.format(date);
		}catch(Exception ex){ErrorDumpUtil.ErrorLog(" Error ModuleTimeUtil in Method getDateformate----"+ex);}
		return formatedDate;
	}
	/*
         * get Course by given entryid.
         *we get Courseid(group_name) User use Currently.
         * if User use course Status=1 in database otherwise 0.
         */
        public static String  getCourseid(int entryid)
        {
                String courseid=null;
                try{
                        Vector vec=new Vector();
                        Criteria crit=null;
                        crit=new Criteria();
                        crit.add(CourseTimePeer.ENTRY_ID,entryid);
                        List v=CourseTimePeer.doSelect(crit);
                        for(int p=0;p<v.size();p++){
                                CourseTime element=(CourseTime)v.get(p);
                                int status=element.getStatus();
                                if(status==1)
                                        courseid=element.getCourseId();
                        }
                
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in CourseTimeUtil in getCourse Method----"+ex);}
                 return courseid;
                }

	 public static String getDate(int userid,String cname,String mname)
        {
                //Vector dates=new Vector();
		  String dateAndmid="";
                try{
			Date de=new Date();
			String de1=getDateformate(de);
                        Criteria crit=new Criteria();
                        crit.add(ModuleTimePeer.USER_ID,userid);
                        crit.add(ModuleTimePeer.COURSE_ID,cname);
                        crit.add(ModuleTimePeer.MNAME,mname);
			crit.add("MODULE_TIME","MLOGIN_DATETIME",(Object) (de1+"%"),crit.LIKE);
                        List v=ModuleTimePeer.doSelect(crit);
			if(v.size()!=0){
                                ModuleTime element=(ModuleTime)v.get(0);
				int mid=element.getMid();
				dateAndmid=de1+"@"+mid;
				}
				else
				dateAndmid="";
                        //}
                }catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error ModuleTimeUtil in Method getDate----"+ex);}
                 return dateAndmid;
        }

	/*
	 *method for getting current manme in module area 
	 *for time calculation.
	 */
	public static String getcMname(int userid,String courseid,String date)
       	{
		String mname=null;
		try{
		       Vector vec=new Vector();
		  /*Take list of all values by USER_ID,
			CNAME from ModuleTime table.*/
		  Criteria crit=null;
		  crit=new Criteria();
		  crit.add(ModuleTimePeer.USER_ID,userid);
		  crit.add(ModuleTimePeer.COURSE_ID,courseid);
		  crit.add("MODULE_TIME","MLOGIN_DATETIME",(Object) (date+"%"),crit.LIKE);
		  List v=ModuleTimePeer.doSelect(crit);
		  /*Get max Logintime of user from list
			take datefomate from this.*/
		  if(v.size()!=0){
			  for(int p=0;p<v.size();p++){
			  	ModuleTime element=(ModuleTime)v.get(p);
				Date mlogintime=element.getMloginDatetime();
				vec.add(mlogintime);
			}
			Object obm = Collections.max(vec);
                        String mlogintime1=obm.toString();
			/*take mname from ModuleTime table 
				behalf of this maxDate.*/
			crit=new Criteria();
			crit.add(ModuleTimePeer.MLOGIN_DATETIME,mlogintime1);
			List v1=ModuleTimePeer.doSelect(crit);
  			ModuleTime element1=(ModuleTime)v1.get(0);
			mname=element1.getMname();
			}
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error ModuleTimeUtil in Method  getcMname----"+ex); }
		 return mname;
	}
	/*
	 *method for time calculation call at every page.
	 */
	public static void getModuleCalculation(int uid)
	{
		try{
		   Criteria crit=new Criteria();
		   /*take max entry_id of user in CourseArea.*/
		   int eid=CourseTimeUtil.getentryid(uid);
		   /*get CouseName by this entryid.*/
		    String courseid=getCourseid(eid);
		   /*take current date and convert into
			dateformate.*/
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date de=new Date();
		    String date=sdf.format(de);
		    //String date=getDateformate(de);
		    /*get current mname.*/
		    String mname=getcMname(uid,courseid,date);
		     if(org.apache.commons.lang.StringUtils.isBlank(mname))
                     {
		     	 Calendar now = Calendar.getInstance();
			 now.setTime(sdf.parse(date));
                         now.add(Calendar.DATE,-1);
                         String priviousdate = sdf.format(now.getTime());
			 String mname1=getcMname(uid,courseid,priviousdate);
                         crit=new Criteria();
                         crit.add(ModuleTimePeer.USER_ID,uid);
                         crit.add(ModuleTimePeer.COURSE_ID,courseid);
                         crit.add(ModuleTimePeer.MNAME,mname1);
                         crit.add(ModuleTimePeer.MLOGIN_DATETIME,de);
                         crit.add(ModuleTimePeer.MTIME,"00");
                         ModuleTimePeer.doInsert(crit);
		     }else{
		         /*get current user's max mid.*/
		    	String dateandmid=getDate(uid,courseid,mname);
		    	String [] Stringsplit=dateandmid.split("@");
		    	int mid=Integer.parseInt(Stringsplit[1]);
		    	/*take current user's current logintime 
			minus from current datetime.*/
		        crit=new Criteria();
		    	crit.add(ModuleTimePeer.MID,mid);
		    	List v=ModuleTimePeer.doSelect(crit);
	            	ModuleTime element=(ModuleTime)v.get(0);
			Date mlogintime=element.getMloginDatetime();
			Integer mtime=(element.getMtime());
			long mtime1=mtime.longValue();
		    	long diff1=de.getTime()-mlogintime.getTime();
			long diff=mtime1+diff1;
			/*value update in database.*/
			crit=new Criteria();
			crit.add(ModuleTimePeer.MID,mid);
			crit.add(ModuleTimePeer.MLOGIN_DATETIME,de);
			crit.add(ModuleTimePeer.MTIME,diff);
			ModuleTimePeer.doUpdate(crit);
		}
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error ModuleTimeUtil in Method getModuleCalculation----"+ex); }
		
	}	
	/*
	 *get max LoginTime of a user in a CourseModule for
	 *user enter in a Module or exit from Coursehome.
	 */
	public static Date getMrecenttime(int uid)
	{
		Date mrecenttime=null;
		try{
			Vector vec=new Vector();
			/*get current date and convert indateformate*/
			Date de=new Date();
			String date=getDateformate(de);
			/*get max entry_id in coursearea.*/
			int eid=CourseTimeUtil.getentryid(uid);
			/*get current cname*/
                        String courseid=getCourseid(eid);
			/*get list behalf of userid,cname.*/
			String mname=getcMname(uid,courseid,date);
			Criteria crit=new Criteria();
			crit.add(ModuleTimePeer.USER_ID,uid);
			crit.add(ModuleTimePeer.COURSE_ID,courseid);
			crit.add(ModuleTimePeer.MNAME,mname);
			crit.add("MODULE_TIME","MLOGIN_DATETIME",(Object) (date+"%"),crit.LIKE);
			List v=ModuleTimePeer.doSelect(crit);
			if(v.size()!=0){
				ModuleTime element=(ModuleTime)v.get(0);
				mrecenttime=element.getMloginDatetime();
			}
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error ModuleTimeUtil in Method getMrecenttime----"+ex); }
		return mrecenttime;
	}
	public static void deleteLastmonthentry()
	{
		try{
			Calendar cal = Calendar.getInstance();
			int tdate=cal.get(Calendar.DAY_OF_MONTH);
                        if(tdate==1)
			{
		
				Criteria crit=new Criteria();
				List v=ModuleTimePeer.doSelect(crit);
				for(int p=0;p<v.size();p++){
                               		 ModuleTime element=(ModuleTime)v.get(p);
					 int mid=element.getMid();
					crit=new Criteria();
                                        crit.add(ModuleTimePeer.MID,mid);
                                        ModuleTimePeer.doDelete(crit);
				}
			}
		}
		catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in ModuleTimeUtil in Method deleteLastMonthEntry----"+ex);}
	}
	
	public static Vector getmName(String courseid,int uid)
	{
		Vector mname=new Vector();
		try{
			Criteria crit=new Criteria();
                        crit.add(ModuleTimePeer.COURSE_ID,courseid);
                        crit.add(ModuleTimePeer.USER_ID,uid);
			crit.setDistinct();
			List v=ModuleTimePeer.doSelect(crit);
			for(int i=0;i<=v.size();i++)
			{	
				ModuleTime element=(ModuleTime)v.get(i);
                                String mnam=element.getMname();
				if(!mname.contains(mnam))
				mname.add(mnam);		
			}
		}catch(Exception ex){}
		return mname;
	}
	public static Vector LastweekModuleTime(String courseid,int uid)
	{
		Vector mTimeMname=new Vector();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cal = Calendar.getInstance();
                        Date todaydate=cal.getTime();
                        String todate=sdf.format(todaydate);
			Vector mname=getmName(courseid,uid);
			for(int j=0;j<mname.size();j++)
			{
				String mnam=mname.get(j).toString();
				int mTime=0;
				for(int i=0;i<=7;i++){
                        	cal.setTime(sdf.parse(todate));
                        	cal.add(Calendar.DATE,-i);
                        	String priviousdate = sdf.format(cal.getTime());
				Criteria crit=new Criteria();
				crit.add(ModuleTimePeer.COURSE_ID,courseid);
				crit.add(ModuleTimePeer.USER_ID,uid);
				crit.add(ModuleTimePeer.MNAME,mnam);
				crit.add("MODULE_TIME","MLOGIN_DATETIME",(Object) (priviousdate+"%"),crit.LIKE);
				List v=ModuleTimePeer.doSelect(crit);
				if(v.size()!=0){
					ModuleTime element=(ModuleTime)v.get(0);
					int mtime=element.getMtime();
					mTime=mTime+mtime;
					}
				}
					int Hours = mTime/(60 * 60 * 1000);
        		                int Hour = mTime%(60 * 60 * 1000);
	                	        int Mins=Hour/(60*1000);
                        		int Min=Hour%(60*1000);
                        		int sec=Min/1000;
                        		String WeekTime=Hours+"h:"+Mins+"m:"+sec+"s";
					CourseUserDetail cDetail=new CourseUserDetail();
					cDetail.setModuleName(mnam);
					cDetail.setModuleTime(WeekTime);
					mTimeMname.add(cDetail);
			}
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Error in ModuleTimeUtil in Method LastweekModuleTime---"+ex);}
		return mTimeMname;
	}

}

