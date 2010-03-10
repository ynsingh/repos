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
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;

import com.workingdogs.village.Record;

import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.om.security.TurbineUser;
import org.apache.turbine.om.security.User;

import org.apache.turbine.util.security.TurbineSecurityException;
import org.apache.turbine.util.security.AccessControlList;

import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.UsageDetailsPeer;
//import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.modules.actions.VelocityAction;
/**
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
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
		User user = data.getUser();
		boolean a=data.userExists();
		// Store the user object.
		String username=user.getName();
		int uid=UserUtil.getUID(username);
		
/////////////////////////////////////////////////////
		Criteria crit;
		List vec=null;
		String userLanguage="";
                crit= new Criteria();
                crit.add(TurbineUserPeer.USER_ID,uid);
                crit.addGroupByColumn(TurbineUserPeer.USER_LANG);
                vec=TurbineUserPeer.doSelect(crit);
                crit = null;
		ErrorDumpUtil.ErrorLog("vc size=="+vec.size());
		if(vec.size() > 0){
	                org.iitk.brihaspati.om.TurbineUser elementTU=(org.iitk.brihaspati.om.TurbineUser)vec.get(0);
        	        userLanguage=elementTU.getUserLang().toString();
		}

//////////////////////////////////////////////////////
       		if(a)
        	if(!TurbineSecurity.isAnonymousUser(user))
        	{
            		if(!user.hasLoggedIn())
            		{
                		return;
            		}
            		user.setHasLoggedIn(new Boolean(false));
            		TurbineSecurity.saveUser(user);
        	}
                data.setUser(user);
                Date date=new Date();
		int least_entry=0,count=0;
        	//code for usage details starts here
		int eid=0,uid3=0;
                java.util.Date logindate=new java.util.Date();
        	crit=new Criteria();
        	crit.add(UsageDetailsPeer.USER_ID,uid);
		crit.addAscendingOrderByColumn(UsageDetailsPeer.ENTRY_ID);
        	List entry=UsageDetailsPeer.doSelect(crit);
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

		if(vec.size() > 0){
                	crit= new Criteria();
	       	        crit.add(TurbineUserPeer.USER_ID,uid);
       			crit.add(TurbineUserPeer.USER_LANG, userLanguage);
                	TurbineUserPeer.doUpdate(crit);
		}
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
    	}
	catch ( TurbineSecurityException e ){
		data.setScreenTemplate("Login.vm");
                }
	}
}
