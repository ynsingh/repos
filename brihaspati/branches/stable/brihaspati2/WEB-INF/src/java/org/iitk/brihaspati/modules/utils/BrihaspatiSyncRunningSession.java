package org.iitk.brihaspati.modules.utils;

/* 
 *  @(#)BrihaspatiSyncRunningSession.java
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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

import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.torque.util.Criteria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.iitk.brihaspati.om.Lecture;
import org.iitk.brihaspati.om.LecturePeer;
	
import org.iitk.brihaspati.om.UrlConection;
import org.iitk.brihaspati.om.UrlConectionPeer;



/**
 *
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal</a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 */


public class BrihaspatiSyncRunningSession{

	public static ArrayList getRunningSession(String groupname,String username)
	{
		try{
			/**
                         *This block of code compare current date ant time to Lecture Date and Time
                         *if Lecture date and time is greater than current date and time then pics 
                         *LECTURE ID of that Lecture and sends to VM for further use.
                         */

			ArrayList list = new ArrayList();
                        Map map = new HashMap();
                        // Get Current date month and year.

                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month=cal.get(Calendar.MONTH)+1;
                        int year=cal.get(Calendar.YEAR);

                        Date date2 = new Date();
                        java.sql.Date currentDate = new java.sql.Date(date2.getTime());
                        Criteria crte = new Criteria();
			if(groupname.equals(""))
                        	crte.addGroupByColumn(LecturePeer.LECTUREID);
			else
				crte.add(LecturePeer.GROUP_NAME,groupname);
			
                        List lec=LecturePeer.doSelect(crte);
                        //Get Current time using SimpleDateFormat.
                        SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
                        String time = stf.format(new Date());
                        Date currenttime=stf.parse(time);
                        //Loop for getting data from Lecture table.
                        for(int i=0;i<lec.size();i++){
                        	Lecture element=(Lecture)lec.get(i);
                                String lectime=element.getSessiontime();
                                String str1[]=lectime.split(":");
                                int itime = Integer.parseInt(str1[0]);
                                String duration=element.getDuration();
                                String str[]=duration.split(":");
                                int iduration=Integer.parseInt(str[0]);
                                int finaltime=itime+iduration;
                                String sf=new Integer(finaltime).toString();
                                String ct=sf+":"+str1[1];
                                Date sessiondate1=element.getSessiondate();
                                java.sql.Date sessiondate= new java.sql.Date(sessiondate1.getTime());
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String strDate = dateFormat.format(sessiondate);
                                String strDate_split[]=strDate.split("-");
                                int sday=Integer.parseInt(strDate_split[2]);
                                int smonth=Integer.parseInt(strDate_split[1]);
                                int syear=Integer.parseInt(strDate_split[0]);
                                SimpleDateFormat stf1 = new SimpleDateFormat("HH:mm");
                                Date date=stf1.parse(ct);
                                //Condition to check if current date is before session date.And if current date is equal to session date then time will also be cmpared.
                                if((currentDate.compareTo(sessiondate)<0 )|| ((day==sday && month==smonth && year==syear) && currenttime.compareTo(date) < 0)){
                                        map = new HashMap();
                                        String lecname = element.getLecturename();
					int sessionkey=0;
					try {
						if(!(username.equals(""))) {
							Criteria role = new Criteria();
				                        role.add(UrlConectionPeer.LECTUREID,element.getLectureid());
							if(!(groupname.equals(""))) {
		        		       		        role.add(UrlConectionPeer.LOGIN_ID,username);
		        		       		        role.add(UrlConectionPeer.GROUP_NAME,groupname);
							}
		                		        List urlconection=UrlConectionPeer.doSelect(role);
                		        	        UrlConection urlcon=(UrlConection)urlconection.get(0);
		                               		sessionkey = urlcon.getSessionKey();
						}
					}catch(Exception e){ }
					map.put("key",sessionkey);
                                        map.put("lecname",lecname);
                                        int lectureId=element.getLectureid();
                                        map.put("lid",lectureId);
                                        list.add(map);
                                }
                        }
                        return list;
                }catch(Exception e){}
		return null;
	}
}
