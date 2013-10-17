package org.iitk.brihaspati.modules.screens.call.ListMgmt_InstituteAdmin;

/*
 * @(#)Parent_Mgmt.java	
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
 *u  WHETHER IN CONTRACT, 
 *  RICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 D*  Contributors: Members of ETRG, I.I.T. Kanpur 
 */

import java.util.Vector;
import java.util.List;
import java.util.ListIterator;
import java.sql.ResultSet;
import java.sql.Statement;
import com.workingdogs.village.Record;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.ParentManagementUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil; 
import org.iitk.brihaspati.modules.utils.StringUtil; 
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil; 
import org.iitk.brihaspati.modules.utils.ListManagement; 
import org.iitk.brihaspati.modules.utils.GroupUtil; 
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.ParentInfoPeer;
import org.iitk.brihaspati.om.ParentInfo;

/**
 * This class contains code for displaying list of parent details
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a> 
 * @createdate 14-10-2013
 */

public class Parent_Mgmt extends SecureScreen_Institute_Admin
{
      /**
       * List of all the registered parents with details
       * @param data RunData
       * @param context Context
       */
	 private String file=null; 	
	public void doBuildTemplate(RunData data, Context context)
	{
		String query="";
		Vector Details=new Vector();

		try
		{
		 /**
                  * Getting Language value from temporary variable
                  * Getting file value from temporary variable according to selection
                  * Replacing the value from Property file
                  **/
 			String counter=data.getParameters().getString("count","");
		        context.put("tdcolor",counter);

                	file=(String)data.getUser().getTemp("LangFile");
                	MultilingualUtil m_u=new MultilingualUtil();
			User user=data.getUser();
			String course_id=(String)user.getTemp("course_id");
			int g_id = 0;

			//get institute id 
		        String instituteId=(user.getTemp("Institute_id")).toString();
			//ErrorDumpUtil.ErrorLog("Inside Parent Mgmt screen. institute_id = "+instituteId);
			String Mode=data.getParameters().getString("mode","");
			String searchFor = data.getParameters().getString("searchFor","");
			String valueString="";

			/**
                         * get all parents for the students
			 * of this institute
                         */
			if(Mode.equals("All"))
                        {
				Details = ParentManagementUtil.getListOfAllParents("All",instituteId);
				//ErrorDumpUtil.ErrorLog("Inside Parent Mgmt screen. details size = "+Details.size());
		//		context.put("details",Details);
				context.put("mode","All");
				context.put("searchFor",searchFor);
			}//if mode	
			else if(Mode.equals("instructor"))
			{
				g_id = GroupUtil.getGID(course_id);
				String groupId = Integer.toString(g_id);
				//Details = ParentManagementUtil.getParentsGroupwise("All",instituteId,g_id);	
				//ErrorDumpUtil.ErrorLog("Group Id inside ParentMgmt screen "+groupId);
				Details = ParentManagementUtil.getParentsGroupwise("All",instituteId,groupId);	
				context.put("course",(String)user.getTemp("course_name"));
                                context.put("mode","instructor");
				context.put("searchFor",searchFor);
			}
			else
			{
				/**
	                         * Get the search criteria and the search string
        	                 * from the screen
                	         */
				query=data.getParameters().getString("queryList");
				//ErrorDumpUtil.ErrorLog("Query String = "+query);
//				String searchFor = data.getParameters().getString("searchFor");
                        	if(query.equals(""))
                                	query=data.getParameters().getString("query");
				//ErrorDumpUtil.ErrorLog("Search for ===="+searchFor);				
				/**
	                         * Check for special characters
        	                 */
                           	valueString =StringUtil.replaceXmlSpecialCharacters(data.getParameters().getString("valueString"));

                        //	valueString=data.getParameters().getString("valueString");
                        	context.put("query",query);
                        	context.put("valueString",valueString);
                        	context.put("mode","Search");
				context.put("searchFor",searchFor);
				context.put("course",(String)user.getTemp("course_name"));
				//get parents list
				//Details = ParentManagementUtil.getParentListBySearchString(query,valueString,instituteId);
				if(searchFor.equals("instructor"))
					g_id = GroupUtil.getGID(course_id);
				Details = ParentManagementUtil.getParentListBySearchString(query,valueString,instituteId,searchFor,g_id);
		//		context.put("details",Details);
			}

			String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
			int AdminConf = Integer.valueOf(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));

//                        int AdminConf = AdminProperties.getValue(path);
                        context.put("AdminConf",new Integer(AdminConf));
                        context.put("AdminConf_str",Integer.toString(AdminConf));
			ParameterParser pp=data.getParameters();
                        int startIndex=pp.getInt("startIndex",0);
			String status=new String();
                        int t_size=Details.size();

                        if(Details.size()!=0){

                                status="Noblank";
                                int value[]=new int[7];
                                value=ListManagement.linkVisibility(startIndex,t_size,AdminConf);

                                int d=value[6];
                                context.put("k",String.valueOf(d));

                                Integer total_size=new Integer(t_size);
                                context.put("total_size",total_size);

                                int eI=value[1];
                                Integer endIndex=new Integer(eI);
                                context.put("endIndex",endIndex);

                                int check_first=value[2];
                                context.put("check_first",String.valueOf(check_first));

                                int check_pre=value[3];
                                context.put("check_pre",String.valueOf(check_pre));
				
				int check_last1=value[4];
                                context.put("check_last1",String.valueOf(check_last1));

                                int check_last=value[5];
                                context.put("check_last",String.valueOf(check_last));

                                context.put("startIndex",String.valueOf(eI));
                                Vector splitlist=ListManagement.listDivide(Details,startIndex,AdminConf);
                                context.put("details",splitlist);
				ErrorDumpUtil.ErrorLog("AdminConf ->>> "+ new Integer(AdminConf)+" AdminConf_str->>>> "+Integer.toString(AdminConf)+" total_size->>> "+total_size+" startIndex->>> "+eI);
                        }
                        else
			{
                                if(Mode.equals("All"))
				{
                                	data.setMessage(m_u.ConvertedString("regCourseMsg",file));
				}
				else
				{
                                	//String str=m_u.ConvertedString("listCourseReg",file);
					String str = "Searched parent";
                                        String str1=m_u.ConvertedString("notExist",file);
					if(((String)data.getUser().getTemp("lang")).equals("hindi"))
                                        	data.setMessage(str +"  "+ str1);      
					else if(((String)data.getUser().getTemp("lang")).equals("urdu"))
                                                data.setMessage(str+" "+str1);

					else
	                                        data.setMessage(str+" "+ str1);      
				}
				status="blank";
                        }
			context.put("status",status);
		}
	 	catch (Exception e)
        	{
			data.setMessage("The error in Parent Listing :-"+e);
	        }       
	}
}

