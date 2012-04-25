package org.iitk.brihaspati.modules.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.om.ModuleTime;
import org.iitk.brihaspati.om.ModuleTimePeer;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

public class ModuleLogin extends SecureAction
{
    /**
      * This method is invoked upon when user logging in a CourseModule Area
      * @param data RunData instance
      * @param context Context instance
     */

    public void doModuleInsert(RunData data, Context context)
    {
        String courseid = data.getParameters().getString("cname", "");
        String mname = data.getParameters().getString("mname", "");
        try
        {
	    /*get Current userid*/
            User user = data.getUser();
	    String username = user.getName();
	   int uid = UserUtil.getUID(username);
	   /*get currentdate and convert dateformat.*/
            Date Todaydate = new Date();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String de =sdf.format(Todaydate);
	    Criteria crit=new Criteria();
	    //ErrorDumpUtil.ErrorLog("da-------------------------"+de);
	      String dateandmid=ModuleTimeUtil.getDate(uid,courseid,mname);
	//	ErrorDumpUtil.ErrorLog("smita--------------------------"+dateandmid);
	     if(dateandmid.equals("")){
	  //  ErrorDumpUtil.ErrorLog("smita-------------------------"+dateandmid.equals(""));
                    crit.add(ModuleTimePeer.USER_ID, uid);
                    crit.add(ModuleTimePeer.COURSE_ID, courseid);
                    crit.add(ModuleTimePeer.MNAME, mname);
                    crit.add(ModuleTimePeer.MLOGIN_DATETIME, Todaydate);
	            crit.add(ModuleTimePeer.MTIME,"00");
                    ModuleTimePeer.doInsert(crit);
	}else{	//or update given entry in database.
		String [] Stringsplit=dateandmid.split("@");
              String date =(Stringsplit[0]);
              int mid=Integer.parseInt(Stringsplit[1]);
		crit=new Criteria();
		crit.add(ModuleTimePeer.MID,mid);
		List v=ModuleTimePeer.doSelect(crit);
		ModuleTime element = (ModuleTime)v.get(0);
		Integer time = (element.getMtime());
		
                    		long time1 = time.longValue();
                    		crit = new Criteria();
                    		crit.add(ModuleTimePeer.MID, mid);
                    		crit.add(ModuleTimePeer.MLOGIN_DATETIME,Todaydate);
                    		crit.add(ModuleTimePeer.MTIME, time1);
                    		ModuleTimePeer.doUpdate(crit);
                	}
	 
        }catch(Exception e) { }
    }

    public void doPerform(RunData data, Context context)throws Exception
    {
        String LangFile = data.getUser().getTemp("LangFile").toString();
        ParameterParser pp = data.getParameters();
        String action = pp.getString("actionName", "");
        context.put("actionName", action);
        if(action.equals("eventSubmit_doModuleInsert"))
        {
            doModuleInsert(data, context);
        }
    }
}
