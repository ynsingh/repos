package org.iitk.brihaspati.modules.actions;

/*
 * Copyright (c) 2005-2007, 2010, 2011 ETRG,IIT Kanpur.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 * 
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 * 
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 * 
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Contributors: Members of ETRG, I.I.T. Kanpur
 */ 
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.StringTokenizer;

import java.sql.Date;

import com.workingdogs.village.Record;

import org.apache.velocity.context.Context;

import org.apache.torque.util.Criteria;

import org.apache.commons.fileupload.FileItem; 

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;

import org.iitk.brihaspati.om.DbSend;
import org.iitk.brihaspati.om.DbSendPeer;
import org.iitk.brihaspati.om.DbReceivePeer;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;   
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;//sunil

import java.nio.MappedByteBuffer;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileChannel;
import java.io.FileOutputStream;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.FaqmovePeer;
import org.iitk.brihaspati.om.Faqmove;


/** This class contains code of Sending Message to the Discussion Board 
 *  with or without attachments
 *  @author <a href="aktri@iitk.ac.in">Awadhesh Kumar Trivedi</a>
 *  @author <a href="sumanrjpt@yahoo.co.in">Suman Rajput</a>
 *  @author <a href="rekha_20july@yahoo.co.in">Rekha Pal</a>
 *  @author <a href="nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @author <a href="mailto:tpthshobhi30@gmail.com">Shobhika</a>
 * @ modified date: 13-Oct-2010 (Shaista)
 * @ modified date: 08-Aug-2011 (Sunil Kr)
 */
