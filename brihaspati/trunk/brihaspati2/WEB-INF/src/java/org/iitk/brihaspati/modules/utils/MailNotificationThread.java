package org.iitk.brihaspati.modules.utils;
/*
 * @(#)MailNotificationThread.java
 *
 *  Copyright (c) 2010-2011, 2012 ETRG,IIT Kanpur.
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
import java.io.File;
import java.sql.Date;
import java.util.Vector;
import java.util.LinkedList;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.commons.lang.StringUtils;


/**
 * @author <a href="mailto:meha1490@gmail.com">Meha Singh</a>Created on march-2011
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @modified Date: 18-08-2011 (Shaista)
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modify date: 09-08-2012 (Priyanka)
 * @modify date: 09-11-2012, 27-12-2012 (Shaista)
 */

public class MailNotificationThread implements Runnable {

	//private static String attachedFile="";
	private static Vector v=null;
	private static String serverPort=null;
	private static LinkedList mailnotification = new LinkedList();
	private boolean flag=false;
	private boolean flag1=false;
	private boolean flag2=false;
	private static Thread runner=null;
	private static MailNotificationThread mailNotificationThread=null;
	private static MultilingualUtil mu=new MultilingualUtil();
	private int userid=0;
	private int eid=0;

	/**
	 * Controller for this class to use as a singleton.
	 */
        public static MailNotificationThread getController(){
                if(mailNotificationThread==null)
                        mailNotificationThread=new MailNotificationThread();
                return mailNotificationThread;
        }	
	
	/**
 	 * Add message to a vector which store in linkedlist.
	 */
	//public String set_Message(String message_text,String mailId,String sub,String filePathForLM,String LangFile, String portNum)
	public String set_Message(String message_text, String msg_Dear, String msg_regard, String msg_UserInfo, String mailId, String sub, String filePathForLM, String LangFile, String instId, String mode)//last parameter added by Priyanka
	{
		String strng="";
		//ErrorDumpUtil.ErrorLog("Lang File in MailNotificationThread Class ="+LangFile);
		v=new Vector();
		v.add(mailId); //0   
                v.add(sub);    //1
                v.add(msg_Dear);  //2 
                v.add(message_text); //3  
                v.add(filePathForLM);//4
                v.add(LangFile);//5
                v.add(instId);//6
                v.add(msg_regard);  //7 
                v.add(msg_UserInfo);  //8
		mailnotification.add(0,v);
		//ErrorDumpUtil.ErrorLog("\n v=="+v); 
		start();
	//following check added by priyanka
		if(mode.equals("act") || mode.equals(""))
			strng= mu.ConvertedString("mail_msg", LangFile);
			//"Message is in queue";
		if(mode.equals("cnfrm_i") || (mode.equals("cnfrm_u")) || (mode.equals("cnfrm_c")))
			strng= mu.ConvertedString("mail_confirm", LangFile);
			//"Please click on the Activation link to Activate your account."
	//....................
		return strng;
	}

	/*public void CourseTimeSystem(int userid){
                this.userid=userid;
		//this.eid=eid;
		flag1=true;	
                start();
        }*/
	public void CourseTimeSystem(int userid,int eid){
		this.userid=userid;
                this.eid=eid;
                flag1=true;
                start();
        }

	
	/**
        * Start MailNotificationThread Thread.
        */ 
        private void start(){
                if (runner == null) {
			flag=true;	
                        runner = new Thread(this);
                        runner.start();
                }
        }

        /**
         * Stop MailNotificationThread Thread.
         */
        private void stop() {
                if (runner != null) {
			flag=false;
                        runner.interrupt();
                        runner = null;
                }
        }
	
