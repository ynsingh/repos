package org.iitk.brihaspati.modules.screens.call.Dis_Board;

/*
 * @(#)DisBoard.java	
 *  Copyright (c) 2005-2007, 2010, 2011 ETRG,IIT Kanpur. 
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
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;

import org.iitk.brihaspati.om.DbSend;
import org.iitk.brihaspati.om.DbSendPeer;

import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.AutoSave;
/**
 * This class contains code for input message for sending and reply in Workgroup 
 * Grab all the records in a table using a Peer, and
 * place the Vector of data objects in the context
 * where they can be displayed by a #foreach loop.
 * 
 * @author  <a href="aktri@iitk.ac.in">Awadhesh Kumar Trivedi</a>
 * @author  <a href="sumanrjpt.@yahoo.co.in">Suman Rajput</a>
 * @author  <a href="rekha_20july@yahoo.co.in">Rekha Pal</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @author <a href="mailto:vipulk@iitk.ac.in">vipul kumar pal</a>
 * @ modified date: 13-Oct-2010 (Shaista)
 * @ modified date: 13-Feb-2011 (Shaista)
 * @ modified date: 25-May-2011 (Sunil Kr)
 * 
 */

public class DisBoard extends SecureScreen
{
	/**
	 * @param data RunData
	 * @param context Context
	 */ 

	public void doBuildTemplate(RunData data,Context context)
	{
		try{
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			context.put("course",(String)user.getTemp("course_name"));
			String stats=data.getParameters().getString("stats","");
			context.put("stats",stats);	
			String mode=data.getParameters().getString("mode","");
			context.put("mode",mode);
			String mode2=data.getParameters().getString("mode2","");
			context.put("mode2",mode2);
			//ErrorDumpUtil.ErrorLog("DisBoard java=========>"+mode2);
			String checkNull=data.getParameters().getString("check","noCheck");
			context.put("check",checkNull);

			// Load saved msg
                        try{
                                String savemsg = AutoSave.doLoad((String)user.getTemp("course_id")+(String)user.getTemp("Institute_id")+(String)user.getTemp("role")+user.getName()+"disboard");
                                context.put("msg",savemsg);
                        }
                        catch(Exception e){}

			//this is use for general and institute wise discussion group	
			String dir="", dir1="";
			if(stats.equals("fromIndex")){
				dir=data.getParameters().getString("course_id","general");
			}else if(mode2.equals("instituteWise")){
				dir=data.getParameters().getString("course_id","instituteWise");
			}else
				dir=(String)user.getTemp("course_id","");
				context.put("course",(String)user.getTemp("course_name"));
				dir1=(String)user.getTemp("course_id");
				context.put("cid",dir);
	                        String grpname=data.getParameters().getString("val1");
        	                context.put("val",grpname);
				context.put("tdcolor",data.getParameters().getString("count","4"));	
		 	/**
		  	*  Getting the actual path where the DB file will be stored
		  	*/
			
			if(stats.equals("fromIndex"))
				dir="general";
			else if(mode2.equals("instituteWise"))
				dir="instituteWise";
			String filePath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/DisBoard";
			if(!(new File(filePath).exists())){
                                        new File(filePath).mkdirs();
                                }
			//ErrorDumpUtil.ErrorLog("instituteWise/general/courese=======filePath===>"+filePath);
			
			File dirHandle=new File(filePath);
			String file[]=dirHandle.list();
			Vector v=new Vector();
			for(int i=0;i<file.length;i++)
			{
				/**
			 	* Add file in to Vector and
			 	* vector put into context for further uses
			 	*/
				v.addElement(file[i]);
			}
			context.put("allTopics",v);
			if(mode.equals("reply"))
			{
				int msg_id=data.getParameters().getInt("msgid");
                		String ss=Integer.toString(msg_id);
		 		context.put("msg_id",ss);

                 		//Select the message from Db_Send table according to messageId
                		Criteria crit=new Criteria();
                		crit.add(DbSendPeer.MSG_ID,msg_id);
               	 		List u=DbSendPeer.doSelect(crit);
				//ErrorDumpUtil.ErrorLog("general all list"+u);

                  		// Getting the record from Vector one by one

				String topic1="",username="",retrive_date="",subject="";
	        		for(int count=0;count<u.size();count++)
	        		{
		        		DbSend element=(DbSend)(u.get(count));
		        		subject=(element.getMsgSubject());
		        		int userid=(element.getUserId());

		       			/**
					* Getting the Username from Turbine_User table
					* @see UserUtil
					*/
		        		username=UserUtil.getLoginName(userid);
					retrive_date=(element.getPostTime()).toString();
		  		}
               			/**
                 		* Getting the actual path where the DB file is stored
                 		* @return String
		 		*/
                 		String str = null,topicDesc="";
                 		StringBuffer sb = new StringBuffer() ;
                 		int start=-1;
                 		int stop = -1;
                		BufferedReader br=new BufferedReader(new FileReader (filePath+"/"+subject+"/"+"Msg.txt"));
                 		for (int i =0; (str=br.readLine())!= null ;i++ )
                 		{
                         		if (str.matches("<....>"))
                         		{
                              			start = i;
                              			continue;
                         		}
                         		else if(str.matches("<.....>"))
                         		{
                              			stop = i;
                              			sb.append("\n\n\n");
                              			continue;
                         		}
                        		if(start > -1)
                        		{
                               	 		sb.append(str);
                        		}
                   		}//for
                                br.close();
		       		topicDesc=topicDesc + sb.toString();
				topic1="Re:"+subject;
				String rep_msg=username+"Wrote:"+topicDesc;
                        	// add the information in context
                 		context.put("topic",topic1);
                 		context.put("rep_msg",rep_msg);
		 		context.put("retrive_date",retrive_date);
			}//(if mode reply)
			 /*
                         *method for how much time user spend in this page.
                         */
			 
			 /*String Role = (String)user.getTemp("role");
			 String username=user.getName();
                        int uid=UserUtil.getUID(username);
                        if((Role.equals("student")) || (Role.equals("instructor")))
                        {
                                CourseTimeUtil.getCalculation(uid);
                                ModuleTimeUtil.getModuleCalculation(uid);
                        }*/
		}//try
                catch(Exception e){data.setMessage("Error in the Disboard screen !! "+e);}
	}//method
}//class
