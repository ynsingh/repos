package org.iitk.brihaspati.modules.screens;

/*
 * @(#)LectureInfo.java	
 *
 *  Copyright (c) 2004-2005,2013 ETRG,IIT Kanpur. 
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
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;       
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.util.List;
import java.util.Date;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.apache.xmlrpc.XmlRpc;
import java.text.SimpleDateFormat;
import java.text.Format;
import org.iitk.brihaspati.om.Lecture;
import org.iitk.brihaspati.om.LecturePeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.UrlConectionPeer;
import org.iitk.brihaspati.om.UrlConection;
import java.text.DateFormat;
import java .util.GregorianCalendar;
import java .util.Calendar;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla</a>
 */
public class LectureInfo extends VelocityScreen
{
    /**
     * Place all the data object in the context
     * for use in the template.
     */
    public void doBuildTemplate( RunData data, Context context )
    {
		boolean flag = false;
		System.gc();
                try{
                        ParameterParser pp=data.getParameters();
                        String lang=pp.getString("lang","");
                        if(lang.equals(""))
			{
				flag = true;
				lang= "english";
			}

				context.put("flag",flag);
				context.put("lang",lang);
				lang= "";
			pp.add("str","session");	
			int Sessionkey=0;
			String lecname=null;
			String courseName=null;
			String iName=null;

                        String LecId = pp.getString("lectureId","");

			// Get Instrutor Name,Course Name, Lecture Name, Time ,Date from Lecture Table
			Criteria lec = new Criteria();
                        lec.add(LecturePeer.LECTUREID,LecId);
                        List ltable_list = LecturePeer.doSelect(lec);
                        Lecture element=(Lecture)ltable_list.get(0);
			lecname = element.getLecturename();
			String user_name=element.getUrlname();
			Criteria name = new Criteria();
                        name.add(TurbineUserPeer.LOGIN_NAME,user_name);
                        List nam = TurbineUserPeer.doSelect(name);
                        TurbineUser ele=(TurbineUser)nam.get(0);
                        String firstName=ele.getFirstName();
                        String lastName=ele.getLastName();
                        iName=firstName+" "+lastName;
			String gName=element.getGroupName();
                        Criteria cName = new Criteria();
                        cName.add(CoursesPeer.GROUP_NAME,gName);
                        List course = CoursesPeer.doSelect(cName);
                        Courses c=(Courses)course.get(0);
                        String lName=c.getCname();
                        String gAlias=c.getGroupAlias();
                        courseName=gAlias+"-"+lName;
                        Date lDate=element.getSessiondate();
                        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String lDate_str = formatter.format(lDate);
                        String lDate_split[]=lDate_str.split(" ");
                        String lTime=element.getSessiontime();
                        String lDuration=element.getDuration();
                        String sGuest=element.getGroupName();
                        int gid=GroupUtil.getGID(sGuest);

                        Criteria guestf = new Criteria();
                        guestf.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
                        List gList = TurbineUserGroupRolePeer.doSelect(guestf);

			//get Session Key for guest from url_connection Table if Session is active for guest. 
                        for(int j=0;j<gList.size();j++){
                                TurbineUserGroupRole g=(TurbineUserGroupRole)gList.get(j);
                            	int gflag=g.getRoleId();
                                    if(gflag==3){
                                       try{
                                           int lid=element.getLectureid();
                                           Criteria role = new Criteria();
                                           role.add(UrlConectionPeer.LECTUREID,lid);
                                           role.add(UrlConectionPeer.LOGIN_ID,"guest");
                                           role.add(UrlConectionPeer.ROLE,"student");
                                           List urlconection=UrlConectionPeer.doSelect(role);
                                           UrlConection urlcon=(UrlConection)urlconection.get(0);
                                           Sessionkey = urlcon.getSessionKey();
                                        }catch(Exception excp){}
                                    }
                        }
			context.put("key",Sessionkey);
                        context.put("date",lDate_split[0]);
                        context.put("duration",lDuration);
                        context.put("time",lTime);
                        context.put("InstructorName",iName);
                        context.put("lecname",lecname);
                        context.put("coursename",courseName);

		}
                catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The Error in Lecture Info !!"+e);
		}

    }
}
