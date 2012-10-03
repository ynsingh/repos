package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;
/*
 * @(#)ViewMarks.java	
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
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */
import java.util.Vector;
import java.util.List;
import org.apache.torque.util.Criteria;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Student;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;

import org.iitk.brihaspati.modules.utils.UserUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
 /** 
  * In this class, View Marks from Marks file uploading by group instructor
  * @author <a href="mailto:ammu_india@yahoo.com">Amit Joshi</a>
  * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
  * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
  * @modified date: 20-10-2010
  */
 
public class ViewMarks extends SecureScreen_Student 
{
	private String rollno1,rollno2="";
	private List v=null;
	public void doBuildTemplate(RunData data,Context context)
	{
		
		try
		{	
			User user=data.getUser();
			String checkUser=user.getName();
			String Role = (String)user.getTemp("role");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(checkUser);
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
				int eid=0;
				MailNotificationThread.getController().CourseTimeSystem(uid,eid);
                         }

			/**
			 * Get rollno from table 
			 */
			try
			{
				Criteria crit=new Criteria();
	                        crit.add(StudentRollnoPeer.EMAIL_ID,checkUser);
	                        v=StudentRollnoPeer.doSelect(crit);
				if(v.size()>0){
					StudentRollno element=(StudentRollno)v.get(0);
		                        rollno1=element.getRollNo();
				/**
 				 * Vector size greater than 1 shows that user have more than 1 rollno
 				 * then get another rollno 
 				 */ 
					if(v.size()>1)
					{
						StudentRollno element1=(StudentRollno)v.get(1);
						rollno2=element1.getRollNo();
					}
				}
			//ErrorDumpUtil.ErrorLog("The roll no is "+checkUser +" ==== "+ rollno1 +"===="+rollno2);
			}
			catch(Exception e)
			{ 
				ErrorDumpUtil.ErrorLog("Error inside getting value in view marks"+e);
			}
			
			boolean flag=true;
			if(v.size()>0){
				String dir=(String)user.getTemp("course_id");
				context.put("course",(String)user.getTemp("course_name"));
				String filePath=TurbineServlet.getRealPath("/Courses")+"/"+dir+"/Marks/MARK.txt";
				
				if(((new File (filePath)).exists())){
					FileReader fr=new FileReader(filePath);
					BufferedReader br=new BufferedReader(fr);
					String line;
					StringTokenizer sTokenizer;
					if((line=br.readLine())!=null)
					{
						sTokenizer=new StringTokenizer(line,",");
						Vector heading=new Vector();
						sTokenizer.nextToken();
						while(sTokenizer.hasMoreTokens())
						{
							heading.addElement(sTokenizer.nextToken());
						}
						context.put("markHeading",heading);
					}
					while((line=br.readLine())!=null)
					{
						sTokenizer=new StringTokenizer(line,",");
						try{
							String userName=sTokenizer.nextToken().trim();
							if(rollno1.equals(userName)||rollno2.equals(userName))
							{
								Vector markDetail=new Vector();
								while(sTokenizer.hasMoreTokens())
								{
									markDetail.addElement(sTokenizer.nextToken());
								}
								br.close();
						//	ErrorDumpUtil.ErrorLog("Error inside getting value in view marks 22"+markDetail.toString());
								context.put("marks",markDetail);
								context.put("marksDSize",Integer.toString(markDetail.size()));
								context.put("status","NoBlank");
								flag=false;
								break;
							}/* 
							else
							{
								context.put("marksDSize","0");
								context.put("status","Blank");
							} */
						}
						catch(Exception e){
							context.put("status","Blank");
							context.put("Exp","Exp");
							ErrorDumpUtil.ErrorLog("The Error in View marks Part "+e);
						}
					}
				}//if file exist
			}
			if (flag){
                                context.put("marksDSize","0");
                                context.put("status","Blank");
                        }
		}
		catch(IOException e)
		{
			context.put("status","Blank");
			context.put("Exp","MExp");
			ErrorDumpUtil.ErrorLog("The Error in View marks "+e);
		}
	}
}

