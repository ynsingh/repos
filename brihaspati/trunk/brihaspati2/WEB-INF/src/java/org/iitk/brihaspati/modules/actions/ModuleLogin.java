package org.iitk.brihaspati.modules.actions;
/*
 * @(#)ModuleLogin.java
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
import org.apache.commons.lang.StringUtils;
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
	    if((uid!=0) || (uid!=1)){
	  	  String dateandmid=ModuleTimeUtil.getDate(uid,courseid,mname);
	     	   if(org.apache.commons.lang.StringUtils.isBlank(dateandmid)){
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
		}	
	 
        }
	catch(Exception ex){
                data.setMessage("The Error in ModuleLogin Action");
                }

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
