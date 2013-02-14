package org.iitk.brihaspati.modules.utils;
/*
 * @(#)WriteXmlThread.java
 *
 *  Copyright (c) 2012, 2013 ETRG,IIT Kanpur.
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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
//import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.services.servlet.TurbineServlet;

/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @modified date: 27-12-2012
 */

public class WriteXmlThread implements Runnable {

	private static String attachFile="", temp = "";
	//private static String xmlFilePath="";
	private static Vector v=null;
	private boolean flag=false;
	private static Thread runner=null;
	private static WriteXmlThread writeXmlThread=null;
	private static LinkedList linkList = new LinkedList();
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
	//public String set_Message(String filePath, String mailId, String sub, String msg, String attachedFile, String date, String time, String langFile, String messageFileName)
	public String set_Message(String filePath, String mailId, String sub, String msg, String attachedFile, String langFile, String messageFileName)
	{
		String strng="";
		//ErrorDumpUtil.ErrorLog("Lang File in MailNotificationThread Class ="+LangFile);
		v=new Vector();
		v.add(mailId); //0   
                v.add(sub);    //1
                v.add(msg);  //2 
                v.add(langFile);//3
                v.add(filePath);//4
                v.add(attachedFile);//5
                v.add(messageFileName);//6
		//ErrorDumpUtil.ErrorLog("\n\n writeXmlThread.v="+v);
		linkList.add(0,v);
		//writeXmlThread.xmlFilePath = filePath+"/"+messageFileName;
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
      	public synchronized void run() {

		while(flag) {
			try{ 	Thread.sleep(200); }catch(Exception e){ErrorDumpUtil.ErrorLog("\nI am  in WriteXmlThread Class  sleep section "+e, TurbineServlet.getRealPath("/logs/Email.txt"));}
			try { 
				int j = 0;
				//Criteria crit = null;
				Vector mailDetail = new Vector();
				while(linkList.size() > 0) {
					String writeinxml = "";
                                        Vector mail_data=(Vector)linkList.pop();
                                        String mailId = mail_data.get(0).toString().trim();
                                        String sub = mail_data.get(1).toString();
                                        String msg = mail_data.get(2).toString().trim();
                                        String LangFile = mail_data.get(3).toString();
                                        String filePath = mail_data.get(4).toString().trim();
                                        String attachedFile = mail_data.get(5).toString();
                                        String msgFileName = mail_data.get(6).toString().trim();
					//ErrorDumpUtil.ErrorLog("mailDetail.size()=="+mailDetail.size());
					/**
		                          * @see ExpiryUtil in Utils
                		         */
					
                        		String curdate = ExpiryUtil.getCurrentDate("-");
		                        Long longTime = new Date().getTime();
                		        String time = longTime.toString();
					File f = new File(filePath);

					if(!f.exists()){
	                                	f.mkdirs();
					}
					/**
						While File is read Filename definetly come{
							if {failure mail & file name is same need not to write }
							else{ write that mail with new name}
						}
						While mail is failure filename is empty
					*/

					if(!StringUtils.isBlank(msgFileName)){
						//ErrorDumpUtil.ErrorLog("\n\n msgFileName====="+msgFileName);
						f = new File(filePath+"/"+msgFileName);
						if(f.exists()){
							try{
								String tmp = StringUtils.substringAfterLast(msgFileName, "_");
                                        			String searchDate = (StringUtils.substringBeforeLast(tmp, "$")).trim();
  								File f2 = new File(filePath+"/"+mailId+"_"+searchDate+"$"+curdate+":"+time+".xml");
								  InputStream in = new FileInputStream(f);
  
								  //For Overwrite the file.
								  OutputStream out = new FileOutputStream(f2);

								  byte[] buf = new byte[1024];
								  int len;
								  while ((len = in.read(buf)) > 0){
									
									  out.write(buf, 0, len);
								  }
								  in.close();
								  out.close();
  							}
							catch(FileNotFoundException ex){
								 ErrorDumpUtil.ErrorLog(ex.getMessage() + "= in WriteXmlThread class of util directory .");
								System.exit(0);
							}
							f.delete();
						}
						else if(!f.exists()){

							writeinxml=XMLWriter_EmailSpooling.EmailSpoolingXml(filePath+"/"+mailId+"_"+curdate+"$"+curdate+":"+time+".xml", sub, msg, attachedFile, LangFile);

						}
					}
					else {
						//writeinxml=XMLWriter_EmailSpooling.EmailSpoolingXml(filePath+"/"+mailId+time+".xml", mailId, sub, msg, attachedFile, curdate, time, LangFile);
							writeinxml=XMLWriter_EmailSpooling.EmailSpoolingXml(filePath+"/"+mailId+"_"+curdate+"$"+curdate+":"+time+".xml", sub, msg, attachedFile, LangFile);
					}

					 if(j== (linkList.size())){
                                        	break;
					}
					j = j + 1;
				} //main while close
				
			}catch(Exception es){ErrorDumpUtil.ErrorLog("\nI am  in WriteXmlThread Class  in run method ", TurbineServlet.getRealPath("/logs/Email.txt"));}
			stop();
		}	
    	}
}// End of file.
