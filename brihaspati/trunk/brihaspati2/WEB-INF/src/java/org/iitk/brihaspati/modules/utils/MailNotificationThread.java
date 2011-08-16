package org.iitk.brihaspati.modules.utils;
/*
 * @(#)MailNotificationThread.java
 *
 *  Copyright (c) 2010-2011 ETRG,IIT Kanpur.
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
import java.util.LinkedList;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;


/**
 *  @author <a href="mailto:meha1490@gmail.com">Meha Singh</a>Created on march-2011
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 */

public class MailNotificationThread implements Runnable {

	private static String attachedFile="";
	private static Vector v=null;
	private static Vector v1=null;
	private static String serverPort=null;
	private static LinkedList mailnotification = new LinkedList();
	private boolean flag=false;
	private static Thread runner=null;
	private static MailNotificationThread mailNotificationThread=null;
	

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
	public String set_Message(String message_text, String msg_Dear, String msg_regard, String msg_UserInfo, String mailId, String sub, String filePathForLM, String LangFile, String instId )
	{
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
		start();
		return "Message is in queue";
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
      	public  void run() {

		while(flag) {
			try{ 	Thread.sleep(200); }catch(Exception e){}
			try { 
				while(mailnotification.size() != 0) {
					boolean boolFlag = false;
					//ErrorDumpUtil.ErrorLog(" ====> mailNotification before "+mailnotification.toString());
					Vector mail_data=(Vector)mailnotification.pop();
					String mailId =mail_data.get(0).toString();
        	                        String sub = mail_data.get(1).toString();
					String msgDear =mail_data.get(2).toString();
               				String message_text =mail_data.get(3).toString();
	               			String LangFile =mail_data.get(5).toString();
					String instId =mail_data.get(6).toString();
					String filePathForLM =mail_data.get(4).toString();
					MailNotificationThread.attachedFile = filePathForLM;
					String msgRegard1 =mail_data.get(7).toString();
					String msgUserInfo =mail_data.get(8).toString().trim();
					int j=0;
					while(mailnotification.size() !=0 ) {
						boolean flag1=false;
						//ErrorDumpUtil.ErrorLog("================> j >"+j);
						Vector mail_data1=(Vector)mailnotification.get(j);
		                                String searchId =mail_data1.get(0).toString();
						String searchInstId =mail_data1.get(6).toString();
						//ErrorDumpUtil.ErrorLog("\n inner while  mailId "+mailId+"\nsearchId="+searchId);
						if(mailId.equals(searchId) && (instId.equals(searchInstId)|| searchInstId.equals(""))){
							flag1=true;
							boolFlag = true;
							String searchMsgUserInfo =mail_data1.get(8).toString().trim();
		               				String searchLangFile =mail_data1.get(5).toString();
							if(!msgUserInfo.equals(searchMsgUserInfo))
               							message_text =message_text+searchMsgUserInfo+"<br><br>"+mail_data1.get(3).toString()+"<br>";
							else
               							message_text =message_text+mail_data1.get(3).toString()+"<br>";
							if(!LangFile.equals(searchLangFile))
								LangFile = searchLangFile;
						}
						if(flag1){
							mailnotification.remove(j);
	                                        } else {
							j++;
                                                }
						//ErrorDumpUtil.ErrorLog(" ----------------->  "+j+"  mailnotification. "+mailnotification.size());
						if(j== (mailnotification.size())){
							break;
						}	
					}
					if(boolFlag){
						sub = "Combined mail from Brihaspati- The Virtual Classroom ";
					}
					String msg=MailNotification.sendMail(msgDear+message_text+msgRegard1+msgUserInfo, mailId, sub, filePathForLM, LangFile);
					//ErrorDumpUtil.ErrorLog(""+msg);
					//ErrorDumpUtil.ErrorLog("\nmailNotification llllllllllllllllllllll  "+mailnotification.toString());
				} //main while close
					if(mailnotification.size() == 0){
					 	if(MailNotificationThread.attachedFile.length() >0)
						MailNotification.deletingAttachedFile(MailNotificationThread.attachedFile);
					}
			}catch(Exception es){}
			stop();
		}	
    	}
}// End of file.
