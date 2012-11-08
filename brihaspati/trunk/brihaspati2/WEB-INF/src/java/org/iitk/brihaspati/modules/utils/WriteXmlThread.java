package org.iitk.brihaspati.modules.utils;
/*
 * @(#)MailNotificationThread.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
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
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 */

public class WriteXmlThread implements Runnable {

	private static String attachFile="", temp = "";
	private static String xmlFilePath="";
	private static Vector v=null;
	private static Vector v1=null;
	private static LinkedList linkList = new LinkedList();
	private boolean flag=false;
	private boolean flag1=false;
	private static Thread runner=null;
	private static WriteXmlThread writeXmlThread=null;
	private static MultilingualUtil mu=new MultilingualUtil();

	/**
	 * Controller for this class to use as a singleton.
	 */
        public static WriteXmlThread getController(){
                if( writeXmlThread==null)
                         writeXmlThread = new WriteXmlThread();
                return  writeXmlThread;
        }	
	
	/**
 	 * Add message to a vector which store in linkedlist.
	 */
	//public String set_Message(String filePath, String mailId, String sub, String msg, String attachedFile, String date, String time, String langFile)
	public String set_Message(String filePath, String mailId, String sub, String msg, String attachedFile, String date, String time, String langFile)
	{
		String strng="";
		//ErrorDumpUtil.ErrorLog("Lang File in MailNotificationThread Class ="+LangFile);
		v=new Vector();
		v.add(mailId); //0   
                v.add(sub);    //1
                v.add(msg);  //2 
                v.add(date); //3  
                v.add(time);//4
                v.add(langFile);//5
                v.add(filePath);//6
                v.add(attachedFile);//7
		//ErrorDumpUtil.ErrorLog("\n\n writeXmlThread.v="+v);
		linkList.add(0,v);
		writeXmlThread.xmlFilePath = filePath;
		start();
			strng= mu.ConvertedString("mail_msg", langFile);
			//"Message is in queue";
	//....................
		return strng;
	}

	
	/**
        * Start EmailSpoolingThread Thread.
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
				int j = 0;
				Criteria crit = null;
				Vector mailDetail = new Vector();
				File f = new File(writeXmlThread.xmlFilePath);
				while(linkList.size() != 0) {
					String searchMailId = "", searchMsg = "", searchSub = "", writeinxml = "";
					boolean boolFlag = false;
					InstituteFileEntry  InstfileEntry = null;
					//boolean boolFlag = false;
                                        Vector mail_data=(Vector)linkList.pop();
                                        String mailId = mail_data.get(0).toString().trim();
                                        String sub = mail_data.get(1).toString();
                                        String msg = mail_data.get(2).toString().trim();
                                        String date = mail_data.get(3).toString();
                                        String time = mail_data.get(4).toString();
                                        String LangFile = mail_data.get(5).toString();
                                        String filePath = mail_data.get(6).toString().trim();
                                        String attachedFile = mail_data.get(7).toString();
					//ErrorDumpUtil.ErrorLog("mailDetail.size()=="+mailDetail.size());
					if(!f.exists()){ 
	                                	f.mkdirs();
						writeinxml=XMLWriter_EmailSpooling.EmailSpoolingXml(filePath+"/EmailSpoolFile.xml", mailId, sub, msg, attachedFile, date, time, LangFile);
					}
					else{ //else 1 open
						mailDetail = XMLWriter_EmailSpooling.getEmailSpoolDetails(writeXmlThread.xmlFilePath+"/EmailSpoolFile.xml");
						if( mailDetail.size() != 0){
							for( int i=0; i < mailDetail.size(); i++)
							{
						
								InstfileEntry =  (InstituteFileEntry)mailDetail.get(i);
								searchMailId = InstfileEntry.getInstituteEmail().trim();
								searchMsg = InstfileEntry.getMessage().trim();
								searchSub = InstfileEntry.getSubject().trim();
								//ErrorDumpUtil.ErrorLog("\nsearchMailId==="+searchMailId+"\t mailId"+ mailId+"\nmsg="+msg.trim()+"\tsearchMsg="+searchMsg.trim());
								 if( mailId.equals(searchMailId) && msg.equals(searchMsg) && sub.equals(searchSub) ){
	                                                        	boolFlag = true;
	                	                                        break;
        	                                        	}

							} //for  mailDetail.size(); close 
							InstfileEntry = null;
							if(!boolFlag)
        	                       				writeinxml=XMLWriter_EmailSpooling.EmailSpoolingXml(filePath+"/EmailSpoolFile.xml", mailId, sub, msg, attachedFile, date, time, LangFile);
                			               mailId = "";
        	        	        	       msg = "";
	                        	               mailDetail = null;
					
						} // close of if( mailDetail.size() != 0)
						else
                               				writeinxml=XMLWriter_EmailSpooling.EmailSpoolingXml(filePath+"/EmailSpoolFile.xml", mailId, sub, msg, attachedFile, date, time, LangFile);
					} //else 1 close
					 if(j== (linkList.size())){
                                        	break;
					}
					j = j + 1;
				} //main while close
				
			}catch(Exception es){}
			stop();
		}	
    	}
}// End of file.
