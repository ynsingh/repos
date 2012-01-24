package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;

/*
 * @(#)StudentList.java	
 *
 *  Copyright (c) 2005-2006, 2010 ETRG,IIT Kanpur. 
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 */


/**
 * @author <a href="awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @author <a href="manjaripal@yahoo.co.in">Manjari Pal</a>
 * @author <a href="richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date: 20-10-2010, 3-11-2010
 */

import java.util.Vector;
import java.util.List;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentExpiryPeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

/**
  * This class contains code for listing of all user without admin and guest
  * Grab all the records in a table using a Peer, and
  * place the Vector of data objects in the context
  * where they can be displayed by a #foreach loop.
  */
  
public class StudentList extends SecureScreen_Instructor{

	 /**
	  * Place all the data object in the context for use in the template.
	  * @param data RunData instance
	  * @param context Context instance
	  * @exception Exception, a generic exception
	  * @see ListManagement from Utils
	  */

	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
		 /**
                 * getting property file According to selection of Language in temporary variable
                 * getting the values of first,last names and
                 * configuration parameter.
                 */
                                                            
		String LangFile = null;
		User user=data.getUser();
		LangFile=(String)user.getTemp("LangFile");
			context.put("tdcolor",data.getParameters().getString("count","")); 
			Vector userList=new Vector();
			List rusrlist;
			String course_id=(String)user.getTemp("course_id");
			String course_name=(String)user.getTemp("course_name");
			context.put("course",course_name);
			int g_id=GroupUtil.getGID(course_id);
			ParameterParser pp=data.getParameters();
                        	String stat=pp.getString("status","");
		                context.put("stat",stat);
			String query="";
			String valueString="";
                        String Mode=data.getParameters().getString("mode");
			 /**
                         *Time calculaion for how long user use this page.
                         */
			 String Role = (String)user.getTemp("role");
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                CourseTimeUtil.getCalculation(uid);
                                ModuleTimeUtil.getModuleCalculation(uid);
                         }

			/**
			 * Getting list of user rollno record 
			 */
			rusrlist=UserManagement.getListOfRollNo(g_id,3);
                        context.put("rollnolist",rusrlist);
			/**
 			 * getting the list of student expiry in this course
 			 */
			Criteria crit=new Criteria();
			crit.add(StudentExpiryPeer.CID,course_id);
			List explst=StudentExpiryPeer.doSelect(crit);
			context.put("sexplst",explst);
			
			if(Mode.equals("All"))
			{
				userList=UserGroupRoleUtil.getUDetail(g_id,3);
                        	context.put("mode","All");
			}
			else
			{
			/**
                         * Get the search criteria and the search string
                         * from the screen
                         */
			query=pp.getString("queryList");
                        if(query.equals(""))
                               	query=pp.getString("query");
                        valueString=pp.getString("valueString");
                        context.put("query",query);
                        context.put("valueString",valueString);
                        context.put("mode","Search");
                        String str=null;

                        if(query.equals("FirstName"))
                                str="FIRST_NAME";
                        else if(query.equals("LastName"))
                                str="LAST_NAME";
                        else if(query.equals("UserName"))
                                str="LOGIN_NAME";
                        else if(query.equals("Email"))
                                str="EMAIL";

                        /**
                          * Checks for Matching Records
                          */

			crit=new Criteria();
			crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
			crit.add("TURBINE_USER",str,(Object)(valueString+"%"),crit.LIKE);
			crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
			crit.add(TurbineUserGroupRolePeer.GROUP_ID,g_id);
			crit.setDistinct();			
			List v=TurbineUserPeer.doSelect(crit);
			userList=ListManagement.getDetails(v,"User"); 
			}
			
			String ConfParam=(String)user.getTemp("confParam");
			int confParam=Integer.parseInt(ConfParam);
                        //context.put("userConf",new Integer(ConfParam));
                        //context.put("userConf",new Integer(confParam));
                        context.put("userConf",confParam);
                        context.put("userConf_str",ConfParam);
                        //context.put("userConf_str",Integer.toString(ConfParam));
                        int startIndex=pp.getInt("startIndex",0);
			String status=new String();
                        int t_size=userList.size();

                        if(userList.size()!=0){

                                status="notempty";
                                int value[]=new int[7];
                                value=ListManagement.linkVisibility(startIndex,t_size,confParam);

                                //k=startIndex+1
                                int k=value[6];
                                context.put("k",String.valueOf(k));

                                Integer total_size=new Integer(t_size);
                                context.put("total_size",total_size);

                                int eI=value[1];
                                Integer endIndex=new Integer(eI);
                                context.put("endIndex",endIndex);

                                //check_first shows the first five records
                                int check_first=value[2];
                                context.put("check_first",String.valueOf(check_first));

                                //check_pre shows the first the previous list to the current records
                                int check_pre=value[3];
                                context.put("check_pre",String.valueOf(check_pre));
				
				//check_last1 gives the remainder values:-
                                int check_last1=value[4];
                                context.put("check_last1",String.valueOf(check_last1));

                                //check_last shows the last records:-
                                int check_last=value[5];
                                context.put("check_last",String.valueOf(check_last));

                                context.put("startIndex",String.valueOf(eI));
                                Vector splitlist=ListManagement.listDivide(userList,startIndex,confParam);
                                context.put("userlist",splitlist);
                        }
                        else
			{
				String msg1="";
                                if(Mode.equals("All"))
				{
					
					msg1=MultilingualUtil.ConvertedString("stu_msgc",LangFile);
					if(LangFile.endsWith("hi.properties"))
		                        	data.setMessage(course_name+" "+msg1);
					else
			                        data.setMessage(msg1+" "+course_name);
                //                	data.setMessage("There are no registered Students on this Course !!");
				}
				else
				{
					if(LangFile.endsWith("hi.properties"))
						msg1=query+" '"+valueString+"' "+MultilingualUtil.ConvertedString("brih_Of2",LangFile)+" "+MultilingualUtil.ConvertedString("brih_match_str",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Of1",LangFile)+" "+MultilingualUtil.ConvertedString("varStudent",LangFile)+" "+MultilingualUtil.ConvertedString("notExist",LangFile);
					else
						msg1= MultilingualUtil.ConvertedString("varStudent",LangFile)+" "+MultilingualUtil.ConvertedString("brih_with",LangFile)+" "+query+" '"+valueString+"' "+MultilingualUtil.ConvertedString("notExist",LangFile);
		                        data.setMessage(msg1);
                                //	data.setMessage(MultilingualUtil.ConvertedString("varStudent",LangFile)+MultilingualUtil.ConvertedString("brih_with",LangFile)+" "++query+" '"+valueString+" '"+MultilingualUtil.ConvertedString("notExist",LangFile));
				}
				status="empty";
                        }
                        context.put("status",status);

		}
		catch(Exception e)
		{
			data.setMessage("The error is Student List [Screen] :- "+e);
		}
	}
}

	
