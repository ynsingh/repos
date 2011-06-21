package org.iitk.brihaspati.modules.actions;

/*
 * @(#)InstructorList_Action.java     
 *
 *  Copyright (c) 2005,2010 ETRG,IIT Kanpur. 
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
 */


import java.io.File;
import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import java.util.StringTokenizer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.actions.SecureAction;
import org.iitk.brihaspati.om.InstructorPermissionsPeer;
import org.iitk.brihaspati.om.InstructorPermissions;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;

/**
 * This class is responsible for removing secondary instructor and changing their permission to the system.
 *  
 *	@author <a href="mailto:hardlucksunil@yahoo.in">Sunil Yadav</a>
 */

public class InstructorList_Action extends SecureAction
{
	/**
 	 * Method for removing temporarily secondary instructor
 	 * @param data RunData instance
 	 * @param context Context instance
 	 */
	private String LangFile=null;
	public void doRemoveUser(RunData data, Context context)
	{
		User user=data.getUser();
		ParameterParser pp=data.getParameters();
		String mid_delete = pp.getString("deleteFileNames","");
		String mode = pp.getString("mode","");
		String institudeName=data.getParameters().getString("institudeName","");
		String gName="";
                if(institudeName.equals("ListAll")){
                        gName=data.getParameters().getString("cName","");
                        context.put("institudeName",institudeName);
			context.put("cName",gName);
                }else{
                        gName=(String)user.getTemp("course_id");
                }
		
		//String gName=(String)user.getTemp("course_id");
			
		
		if(!mid_delete.equals(""))
		{
		java.util.StringTokenizer st=new java.util.StringTokenizer(mid_delete,"^");
		for(int j=0;st.hasMoreTokens();j++) 
		   {
			String username=st.nextToken();
			int uid=UserUtil.getUID(username);
			int GID=GroupUtil.getGID(gName);
			try{
				Criteria crit=new Criteria();
				crit.add(TurbineUserGroupRolePeer.ROLE_ID,2);
				crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
				crit.add(TurbineUserGroupRolePeer.GROUP_ID,GID);
				TurbineUserGroupRolePeer.doDelete(crit);
				String msg=MultilingualUtil.ConvertedString("Repo_prm3",LangFile);
				data.addMessage(msg);
				//data.addMessage("Secondry Instructor Delete Successfully !!!");
			}catch (Exception ex){ data.setMessage("Error in doRemoveUser of action InstructorList !!  " +ex); }
		     }
		}
		 context.put("mode",mode);
	}

	/**
         * Method for changing secondary instructor permission
         */		

	public void doPermissionUser(RunData data, Context context)
	{
		User user=data.getUser();
		ParameterParser pp=data.getParameters();
		String username=pp.getString("username");
		int permission=Integer.parseInt(pp.getString("permission"));
		int uid=UserUtil.getUID(username);

		String institudeName=data.getParameters().getString("institudeName","");
                String gName="";
                if(institudeName.equals("ListAll")){
                        gName=data.getParameters().getString("cName","");
                        context.put("institudeName",institudeName);
                        context.put("cName",gName);
                }else{
                        gName=(String)user.getTemp("course_id");
                }
                int GID=GroupUtil.getGID(gName);
		try{
			Criteria crit=new Criteria();
			crit.add(InstructorPermissionsPeer.USER_ID,uid);
			List l=InstructorPermissionsPeer.doSelect(crit);
			if(l.size()>0) {
				for(int i=0;i<l.size();i++) {
					InstructorPermissions element=(InstructorPermissions)(l.get(i));
   					int id=element.getId();
                                	crit=new Criteria();
					crit.add(InstructorPermissionsPeer.ID,id);
					InstructorPermissionsPeer.doDelete(crit);
			    	}
			 }else{
				crit=new Criteria();
				crit.add(InstructorPermissionsPeer.USER_ID,uid);
				crit.add(InstructorPermissionsPeer.GROUP_NAME,GID);
				crit.add(InstructorPermissionsPeer.PERMISSION_STATUS,permission);
				InstructorPermissionsPeer.doInsert(crit);
			}
			String msg=MultilingualUtil.ConvertedString("Repo_prm1",LangFile);	
			data.addMessage(msg);
			//data.addMessage("Instructor Permission Change Successfully !!!!");
		}catch(Exception ex){data.setMessage("Error in doRemoveUser of action InstructorList !!  " +ex); }
	}			


	
	public void doPerform(RunData data,Context context) throws Exception
	{
		LangFile=(String)data.getUser().getTemp("LangFile");
		String action=data.getParameters().getString("actionName","");
		 if(action.equals("eventSubmit_doRemoveUser")){
                        doRemoveUser(data,context);
		}else if(action.equals("eventSubmit_doPermissionUser")){
                        doPermissionUser(data,context);
		}else {
			String c_msg=MultilingualUtil.ConvertedString("c_msg",LangFile);
                        data.setMessage(c_msg);
		}
	}
}
		
	














			


		


	



























