package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;

/*
 * @(#)UploadMarks.java	
 *
 *  Copyright (c) 2005 ETRG,IIT Kanpur. 
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
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Instructor;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_Marks;

import org.iitk.brihaspati.modules.utils.UserUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;

 /** 
  * In this class, We upload marks in particuler course by instructor 
  * if before upload marks then show and download marks list by Instructor
  * @author <a href="mailto:ammu_india@yahoo.com">Amit Joshi</a>
  * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
  * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
  * @author <a href="mailto:vipulk@iitk.ac.in">vipul kumar pal</a>
  * @modified date 15-09-2010
  */
 
  
public class UploadMarks extends SecureScreen_Instructor  
{
	public void doBuildTemplate( RunData data, Context context )
	{
		try{
			/**
			 *@param user Getting User object
			 */
			User user=data.getUser();
			String Role = (String)user.getTemp("role");
			 /**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor"))||(Role.equals("teacher_assistant")))
                         {
                                //CourseTimeUtil.getCalculation(uid);
                                //ModuleTimeUtil.getModuleCalculation(uid);
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                         }

			/**
                         *@param pp instance of ParameterParser
                         */
			ParameterParser pp=data.getParameters();
			/**
                         * @param dir getting course id which is set by setTemp() method
                         */	
			String dir=(String)user.getTemp("course_id");
			/**
                         * @param Type getting type
			 * @param status getting status
                         */	
			String Type=pp.getString("type","");
			String status=pp.getString("status","");
			String fileName=pp.getString("filename","");
			context.put("status",status);
			context.put("type",Type);
			context.put("course",(String)user.getTemp("course_name"));
			/**
                         * getting actual path of Courses
                         * @RETURN String
                         */
	        	String coursesRealPath=TurbineServlet.getRealPath("/Courses");
			/**
                         * getting actual path where marks saved
                         * @RETURN String                               
			 */
			String xmlPath=coursesRealPath+"/"+dir+"/Marks.xml";
			// Read xml file and saves data in show vector.
			// Send them into vm file
			Vector show = new Vector();
			show = XMLWriter_Marks.ReadMarksDeatils(xmlPath);
			context.put("show",show);

			/**
                         * getting actual path where formula saved
                         * @RETURN String
                         */
			String marks=coursesRealPath+"/"+dir+"/Marks/"+fileName;
			String tmpmarks=coursesRealPath+"/"+dir+"/TMPMARK.txt";
			File marksFile=new File(marks);
			File tmpFile=new File(tmpmarks);
			/**
			 * if marks file exist then put true in context
			 * put FileName in context
			 * else put false in context
			 */
				if(marksFile.exists())
					{// 1 if
						context.put("fileExists","true");
						context.put("fileName",fileName);
						if(data.getMessage()==null)
						{// 2 if
							String LangFile=user.getTemp("LangFile").toString();
						}// end of 2 if
					}// end of 1 if
				else
					{// 1 else
						context.put("fileExists","false");
					}// end of 1 else

			/**
			 * if tmpfile exist it shows marks are uploaded through spreadsheet
			 * put filename in context
			 * else put false in context
			 */
			 	if(tmpFile.exists())
                                        {// 3 if
                                                context.put("FileExist","true");
                                                context.put("fileName",fileName);
                                                if(data.getMessage()==null)
                                                {// 4 if
                                                        String LangFile=user.getTemp("LangFile").toString();
                                                }// end of 4 if
                                        }// end of 3 if
                                else
                                        {// 3 else
                                                context.put("FileExist","false");
                                        }// end of 3 else


			/**
			 * below method for reading MARK.txt
			 * if{status edit
			 * read Marks file through filereader & put into context
			 * then read tmpmarks file if formula exist in a cell & put into context
			 */
			if(status.equals("edit"))
			{// 5 if	
			FileReader fr=new FileReader(marks);
                       	BufferedReader br=new BufferedReader(fr);
			String line;
			String str = new String();
			Vector markDetail=new Vector();
			while((line=br.readLine())!=null)
                  		{// 1 while
					/**
					 *add '\n' after every line that tells change of line in spreadsheet 
					 */
					str = str+line+"\n";
					//ErrorDumpUtil.ErrorLog("str in screen file inside edit check---"+str);
				}// end of 1 while
			int strln = str.length();
			String substr = str.substring(0,strln-1);
			//ErrorDumpUtil.ErrorLog("the substring is-------->"+substr);
			context.put("marksDetail",substr);
			/**
			 *below method for reading TMPMARK.txt
			 */
			FileReader freader = new FileReader(tmpmarks);
                        BufferedReader breader=new BufferedReader(freader);
			/**
			 * Check size of file if not equal to zero then read file
			 */
      			long file_size = tmpFile.length();
			//ErrorDumpUtil.ErrorLog("Size of file"+file_size);
			if(file_size != 0)
				{// 6 if
                        	String line2;
	                        String strg = new String();
	                        Vector formulaDetail=new Vector();
	                        while((line2=breader.readLine())!=null)
	                                {// 2 while
	                                        strg = strg+line2+"/";
	                                }// end of 2 while
				int strglen = strg.length();
				String substrg = strg.substring(0,strglen-1);
	                        //ErrorDumpUtil.ErrorLog("the formula sub string is-------->"+substrg);
	                        context.put("formulaDetail",substrg);
				}// end of 6 if
			}// end of 5 if

		}// end of try
	catch(Exception ex)
		{
			//data.setMessage("The error in Upload Screen !!"+ex);
		}
	}
}


