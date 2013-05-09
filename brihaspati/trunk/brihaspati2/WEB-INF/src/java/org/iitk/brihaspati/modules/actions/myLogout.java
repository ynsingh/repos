package org.iitk.brihaspati.modules.actions;

/*
 * @(#)myLogout.java
 *
 *  Copyright (c)2010 ETRG,IIT Kanpur.
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
//java classes
import java.util.Date;
import java.util.List;
//Turbine classes
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.TurbineConstants;
import org.apache.turbine.om.security.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati class
import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.SystemCleantimePeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UsageDetailsUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils. ModuleTimeThread;

/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 **/

public class myLogout extends VelocityAction{
	private Log log = LogFactory.getLog(this.getClass()); 

    /**
     * Updates the user's Logout timestamp, sets their state to
     * "logged in" and calls RunData.setUser() .  If the user cannot
     * be authenticated (database error?) the user is assigned
     * anonymous status and, if tr.props contains a TEMPLATE_LOGIN,
     * the screenTemplate is set to this, otherwise the screen is set
     * to SCREEN_LOGIN
     * @exception Exception, a generic exception.
     */
	public void doPerform( RunData data, Context context ) throws Exception
    	{
		try
		{
			Criteria criteria;
			int load_flag = 0;
		/*
		 * Check user exist in this session or not
                 */
			boolean a=data.userExists();

		/* if yes { */
	       		if(a){
		
                /*    Get the user name */
				User user = data.getUser();
				String username=user.getName();
		/* Check user is guest */
		/*  If no { */
				if (!(username.equals("guest"))){	
		/* Get user id for this user  */
					int uid=UserUtil.getUID(username);
		/* Set logout time in usage details */ 
					Date date=new Date();
        			        int least_entry=0,count=0;
                			int eid=0,uid3=0;
		        	        Criteria crit=new Criteria();
                			crit.add(UsageDetailsPeer.USER_ID,uid);
			                crit.addAscendingOrderByColumn(UsageDetailsPeer.ENTRY_ID);
        	        		List entry=UsageDetailsPeer.doSelect(crit);
			                for(int k=0;k<entry.size();k++)
                			{
		                	        UsageDetails element=(UsageDetails)entry.get(k);
                		        	eid=element.getEntryId();
	                        		uid3=element.getUserId();
        	        		}
        		        	crit=new Criteria();
        	        		crit.add(UsageDetailsPeer.ENTRY_ID,(eid));
		                	crit.add(UsageDetailsPeer.USER_ID,Integer.toString(uid));
	                		crit.add(UsageDetailsPeer.LOGOUT_TIME,date);
			                UsageDetailsPeer.doUpdate(crit);
		/* Set has logged in false */
					user.setHasLoggedIn(new Boolean(false));
		/*
                 *check for Course_Time table update
		 *This check add by Smita.
                 */
                                	/*entry id fron USAGE_DETAILS*/
                                	int eid1=UsageDetailsUtil.getentryId(uid);
                                	/*entry id from COURSE_TIME */
                                	int eid2=CourseTimeUtil.getentryid(uid);
                                	if(eid1==eid2)
                                	{
						ModuleTimeThread.getController().CourseTimeSystem(uid,eid2);
                        		}
				/**
				* Create log file for user
				* Parameters are user name, Role, Time and IP address
				**/
				String server=data.getRemoteAddr() ;
				User user1=data.getUser();
                                String Role=(String)user1.getTemp("role");	
				String role;
				if(Role.equals("")) role = "Admin";else role = Role;
				log.info("User Name --> "+username +"| Role --> "+role +"| Logout time --> "+date+"| IP Address --> "+server);
                		}
		/*  Invalidate session */
				data.setACL(null);
				data.getSession().invalidate();
		/* Set next screen (ie login) and display logout message */
				if (!Turbine.getConfiguration().getString(TurbineConstants.ACTION_LOGOUT_DEFAULT , "").equals("LogoutUser"))
		        	        data.setScreenTemplate("Login.vm");
		               		data.setMessage(Turbine.getConfiguration().getString(TurbineConstants.LOGOUT_MESSAGE));
                	}

			//Updating load factor in database
			criteria = new Criteria();
                        criteria.add(SystemCleantimePeer.ID,"1");
                        criteria.add(SystemCleantimePeer.LOAD_FLAG,load_flag);
                        SystemCleantimePeer.doUpdate(criteria);
		}
		catch ( Exception e ){
			ErrorDumpUtil.ErrorLog("The Exception in logout action is "+e);
	                data.setScreenTemplate("Login.vm");
        	}
	}
}
