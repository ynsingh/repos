package org.iitk.brihaspati.modules.actions;

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
	    ErrorDumpUtil.ErrorLog("-------------uid"+uid);
	    if(uid!=0){
	     ErrorDumpUtil.ErrorLog("-------------(uid!=0)"+(uid!=0));
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
        catch(Exception e) { }
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
