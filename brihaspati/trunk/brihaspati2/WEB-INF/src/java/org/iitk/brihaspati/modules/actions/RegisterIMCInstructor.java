package org.iitk.brihaspati.modules.actions;
/*
 * @(#)RegisterIMCInstructor.java	
 *
 *  Copyright (c) 2009-2010 ETRG,IIT Kanpur. 
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
//JDK
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
//Turbine
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
/**
 *
 * This Action class for Registering a multiple course with Instructor(Primary) 
 * in the system from file.
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a> 
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a> 
 */
public class RegisterIMCInstructor extends SecureAction_Institute_Admin
{
	
	private String LangFile=new String();
	/**
 	  * This method actually registers a new course along with the instructor 
	  * in the system
 	  * @param data RunData instance
 	  * @param context Context instance
 	  * @exception Exception, a generic exception
 	  */
	public void doRegister(RunData data, Context context)
	{
	 	/**
          	* Getting file value from temporary variable according to selection
          	* Replacing the value from Property file
         	*/
		CourseUserDetail MsgDetails=new CourseUserDetail();
		LangFile=(String)data.getUser().getTemp("LangFile");
	        try
		{
			String instituteId=(data.getUser().getTemp("Institute_id").toString());
			ErrorDumpUtil.ErrorLog("iid in action at line 86==="+instituteId);
			int InstituteId=Integer.parseInt(instituteId);	
		 	ParameterParser pp=data.getParameters();
                        FileItem file = pp.getFileItem("file");
                        String fileName=file.getName();
			String domainname=(data.getUser().getTemp("DomainName")).toString();
                        if((!(fileName.toLowerCase()).endsWith(".txt"))||(file.getSize()<=0))
                        {
                        	/**
                                 * Getting file value from temporary variable according to selection of Language
                                 * Replacing the static value from Property file
                                 */
                                String upload_msg1=MultilingualUtil.ConvertedString("upload_msg2",LangFile);
                                data.setMessage(upload_msg1+fileName);
                        }
                        else{
				Date date=new Date();
                                File f=new File(TurbineServlet.getRealPath("/tmp")+"/"+date.toString()+".txt");
                                file.write(f);
				int entryNumber=0;
				Vector ErrType=new Vector();
				FileReader fr=new FileReader(f);
                	        BufferedReader br=new BufferedReader(fr);
                        	String line;
                        	/**
                        	* Read the lines in the file one by one and extracts
                        	* the user details with the
                        	* help of StringTokenizer
                        	*/
                        	while((line=br.readLine())!=null){
                                StringTokenizer st1=new StringTokenizer(line,";",true);
	                        entryNumber++;
        	                String first_name="",email="";
				String  courseid="", courseName="",uname="";
                	        int error=0;
                        	String mail_msg="";
                                String errMsg="";
		                if(st1.countTokens()<7 ||st1.countTokens()>7)
                                {error=1;}
				//insufficient argument
				else
                                {
		                	first_name=st1.nextToken().trim();
                			if(first_name.equals(";"))
                                     	{error=2;}
                                        else{
		                        st1.nextToken();
                		        email=st1.nextToken().trim();
                                        if(email.equals(";"))
                                        {error=2;}
		                        else{
                			st1.nextToken();
                                        courseid=st1.nextToken().trim();
		                        if(courseid.equals(";"))
                                        {error=2;}
					else{
                			st1.nextToken();
                                        courseName=st1.nextToken().trim();
                                        if(courseName.equals(";"))
					{error=2;}
                        		else{//else#6
					int i=email.indexOf("@");
					String pass=email.substring(0,i);
					ErrorDumpUtil.ErrorLog("emailat153="+email+"\npass=="+pass);
					uname=email;
					String check=courseid.concat(uname);
					ErrorDumpUtil.ErrorLog("unameatline 156="+uname);
					/** Getting the group name from the database
					* and compare this group name with current group name
					*/
					Criteria crit = new Criteria();
					crit.add(CoursesPeer.GROUP_NAME,check);
		                	List v=CoursesPeer.doSelect(crit);
					String gName="";
				        if(v.size() > 0 )
					gName=((Courses)(v.get(0))).getGroupName();
					if(check.equalsIgnoreCase(gName)){
					error=4;
					}
					else{//else#7
					String passwd=pass;
					ErrorDumpUtil.ErrorLog("pass==="+passwd);
					String serverName=data.getServerName();
					int srvrPort=data.getServerPort();
					String serverPort=Integer.toString(srvrPort);
					String dept="", description="", lname="";
					/**
					* Register a new course with instructor
					* @see CourseManagement Utils
					*/ 
					//String msg=CourseManagement.CreateCourse(courseid,courseName,dept,description,uname,passwd,first_name,lname,email,serverName,serverPort,LangFile,0);
					String msg=CourseManagement.CreateCourse(courseid,courseName,dept,description,uname,passwd,first_name,lname,email,serverName,serverPort,LangFile,InstituteId);
					error=3;
		                        errMsg=msg;
					}//end Else#7
					//}//end if add by Jaivir
					}//endelse#6
                                        }//endelse#5
					}//endelse#4
					}//endelse#3
					}//endelse#2
			/**
                         * Adds the error message to a vector if all the required fields
                         * are not entered in the file. The entry number is also added.
                         */
                        if( error!=0){//if error
                                MsgDetails=new CourseUserDetail();
                                String ErrorEntryNumber=Integer.toString(entryNumber);
                                MsgDetails.setErr_User(ErrorEntryNumber);
                                if(error==1){
                                String error_msg1=MultilingualUtil.ConvertedString("error_msg1",LangFile);
                                MsgDetails.setErr_Type(error_msg1);

                                }
                                if(error==2){


                                                String error_msg2=MultilingualUtil.ConvertedString("error_msg2",LangFile);
                                                MsgDetails.setErr_Type(error_msg2);
                                }
                                if(error==3){
                                                MsgDetails.setErr_Type(errMsg);}
				if(error==4){
					MsgDetails.setErr_Type(MultilingualUtil.ConvertedString("c_msg1",LangFile));}
                                ErrType.add(MsgDetails);
                        }//endif error


			}//end while
			context.put("Msg",ErrType);
			br.close();
			fr.close();
			f.delete();
			}//end else#1
		}//end try
		catch(Exception e)
		{
			 data.setMessage("The error"+e);	
                          
		}
		
	}

	/**
 	  * This method is invoked when no button corresponding to 
 	  * doRegister is found
 	  * @param data RunData
  	  * @param context Context
 	  * @exception Exception, a generic exception
 	  */
	public void doPerform(RunData data,Context context) throws Exception{
		String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_doUploadcourse"))
			doRegister(data,context);
		else{
			
		
		/**
                 * getting property file According to selection of Language in temporary variable
                 * getting the values of first,last names and
                 * configuration parameter.
                 */
 
                	LangFile=(String)data.getUser().getTemp("LangFile"); 
			String str=MultilingualUtil.ConvertedString("c_msg",LangFile);
                        data.setMessage(str);
		}
	}
}