public class SendDB extends SecureAction
{
	/**
     	* @param data RunData
     	* @param context Context
     	* @exception Exception a generic exception
     	*/ 
    	public void doSend(RunData data, Context context)
    	{  
		try
		{
			//Here we retrive from the templates by the help parameter parser 

			int Status=0; 
			//boolean Status=false;
			context.put("count",data.getParameters().getString("count",""));
		        String LangFile=data.getUser().getTemp("LangFile").toString();
			ParameterParser pp=data.getParameters();
                        String UserName=data.getUser().getName();
			String DB_message=pp.getString("message");
			String DB_subject=pp.getString("contentTopic");
			String mode=pp.getString("mode1","");
                        String mode3=pp.getString("mode3","");
                        String grpname=pp.getString("val","");

			/**
			* Check for special character in Topic Name excluding Re: /Re:Re:Re:
			*/
			if(StringUtil.checkString(DB_subject) != -1)
	                {
                        	data.addMessage(MultilingualUtil.ConvertedString("usr_prof1",LangFile));
                                return;
                        }
			String TopicArray[]=DB_subject.split("Re:");
			if(TopicArray.length >1 )
			DB_subject=TopicArray[TopicArray.length -1];
			/**
			* Add Re:
			*/	
			String Add_RE_DB_subject = "";
			for(int i=0;i<TopicArray.length -1;i++)
			{
				Add_RE_DB_subject = Add_RE_DB_subject+"Re:";
			}
			DB_subject=Add_RE_DB_subject + DB_subject;
			
	
			String Rep_id=pp.getString("Repid");
			context.put("Repid",Rep_id);
			String expiry=pp.getString("expiry","");
			String status=pp.getString("status");
                        context.put("status",status);
			String course_id=pp.getString("courseid");
        		context.put("courseid",course_id);
			
			/** 
			* this is use for General Discussion Group
			*/
			String stats=data.getParameters().getString("stats","");
                        context.put("stats",stats);
			
			/**
			* this is use for institute wise Discusson Group
			*/
			String mode2=data.getParameters().getString("mode2","");
                        context.put("mode2",mode2);
			
			/**
			* Here we get userid by the help of UserUtil
			*/
			int userid=UserUtil.getUID(UserName);
			if(expiry.equals(""))
			{
				expiry=pp.getString("expstatus");
				
			}
			int exp=Integer.parseInt(expiry);
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			Date Post_date=Date.valueOf(Cur_date);
			Date exDate=Date.valueOf(ExpiryUtil.getExpired(Cur_date,exp));
			
			/**
			* ************* suneel ***********
			* group_id define for gereral and institute wise *
			*/
			int group_id=0;
			if(stats.equals("fromIndex")){
				group_id=4;
			}else if(mode2.equals("instituteWise")){
				group_id=5;
                        }else{
				group_id=GroupUtil.getGID(course_id);
			}
               		
			/********** Insert message in database**********/
			Criteria crit=new Criteria();
			crit.add(DbSendPeer.REPLY_ID,Rep_id); 
			crit.add(DbSendPeer.MSG_SUBJECT,DB_subject); 
			crit.add(DbSendPeer.USER_ID,Integer.toString(userid)); 
			crit.add(DbSendPeer.GROUP_ID,Integer.toString(group_id)); 
			crit.add(DbSendPeer.POST_TIME,Post_date); 
			crit.add(DbSendPeer.EXPIRY,expiry);
			crit.add(DbSendPeer.EXPIRY_DATE,exDate);
			crit.add(DbSendPeer.PERMISSION,1);
			crit.add(DbSendPeer.STATUS,0);
			if(mode.equals("grpmgmt"))
                                crit.add(DbSendPeer.GRPMGMT_TYPE,1);
                        else
                                crit.add(DbSendPeer.GRPMGMT_TYPE,0);
			DbSendPeer.doInsert(crit);
			int msg_id=0;
	  		String Query_msgid="SELECT MAX(MSG_ID) FROM DB_SEND";
	   		List u=DbSendPeer.executeQuery(Query_msgid);
             		for(ListIterator j=u.listIterator();j.hasNext();)
			{
				Record item=(Record)j.next();
				msg_id=item.getValue("MAX(MSG_ID)").asInt();
			}

                        /**
			*  From here starts the code do store message               
		        * in a single txt file.
			*/
			String path="";
			//This is use for general discussion group
          		if(stats.equals("fromIndex"))
        			path = data.getServletContext().getRealPath("/Courses/"+"general"+"/DisBoard/"+DB_subject);
			//This is use for institutewise discussion group
			else if(mode2.equals("instituteWise"))
        			path = data.getServletContext().getRealPath("/Courses/"+"instituteWise"+"/DisBoard/"+DB_subject);
			else 
        			path = data.getServletContext().getRealPath("/Courses/"+course_id+"/DisBoard")+"/"+DB_subject;
			File topicDir = new File(path);
			topicDir.mkdirs();
			path = path+"/"+"Msg.txt";
			java.util.Date date=new java.util.Date();
			FileWriter fw = new FileWriter(path,true);
			fw.write("\n");
			fw.write("<" + msg_id + ">");
			fw.write("\n");
			fw.write("< Send date "+ date +">"+"<br>"+" &nbsp; &nbsp; &nbsp; "+DB_message+"\n");
			fw.write("\n"+"</" + msg_id + ">");
			fw.close();

                     	// From here starts the code for uploading the file 
			try
			{
				FileItem fileItem = pp.getFileItem("file");
				String tempFile=(fileItem.getName()).substring(((fileItem.getName()).lastIndexOf("\\"))+1);
				File f=null;
				//This is use for general discussion group
				if(stats.equals("fromIndex")){
					f= new File(TurbineServlet.getRealPath("/Courses/"+"general"+"/DisBoard/"+DB_subject+"/Attachment/"+msg_id));
				//This is use for institutewise discussion group
				}else if(mode2.equals("instituteWise")){
					f= new File(TurbineServlet.getRealPath("/Courses/"+"instituteWise"+"/DisBoard/"+DB_subject+"/Attachment/"+msg_id));
				}else
					f= new File(TurbineServlet.getRealPath("/Courses/"+course_id+"/DisBoard/"+DB_subject+"/Attachment/"+msg_id));
				f.mkdirs();
				if(fileItem.getSize() > 0 )
				{
					fileItem.write(new File(f+"/"+tempFile));
					Status=1;
				}
				
            		}//try
                 	catch(Exception e){}
		
			//Insert value in DB_RECEIVE Table
			//general and  institutewise discussion group
			if((stats.equals("fromIndex")) || (mode2.equals("instituteWise"))){
				int u_id=UserUtil.getUID(UserName);
				crit=new Criteria();
                                crit.add(DbReceivePeer.RECEIVER_ID,u_id);
                                crit.add(DbReceivePeer.MSG_ID,msg_id);
                                crit.add(DbReceivePeer.GROUP_ID,group_id);
                                crit.add(DbReceivePeer.READ_FLAG,0);
                                context.put("test",Integer.toString(msg_id));
                                DbReceivePeer.doInsert(crit);
				//ErrorDumpUtil.ErrorLog("\nGeneral insert value in to the recive table==>"+crit);
			}else
      				//Insert Detail in Receiver Table 
				crit=new Criteria();
				int i[]={0,1};
				crit.add(TurbineUserGroupRolePeer.GROUP_ID,group_id);
				crit.addNotIn(TurbineUserGroupRolePeer.USER_ID,i);
				List q=TurbineUserGroupRolePeer.doSelect(crit);
				String uid=new String();
				if(mode.equals("grpmgmt"))
                        	{
                                	String gpath=TurbineServlet.getRealPath("/Courses"+"/"+course_id)+"/GroupManagement";
	                                TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(gpath+"/"+grpname+"__des.xml");
		                        Vector grouplist=topicmetadata.getGroupDetails();
                	                int usrid=0;
                        	        if(grouplist!=null)
                                	{
                                        	String username[]=new String[1000];
	                                        int l=0;
        	                                for(int m=0;m<grouplist.size();m++)
                	                        {//for
                        	                        String uname =((FileEntry) grouplist.elementAt(m)).getUserName();
                                	                username[m]=uname;
                                        	        l++;
                                        	}
	                                        username[l]=UserName;
        	                                for(int k=0;k<=l;k++)
                	                        {
                        	                        String str=username[k];
                                	                usrid=UserUtil.getUID(str);
                                        	        crit.add(DbReceivePeer.RECEIVER_ID,usrid);
                                                	crit.add(DbReceivePeer.MSG_ID,msg_id);
	                                                crit.add(DbReceivePeer.GROUP_ID,group_id);
        	                                        crit.add(DbReceivePeer.READ_FLAG,0);
                	                                context.put("test",Integer.toString(msg_id));
                        	                        DbReceivePeer.doInsert(crit);
							//ErrorDumpUtil.ErrorLog("\ninsert value in to the recive table from Courses if=====>"+crit);
                                        	}
                                	}
                        	}
	                        else
				{
					for(int k=0;k<q.size();k++)
					{
						//int k=0;
						TurbineUserGroupRole element=(TurbineUserGroupRole)q.get(k);
						int u_id=element.getUserId();
						crit=new Criteria();
						crit.add(DbReceivePeer.RECEIVER_ID,u_id);
						crit.add(DbReceivePeer.MSG_ID,msg_id);
						crit.add(DbReceivePeer.GROUP_ID,group_id);
						crit.add(DbReceivePeer.READ_FLAG,0);
		 				context.put("test",Integer.toString(msg_id));
						DbReceivePeer.doInsert(crit);
						//ErrorDumpUtil.ErrorLog("\ninsert value in to the recive table from Courses else======>"+crit);
					}//for
				}
				String msg="",msg1="";
		   		if(Status==1)
				{	
                       			msg=MultilingualUtil.ConvertedString("attach",LangFile);			
                       			msg1=MultilingualUtil.ConvertedString("db_msg1",LangFile);
					if(LangFile.endsWith("en.properties"))
                                        	data.setMessage(msg1+" "+msg);
                                 	else
                                        	data.setMessage(msg+" "+msg1);
				}
				else
				{
					msg=MultilingualUtil.ConvertedString("db_msg1",LangFile);
					data.setMessage(msg);
				}
		    	}//try
        	    	catch(Exception e){data.setMessage("Some Error Occured in Sending Discussion !!!!" +e);}
			}//method(doSend)												

