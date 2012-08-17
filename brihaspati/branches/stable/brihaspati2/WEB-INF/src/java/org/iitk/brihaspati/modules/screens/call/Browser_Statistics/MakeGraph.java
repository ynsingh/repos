package org.iitk.brihaspati.modules.screens.call.Browser_Statistics;
/*
 * @(#)MakeGraph.java   
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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
i *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
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

//Java
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
//import java.sql.Date;
import java.util.Date;
import java.lang.*;
//Apache turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.torque.util.Criteria;
//brihaspati
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.iitk.brihaspati.om.CourseTimedayPeer;
import org.iitk.brihaspati.om.CourseTimeday;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.io.File;
import org.iitk.brihaspati.modules.utils.XMLWriter_GraphCalculation;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;


public class MakeGraph extends SecureScreen
{
	public void doBuildTemplate(RunData data, Context context)
        {
		String LangFile=data.getUser().getTemp("LangFile").toString();
		ParameterParser pp=data.getParameters();

		try{
			User user=data.getUser();
			String username=user.getName();
			int userid=UserUtil.getUID(username);
			String coursename=(String)user.getTemp("course_name");
			context.put("coursename",coursename);
			String courseid=(String)user.getTemp("course_id");
			int gid=GroupUtil.getGID(courseid);
			String userrole=data.getUser().getTemp("role").toString();
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			String usrname=pp.getString("usrname","");
               		context.put("usrname",usrname);
			String type=pp.getString("type","");
			context.put("type",type);
			String mname=pp.getString("mname","");
                        context.put("mname",mname);
			String value=pp.getString("value","");
			context.put("value",value);
			int uid=UserUtil.getUID(usrname);
			Vector userList1=new Vector();
			Vector userList2=new Vector();
			if(userrole.equals("instructor"))
                	{
                        	userList1=ListManagement.getCourseUser(userid,gid);
				if(userList1.size()!=0){
                        	context.put("userList1",userList1);
				}
				else
				{
				context.put("userList1",userList1);
				data.setMessage(MultilingualUtil.ConvertedString("stu_msgc",LangFile));
				//data.setMessage(stu_msgc);
				}
				if(type.equals("sgraph")){
					userList2=ModuleTimeUtil.getmName(courseid,uid);
					context.put("userList2",userList2);
				}
				String filepath=TurbineServlet.getRealPath("/Graph");
				String filePath=filepath+"/GraphCalculation.xml";
				String filePath1=filepath+"/ModuleGraphCalculation.xml";
				int count=0;
				if(!value.equals("")){
					int val=Integer.parseInt(value);
                                	String valuestr1=XMLWriter_GraphCalculation.getGraphDetails(filePath,uid,courseid,val);					     
					if(!valuestr1.equals("")){
						count=count+1;
        	                        	String[] valuestr2=valuestr1.split(" ");
                	                	String valuestr="Date,Time\n";
                        	        	for (int j=0;j<valuestr2.length;j++)
                                		{
                                        		valuestr=valuestr+valuestr2[j]+"\n";
	                                	}
						context.put("valuestr",valuestr);
					}
					else
					{         
						//data.setMessage("action_msg");
						data.setMessage(MultilingualUtil.ConvertedString("bs_msg1",LangFile));
					}
				}//else
				if((!mname.equals("")) && (!value.equals(""))){
				
					String Modulevaluestr1=XMLWriter_GraphCalculation.getModuleGraphDetails(filePath1,uid,courseid,mname,Integer.parseInt(value));
					if(!Modulevaluestr1.equals("")){
                                	String[] Modulevaluestr2=Modulevaluestr1.split(" ");
                                	String Modulevaluestr="Date,Time\n";
                                	for (int i=0;i<Modulevaluestr2.length;i++)
                                	{
                                        	Modulevaluestr=Modulevaluestr+Modulevaluestr2[i]+"\n";
                                	}
			
                                	context.put("Modulevaluestr",Modulevaluestr);
					}
					else{
						if(count==0)
						data.setMessage(MultilingualUtil.ConvertedString("bs_msg2",LangFile));
						else
						data.setMessage(MultilingualUtil.ConvertedString("bs_msg3",LangFile));
					}
				}
			}
		}catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in Screen:MakeGraph !!"+e);
                                  }

	}
}
	
