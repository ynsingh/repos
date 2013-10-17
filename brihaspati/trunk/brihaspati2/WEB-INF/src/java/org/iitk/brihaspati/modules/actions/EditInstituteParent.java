package org.iitk.brihaspati.modules.actions;

/*
 * @(#)EditInstituteParent.java	
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. 
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
 */


import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;

import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ParentManagementUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 * This class contains code for updating parent details
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a> 
 * @modify date: 14-10-2013
 */
 
public class EditInstituteParent extends SecureAction_Institute_Admin
{
	/**
	 * This function is responsible for updating parent details in
	 * the database.
	 * @param data RunData instance
	 * @param context Context instance
	 * @throws Exception, a generic exception
	 * 
	 */
	private String file=null;
	private String msg=null;
	public void doUpdate(RunData data, Context context)
	{
		try
		{
		/**
		  * Getting file Parameter by Temporary Variable
		  * file Parameter having the property file according to selected language
		  */
			file=(String)data.getUser().getTemp("LangFile");
			StringUtil S=new StringUtil();

		/**
		 * Get all detail of parent for updatation 	   
		 */
			ParameterParser pp=data.getParameters();
			String parent_id=pp.getString("parentId");
			String first_name=pp.getString("fname");
                        String last_name=pp.getString("lname");
                        String email=pp.getString("email");
                        String mobile=pp.getString("mobile");
			String student_id = pp.getString("studentId");
			//ErrorDumpUtil.ErrorLog("Inside action:EditInstituteParent "+parent_id +" "+student_id+" "+first_name+" "+last_name+" "+email+" "+mobile);
			
			if(parent_id.equals(""))
			{
				/**
                		 * Add the parent details
		                 * @see ParentManagementUtil from Utils
		                 */
				msg=ParentManagementUtil.updateParentDetails("", student_id, file, first_name, last_name, email, mobile);
				//msg=ParentManagementUtil.updateParentDetails("", file, first_name, last_name, email, mobile);
				data.setMessage(msg);
			}
			else{
				/**
				 * Update the parent details
				 * @see ParentManagementUtil from Utils
				 */
				msg=ParentManagementUtil.updateParentDetails(parent_id, student_id, file, first_name, last_name, email, mobile);
				//msg=ParentManagementUtil.updateParentDetails(parent_id, file, first_name, last_name, email, mobile);
				data.setMessage(msg);
			}
		}//try
		catch(Exception e)
		{
			
			data.setMessage("The error in Updating Parent Details is: "+e);
		}
	}
	
	/**
	 * This method is called by default when no method corresponding to the 
	 * button is found.
	 * @param data RunData instance
	 * @param context Context instance
 	 */

	public void doPerform(RunData data,Context context)
	{
		/**
		  * Getting file Parameter by Temporary Variable
		  * file Parameter having the property file according to selected language
		  */
		file=(String)data.getUser().getTemp("LangFile");
		try
		{
			String actionToPerform=data.getParameters().getString("actionButton","");
			if(actionToPerform.equals("eventSubmit_doUpdate"))
			{
				doUpdate(data,context);
			}
			else
			{
				msg=MultilingualUtil.ConvertedString("error_msg4",file);
				data.setMessage(msg);
			}
		}
		catch(Exception e)
		{
			
			data.setMessage("The error"+e);
		}
	}
}

