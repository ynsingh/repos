package org.iitk.brihaspati.modules.actions;

/*
 * @(#)myLogin.java
 *
 *  Copyright (c)2009 ETRG,IIT Kanpur.
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
import java.util.Iterator;

import org.apache.turbine.Turbine;
import org.apache.turbine.TurbineConstants;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.workingdogs.village.Record;
import org.apache.torque.util.Criteria;

import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.om.security.TurbineUser;
import org.apache.turbine.om.security.User;

import org.apache.turbine.util.security.TurbineSecurityException;
import org.apache.turbine.util.security.AccessControlList;

import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.UsageDetailsPeer;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.modules.actions.VelocityAction;

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
		User user = data.getUser();
		boolean a=data.userExists();
        	ErrorDumpUtil.ErrorLog("check data.userexist=="+a);
       		if(a)
        	ErrorDumpUtil.ErrorLog("User exists already");
        	if(!TurbineSecurity.isAnonymousUser(user))
        	{
            		if(!user.hasLoggedIn())
            		{
                		return;
            		}
            		user.setHasLoggedIn(new Boolean(false));
            		TurbineSecurity.saveUser(user);
        	}
		// Store the user object.
		String username=data.getUser().getName();
		int uid=UserUtil.getUID(username);
                data.setUser(user);
                Date date=new Date();
		int least_entry=0,count=0;
        	//code for usage details starts here
		int eid=0,uid3=0;
                java.util.Date logindate=new java.util.Date();
        	Criteria crit=new Criteria();
        	crit.add(UsageDetailsPeer.USER_ID,uid);
		crit.addAscendingOrderByColumn(UsageDetailsPeer.ENTRY_ID);
        	List entry=UsageDetailsPeer.doSelect(crit);
               //ErrorDumpUtil.ErrorLog("actionlogout"+entry);
		for(int k=0;k<entry.size();k++)
		{
			UsageDetails element=(UsageDetails)entry.get(k);
                        eid=element.getEntryId();
                        uid3=element.getUserId();
                        logindate=(element.getLoginTime());

		}
        	crit=new Criteria();
        	crit.add(UsageDetailsPeer.ENTRY_ID,(eid));
        	crit.add(UsageDetailsPeer.USER_ID,Integer.toString(uid));
        	crit.add(UsageDetailsPeer.LOGIN_TIME,logindate);
        	crit.add(UsageDetailsPeer.LOGOUT_TIME,date);
        	UsageDetailsPeer.doUpdate(crit);
		data.setACL(null);
                // Retrieve an anonymous user.
                data.save();
		data.getSession().removeAttribute(AccessControlList.SESSION_KEY);
		if (!Turbine.getConfiguration().getString(TurbineConstants.ACTION_LOGOUT_DEFAULT , "").equals("LogoutUser"))
		//data.setScreen(Turbine.getConfiguration().getString( TurbineConstants.SCREEN_HOMEPAGE));
		data.setScreenTemplate("Login.vm");
                //data.setScreen(Turbine.getConfiguration().getString( TurbineConstants.SCREEN_HOMEPAGE));
		data.setMessage(Turbine.getConfiguration().getString(TurbineConstants.LOGOUT_MESSAGE));
		//int timeout=data.getSession().getMaxInactiveInterval();
               	//ErrorDumpUtil.ErrorLog("actionlogout timeout"+timeout);
    	}
	catch ( TurbineSecurityException e ){
		data.setScreenTemplate("Login.vm");
                }
	}
}