 	/**
  	* Place all the data object in the context for use in the template.
 	* @param data RunData instance
  	* @param context Context instance
 	* @exception Exception, a generic exception
  	**/

        public void doPermission(RunData data, Context context)
        {
		try
		{
			/**
			* Get the user name  and find the user id
                	* @see UserUtil in utils
                	*/
			
			ParameterParser pp=data.getParameters();
			String mgid=pp.getString("msgid");
                        context.put("mgid",mgid);
                        String permit=pp.getString("perm");
			context.put("permit",permit);
			//this is use for general discussion group
			String stats=data.getParameters().getString("stats","");
                        context.put("stats",stats);
			//this is use for institutewise disscussion group
			String mode2=data.getParameters().getString("mode2","");
                        context.put("mode2",mode2);
			String course_id=null;
                        if(stats.equals("fromIndex")){
				course_id="general";
			}else if(mode2.equals("instituteWise")){
				course_id="instituteWise";
                        }else
                        	course_id=pp.getString("courseid");
                        //String course_id=pp.getString("courseid");
                        context.put("courseid",course_id);
                        int group_id=GroupUtil.getGID(course_id);
                	/* Update Permision field according to instructor permission*/
			Criteria crit=new Criteria();
			crit.add(DbSendPeer.MSG_ID,mgid);
			crit.add(DbSendPeer.PERMISSION,permit);
			DbSendPeer.doUpdate(crit);
			String LangFile=data.getUser().getTemp("LangFile").toString();
			String msg5=MultilingualUtil.ConvertedString("db_msg3",LangFile);
			data.setMessage(msg5);
			data.setScreenTemplate("call,Dis_Board,Edit.vm");
		}//try
		catch(Exception e){data.setMessage("Some Error Occured in Sending Permission !!!!" +e);}
	}//doPermission
                    
	/**
 	* Get the user name  and find the user id
 	* @see UserUtil in utils
 	*/

