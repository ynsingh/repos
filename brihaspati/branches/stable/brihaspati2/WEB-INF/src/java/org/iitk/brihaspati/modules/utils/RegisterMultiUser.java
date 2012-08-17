package org.iitk.brihaspati.modules.utils;

/*@(#)RegisterMultiUser.java
 *  Copyright (c) 2005-2006,2010,2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */
import java.io.*;
import java.io.File;
import java.util.List;
import java.util.Vector;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.apache.turbine.services.servlet.TurbineServlet;

import java.io.FileOutputStream;
import java.io.InputStream;
import javax.servlet.ServletRequest;
import org.iitk.brihaspati.modules.utils.CourseUtil;
/**
 * This is the util class used to upload multiple users simultaneously
 *  @author <a href="mailto:ammuamit@iitk.ac.in">Amit Joshi</a> 
 *  @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 *  @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a> 
 *  @author <a href="mailto:madhavi_mungole@hotmail.com">Madhavi Mungole</a> 
 *  @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a> 
 *  @author <a href="mailto:shaista.shekh@gmail.com">Shaista</a> 
 *  @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur Gurung</a> 
 *  @modified date: 20-10-2010, 23-12-2010, 26-07-2011,17-05-2012
 *  @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 *  @modify date: 09-08-2012 (Priyanka)
 */