	/**
	 * MailNotification thread for send mails.
	 */
      	public synchronized void run() {
		
		while(flag) {
			try{ 	Thread.sleep(200); }catch(Exception e){}
			try {
				/** Please dont remove below line */
 
				ErrorDumpUtil.ErrorLog("\nMailNotificationThread Class  mailnotification.size()!!!!!!!!!!!!!!!!"+mailnotification.size(), TurbineServlet.getRealPath("/logs/Email.txt"));
				while(mailnotification.size() != 0) {
					boolean boolFlag = false;
					Vector mail_data=(Vector)mailnotification.pop();
					//ErrorDumpUtil.ErrorLog("\nMailNotificationThread   mail_data=="+mail_data);
					String mailId =mail_data.get(0).toString().trim();
        	                        String sub = mail_data.get(1).toString();
					String msgDear =mail_data.get(2).toString();
               				String message_text =mail_data.get(3).toString().trim();
					String filePathForLM =mail_data.get(4).toString();
	               			String LangFile =mail_data.get(5).toString();
					String instId =mail_data.get(6).toString();
					//MailNotificationThread.attachedFile = filePathForLM;
					String msgRegard1 =mail_data.get(7).toString();
					String msgUserInfo =mail_data.get(8).toString().trim();
					int j=0;
					while(mailnotification.size() !=0 ) {
						boolean flag1=false;
						Vector mail_data1 = (Vector)mailnotification.get(j);
		                                String searchId = mail_data1.get(0).toString();
						String searchInstId = mail_data1.get(6).toString();
						String searchAttachFile = mail_data1.get(4).toString();
						//ErrorDumpUtil.ErrorLog("mail_data1=="+mail_data1);
						if(mailId.equals(searchId) && ((instId.equals(searchInstId)|| searchInstId.equals("")))){
				
							flag1=true;
							boolFlag = true;
							String searchMsgUserInfo =mail_data1.get(8).toString().trim();
		               				String searchLangFile =mail_data1.get(5).toString();
							if(!msgUserInfo.equals(searchMsgUserInfo))
							{
								/* If One User in Same institute have same message then it would go in "else" part 
								 * Other wise it will go in "if" part.
								*/
								if(!message_text.equals(mail_data1.get(3).toString())){
									/**
									  * If MailId & txt message is in EmailSpool file and mail get combined 
									  * then existing entry of xml need to remove before writing combined mail again while it get failur.
									  * Other wise combined mail would be sent
									 */
	               							message_text =message_text+searchMsgUserInfo+"<br><br>"+mail_data1.get(3).toString()+"<br>";
								}
								else
	               							message_text =message_text+searchMsgUserInfo+"<br>";
								
							}
							else{
								if(!message_text.equals(mail_data1.get(3).toString())){
									/**
									  * If MailId & txt message is in EmailSpool file and mail get combined 
									  * then existing entry of xml need to remove before writing combined mail again while it get failur.
									 */
               								message_text =message_text+"<br><br>"+mail_data1.get(3).toString()+"<br>";
								}
							}
							String tmpAtFile ="";
							if(!LangFile.equals(searchLangFile))
								LangFile = searchLangFile;
							/** While file is attached and default value 'tmp' from xml is coming
							  * then attached file for same mail ID have to check 
							  * If it is not tmp then attach file value would be set
							  * Else both atached file value need to concatenate.
							  */ 
							if(!filePathForLM.equals("tmp"))
								tmpAtFile = filePathForLM;
							
							if(filePathForLM.equals("tmp") && !searchAttachFile.equals("tmp"))
									filePathForLM = searchAttachFile;
							
							else if(!searchAttachFile.equals("tmp") && !StringUtils.isBlank(tmpAtFile))
									 filePathForLM =  filePathForLM+","+searchAttachFile;
								
						
							else if(!searchAttachFile.equals("tmp") && StringUtils.isBlank(filePathForLM))
									filePathForLM = searchAttachFile;
							else if(searchAttachFile.equals("tmp") && !StringUtils.isBlank(tmpAtFile))
								 filePathForLM = tmpAtFile;
						}
						if(flag1){
							mailnotification.remove(j);
	                                        } else {
							j++;
                                                }
						
						if(j== (mailnotification.size())){
							break;
						}	
					}
					if(boolFlag){
						sub = "Combined mail from Brihaspati- The Virtual Classroom ";
					}

					String msg=MailNotification.sendMail(msgDear+message_text+msgRegard1+msgUserInfo, mailId, sub, filePathForLM, LangFile, "");
				} //main while close
				this.flag2 = true;

				if(flag1){
					 ModuleTimeUtil.AllCalculations(userid,eid);
                                        flag1=false;
                                }
				
				if(flag2){
 	              			File f1 = new File(TurbineServlet.getRealPath("/EmailSpooling"));
	                       		if(f1.exists())
						emailXMLRead();
	        			flag2 = false;
				}
					
			}catch(Exception es){}
			stop();
		}	
    	}
	public void emailXMLRead(){
		Vector  mailDetail1 = new Vector();
		 String searchMailId= "", searchMsg ="", sendAttemptTime = "", searchDate = "", attachedFile = "", writeinxml = "", sub = "", langFile = "";
		int intSearchAttempt =0;
		long longCreationDate;
                long longCurrentDate;
		long noOfmin, noOfDays;
		String filePath1=TurbineServlet.getRealPath("/EmailSpooling");
        	File f1 = new File(filePath1);
		if(f1 != null){ //if1
			File[] files = f1.listFiles();  
			if (files.length >0){ //if2
				for (File file : files) {
					String fileName = file.getName();
					searchMailId = StringUtils.substringBeforeLast(fileName, "_");
					String tmp = StringUtils.substringAfterLast(fileName, "_");
					searchDate = (StringUtils.substringBeforeLast(tmp, "$")).trim();
					String temp = (StringUtils.substringAfterLast(tmp, "$")).trim();
					String tmpSearchDate = (StringUtils.substringBeforeLast(temp, ":")).trim();
					String tmpTime = (StringUtils.substringAfterLast(tmp, ":")).trim();
					sendAttemptTime = StringUtils.substringBeforeLast(tmpTime,".xml");
					//ErrorDumpUtil.ErrorLog("\nsearchMailId=="+searchMailId+"\t searchDate="+searchDate+"\t ExpiryUtil.getCurrentDate=="+ExpiryUtil.getCurrentDate("-")+"\t sendAttemptTime="+sendAttemptTime);
					/* Getting current time */

					java.util.Date date = new java.util.Date();
					longCurrentDate =  date.getTime();


					 /** Calculating Days*/

        				longCreationDate = Date.valueOf(searchDate).getTime();
					noOfDays = (longCurrentDate-longCreationDate)/(24*3600*1000)+1;

					//ErrorDumpUtil.ErrorLog("\nlongCurrentDate ="+longCurrentDate);
					//ErrorDumpUtil.ErrorLog("\nlongCreationDate ="+longCreationDate);
					//ErrorDumpUtil.ErrorLog("\n noOfDays ="+noOfDays);
					String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
					try{
				        	String mailSpoolingExpiryDay = AdminProperties.getValue(path,"brihaspati.admin.mailSpoolingExpiry.value"); 
					
						if(StringUtils.isBlank(mailSpoolingExpiryDay))
							mailSpoolingExpiryDay = "3";
						String mailResendTime =  AdminProperties.getValue(path,"brihaspati.admin.spoolMailResendTime.value");
						if(StringUtils.isBlank(mailResendTime))
							mailResendTime = "60";
						
						if(noOfDays > Long.valueOf(mailSpoolingExpiryDay)){
							File f = new File(filePath1+"/"+fileName);
							f.delete();
						}
						else {
							/** Calculating minuts */

       	       						longCreationDate = Long.valueOf(sendAttemptTime);
        		        	        	noOfmin = (longCurrentDate-longCreationDate)/(60*1000);

							if(!tmpSearchDate.equals(ExpiryUtil.getCurrentDate("-"))) 
							{
								if(noOfDays >1 )
									noOfmin = 24*(noOfDays-1)*60 + noOfmin;
							}
							//ErrorDumpUtil.ErrorLog("\nAFTERRRRRRRRRRRRRRRR noOfmin ="+noOfmin);
		
							if(noOfmin > Long.valueOf(mailResendTime) && (longCurrentDate-longCreationDate)!=0){
								mailDetail1 = XMLWriter_EmailSpooling.getEmailSpoolDetails(filePath1+"/"+fileName);
		        		                	if( mailDetail1.size() != 0){

	                               			        	InstituteFileEntry  InstfileEntry =  (InstituteFileEntry)mailDetail1.get(0);
              				        		        searchMsg = InstfileEntry.getMessage().trim();
									sub = InstfileEntry.getSubject();
									attachedFile = InstfileEntry.getAttachFile();
									langFile =  InstfileEntry.getLangFile();
									Long cDate = date.getTime();
									EmailSpoolingThread.getController().set_Message( searchMailId, sub, searchMsg, attachedFile, langFile, fileName);
       			           	        	        }
							}		
						} //else
					} //try close
					catch (Exception e){ ErrorDumpUtil.ErrorLog("\nError in readXml() Method in MailNotificationThread class="+e.getMessage());}
				} //for
			}//if 2
		} //if 1
	}
}// End of file.