	public void doUpdate(RunData data, Context context)
	{
		try
		{
			context.put("count",data.getParameters().getString("count",""));
			context.put("countTemp",data.getParameters().getString("countTemp",""));
                        String LangFile=data.getUser().getTemp("LangFile").toString();
			int Status=0;
		 	String UserName=data.getUser().getName();
	  	 	ParameterParser pp=data.getParameters();
		  	String DB_message=pp.getString("message");
			String DB_mgid=pp.getString("mgid");
			String Rep_id=pp.getString("Repid");
	     		int expiry=pp.getInt("expiry");
	     		String expirytime=pp.getString("expiry","");
                        context.put("expirytime",expiry);      
	                String course_id=pp.getString("course_id","");
			context.put("course_id",course_id);
	      		int group_id=GroupUtil.getGID(course_id);
	        	context.put("cid",course_id);

			//Here  we get the userid by the help of UserUtil

			int userid=UserUtil.getUID(UserName);
			String DB_subject=pp.getString("contentTopic","");
			if(StringUtil.checkString(DB_subject) != -1)
	                {
                        	data.addMessage(MultilingualUtil.ConvertedString("usr_prof1",LangFile));
                                return;
                        }
			context.put("contentTopic",DB_subject);
			String status=pp.getString("status","");
                        context.put("status",status);   
			/**
			 * Get Post date and Expiry Date in yyyy-mm-dd format
			 */
                        if(expirytime.equals(""))
                        {
                                expirytime=pp.getString("expstatus");

                        }
                        int exp=Integer.parseInt(expirytime);
			String Cur_date=ExpiryUtil.getCurrentDate("-"); 
			Date P_Date=Date.valueOf(Cur_date);
			Date Ex_Date=Date.valueOf(ExpiryUtil.getExpired(Cur_date,exp));
			
			Criteria crit=new Criteria();
                        crit.add(DbSendPeer.MSG_ID,DB_mgid);
                        List sendDetail=DbSendPeer.doSelect(crit);
                        for(int i=0;i<sendDetail.size();i++)
                        {
                                DbSend element=(DbSend)sendDetail.get(i);
                                Rep_id=Integer.toString(element.getReplyId());
                        }
			

	          	/* Update  the message  */
	      		crit=new Criteria();
			crit.add(DbSendPeer.MSG_ID,DB_mgid);
	      		crit.add(DbSendPeer.MSG_SUBJECT,DB_subject);
	      		crit.add(DbSendPeer.REPLY_ID,Rep_id);
	      		crit.add(DbSendPeer.POST_TIME,P_Date);
	      		crit.add(DbSendPeer.EXPIRY,expiry);
	      		crit.add(DbSendPeer.EXPIRY,expirytime);
	      		crit.add(DbSendPeer.EXPIRY_DATE,Ex_Date);
	      		DbSendPeer.doUpdate(crit);
                        int msg_id=0;
		        List u=null;
			String Query_msgid="SELECT MAX(MSG_ID) FROM DB_SEND";
			u=DbSendPeer.executeQuery(Query_msgid);
		        for(ListIterator j=u.listIterator();j.hasNext();)
			{
                        	Record item=(Record)j.next();
                             	msg_id=item.getValue("MAX(MSG_ID)").asInt();
			}
			
		        /**
                        * read the file using fileReader
                        * @try and catch Identifies statements that user want to monitor
                        * and catch for exceptoin.
                        */
	                 		
  			String filePath=data.getServletContext().getRealPath("/Courses")+"/"+course_id+"/DisBoard/"+ DB_subject+"/Msg.txt";
			CommonUtility.UpdateTxtFile(filePath,DB_mgid,DB_message,true);
			String msg6=MultilingualUtil.ConvertedString("c_msg5",LangFile);
			data.setMessage(msg6);
                        	
			try
			{	
				FileItem fileItem = pp.getFileItem("file");
                                String realPath = TurbineServlet.getRealPath("/Courses/"+course_id+"/DisBoard");
		        	String  temp = fileItem.getName();
		        	int index = temp.lastIndexOf("\\");
		        	String tempFile=temp.substring(index+1);
				File filePath1 = new File (realPath+"/"+DB_subject+"/Attachment/"+msg_id+"/"+tempFile);
				File f=new File(realPath+"/"+DB_subject+"/Attachment/"+msg_id);
			       	f.mkdirs();
                                if(fileItem.getSize() > 0 )
                                {
                                        fileItem.write(filePath1);
                                        Status=1;
                                }

                               
			}//try
			catch(Exception e1){}

			if(Status==1)
			{	
				String msg7=MultilingualUtil.ConvertedString("db_msg5",LangFile);
			    	data.setMessage(msg7);
				Status=0;
			}					   
		}//try
		catch(Exception e){data.setMessage("Some Error Occured in doUpdate !!!!" +e);}
	}//method doupdate 

