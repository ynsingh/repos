package org.iitk.brihaspati.modules.actions;
/*
 * @(#)remoteAction.java  
 * 
 * Copyright (c) 2012 ETRG,IIT Kanpur. 
 * All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 *  
 * Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 *  
 * Redistribution in binary form must reproducuce the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 *  
 *  
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 * Contributors : members of ETRG, IIT Kanpur  
 *   
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.apache.velocity.context.Context;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.List;
import javax.servlet.ServletOutputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.LinkedList;

import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;
import org.iitk.brihaspati.om.RemoteUsersPeer;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.services.servlet.TurbineServlet;
/**
 * Action class for authenticating a apllication form authentic server 
 * and send the data to that application also maintain a log
 *
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */


public class RemoteAction extends VelocitySecureAction
{
	private Log log = LogFactory.getLog(this.getClass());

	protected boolean isAuthorized( RunData data ) throws Exception
        {
                return true;
        }
	public void ChkAuthenticate(RunData data, Context context)
	{
		ParameterParser pp=data.getParameters();
		String email = pp.getString("email","");
//		int prt=TurbineServlet.getServerPort();

		String val=TurbineServlet.getServerScheme()+"://"+TurbineServlet.getServerName()+":"+TurbineServlet.getServerPort(); 
ErrorDumpUtil.ErrorLog("The value of redirection url in  Remote action at web client "+val);
		String returnurl=TurbineServlet.getServerScheme()+"://"+TurbineServlet.getServerName()+":"+TurbineServlet.getServerPort()+"/brihaspati/servlet/brihaspati/action/LoginFromBrihspti";
		String resp=RemoteAuth.AuthR(email,returnurl,"iitk_test");
		ErrorDumpUtil.ErrorLog("The redirection url resp Remote action at web client "+resp);
		try{
                                data.getResponse().sendRedirect(resp);
                        }
                        catch (Exception ex){
                             ErrorDumpUtil.ErrorLog("The error in redirection url resp "+ex);
                        }
        }

	public void doPerform(RunData data,Context context) throws Exception
        {
		String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_dochk"))
                        ChkAuthenticate(data,context);
	}
}

