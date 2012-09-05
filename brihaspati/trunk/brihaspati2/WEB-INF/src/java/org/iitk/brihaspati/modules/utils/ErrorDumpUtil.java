package org.iitk.brihaspati.modules.utils;         
/*
 * @(#) ErrorDumpUtil.java	
 *
 *  Copyright (c) 2005-2006,2012 ETRG,IIT Kanpur. 
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
import java.util.Date;
import java.lang.String;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;
import org.apache.turbine.services.servlet.TurbineServlet;
/**
 * This class create error log file with error message's
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @modified date:- 05-09-2012
 */

public class ErrorDumpUtil
{
	/**
	* In this method, Dump error message in logfile
	* @param msg String
	*/
	private static int count = 1;
	public static void ErrorLog(String msg) 
	{
		try
		{
			Date Errordate=new Date();
			String LogfilePath=TurbineServlet.getRealPath("/logs")+"/ExceptionLog.txt";
			File existingFile=new File(LogfilePath);
			if(existingFile.length() >= 1048576 )
			{
				boolean success=existingFile.delete();
			}
			else{
				FileOutputStream log=new FileOutputStream(LogfilePath,true);
				log.write((Errordate+"---"+msg+"\n").getBytes());
				log.close();
			}
		}//try
		catch(Exception e)
		{
		}
    	}
	/**
	* In this method, Dump error message in logfile
	* @param msg String
	*/
	public static void ErrorLog(String msg, String path) 
	{
		try
		{
			Date Errordate=new Date();
			File existingFile=new File(path);
			// check log file size 
			if(existingFile.length() >= 5242880)
			{
				//create next log file if file size equal to 5 mb.
				if (count <= 5) {
				String newFile=path+"."+count;
				boolean success=existingFile.renameTo(new File(newFile));
				count += 1;
				} 
				else{
				//set counter
				count = 1;
				// Get parent file name
				String parent=existingFile.getParent();
				File parentFile=new File(parent);
				
				//get user define log file name from fiel path
				String fileName=existingFile.getName();
                        	String flName[]=fileName.split("\\.");
                        	String filname=flName[0];
				
				//Delete old log files
				String[] listOfFiles = parentFile.list();
					for(int i=0;i<=listOfFiles.length;i++){
						String file= listOfFiles[i];
						if(file.startsWith(filname)){
							File FileD=new File(parentFile+"/"+file);
							boolean success=FileD.delete();
						}
					}
				}
			}
			else{
				//write logs
				FileOutputStream log=new FileOutputStream(path,true);
				log.write((Errordate+"---"+msg+"\n").getBytes());
				//close log file
				log.close();
			}
		}//try
                catch(Exception e)
                {
                }
	}
}


