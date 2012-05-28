package org.iitk.brihaspati.modules.screens.call.Dis_Board;

/*
 * @(#)DBContent.java	
 *  Copyright (c) 2005-2007,2009, 2010, 2011 ETRG/IIT Kanpur. 
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
import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.security.AccessControlList;

import org.iitk.brihaspati.om.DbSendPeer;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.om.DbReceive;
import org.iitk.brihaspati.om.DbSend;

import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.DbDetail;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;


/** 
 * This class contains code for all discussion are shows 
 * @author  <a href="nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author  <a href="aktri@iitk.ac.in">Awadhesh Kumar Trivedi</a>
 * @author  <a href="sumanrjpt@yahoo.co.in">Suman Rajput</a>
 * @author  <a href="rekha_20july@yahoo.co.in">Rekha Pal</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @author <a href="mailto:tpthshobhi30@gmail.com">Shobhika</a>
 * @ modified date: 13-Oct-2010 (Shaista)
 * @ modified date: 15-Feb-2011 (Shaista)
 * @ modified date: 24-Aug-2011 (Sunil Kumar)
 */

public class DBContent extends SecureScreen
{
	/**
	 *   This method actually give the detail of messages in the Disscussion board in group system
	 *   @param data RunData instance
	 *   @param context Context instance
	 *   try and catch Identifies statements that user want to monitor
	 *   and catch for exceptoin.
	 */
    	public void doBuildTemplate( RunData data, Context context )
    	{
		try
		{
			ParameterParser pp=data.getParameters();
               	 	String perm=pp.getString("perm");
		 	context.put("perm",perm);
		 	String DB_subject=pp.getString("DB_subject","");
			String Username=data.getUser().getName();
			context.put("UserName",Username);
			String frompath=data.getParameters().getString("from");
                        context.put("from",frompath);
                        context.put("tdcolor",pp.getString("count","4"));
                        context.put("tdcolor1",pp.getString("countTemp",""));
			String userid1=pp.getString("userid1","");
                        context.put("userid1",userid1);	

           		/**	
	    		* Retrive the UserId from Turbine_User table
	    		* @see UserUtil
	    		*/
			int user_id=UserUtil.getUID(Username);
	        	String group=(String)data.getUser().getTemp("course_id");
			context.put("course_id",group);
			/*
                        * stats=data.getParameters().getString("stats","");
                        * mode2=data.getParameters().getString("mode2","");
                        * stats and mode2 use for general and institute wise discussion group
                        */
			String stats=data.getParameters().getString("stats","");
                        context.put("stats",stats);
			String mode2=data.getParameters().getString("mode2","");
                        context.put("mode2",mode2);
                        int group_id=0;
			String mode=data.getParameters().getString("mode");
			//this is use for general and institute wise discussion group
                        if(stats.equals("fromIndex")){
                                group_id=4;
				group="general";
                        }else if(mode2.equals("instituteWise")){
                                group_id=5;
				group="instituteWise";
                        }else
	        		group_id=GroupUtil.getGID(group);  
	        	String gid=Integer.toString(group_id);   
	        	String uid=Integer.toString(user_id);   
	        	context.put("group",gid);
	        	context.put("userid",uid);
	    		/** 
	     		* Select all the messagesid according to the ReceiverId
	     		* from the Db_Receive table
	     		*/
			context.put("mode",mode);
			Criteria crit=new Criteria();
			List v=null;
			if(mode.equals("All"))
			{
				if(!stats.equals("fromIndex")){
					crit.add(DbReceivePeer.RECEIVER_ID,user_id);
				}
				crit.add(DbReceivePeer.GROUP_ID,group_id);
				v=DbReceivePeer.doSelect(crit);
				
			}
			else
			{
				int readflg=0;
				if(!stats.equals("fromIndex")){
					crit.add(DbReceivePeer.RECEIVER_ID,user_id);
				}
				crit.add(DbReceivePeer.GROUP_ID,group_id);
				crit.add(DbReceivePeer.READ_FLAG,readflg);
	        		v=DbReceivePeer.doSelect(crit);
			}
			/**
			*This is use for get STATUS in DB_SEND table
			*/	
			List l=null;
			Criteria crit2=new Criteria();
			crit2.add(DbSendPeer.GROUP_ID,group_id);
                        l=DbSendPeer.doSelect(crit2);
			for(int i=0;i<l.size();i++)
                        {
                                DbSend element=(DbSend)(l.get(i));
                                String stat=Integer.toString(element.getStatus());
				context.put("stat",stat);
			}
			
			/**
		 	* Below code just converts the List 'v' into Vector 'entry' 
		 	*/ 
		
			Vector entry=new Vector();
                	for(int count=0;count<v.size();count++)
			{//for1
				DbReceive element=(DbReceive)(v.get(count));
                         	String m_id=Integer.toString(element.getMsgId());
			 	int msg_id=Integer.parseInt(m_id);
			 	int read_flag=(element.getReadFlag());
			 	String read_flag1=Integer.toString(read_flag);
	 			/**
	  			* Select all the messages according to the MessageId
	  			* from the Db_Send table
	  			*/ 
			 	Criteria crit1=new Criteria();
			 	crit1.add(DbSendPeer.MSG_ID,msg_id);
			 	List u=DbSendPeer.doSelect(crit1);
				for(int count1=0;count1<u.size();count1++)
				{//for2 
					DbSend element1=(DbSend)(u.get(count1));
			  		String msgid=Integer.toString(element1.getMsgId());
					String message_subject=(element1.getMsgSubject());
					String stat=Integer.toString(element1.getStatus());
					int sender_userid=(element1.getUserId());
					String permit=Integer.toString(element1.getPermission());
					String dbType=(element1.getGrpmgmtType());
				
					String sender_name=UserUtil.getLoginName(sender_userid);
                                	context.put("msgid",msgid);
                                	context.put("contentTopic",message_subject);
                              
                                	String sender=UserUtil.getLoginName(sender_userid);
				 	context.put("sender",sender);
				 			 
					Date dat=(element1.getPostTime());
					String posttime=dat.toString();
					int ExDay=(element1.getExpiry());
					context.put("ExDay",ExDay);
					String exDate= null;
					if(ExDay == -1)
					{
						exDate="infinte";
					}
					else
					{
						exDate = ExpiryUtil.getExpired(posttime, ExDay);				     
					}
					DbDetail dbDetail= new DbDetail();
                        		dbDetail.setSender(sender_name);
		                	dbDetail.setPDate(posttime);
        	                	dbDetail.setMSubject(message_subject);
		       	        	dbDetail.setStatus(read_flag1);
		               		dbDetail.setMsgID(m_id);
		               		dbDetail.setPermission(permit);
		               		dbDetail.setExpiryDate(exDate);
					dbDetail.setGrpmgmtType(dbType);
					dbDetail.setPrgCode(stat);
					entry.addElement(dbDetail);
				}//for2	
			}//for1
			String newgroup=(String)data.getUser().getTemp("course_id");
			String cname=CourseUtil.getCourseName(newgroup);
			AccessControlList acl=data.getACL();
			if(acl.hasRole("instructor",newgroup))
			{
				context.put("isInstructor","true");
			}
			context.put("user_role",data.getUser().getTemp("role"));
		  	//Adds the information to the context
			if(entry.size()!=0)
			{
				context.put("status","Noblank");
				context.put("entry",entry);
			}
			else
			{
				context.put("status","blank");
		 		String LangFile=(String)data.getUser().getTemp("LangFile");	
				if(mode.equals("All")){
	               			String  mssg=MultilingualUtil.ConvertedString("db-Contmsg",LangFile);
			 		data.setMessage(mssg);
				}
				else{
					String  mssg=MultilingualUtil.ConvertedString("db-Contmsg1",LangFile);
	                                data.setMessage(mssg);
				}
			}
			context.put("username",Username);
			context.put("cname",(String)data.getUser().getTemp("course_name"));
			context.put("workgroup",group);
			/*
                         *method for how much time user spend in this page.
                         */
			if((!group.equals("instituteWise")) || (!group.equals("general")) || (!group.equals(" ")))
			{
				String Role = (String)data.getUser().getTemp("role");
				if((Role.equals("student")) || (Role.equals("instructor")))
                	        {
                        	        //CourseTimeUtil.getCalculation(user_id);
                            	        // ModuleTimeUtil.getModuleCalculation(user_id);
					MailNotificationThread.getController().CourseTimeSystem(user_id);
                      		}
			}
			
		}//try
		catch(Exception e){data.setMessage("Exception screens [Dis_Board,DBContent.java]" + e);}
    	}//method
}//class