	/* for the deletion message*/
 	public void doDelete(RunData data, Context context) throws Exception
	{
		try{
			context.put("count",data.getParameters().getString("count",""));
			context.put("countTemp",data.getParameters().getString("countTemp",""));
 			ParameterParser pp=data.getParameters();
			String UserName=data.getUser().getName();
			int userid=UserUtil.getUID(UserName);
			String DB_subject=pp.getString("DB_subject","");
			context.put("contentTopic",DB_subject);
			String msg_id=pp.getString("msg_id","");
                        context.put("msgid",msg_id);
 			String course_id=pp.getString("course_id","");
			//use for general
			String stats=data.getParameters().getString("stats","");
 			context.put("stats",stats);
			//use for instituteWise
			String mode2=data.getParameters().getString("mode2","");
                        context.put("mode2",mode2);
 			context.put("courseid",course_id);
 		        context.put("userid",userid);
                        Criteria crit=new Criteria();
 			String mid_delete = pp.getString("deleteFileNames","");
			String Deleteper = pp.getString("Deleteper","");
			
			// Code to get message from DB_subject
			String [] subjectarray = DB_subject.split("@@@@");
                       	if(!mid_delete.equals(""))
		        { //outer 'if'
                        	StringTokenizer st=new StringTokenizer(mid_delete,"^");
		          	for(int j=0;st.hasMoreTokens();j++)
		              	{ //first 'for' loop
		                	String msg_idd=st.nextToken();
			 		// get New subject	
			 
					DB_subject = subjectarray[j];
					if(!Deleteper.equals("Archive_Deleted")){
                                                /***  select the replyid in database  */
						crit=new Criteria();
	                                        crit.add(DbSendPeer.MSG_ID,msg_idd); //7
        	                                List sendDetail=DbSendPeer.doSelect(crit);
                	                        int Rep_id=0;
                        	                for(int i=0;i<sendDetail.size();i++)
                                	        {
                                        	        DbSend element=(DbSend)sendDetail.get(i);
                                                	Rep_id=element.getReplyId(); //4
                                        	}

	                                        sendDetail=null;
        	                                crit=new Criteria();
                	                        crit.add(DbSendPeer.REPLY_ID,Integer.parseInt(msg_idd));
                        	                sendDetail=DbSendPeer.doSelect(crit);
						
                                	        for(int i=0;i<sendDetail.size();i++)
                                        	{
	                                                DbSend element=(DbSend)sendDetail.get(i);
        	                                        int newid=element.getMsgId(); //4
                	                                crit=new Criteria();
                        	                        crit.add(DbSendPeer.MSG_ID,newid);
                                	                crit.add(DbSendPeer.REPLY_ID,Rep_id);
                                        	        DbSendPeer.doUpdate(crit);

                                        	}	

	                   			/*Delete message in database */
	
			        		//crit.add(DbSendPeer.MSG_ID,msg_idd);
			        		//DbSendPeer.doDelete(crit);
                        			crit=new Criteria();
			        		crit.add(DbSendPeer.MSG_ID,msg_idd);
			        		DbSendPeer.doDelete(crit);
		        			crit.add(DbReceivePeer.MSG_ID,msg_idd);
						String LangFile=data.getUser().getTemp("LangFile").toString();
			        		DbReceivePeer.doDelete(crit);
						String msg8=MultilingualUtil.ConvertedString("db_msg7",LangFile);
						data.setMessage(msg8);
						
						///delete from Course
						//String courseRealPath=TurbineServlet.getRealPath("/Courses");
						String courseRealPath=TurbineServlet.getRealPath("/Courses");
			                        String filepath=(courseRealPath+"/"+course_id+"/"+"/DisBoard"+"/"+ DB_subject);
                        			File AssDir=new File(filepath+"/"+msg_idd);
			                        //ErrorDumpUtil.ErrorLog("\nassignid"+assignid+"\nfilepath"+filepath+"\nAssDir"+AssDir);
                        			SystemIndependentUtil.deleteFile(AssDir);
			                        //AssDir.delete();
			                        data.setMessage("DiscussionBoard deleted successfully !!");
						
						if(stats.equals("fromIndex"))
                        			{
			                                course_id="general";
			                        	context.put("stats",stats);
                        			}
			                        else{
                        			        if(mode2.equals("instituteWise")){
			                                	course_id="instituteWise";
				                        	context.put("mode2",mode2);
                        		        	}
                        			}
						String filePath=data.getServletContext().getRealPath("/Courses")+"/"+course_id+"/DisBoard"+"/"+ DB_subject;
						//ErrorDumpUtil.ErrorLog("file Paht================Courses======>>>"+filePath);
						CommonUtility.UpdateTxtFile(filePath,msg_idd,"",false);
	
 						/* deleting attachment if any with the message */	
	
						String fPath1=data.getServletContext().getRealPath("/Courses")+"/"+course_id+"/DisBoard"+"/"+ DB_subject;
	
						String fPath= fPath1 +"/Attachment"+"/"+msg_idd;
	     					File f11 = new File(fPath1);
	    					File f1 = new File(fPath);
					        if(f1.exists() && f1.isDirectory())
						{
							String flist[] = f1.list();
							for(int z=0;z<flist.length;z++)
							{
								File f2 = new File(fPath+"/"+flist[z]);
      	
       							 	/* removing directory of attachment with message */
	     							f2.delete();
             						}
	     						f1.delete();
	  					}
					} else {
						if(stats.equals("fromIndex")) 
						{
                                                        course_id="general";
                                                        context.put("stats",stats);
                                                }
						else
						{
							if(mode2.equals("instituteWise"))
							{
                                                        	course_id="instituteWise";
	                                                        context.put("mode2",mode2);
        	                                        }
						}
                                                String LangFile=data.getUser().getTemp("LangFile").toString();
                                                String msg8=MultilingualUtil.ConvertedString("db_msg7",LangFile);
                                                String APath1=data.getServletContext().getRealPath("/Courses"+"/"+course_id+"/Archive");
						//ErrorDumpUtil.ErrorLog("===================690=======>>>>"+APath1);
                                                String msgId=APath1 +"/"+msg_idd;
                                                String message= msgId+"/"+DB_subject;
                                                File fid=new File(msgId);
                                                if(fid.exists())
                                                {
                                                        File f3 = new File(message);
                                                        f3.delete();
                                                        String amsgId=msgId+"/Attachment/";
                                                        File afile=new File(amsgId);
                                                        String flist[] = afile.list();
                                                        for(int z=0;z<flist.length;z++)
                                                        {
                                                                File f2 = new File(afile+"/"+flist[z]);
                                                                f2.delete();
                                                        }
                                                        afile.delete();
                                                }
                                                fid.delete();
                                                data.setMessage(msg8);
                                        }
      				}//for
			}//if		     
		
		}//TRY	
		catch(Exception e){data.setMessage("Some Error Occured in DeletingMessage !!!!" +e);}
	}//do delete
	
