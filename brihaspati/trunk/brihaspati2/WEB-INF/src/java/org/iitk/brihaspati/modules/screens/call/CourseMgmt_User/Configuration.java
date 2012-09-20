package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;         

/*
 * @(#)Configuration.java        
 *
 *  Copyright (c) 2006,2011,2012 ETRG,IIT Kanpur. 
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

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import java.util.Arrays;
import java.util.StringTokenizer;
import org.apache.torque.util.Criteria;

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;  
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.xmlrpc.XmlRpc;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.RemoteCourses; 
import org.iitk.brihaspati.om.RemoteCoursesPeer;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.RemoteCourseUtilClient;
import org.iitk.brihaspati.modules.screens.call.News.News_Add;
import  org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author <a href="mailto:manav_cv@yahoo.co.in">Manvendra Baghel</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:palseema30@gmail.com">Seema Pal</a>
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>29August2012
 */

public class Configuration extends SecureScreen_Instructor
{
	public void doBuildTemplate( RunData data, Context context ){
        	try{
			MultilingualUtil m_u=new MultilingualUtil();
			String file=data.getUser().getTemp("LangFile").toString();
                	User user=data.getUser();
 	                context.put("course",(String)user.getTemp("course_name"));
			ParameterParser pp = data.getParameters();
			String serial = pp.getString("serial","");
 	                context.put("serial",serial);
			context.put("tdcolor",pp.getString("count",""));
			 /**
                         *Time calculaion for how long user use this page.
                         */
			 String Role = (String)user.getTemp("role");
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(uid);
                                //ModuleTimeUtil.getModuleCalculation(uid);
				int eid=0;
				MailNotificationThread.getController().CourseTimeSystem(uid,eid);	
                         }

			/** get instituteId from temp for the configuration parameter
                         *and get configuration value from institute admin property file
                         *@see AdminProperties in utils         
                         */
                        String instituteId=(data.getUser().getTemp("Institute_id")).toString();
                        String Confpath=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteId+"Admin.properties";
                        String conf =AdminProperties.getValue(Confpath,"brihaspati.admin.listconfiguration.value");
                        int list_conf=Integer.parseInt(conf);

			//online registration configuration by sharad 01-01-2010
			String courseId=data.getUser().getTemp("course_id").toString();
			Criteria crit=new Criteria();
	                crit.add(CoursesPeer.GROUP_NAME,courseId);
                	List onlineconf = CoursesPeer.doSelect(crit);
			int online_conf=((Courses)onlineconf.get(0)).getOnlineconf();
			context.put("online_conf",online_conf);

			//code end
                        int startIndex=pp.getInt("updatestartIndex",0);
			if(startIndex > 0)
			{
                        	context.put("updatestartIndex",startIndex - list_conf);
			}

			RemoteCourses rce = null;

			if(!serial.equals(""))
			{

			 	crit=new Criteria();
				crit.add(RemoteCoursesPeer.SR_NO, serial);
				List l =  RemoteCoursesPeer.doSelect(crit);
				rce =(org.iitk.brihaspati.om.RemoteCourses)l.get(0);
				String course_id= rce.getRemoteCourseId() ;
			        context.put("cvalue",course_id);
                		String inst_ip=rce.getInstituteIp();
	                        context.put("url",inst_ip);

        	                String inst_name=rce.getInstituteName();
                	        context.put("ivalue",inst_name);

        	                String sec_key=rce.getSecretKey();
                	        context.put("sec_key",sec_key);

                        	int status=rce.getStatus();
                        	String order=null;
				String RemoteInstructor = null;
				if(status == 1)
		                {
                	        	order = "Sell" ;
                   			RemoteInstructor = rce.getCoursePurchaser();
                        	}
                        	else if(status == 0)
                        	{
                                	order = "Purchase";
                        		RemoteInstructor = rce.getCourseSeller();
                        	}
                    		context.put("order",order);
              		        context.put("cval",RemoteInstructor);
			}//if serial
			/**
			* Keep xmlrpc port Alive
			*/
                        boolean bool= XmlRpc.getKeepAlive();
                        if(!bool)
			{
                        	XmlRpc.setKeepAlive(true);
			}//if
			/*String activatemsg = "";
			String Successmsg =   data.getServerName();
			while(!activatemsg.equals(Successmsg))
			{
				activatemsg = RemoteCourseUtilClient.ActivateLocalXmlRpcPort();
				 ErrorDumpUtil.ErrorLog("activatemsgactivatemsg========="+activatemsg);
			}*/

			/**
			* call new add template
			*/
			News_Add na = new News_Add();
			na.doBuildTemplate(data, context);
			/**
			* Change here for date from update action
						ErrorDumpUtil.ErrorLog("I am here in Course configuration 1" );
			*/
			if(!serial.equals(""))
			{
				String Expiry = rce.getExpiryDate().toString();
                        	Expiry=Expiry.replace(" 00:00:00.0","");
                        	String sarr[]=Expiry.split("-");
				context.put("cyear",sarr[0]);
				context.put("cmonth",sarr[1]);
				context.put("cdays",sarr[2]);
			}//if
			/**
			* Call Guest 
			*/
			Guest(data,context);
			/**get Institute list from remote server */
			if(serial.equals(""))
			{
				String url =pp.getString("iip","");
				context.put("url",url);
				String instname =pp.getString("inm","");
				context.put("ivalue",instname);
                        	String RemoteTurbineMsg = RemoteCourseUtilClient.ActivateRemoteXmlRpcPort(url);
                        	if(RemoteTurbineMsg.equals("ERROR"))
                        	{
					String RemoteAction_msg2=m_u.ConvertedString("RemoteAction_msg2",file);
                                	data.addMessage(RemoteAction_msg2);
                        	}
				if(!url.equals("")){
					
                        		String serverURL =  "http://" + url + ":12345/" ;
                        		Vector param=new Vector();
                        		param.add(url);
                        		String instlist = RemoteCourseUtilClient.getInstituteList(serverURL,param);
					String str123=StringUtils.substringBetween(instlist,"[","]");
					Vector vct=new Vector();
					StringTokenizer st=new StringTokenizer(str123,",");
                                	while(st.hasMoreTokens())
                                	{
						String token = st.nextElement().toString();
						vct.add(token);
                                	}
					context.put("instlistname",vct);
					if(!instname.equals("")){
		
						ArrayList list = new ArrayList();
						Map map = new HashMap();

						String instvalue=instname.trim();
						param=new Vector();
					//	vct=new Vector();
					//	Vector onlycrs=new Vector();
                        			param.add(url);
                        			param.add(instvalue);
                        			String courselist = RemoteCourseUtilClient.getCourseList(serverURL,param);
					//	ErrorDumpUtil.ErrorLog("The list of course is :"+courselist );
						String crslist=StringUtils.substringBetween(courselist,"[","]");
						st=new StringTokenizer(crslist,",");
						
						while(st.hasMoreTokens())
						{
                                                        String ctoken = st.nextElement().toString();
							String cnme=StringUtils.substringAfterLast(ctoken,"^");
							String ctoken1=StringUtils.substringBeforeLast(ctoken,"^");
							map = new HashMap();
							map.put("idc", ctoken1);
							map.put("namecnm", cnme);
							list.add(map);
                                                }		
						context.put("csList", list);	
					}//if inst not empty
					String crsname=pp.getString("cid","");
					String invalue=pp.getString("ivalue","");
					if(!instname.equals(invalue))
					{
						String crsid=StringUtils.substringBeforeLast(crsname,"^");
						context.put("cvalue",crsid);
						String cval=pp.getString("cval");
						String lgname=StringUtils.substringAfterLast(cval,"^");
						context.put("cval",lgname);
					//	ErrorDumpUtil.ErrorLog("The two value is :"+lgname+" "+crsid);
					}
				}//if url empty
			}//if serial is empty
                }//try
                catch(Exception e)
  	        {        
                       data.addMessage("Error in screen [call,CourseMgmt_User,RemoteCourses, Course Configuration] is "+ e);
                }//catch
        }//function ends

    
    public void Guest( RunData data, Context context) throws Exception
    {
			String LangFile=(String)data.getUser().getTemp("LangFile");

			String g=data.getUser().getTemp("course_id").toString();
			String cname=data.getParameters().getString("cName");
			context.put("course",cname);
			User user=TurbineSecurity.getUser("guest");
			AccessControlList acl=TurbineSecurity.getACL(user);
			if(acl.hasRole("student",g))
			{
				context.put("Guest",true);
				
			}
			else
			{
				context.put("Guest",false);
			}
    }
	
/**--------------------------------------------------------------------------------------- */

}//class ends