public class RegisterMultiUser
{
	/**
	 * This method uploads simultaneously multiple users having the
	 * details stored in a file
	 * @param f File
	 * @param Gname String
	 * @param Inst_id String
	 * @param Role String
	 * @param serverName String
	 * @param serverPort String
	 * @param Langfile String
	 * @return Vector 
	 */
	public static Vector Multi_Register(File StudFile,String Gname,String Role,String Langfile, String instName)
	{
		System.gc();
		Vector ErrType=new Vector();
		try
		{
			String serverName=TurbineServlet.getServerName();
                	String serverPort=TurbineServlet.getServerPort();
			int entryNumber=0;
			CourseUserDetail MsgDetails=new CourseUserDetail();
			String course_id="";
			String Cname="";
                        String GroupName=Gname;
			String groupName="";
                        String fileName=StudFile.getName();
                        String flName[]=fileName.split("\\.");
                        String filname=flName[0].toUpperCase();
			course_id=filname;
			/**
		 	* Stores the uploaded file on the specified path
		 	*/
			FileReader fr=new FileReader(StudFile);
			BufferedReader br=new BufferedReader(fr);
			String line;
			/**
			* Get domain name from Group Name
			*/
			/*String []starr=Gname.split("@");
	                String gnamewdomain=starr[1];
        	        String actgname[]=gnamewdomain.split("_");
                	String addUname=actgname[0];
			*/
			/**
		 	* Read the lines in the file one by one and extracts
		 	* the user details with the
		 	* help of StringTokenizer
		 	*/
			while((line=br.readLine())!=null)
			{
				StringTokenizer st2=new StringTokenizer(line,";",true);
				entryNumber++;
				String username="",first_name="",last_name="",email="", passwd="", rollno="" ,program="";
				int error=0;
				String mail_msg="";
				String errMsg="";
				String Cid="";
	
				if(st2.countTokens()<7)
				{ 
					error=1;
				}
				else 
				{
					username=st2.nextToken().trim();
					//username=username+"@"+addUname;
					if(username.equals(";"))
						{error=2;}
			      		else 
					{
						st2.nextToken();				
			      			passwd=st2.nextToken().trim();
			      			if(!passwd.equals(";"))
							{st2.nextToken();}
						else 
							{String psd[]=username.split("@");
							passwd=psd[0];}
							//passwd=username;}
			      			first_name=st2.nextToken().trim();
			      			if(!first_name.equals(";"))
						{
			         			if(!st2.hasMoreTokens())
								{ error=1;}
							else
								{st2.nextToken();}
						}
						else 
							{first_name="";}
 						if(!st2.hasMoreTokens())
							{error=1;}
						else {
							last_name=st2.nextToken().trim();
			      				if(!last_name.equals(";")){
								if(!st2.hasMoreTokens())
								{error=1;}
								else {st2.nextToken();}
							} else {last_name="";}
                              				if(!st2.hasMoreTokens()) 
								{error=1;}
							else{	 
								program=st2.nextToken().trim();
							    }
                       					if(!program.equals(";"))
							{
								if(!st2.hasMoreTokens())
								{error=1;}
								else
								{st2.nextToken();}
							} else {program="";}
						}							
						if(!st2.hasMoreTokens())
		                                         {error=1;}
						else
                                                {
							rollno=st2.nextToken().trim();
							if(rollno.equals(""))
							{
								if(program.equals(""))
									{error=0;
									}
								else{
									program="";
								}
							}
							else
							{			
								if(program.equals("")){
								
									rollno="";
								}
								else	{
									error=0;
								}
							}
						}
						/**
                                        	* if roll no terminated with semicolon or null 
                                        	* check last token for courseid.
                                        	**/
                                        	if(GroupName.equals("")){
								Cname=CreateGroupName(course_id,instName,StudFile);
                                                	if(!st2.hasMoreTokens() && Cname.equals("")){
                                                        	error=2;
                                                	}
							else if((st2.hasMoreTokens())&& (Cname.equals(""))){
								st2.nextToken();
								Cid=st2.nextToken().trim().toUpperCase();
								if(Cid.equals("")){
                                                                        error=2;
                                                		}
								else {
									course_id=CreateGroupName(Cid,instName,StudFile);
									groupName=course_id;
                                                                       	error=0;
                                                                }
							}
							else{
								groupName=Cname;
								error=0;
								if(groupName.equals("")){
								error=2;
								}
							}
					}
					else{
					groupName=Gname;
					error=0;
					}
						
					if(error==0){
							email=username;
							/**
							 * instName is passed by shaista
 							 * Passing the institue_name as string variable for getting 
 							 * expirydate according to admin profile
 							 * and to get Institute Admin First and last name to send in mail 
 							 * @see UserManagement Util
 							 */
							String str="";
                                                        if(instName.length() >0){
                                                                str=UserManagement.CreateUserProfile(email,passwd,first_name,last_name,instName,email,groupName,Role,serverName,serverPort,Langfile,rollno,program,"act"); //modified by Shikha Shukla. Last parameter added by Priyanka
							error=3;
								if(Role.equals("student")){
								errMsg=MultilingualUtil.ConvertedString("varStudent",Langfile)+" - "+str;
								}
								else{
                                                                errMsg=MultilingualUtil.ConvertedString("instructor",Langfile)+" - "+str;
                                                                }

							}
                                                        else{
								str="InstituteName is null please check instName=="+instName;
								//str=UserManagement.CreateUserProfile(email,passwd,first_name,last_name,"",email,Gname,Role,serverName,serverPort,Langfile,rollno,program); //modified by Shikha Shukla
								error=4;
								errMsg=str;
							}
						}
					}
				}
			/**
			 * Adds the error message to a vector if all the required fields 
			 * are not entered in the file. The entry number is also added.
			 */
			String report=("File Name="+fileName+" | Role=student | emailid="+username+" | roll no="+rollno+" | Institute name="+instName+" | Group Name="+groupName+"\n");
			String logPath=TurbineServlet.getRealPath("/logs/RegistrationReportLog.txt");
			if( error!=0){
				MsgDetails=new CourseUserDetail();
				String ErrorEntryNumber=Integer.toString(entryNumber);
				MsgDetails.setErr_User(ErrorEntryNumber);
				if(error==1){		
					String error_msg1=MultilingualUtil.ConvertedString("error_msg1",Langfile);
					MsgDetails.setErr_Type(error_msg1);
					// This Error Log is used for create log file of Registered User.
					ErrorDumpUtil.ErrorLog("Student registration log ===> "+error_msg1+"\n"+report,logPath); 
				}
				if(error==2){
					String error_msg2=MultilingualUtil.ConvertedString("error_msg2",Langfile); 
					MsgDetails.setErr_Type(error_msg2);
					// This Error Log is used for create log file of Registered User.
					ErrorDumpUtil.ErrorLog("Student registration log ===> "+error_msg2+"\n"+report,logPath);
				}
				if(error==3|| error==4){
					MsgDetails.setErr_Type(errMsg);
					// This Error Log is used for create log file of Registered User.
					ErrorDumpUtil.ErrorLog("Student registeration log ===> "+errMsg+"\n"+report,logPath);
				}
					ErrType.add(MsgDetails);
				}
			}
		fr.close();
		StudFile.delete();
		}
		catch(Exception ex)
		{	
			ErrType.add("The error in multi user registraion "+ex);
		}
		System.gc();
		return(ErrType);
	}

	/**
         * This method register a new course along with the instructor 
         * in the system
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */

	 public static Vector RegisterInstructor(File InstFile,String LangFile,String instName)
         {
         	/**
                 * Getting file value from temporary variable according to selection
                 * Replacing the value from Property file
                 */
                Vector ErrType=new Vector();
                try
                {
		 	String serverName=TurbineServlet.getServerName();
                	String serverPort=TurbineServlet.getServerPort();
                	int entryNumber=0;
			int InstituteId=0;
                        CourseUserDetail MsgDetails=new CourseUserDetail();
			if(instName.equals("")){
				InstituteId=0;
			}
			else{
                        	InstituteId= InstituteIdUtil.getIst_Id(instName);
			}
                                FileReader fr=new FileReader(InstFile);
                                BufferedReader br=new BufferedReader(fr);
                                String line;
                                
				/**
                                 * Read the lines in the file one by one and extracts
                                 * the user details with the
                                 * help of StringTokenizer
                                 */
                                
				while((line=br.readLine())!=null)
				{
                                	StringTokenizer st1=new StringTokenizer(line,";",true);
                                	entryNumber++;
                                	String first_name="",email="";
                                	String  courseid="", courseName="",uname="";
                                	int error=0;
                                	String mail_msg="";
                                	String errMsg="";
                                	if(st1.countTokens()<7 ||st1.countTokens()>7)
                                	{
                                		error=1;
					}
                                	//insufficient argument
                                	else
                                	{
                                        	first_name=st1.nextToken().trim();
                                        	if(first_name.equals(";"))
                                        		{error=2;}
                                        	else{
                                        		st1.nextToken();
                                        		email=st1.nextToken().trim();
                                        		if(email.equals(";")){
                                        			error=2;}
                                        		else{
                                        			st1.nextToken();
                                        			courseid=st1.nextToken().trim();
                                        			if(courseid.equals(";")){
                                        				error=2;}
                                        			else{
                                        				st1.nextToken();
                                        				courseName=st1.nextToken().trim();
                                        				if(courseName.equals(";"))
                                        					{error=2;}
                                        				else{
                                        					int i=email.indexOf("@");
                                        					String pass=email.substring(0,i);
                                        					uname=email;
                                        					String check=courseid.concat(uname);
                                        					/** Getting the group name from the database
                                        					 * and compare this group name with current group name
                                        					 */
                                        					Criteria  crit = new Criteria();
                                        					crit.add(CoursesPeer.GROUP_NAME,check);
                                        					List v=CoursesPeer.doSelect(crit);
                                        					String gName="";
                                        					if(v.size() > 0 )
                                        						gName=((Courses)(v.get(0))).getGroupName();
                                        						if(check.equalsIgnoreCase(gName)){
                                        							error=4;
                                        						}
                                        						else{
                                        							String passwd=pass;
                                        							String dept="", description="", lname="";
                                        							/**
                                        							* Register a new course with instructor
                                        							* @see CourseManagement Utils
                                        							*/
												if(InstituteId==0){
													String msg=CourseManagement.CreateCourse(courseid,courseName,dept,description,uname,passwd,first_name,lname,email,serverName,serverPort,LangFile,0,"","");//last parameter added by Priyanka
     													error=3;
                                        								errMsg=msg;
												}
												else{
													boolean checkspace=QuotaUtil.CompareAllotedQuota(Integer.toString(InstituteId));
                                        								if(checkspace){

                                        									/** 
                                         									* Added By shaista     
                                         									* passing instituteName variable as a string 
                                         									* to get institute id for exprydate according to admin profile
                                         									* @see RegisterMultiUser util
                                         									* @see UserManagement Util
                                         									**/

                                        									String msg=CourseManagement.CreateCourse(courseid,courseName,dept,description,uname,passwd,first_name,lname,email,serverName,serverPort,LangFile,InstituteId,instName,"");//last parameter added by Priyanka
                                        									error=3;
                                        									errMsg=MultilingualUtil.ConvertedString("instructor",LangFile)+" - "+msg;
                                        								}
                                        								else{
                                                								error=5;
                                        								}
												}
                                        						}
                                        					}
                                        				}
                                        			}
                                        		}
                                		}

   					/**
                                	* Adds the error message to a vector if all the required fields
                                	* are not entered in the file 
                                	* The entry number is also added.
                                	**/
                                	String report=("Instructor file name="+InstFile.getName()+" |  Role=Instructor |  First name="+first_name+" |  Email="+email+" |  Course Id="+courseid+" |  course name="+courseName+"\n");
					String logPath=TurbineServlet.getRealPath("/logs/RegistrationReportLog.txt");
                                	if( error!=0){//if error
                                		MsgDetails=new CourseUserDetail();
                                		String ErrorEntryNumber=Integer.toString(entryNumber);
                                		MsgDetails.setErr_User(ErrorEntryNumber);
                                		if(error==1){
                                        		String error_msg1=MultilingualUtil.ConvertedString("instructor",LangFile)+" - "+MultilingualUtil.ConvertedString("error_msg1",LangFile);
                                        		MsgDetails.setErr_Type(error_msg1);
							// This Error Log is used for create log file of Registered User.
							ErrorDumpUtil.ErrorLog("Instructor registeration log ===> "+error_msg1+"\n"+report,logPath);
                                		}
                                		if(error==2){
                                        		String error_msg2=MultilingualUtil.ConvertedString("instructor",LangFile)+" - "+MultilingualUtil.ConvertedString("error_msg2",LangFile);
                                        		MsgDetails.setErr_Type(error_msg2);
							// This Error Log is used for create log file of Registered User.
							ErrorDumpUtil.ErrorLog("Instructor registeration log ===> "+error_msg2+"\n"+report,logPath);
                                		}
                                		if(error==3){
                                       	 		MsgDetails.setErr_Type(errMsg);
							// This Error Log is used for create log file of Registered User.
							ErrorDumpUtil.ErrorLog("Instructor registeration log ===> "+errMsg+"\n"+report,logPath);
                                		}
                                		if(error==4){
                                        		String c_msg1=MultilingualUtil.ConvertedString("instructor",LangFile)+ " - "+MultilingualUtil.ConvertedString("c_msg1",LangFile);
                                        		MsgDetails.setErr_Type(c_msg1);
							// This Error Log is used for create log file of Registered User.
							ErrorDumpUtil.ErrorLog("Instructor registeration log ===> "+c_msg1+"\n"+report,logPath);
                                		}
                                		if(error==5){
                                        		String qmgmt_msg7=MultilingualUtil.ConvertedString("instructor",LangFile)+" - "+MultilingualUtil.ConvertedString("qmgmt_msg7",LangFile);
                                        		MsgDetails.setErr_Type(qmgmt_msg7);
							// This Error Log is used for create log file of Registered User.
							ErrorDumpUtil.ErrorLog("Instructor registeration log ===> "+qmgmt_msg7+"\n"+report,logPath);
                                		}
                                		ErrType.add(MsgDetails);
                        		}
                		}
                  		br.close();
                  		fr.close();
                  		//InstFile.delete();
        	}
                catch(Exception e)
                {
                     ErrType.add("  The error in Instructor registraion "+e);
                }
                System.gc();
                return(ErrType);
	}
	