        /**
         * This method is invoked when no button corresponding to
         * @param data RunData
         * @param context Context
         * @exception Exception, a generic exception
         */

        public void doArchive(RunData data , Context context)
        {
                try
                {
			context.put("count",data.getParameters().getString("count",""));
                        String LangFile=data.getUser().getTemp("LangFile").toString();
                        ParameterParser pp=data.getParameters();
                        String UserName=data.getUser().getName();
                        String DB_subject=pp.getString("DB_subject","");
                        context.put("contentTopic",DB_subject);
                        String Cur_date=ExpiryUtil.getCurrentDate("-");
                        Date Post_date=Date.valueOf(Cur_date);
                        context.put("pst",Post_date);
                        String DB_message=pp.getString("message");
                        context.put("DB_message",DB_message);
                        String msg_id=pp.getString("msg_id","");
                        context.put("msgid",msg_id);
			
                        String course_id=pp.getString("course_id","");
                        context.put("courseid",course_id);
                        context.put("Post_date",Post_date);
                        String topiclist = pp.getString("deleteFileNames","");
                        String [] topicarray = DB_subject.split("@@@@");
			String stats=pp.getString("stats","");
                        context.put("stats",stats);
			String mode2=pp.getString("mode2","");
                        context.put("mode2",mode2);
			//ErrorDumpUtil.ErrorLog("Occured in ArchiveMessage==================>>"+stats);
                        if(!topiclist.equals(""))
                        { //outer 'if'
                                StringTokenizer st=new StringTokenizer(topiclist,"^");
                                for(int j=0;st.hasMoreTokens();j++)
                                { //first 'for' loop
                                        String msg_idd=st.nextToken();
                                        // get New subject
					if(stats.equals("fromIndex"))
					{
		                                course_id="general";
					}else{
						if(mode2.equals("instituteWise"))
		                                {	
							course_id="instituteWise";
						}
					}      	DB_subject = topicarray[j];
                                        	String readMsg=data.getServletContext().getRealPath("/Courses"+"/"+course_id+"/DisBoard"+"/"+DB_subject+"/Msg.txt");
						String readAttach = data.getServletContext().getRealPath("/Courses"+"/"+course_id+"/DisBoard"+"/"+DB_subject+"/Attachment/"+msg_idd);
                                        	String writepath=data.getServletContext().getRealPath("/Courses"+"/"+course_id+"/Archive"+"/"+msg_idd);
						//ErrorDumpUtil.ErrorLog("\nread msg====>>"+readMsg+"\nread Attachment====>>>"+readAttach+"\nwrithepath====>>"+writepath);
                                        File f=new File(writepath);
                                        if(f.exists()){
                                                String strmess=MultilingualUtil.ConvertedString("archive_msg1",LangFile);
                                                data.setMessage(strmess);
                                                return;
                                        }
                                        String temp1=writepath+"/"+"Attachment/";
                                        File dest=new File(temp1);
                                        if(!dest.exists())
                                                dest.mkdirs();
                                        File source = new File(readAttach);
                                        String flist[] = source.list();
                                        for(int z=0;z<flist.length;z++)
                                        {
                                                source = new File(readAttach+"/"+flist[z]);
                                                temp1=temp1+flist[z];
                                                dest=new File(temp1);
                                                FileChannel in = null, out = null;
                                                try {
                                                        in = new FileInputStream(source).getChannel();
                                                        out = new FileOutputStream(dest).getChannel();
                                                        long size = in.size();
                                                        MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
                                                        out.write(buf);
                                                } finally {
                                                        if (in != null)          in.close();
                                                        if (out != null)     out.close();
                                                }
                                        }

                                        File topicDir = new File(writepath);
					if(!topicDir.exists())
                                                topicDir.mkdirs();
                                        writepath=writepath+"/"+DB_subject+".html";
                                        String str[]=new String[10000];
                                        int i=0; int start = 0; int stop= 0;String string="";
					//String readMsg=null;//add by sunil
                                        BufferedReader br=new BufferedReader(new FileReader (readMsg));
                                        while ((str[i]=br.readLine()) != null)
                                        {
                                                if (str[i].equals("<"+msg_idd+">"))
                                                {
                                                        start = i;
                                                }
                                                else if(str[i].equals("</"+msg_idd+">"))
                                                {
                                                        stop = i;
                                                }
                                                     i= i +1;
                                        }                        //while
                                        br.close();
                                        FileWriter fw=new FileWriter(writepath);
                                        fw.write("<html>"+"<title>"+"</title>"+"<body>");
                                        fw.write("<b>"+"Subject :  "+"</b>"+DB_subject+"<br>\n");
                                        fw.write("<b>"+"Sender  :  "+"</b>"+UserName+"<br>");
                                        fw.write("<b>"+"Posted on  :  "+"</b>"+Post_date+"<br>");
                                        fw.write("<b>"+"Message  :  "+"</b><br>");
                                        for(int x=start;x<stop;x++)
                                        {
                                                string=string+str[x];
                                        }
                                        stop=0;
                                        stop=string.lastIndexOf("Send date");
                                        //stop=stop+21;
                                        String tempString="";
                                        for(int x=stop;x<string.length();x++)
                                        {
                                                tempString=tempString+string.charAt(x);
                                        }
					fw.write(tempString);
                                        fw.close();
                                }// for
                        }// outer if
                        String strmess=MultilingualUtil.ConvertedString("archive_msg2",LangFile);
                        data.setMessage(strmess);
                }//TRY
                catch(Exception e){  data.addMessage("Some Error Occured in ArchiveMessage !!!!" +e);   }
        } //do Archive
		
	
	public void doShowArchive(RunData data , Context context)
        {
                        ParameterParser pp=data.getParameters();
		String stats=pp.getString("stats","");
                context.put("stats",stats);
		String mode2=pp.getString("mode2","");
                context.put("mode2",mode2);
		context.put("count",data.getParameters().getString("count",""));
		context.put("countTemp",data.getParameters().getString("countTemp",""));
                data.setScreenTemplate("call,Dis_Board,Archive.vm");
        }

