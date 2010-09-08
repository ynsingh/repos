package org.iitk.brihaspati.modules.actions;

/*
 *  @(#)RolePermissionMap.java
 
 *  Copyright (c) 2010 ETRG,IIT Kanpur.
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
import org.apache.turbine.services.security.torque.om.TurbineRolePeer;
import org.apache.turbine.services.security.torque.om.TurbinePermissionPeer;
import org.apache.turbine.services.security.torque.om.TurbineRolePermissionPeer;
//import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;    
//Brihaspati
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/** 
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 */


public class RolePermissionMap extends SecureAction_Admin{
 MultilingualUtil m_u=new MultilingualUtil(); 
        String msg="";
/**
* Add new role in Brihaspati LMS 
* @param data RunData 
* @param context Context
* @exception Exception, a generic exception
* @return nothing
*/
	public void doAddRole(RunData data,Context context)
	{
	String LangFile=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();

			try	
			{
			/**
                       	*Retrieve the parameters by using the ParameterParser
                        */
                        String rName=pp.getString("rname","");
//			ErrorDumpUtil.ErrorLog("Role name "+ rName);
			Criteria crit=new Criteria();
                        crit.addAscendingOrderByColumn(TurbineRolePeer.ROLE_ID);
			List rcord=TurbineRolePeer.doSelect(crit);
			int size=rcord.size();
			crit =new Criteria();
                        crit.add(TurbineRolePeer.ROLE_ID,size);
                        crit.add(TurbineRolePeer.ROLE_NAME,rName);
			TurbineRolePeer.doInsert(crit);
//			ErrorDumpUtil.ErrorLog("I am Here ");
			msg=m_u.ConvertedString("roleadd",LangFile);
                        data.setMessage(msg);
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doAddRole of action RolePermissionMap ==========> " + e);

			}
	}

/**
* Add new permission in Brihaspati LMS 
* @param data RunData 
* @param context Context
* @exception Exception, a generic exception
* @return nothing
*/
	public void doAddPerm(RunData data,Context context)
	{
	String LangFile=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();

			try	
			{
			/**
                       	*Retrieve the parameters by using the ParameterParser
                        */
                        String pName=pp.getString("pname","");
			Criteria crit=new Criteria();
                        crit.addAscendingOrderByColumn(TurbinePermissionPeer.PERMISSION_ID);
                        List rcord=TurbinePermissionPeer.doSelect(crit);
                        int size=rcord.size();
                        crit =new Criteria();
                        crit.add(TurbinePermissionPeer.PERMISSION_ID,size);
                        crit.add(TurbinePermissionPeer.PERMISSION_NAME,pName);
			TurbinePermissionPeer.doInsert(crit);
			msg=m_u.ConvertedString("permadd",LangFile);
                        data.setMessage(msg);
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doAddPermission of action RolePermissionMap ==========> " + e);

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
                        String rId=pp.getString("rlid","");
			Criteria crit=new Criteria();
                        crit.add(TurbineRolePeer.ROLE_ID,rId);
			TurbineRolePeer.doDelete(crit);
//			ErrorDumpUtil.ErrorLog("I am Here ");
			msg=m_u.ConvertedString("c_msg5",LangFile);
                        data.setMessage(msg);
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doDeleteRole of action RolePermissionMap ==========> " + e);

			}
	}

/**
* Map Permission to Role in Brihaspati LMS 
* @param data RunData 
* @param context Context
* @exception Exception, a generic exception
* @return nothing
*/
	public void doMap(RunData data,Context context)
	{
	String LangFile=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();

			try	
			{
			/**
                       	*Retrieve the parameters by using the ParameterParser
                        */
                        String roleId=pp.getString("role","");
                        String permiId=pp.getString("permi","");
			ErrorDumpUtil.ErrorLog("Role Id "+roleId+" Permission Id "+permiId);
			Criteria crit=new Criteria();
       	                crit.add(TurbineRolePermissionPeer.ROLE_ID,roleId);
                        crit.add(TurbineRolePermissionPeer.PERMISSION_ID,permiId);
			TurbineRolePermissionPeer.doInsert(crit);
//			ErrorDumpUtil.ErrorLog("I am Here ");
		//	msg= +update_msg;
			msg=m_u.ConvertedString("Repo_perm1",LangFile);
                        data.setMessage(msg);
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doDeleteRole of action RolePermissionMap ==========> " + e);

			}
	}

/**
* Remove Permission to Role in Brihaspati LMS 
* @param data RunData 
* @param context Context
* @exception Exception, a generic exception
* @return nothing
*/
	public void doDelMap(RunData data,Context context)
	{
	String LangFile=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();

			try	
			{
			/**
                       	*Retrieve the parameters by using the ParameterParser
                        */
                        String roleId=pp.getString("role","");
                        String permiId=pp.getString("permi","");
			ErrorDumpUtil.ErrorLog("Role Id "+roleId+" Permission Id "+permiId);
			Criteria crit=new Criteria();
                        crit.add(TurbineRolePermissionPeer.ROLE_ID,roleId);
       	                crit.add(TurbineRolePermissionPeer.PERMISSION_ID,permiId);
			TurbineRolePermissionPeer.doDelete(crit);
//			ErrorDumpUtil.ErrorLog("I am Here ");
			msg=m_u.ConvertedString("repo_perm3",LangFile);
                        data.setMessage(msg);
                	}//end of try 
                	catch(Exception e)
                	{
				     data.setMessage("Error in doDelMap of action RolePermissionMap ==========> " + e);

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
                        doAddRole(data,context);
		else if(action.equals("eventSubmit_doAddPerm"))
			doAddPerm(data,context);
		else if(action.equals("eventSubmit_doDelete"))
                        doDeleteRole(data,context);
		else if(action.equals("eventSubmit_doAccept"))
                        doMap(data,context);
		else if(action.equals("eventSubmit_doReject"))
                        doDelMap(data,context);	
		else
                        data.setMessage(c_msg);
        }

}//class ends