	/**
         * This method search emailId corresponding their courseId
	 * in the instructor registeration file and create groupName. 
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */

	public static String CreateGroupName(String courseId,String instName,File filePath)
	{
   		String groupName="";
   		try{
   			int tokencount;
			Vector<String> gName = new Vector<String>();
			int InstituteId= InstituteIdUtil.getIst_Id(instName);
			String instId=Integer.toString(InstituteId);
   			String instPath=(filePath.getParent()+"/inst.txt");	
			File InstructorFile=new File(instPath);
                        boolean exist = InstructorFile.exists();
			if(!exist){
				/** 
                         	* Getting the group name from the database 
				* if "inst.txt" file not exist.
                         	*/
				gName=CourseUtil.getCourseNameWithGAlias(courseId,instId);
				if(gName.size()==1){
				groupName=gName.get(0).toString();
				}
				else{
                              			groupName="";
				}
			}
			else {
   			FileReader fr=new FileReader(InstructorFile);
   			BufferedReader br=new BufferedReader(fr);
   			String s;
			String TempgroupName="";
   			int linecount=0;
   			String line;
   			String words[]=new String[500];
                         while ((s=br.readLine())!=null)
                         {
                         	linecount++;
                                int indexfound=s.indexOf(courseId);
                                	if (indexfound>-1)
                                        {
                                                line=s;
                                                int idx=0;
                                                StringTokenizer st= new StringTokenizer(line,";",true);
                                                tokencount= st.countTokens();
                                                for (idx=0;idx<tokencount;idx++)
                                                {
	                                                String instructorName=st.nextToken().trim();
                                                        if(!instructorName.equals(""))
							{
                                                        	st.nextToken();
                                                        	String email=st.nextToken().trim();
                                                        	if(!email.equals("")){
									TempgroupName=courseId+email+"_"+InstituteId;
							   		/** 
						                	* Getting the group name from the database
                                                                	* and compare this group name with current group name
                                                                	*/
										String CourseName=CourseUtil.getCourseName(TempgroupName);
										if(!CourseName.equals("null")){
										groupName=TempgroupName;
										}
										else{
										groupName="";
										}
								}
							}
                                                        break;
						}
					}
				}
   		fr.close();
   		}
	}
	catch(Exception e){}
		return groupName;
	}
}
