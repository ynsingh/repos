package org.iitk.brihaspati.modules.screens.call.UserMgmt_Admin;

/*
 * @(#)UserManagement_Admin.java	
 *
 *  Copyright (c) 2004 -2005,2012 ETRG,IIT Kanpur. 
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
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */

/**
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a> 
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>1Nov2012
 * @author <a href="mailto:palseema@rediffmail.com">Manorama Pal</a> 
 */
import java.util.List;
import java.util.Vector;

import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.util.parser.ParameterParser;

import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;

public class UserManagement_Admin extends SecureScreen_Admin
{
	public void doBuildTemplate( RunData data, Context context )
    	{
		/** Get prameters from the templates
 		 *put in the context for use in template 
		 */	
		String mode=data.getParameters().getString("mode","");
		String counter=data.getParameters().getString("count"," ");
		context.put("tdcolor",counter);
		context.put("mode",mode);

		/** Get the Institute Name List from the database
		 *Institute List use in adduser,StudentCourseList,Delete Instructor and Student
		 *Add Multiple User and put in context for use in template
		 */
		try{ 
			Criteria crit = new Criteria();
        		crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
        		crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"1");
        		List instdetail=InstituteAdminRegistrationPeer.doSelect(crit);
			context.put("instituteDetail",instdetail);
			/** Get Institute name from template and put in context*/
			String iname=data.getParameters().getString("institute","");
			context.put("instname",iname);	
		}
		catch(Exception ex){ErrorDumpUtil.ErrorLog("The error in getting Institute Detail !!"+ex);}

		/**Get the institute to delete the instructor and put in context*/
		String institutename=data.getParameters().getString("group","");
		context.put("group",institutename);
		/**Get the Course to delete the instructor and put in context*/
		String Crsname=data.getParameters().getString("Cgroup","");
		if(!institutename.equals("All")){
		/**Get InstituteId of selected institute(institutename)
 		 *@see InstituteIdUtil in utils.
		 */ 	
		int instid=InstituteIdUtil.getIst_Id(institutename);
		if(Crsname.endsWith(Integer.toString(instid)))
		context.put("Cgroup",Crsname);
		}
		/** Check mode for delete user(Instructor/Student)
 		 *get role from template and put in context
		 */	
		if(mode.equals("userdelete")){
			String role=data.getParameters().getString("role");
			context.put("role",role);
			/**Get the Course list of selected Institute and All Courses.
 			 *@see getCourseId below 		
  			 */
		}
		/** Check mode for Student Course list
 		 *get Student course list according to the query
		 */
		if(mode.equals("sclist")){
			String mode1=data.getParameters().getString("mode1","");
			context.put("mode1",mode1);
			if(mode1.equals("list")){
				try{
					String file=null;
                       			MultilingualUtil m_u=new MultilingualUtil();
                       			file=(String)data.getUser().getTemp("LangFile");
                        		ParameterParser pp=data.getParameters();
					List v=null;
                        		Vector userList=new Vector();
                        		String query=pp.getString("queryList");
                        		if(query.equals(""))
                              		query=pp.getString("query");
                        		/**
                          		* Check for special characters
                          		*/
					String  valueString =StringUtil.replaceXmlSpecialCharacters(data.getParameters().getString("value"));
                         		context.put("query",query);
                        		context.put("value",valueString);
                        		String str=null;
                        		if(query.equals("First Name"))
                                		str="FIRST_NAME";
                        		else if(query.equals("Last Name"))
                                		str="LAST_NAME";
                        		else if(query.equals("User Name"))
                                		str="LOGIN_NAME";
                        		else if(query.equals("Email"))
                                		str="EMAIL";
					/** Get the list of student according to query
 					*add in vector v 			
					*/
                        		Criteria crit=new Criteria();
                        		crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
                        		crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
                        		crit.add("TURBINE_USER",str,(Object)(valueString+"%"),crit.LIKE);
                        		crit.setDistinct();
                        		v=TurbineUserPeer.doSelect(crit);
					/** If User exists then search the detail of user
 					*according to the institute.
					*@see ListManagement in utils.
					*put in the context.
					*/
					String insname=data.getParameters().getString("institutename","");
					context.put("slctdinstname",insname);
                        		if(v.size()!=0)
                                	{
					
                                		userList=ListManagement.getInstituteUDetails(v,"User",Integer.toString(InstituteIdUtil.getIst_Id(insname)));
						if(userList.size()==0)
							data.setMessage("The String"+" "+"'"+valueString+"'"+" "+"not matched in the Institute"+" "+insname);
						/** Get the path of Admin.properties file
  						 * get the value of Conf parameter and put in context
  						 * divide the list of user according to the Conf Parameter
 						 * put in context for use in template
 						 * @see CommonUtility in utils.
 						 */		
                                		String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                		int AdminConf = Integer.parseInt(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));
                                		context.put("AdminConf",new Integer(AdminConf));
                                		context.put("AdminConf_str",Integer.toString(AdminConf));
						Vector splitlist=CommonUtility.PListing(data,context,userList,AdminConf);
                                		context.put("ListUser",splitlist);
                        		}
                        		else
                        		{
                                		String usrWith=m_u.ConvertedString("usrWith",file);
                                		String notExist=m_u.ConvertedString("notExist",file);
                                		if(((String)data.getUser().getTemp("lang")).equals("hindi"))
                                        		data.setMessage(usrWith+" "+query+" "+"'"+ valueString+"'"+" "+notExist );
                                		else
                                        	data.setMessage(usrWith+" "+query+" "+"'"+ valueString+"'"+" "+notExist );

                                		context.put("stat","ForallUser");
                                		data.setScreenTemplate("call,UserMgmt_Admin,UserManagement_Admin.vm");
                        		}
				}//try
				catch(Exception ex){data.setMessage("error in select course list---->"+ex);}
			}//if mode1
		}//ifmode
	}//method
}//calss

