package org.iitk.brihaspati.modules.actions;
/*
 * @(#)CourseLogin.java
 *
 *  Copyright (c)2011 ETRG,IIT Kanpur.
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
 *  Contributors : members of ETRG, IIT Kanpur
 *
 */

import java.util.Date;
import java.util.List;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.*;
import org.iitk.brihaspati.om.CourseTime;
import org.iitk.brihaspati.om.CourseTimePeer;

// Referenced classes of package org.iitk.brihaspati.modules.actions:
//            SecureAction

public class CourseLogin extends SecureAction
{
/**
 * This method is invoked upon when user logging in a Course Area
 * @param data RunData instance
 * @param context Context instance
 */

    public void doCourseInsert(RunData data, Context context)
    {   /*get Course_id(GroupName) by temp*/
        String cname = data.getParameters().getString("courseid", "");
        try
        {
	    Date date = new Date();
	    /*get current User*/
            User user = data.getUser();
	    /*get UserName*/
            String username = user.getName();
	    /*get USER_ID*/
            int uid = UserUtil.getUID(username);
	    if(uid!=0){
	    /*get ENTRY_ID from UsageDetail Table in DataBase*/
            int eid = UsageDetailsUtil.getentryId(uid);
	    /*getList of All entries in Course_Time Table in 
	     DataBase on the behalf of ENTRY_ID AND CNAME*/
            Criteria crit = null;
            crit = new Criteria();
            crit.add(CourseTimePeer.ENTRY_ID, eid);
            crit.add(CourseTimePeer.COURSE_ID, cname);
            List v = CourseTimePeer.doSelect(crit);
	    /*if list is empty(no entry for that CNAME on given ENTRY_ID ) 
	     *than enter a new entry in DataBase.
             */
            if(v.size() == 0)
            {
                Criteria cr = new Criteria();
                cr.add(CourseTimePeer.ENTRY_ID, eid);
                cr.add(CourseTimePeer.USER_ID, uid);
                cr.add(CourseTimePeer.COURSE_ID, cname);
                cr.add(CourseTimePeer.CLOGIN_DATE,date);
                cr.add(CourseTimePeer.COURSE_TIME, "00");
		cr.add(CourseTimePeer.COUNT_COURSELOGIN,"1");
		cr.add(CourseTimePeer.STATUS,"1");
		//cr.add(CourseTimePeer.LOGIN,"1");
                CourseTimePeer.doInsert(cr);
            } 
	    else{
	    CourseTime element = (CourseTime)v.get(0);
	    Integer time = (element.getCourseTime());
	    long diff1 = time.longValue();
	    int status=element.getStatus();
	    int login=element.getCountCourselogin();
	    Date date1=new Date();
            int ctid = CourseTimeUtil.getACid(eid,cname);
	    Date de1 = element.getCloginDate();
	    long diff=0;
	    if(status==1){
	    long diff2=date1.getTime()-de1.getTime();
	    diff=diff1+diff2;
	    //login=login+1;
	    }else{
	    	diff=diff1;
		login=login+1;
	    }
	    	crit = new Criteria();
                crit.add(CourseTimePeer.CT_ID,ctid);
                crit.add(CourseTimePeer.CLOGIN_DATE, date1);
                crit.add(CourseTimePeer.COURSE_TIME, diff);
		crit.add(CourseTimePeer.COUNT_COURSELOGIN, login);
                crit.add(CourseTimePeer.STATUS,"1");
                CourseTimePeer.doUpdate(crit);
		
		
	    }
            }
	}
        catch(Exception ex) { 
                data.setMessage("The Error in CourseLogin Action");
                }

        }

    public void doPerform(RunData data, Context context)
        throws Exception
    	{
        String LangFile = data.getUser().getTemp("LangFile").toString();
        ParameterParser pp = data.getParameters();
        String action = pp.getString("actionName", "");
        context.put("actionName", action);
        if(action.equals("eventSubmit_doCourseInsert"))
        {
            doCourseInsert(data, context);
        }
    }
}
