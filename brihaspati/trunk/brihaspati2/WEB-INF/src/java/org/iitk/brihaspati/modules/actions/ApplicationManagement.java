package org.iitk.brihaspati.modules.actions;

/*
 *  @(#)ApplicationManagement.java
 
 *  Copyright (c) 2014 ETRG,IIT Kanpur.
 *  All Rights Reserved.

 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 
 
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
 */
//JAVA
import java.util.List;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;    
//Brihaspati
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.ApplistPeer;

/** 
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */


public class ApplicationManagement extends SecureAction_Admin{
 MultilingualUtil m_u=new MultilingualUtil(); 
        String msg="";
	private Log log = LogFactory.getLog(this.getClass());
/**
* Add new role in Brihaspati LMS 
* @param data RunData 
* @param context Context
* @exception Exception, a generic exception
* @return nothing
*/
	public void doAdd(RunData data,Context context)
	{
	String LangFile=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();

			try	
			{
			/**
                       	*Retrieve the parameters by using the ParameterParser
                        */
                        String appName=pp.getString("aname","");
                        String appAcrm=pp.getString("aarcm","");
                        String appStat=pp.getString("astatus","");
                        String appUrl=pp.getString("aurl","");
                        String appSec=pp.getString("asec","");
                        String appSerLoc=pp.getString("aserloc","");
//			ErrorDumpUtil.ErrorLog("Role name "+ rName);
			Criteria crit=new Criteria();
			crit.add(ApplistPeer.APPURL,appUrl);
			crit.add(ApplistPeer.APPSERVERLOC,appSerLoc);
                        List alist=ApplistPeer.doSelect(crit);
			if(alist.size()<1){
				crit=new Criteria();
	                        crit.add(ApplistPeer.APPNAME,appName);
        	                crit.add(ApplistPeer.ACRONYM, appAcrm);
                	        crit.add(ApplistPeer.APPURL,appUrl);
                        	crit.add(ApplistPeer.APPSTATUS, appStat);
	                        crit.add(ApplistPeer.APPSECRETKEY,appSec);
        	                crit.add(ApplistPeer.APPSERVERLOC,appSerLoc);
				ApplistPeer.doInsert(crit);
//			ErrorDumpUtil.ErrorLog("I am Here ");
				msg=m_u.ConvertedString("appadd",LangFile);
	                        data.setMessage(msg);
			}
			else{
				msg=m_u.ConvertedString("appalready",LangFile);
                                data.setMessage(msg);
			}
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doAddApp of action Application Management ==========> " + e);

			}
	}


/**
* Remove any role in Brihaspati LMS 
* @param data RunData 
* @param context Context
* @exception Exception, a generic exception
* @return nothing
*/
	public void doDeleteRole(RunData data,Context context)
	{
	String LangFile=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();

			try	
			{
			/**
                       	*Retrieve the parameters by using the ParameterParser
                        */
                        String rId=pp.getString("id","");
			Criteria crit=new Criteria();
                        crit.add(ApplistPeer.ID,rId);
			ApplistPeer.doDelete(crit);
//			ErrorDumpUtil.ErrorLog("I am Here ");
			msg=m_u.ConvertedString("c_msg5",LangFile);
                        data.setMessage(msg);
			log.info("Application of "+rId+" Deleted by Admin");
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doDelete of action Application Management ==========> " + e);

			}
	}

/**
* Map Permission to Role in Brihaspati LMS 
* @param data RunData 
* @param context Context
* @exception Exception, a generic exception
* @return nothing
*/
	public void doUpdate(RunData data,Context context)
	{
	String LangFile=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();

			try	
			{
			/**
                       	*Retrieve the parameters by using the ParameterParser
                        */
                        String Id=pp.getString("id","");
						
			String appName=pp.getString("aname","");
                        String appAcrm=pp.getString("aarcm","");
                        String appStat=pp.getString("astatus","");
                        String appUrl=pp.getString("aurl","");
                        String appSec=pp.getString("asec","");
                        String appSerLoc=pp.getString("aserloc","");

			Criteria crit=new Criteria();
       	                crit.add(ApplistPeer.ID,Id);
			crit.add(ApplistPeer.APPNAME,appName);
                        crit.add(ApplistPeer.ACRONYM, appAcrm);
                        crit.add(ApplistPeer.APPURL,appUrl);
                        crit.add(ApplistPeer.APPSTATUS, appStat);
                        crit.add(ApplistPeer.APPSECRETKEY,appSec);
                        crit.add(ApplistPeer.APPSERVERLOC,appSerLoc);
			ApplistPeer.doUpdate(crit);
//			ErrorDumpUtil.ErrorLog("I am Here ");
			msg=m_u.ConvertedString("instAreg_msg4",LangFile);
                        data.setMessage(msg);
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doUpdate of action Application Management ==========> " + e);

			}
	}


/**
* This method is invoked when no button corresponding to
* action is found
* @param data RunData
* @param context Context
* @exception Exception, a generic exception
*/
public void doPerform(RunData data,Context context) throws Exception{
		String file=data.getUser().getTemp("LangFile").toString();
                String c_msg=m_u.ConvertedString("action_msg",file);
                String action=data.getParameters().getString("actionName","");
		ErrorDumpUtil.ErrorLog("The name of action is "+action);
		if(action.equals("eventSubmit_doAddRole"))
                        doAdd(data,context);
		else if(action.equals("eventSubmit_doDelete"))
                        doDeleteRole(data,context);
		else if(action.equals("eventSubmit_doUpdate"))
                        doUpdate(data,context);
		else
                        data.setMessage(c_msg);
        }

}//class ends