        public void doShowDBContent(RunData data , Context context)
        {
		context.put("count",data.getParameters().getString("count",""));
		context.put("countTemp",data.getParameters().getString("countTemp",""));
                data.setScreenTemplate("call,Dis_Board,DBContent.vm");
        }
	
	/**  
 	* This method is invoked when move button is clicked,
        * with the help of this move button we can move important disscusion messages to faq.
 	*/
	public void doMove(RunData data, Context context)
    	{
			try
                {
			context.put("count",data.getParameters().getString("count",""));
                        String LangFile=data.getUser().getTemp("LangFile").toString();
                        ParameterParser pp=data.getParameters();
			//get username for make user directory
                        String UserName=data.getUser().getName();
                        String DB_subject=pp.getString("DB_subject","");
                        context.put("contentTopic",DB_subject);
			//msg id
                        String msg_id=pp.getString("msg_id","");
                        context.put("msgid",msg_id);
                        String course_id=pp.getString("course_id","");
                        context.put("courseid",course_id);
                        String topiclist = pp.getString("deleteFileNames","");
                        String [] topicarray = DB_subject.split("@@@@");
                        String stats=pp.getString("stats","");
                        context.put("stats",stats);
                        String mode2=pp.getString("mode2","");
                        context.put("mode2",mode2);
			User user=data.getUser();
			String instid=(String)user.getTemp("Institute_id");

			if(!topiclist.equals(""))
                        { //outer 'if'
				/**  
				* the division of text or string into a set of discrete parts or tokens,
				* and token is a small piece of string.
				*/
                                StringTokenizer st=new StringTokenizer(topiclist,"^");
                                for(int j=0;st.hasMoreTokens();j++)
                                { //first 'for' loop
                                        String msg_idd=st.nextToken();
                                        // get New subject
                                        if(stats.equals("fromIndex"))
                                        {
                                                course_id="general";
                                                instid="general";
                                        }else{
                                                if(mode2.equals("instituteWise"))
                                                {
                                                        course_id="instituteWise";
                                                        instid="instituteWise";
                                                }
                                        }
					/************for set status 1 when click move button ***********/
					Criteria crit=new Criteria();
	                                crit.add(DbSendPeer.MSG_ID,msg_id);
        	                        List l=DbSendPeer.doSelect(crit);
        	                        String information="UPDATE DB_SEND SET STATUS=1  WHERE MSG_ID="+msg_idd;
                	                DbSendPeer.executeStatement(information);
					/************************/
					String username=data.getUser().getName();
                                        int uid=UserUtil.getUID(username);
					instid=(String)user.getTemp("Institute_id");
					
					Criteria crit1=new Criteria();
                                        crit1.add(FaqmovePeer.USER_ID,uid);
                                        crit1.add(FaqmovePeer.INST_ID,instid);
                                        FaqmovePeer.doInsert(crit1);

					/**  
					* get the dbsubject and msg file.
					*/
					DB_subject = topicarray[j];
                        	        String readMsg=data.getServletContext().getRealPath("/Courses"+"/"+course_id+"/DisBoard"+"/"+DB_subject+"/Msg.txt");
                        		String writepath=data.getServletContext().getRealPath("/UserArea"+"/"+instid+"/"+msg_idd);
                                        File f=new File(writepath);
	                                if(f.exists()){
						String strmess=MultilingualUtil.ConvertedString("faq_msg1",LangFile);
                	                        data.setMessage(strmess);
                        	                return;
                                	}
					/**  
					* make a directory where we store dbsubject and message file.
					*/
	   	                                File topicDir = new File(writepath);
                	                        if(!topicDir.exists()) //{
                        	                        topicDir.mkdirs();
							writepath = writepath+"/"+DB_subject+".txt";
				                        //java.util.Date date=new java.util.Date();
                				        FileWriter fw = new FileWriter(writepath,true);
							String str[]=new String[10000];
		                                        int i=0; int start = 0; int stop= 0;String string="";
        						/**  
						        * read the file of disscussion board messages.
							*/

                		                        BufferedReader br=new BufferedReader(new FileReader (readMsg));
		                                        while ((str[i]=br.readLine()) != null)
                		                        {
                                		                if (str[i].equals("<"+msg_idd+">"))
                                                		{
		                                                        start = i;
                		                                }
                                		                else if(str[i].equals("</"+msg_idd+">"))
                                                		{
		                                                        stop = i;
                		                                }
                                		                     i= i +1;
		                                        }                        
                		                        br.close();
							for(int x=start;x<stop;x++)
                                        		{
		                                                string=string+str[x];
                		                        }
                                		      	stop=string.lastIndexOf("Send date");
		                                        //stop=stop+21;
                		                        String tempString="";
                                		        for(int x=stop;x<string.length();x++)
                                        		{
		                                                tempString=tempString+string.charAt(x);
                		                        }
                				        fw.write("\n"+tempString+"\n");
				                        fw.close();
                        				String strmess=MultilingualUtil.ConvertedString("faq_msg2",LangFile);
				                        data.setMessage(strmess);
 					}// for
			   	//}
                        }// outer if
                }//TRY
                catch(Exception e){  data.addMessage("Some Error Occured in movemessages!!!!" +e);   }
	}
        public void doPerform(RunData data,Context context)
	{
		try{
			String action=data.getParameters().getString("actionName","");
			if(action.equals("eventSubmit_doSend"))
			{
				doSend(data,context);
			}
			else if(action.equals("eventSubmit_doPermission"))
				doPermission(data,context);
			else if(action.equals("eventSubmit_doUpdate"))
		        	doUpdate(data,context);
		 	else if(action.equals("eventSubmit_doDelete"))
				doDelete(data,context);
			else if(action.equals("eventSubmit_doArchive"))
                        	doArchive(data,context);
			else if(action.equals("eventSubmit_doShowDBContent"))
                                doShowDBContent(data,context);
			else if(action.equals("eventSubmit_doShowArchive"))
				doShowArchive(data,context);
                        else if(action.equals("eventSubmit_doMove"))
				doMove(data,context);
			else
			{ 
		        	String LangFile=data.getUser().getTemp("LangFile").toString();
		        	String msg=MultilingualUtil.ConvertedString("action_msg",LangFile);
		        	data.setMessage(msg);
			}
		}//try
		catch(Exception ex){data.setMessage("The error in Send DB !!"+ex);}
        }//(doPerform)
}//class	
