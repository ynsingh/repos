package org.iitk.brihaspati.modules.actions;
/*
 * @(#)OnlineExaminationSystem.java	
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur. 
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;

/**
 * This Action class for Online Examination system 
 * @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a> 
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar singh</a> 
 */
public class OnlineExaminationSystem extends SecureAction
{
	String QuestionBankPath=TurbineServlet.getRealPath("/QuestionBank");
	
	private String LangFile=new String();
	/** This method is responsible for uploading multiple question in QBR in single step
 	  * @param data RunData instance
 	  * @param context Context instance
 	  * @exception Exception, a generic exception
 	  */
	public void doUploadQues_Bank(RunData data, Context context)
	{
	        try
		 {//try
			LangFile=(String)data.getUser().getTemp("LangFile");
		 	ParameterParser pp=data.getParameters();
			User user=data.getUser();
                        String username=data.getUser().getName();
			String topic=pp.getString("Topicname","");
			String Questype=pp.getString("valQuestype","");
			String difflevel=pp.getString("valdifflevel","");
			String typeques=pp.getString("typeques","");
			String addques=pp.getString("addques","");
			String filepath=QuestionBankPath+"/"+username;
			File ff=new File(filepath);
                        if(!ff.exists())
                        ff.mkdirs();
			String QBpath="/QBtopiclist.xml";
			String fulltopic=topic+"_"+difflevel+"_"+Questype;
			String QBpath1=fulltopic+".xml";
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			FileItem file = pp.getFileItem("file");
                        String fileName=file.getName();
			String ImgUrl="";
			if((!(fileName.toLowerCase()).endsWith(".txt"))||(file.getSize()<=0))
                        {
                                 /**
                                 * Getting file value from temporary variable according to selection of Language
                                 * Replacing the static value from Property file
                                 **/

                                String upload_msg2=MultilingualUtil.ConvertedString("upload_msg2",LangFile);
                                data.setMessage(upload_msg2);
                        }
                        else{//else#1
				xmlwritetopiclist(filepath,topic,Questype,difflevel,QBpath1,Cur_date,QBpath,data);
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
                        	while((line=br.readLine())!=null) {//while#1
                               	StringTokenizer st1=new StringTokenizer(line,";",true);
	                        //entryNumber++;
        	                String Quesno="",Ques="",option1="",option2="",option3="",option4="",Answer="",Desc="";
                	        int error=0;
				String errMsg="";
		                //if(st1.countTokens()<8 ||st1.countTokens()>8)//if#2
		                if(st1.countTokens()<8)//if#2
	                                {error=1;}
				else
                                {//else#2
					File QBpathxml=new File(filepath+"/"+fulltopic+".xml");
                                	if(!QBpathxml.exists())
                                	{	
                                        	TopicMetaDataXmlWriter.OLESRootOnly(QBpathxml.getAbsolutePath());
                                	}//if
		                	Quesno=st1.nextToken().trim();
					if(addques.equals("addques"))
						Quesno=getMaxQuesid(filepath,QBpath1,Questype,data);
                		        	if(Quesno.equals(";"))//if#3
                                       			{error=2;}
	                                        else{
				                       	st1.nextToken();
        		        		        Ques=st1.nextToken().trim();
						//ErrorDumpUtil.ErrorLog("\nst1======"+Ques);
                	        	        	if(Ques.equals(";"))
                        			                {error=2;}
		                        		else{//else#4
		                				st1.nextToken();
	                		                        option1=st1.nextToken().trim();
					//ErrorDumpUtil.ErrorLog("\nst1======"+option1);
			                		        if(option1.equals(";"))
                				                       	{error=2;}
								else{//else#5
                						       	st1.nextToken();
				                                    	option2=st1.nextToken().trim();
					//ErrorDumpUtil.ErrorLog("\nst1======"+option2);
                                				        if(option2.equals(";"))
										{error=2;}
									else{//else6
					                			st1.nextToken();
                                        					option3=st1.nextToken().trim();
					//ErrorDumpUtil.ErrorLog("\nst1======"+option3);
					                                        if(option3.equals(";"))
											{error=2;}
										else{//else7
                									st1.nextToken();
						                                        option4=st1.nextToken().trim();
					//ErrorDumpUtil.ErrorLog("\nst1======"+option4);
						                                	if(option4.equals(";"))
												{error=2;}
											else{//else8
                										st1.nextToken();
                                							        Answer=st1.nextToken().trim();
					//ErrorDumpUtil.ErrorLog("\nst1======"+Answer);
							                                        if(Answer.equals(";"))
													{error=2;}
												else{//else9
								                			st1.nextToken();
								                                        Desc=st1.nextToken().trim();
								                                        if(Desc.equals(";"))
														{error=2;}
														xmlwriteQues(filepath,Questype,option1,option2,option3,option4,Ques,Answer,Quesno,Desc,QBpath1,ImgUrl,data);
												}//else9
											}//else8
										}//else7
									}//else6
								}//else5
							}//else4
						}//else3
					}//else2
				}//while1
				br.close();
                        	fr.close();
                        	f.delete();
				}//else1
			//}//else
			}//try
			catch(Exception e)
                	{
				 ErrorDumpUtil.ErrorLog("The exception in On Line Examination Action - doUploadQues_Bank ======"+e);
                        	 data.setMessage("Error in action[OLES:doUploadQues_Bank]"+e);

                	}
		}//method	
		 /** This method is responsible for seting templates on the basis of type of question
 	           * @param data RunData instance
                   * @param context Context instance
                   * @exception Exception, a generic exception
                   */

		public void doSettemplate_QB(RunData data, Context context)
		{
	        	try
		 	{//try
				LangFile=(String)data.getUser().getTemp("LangFile");
                        	ParameterParser pp=data.getParameters();
				String topic=pp.getString("Topicname","");
                        	String Questype=pp.getString("valQuestype","");
                        	String difflevel=pp.getString("valdifflevel","");
                        	String typeques=pp.getString("typeques","");
				if((typeques.equals("obo_ques"))||(typeques.equals("imgtypeques")))
                        	{
                                	if(Questype.equals("mcq"))
                                        	data.setScreenTemplate("call,OLES,Insert_Multiple.vm");
                                	if(Questype.equals("sat")||Questype.equals("lat"))
                                        	data.setScreenTemplate("call,OLES,Insert_Short.vm");
                                	if(Questype.equals("tft"))
                                        	data.setScreenTemplate("call,OLES,Insert_TF.vm");
				}
			}//try
			catch(Exception e)
                	{
				 ErrorDumpUtil.ErrorLog("The exception in On Line Examination Action - doSettemplate_QB "+e);
                        	 data.setMessage("Error in action[OLES:doSettemplate_QB]"+e);
                	}
		}//method	
		 /** This method is responsible for writing the question in QBR under respective topic
 	           * @param data RunData instance
                   * @param context Context instance
                   * @exception Exception, a generic exception
                   */
		public void doInserQuestion(RunData data, Context context,String status)
		{
	        	try
		 	{//try
				LangFile=(String)data.getUser().getTemp("LangFile");
                        	ParameterParser pp=data.getParameters();
				User user=data.getUser();
                        	String username=data.getUser().getName();
				String topic=pp.getString("Topicname","");
                                String Questype=pp.getString("valQuestype","");
                                String difflevel=pp.getString("valdifflevel","");
                                String typeques=pp.getString("typeques","");
				String Ques=pp.getString("Question","");
                                String Answer=pp.getString("Answer","");
                                String Desc=pp.getString("hint","");
                                String option1=pp.getString("op1","");
                                String option2=pp.getString("op2","");
                                String option3=pp.getString("op3","");
                                String option4=pp.getString("op4","");
				String ImgUrl="";
				String filepath=QuestionBankPath+"/"+username;
                        	File ff=new File(filepath);
                        	if(!ff.exists())
                        	ff.mkdirs();
				String QBpath="/QBtopiclist.xml";
				String fulltopic=topic+"_"+difflevel+"_"+Questype;
				File QBpathxml=new File(filepath+"/"+fulltopic+".xml");
                        	if(!QBpathxml.exists())
                        	{
                                	TopicMetaDataXmlWriter.OLESRootOnly(QBpathxml.getAbsolutePath());
                        	}//if
                        	String QBpath1=fulltopic+".xml";
                       		String Cur_date=ExpiryUtil.getCurrentDate("-");
				String Quesid=getMaxQuesid(filepath,QBpath1,Questype,data);
                        	String quesimg=new String();
				quesimg=Quesid+"_"+topic+"_"+difflevel+"_"+Questype;
				if(typeques.equals("imgtypeques"))
				{
					FileItem fileItem=pp.getFileItem("quesimg");
					if(fileItem.getSize() >0)
                			{
                        			long size=fileItem.getSize();
                        			Long size1=new Long(size);
                        			byte Filesize=size1.byteValue();
                        			String temp=fileItem.getName();
                        			int index=temp.lastIndexOf("\\");
                        			String tempFile=temp.substring(index+1);
                        			StringTokenizer st=new StringTokenizer(tempFile,".");
                        			String fileExt=null;
                        			for(int a=0;st.hasMoreTokens();a++)
                        			{ //first 'for' loop
                                			fileExt=st.nextToken();
                                			quesimg=Quesid+"_"+topic+"_"+difflevel+"_"+Questype;
                                			context.put("ImageName1",Byte.toString(Filesize));
                        			}
                      				if(fileExt.equals("jpg")|| fileExt.equals("gif")|| fileExt.equals("png"))//if3
                        			{
                                			int i=Integer.parseInt(Byte.toString(Filesize));
                                			if(i>0 && i<10000)//if4
                                			{
								String imagepath=TurbineServlet.getRealPath("/images"+"/QuestionBank");
								File imgPath=new File(imagepath+"/"+username+"/"+topic);
                                        			imgPath.mkdirs();
                                        			imgPath=new File(imgPath+"/"+quesimg);
						//		ErrorDumpUtil.ErrorLog("\nfileItem======"+imgPath+"\nimgPath"+imgPath+"\nimagepath"+imagepath);
                                        			fileItem.write(imgPath);
							}
						}
					}
				}
				xmlwritetopiclist(filepath,topic,Questype,difflevel,fulltopic+".xml",Cur_date,QBpath,data);
                                xmlwriteQues(filepath,Questype,option1,option2,option3,option4,Ques,Answer,Quesid,Desc,QBpath1,quesimg,data);
				if(status.equals("More"))
				{
					if(Questype.equals("mcq"))
                                        	setTemplate(data,"call,OLES,Insert_Multiple.vm");
                                        if(Questype.equals("sat")||Questype.equals("lat"))
                                        	setTemplate(data,"call,OLES,Insert_Short.vm");
                                        if(Questype.equals("tft"))
                                        	setTemplate(data,"call,OLES,Insert_TF.vm");
				}
                                if(status.equals("Finish"))
                                        setTemplate(data,"call,OLES,Oles_QB.vm");
			}//try
			catch(Exception e)
                	{
				 ErrorDumpUtil.ErrorLog("The exception in On Line Examination Action - doInserQuestion "+e);
                        	 data.setMessage("Error in action[OLES:doInserQuestion]"+e);
                	}
		}
		 /** This method is responsible for deleting the whole topic with all data
 	           * @param data RunData instance
                   * @param context Context instance
                   * @exception Exception, a generic exception
                   */
		public void doDeleteTopic(RunData data, Context context)
		{
	        	try
		 	{//try
				LangFile=(String)data.getUser().getTemp("LangFile");
                                ParameterParser pp=data.getParameters();
				User user=data.getUser();
                        	String username=data.getUser().getName();
                                String deltype=pp.getString("deltype","");
                                String questiontype=pp.getString("questype","");
				String topicname_quesid="";
				String topic=pp.getString("topic","");
				String quesid=pp.getString("quesid","");
				String dlevel=pp.getString("difflevel","");
				String filepath=QuestionBankPath+"/"+username;
				String fulltopic=topic+"_"+dlevel+"_"+questiontype;
				TopicMetaDataXmlReader topicmetadata=null;
				if(deltype.equals("topicdel"))
                        		topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+"QBtopiclist.xml");
				else
                        		topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+fulltopic+".xml");
				Vector collect=new Vector();
				Vector str=new Vector();
				if(deltype.equals("topicdel"))
                        		collect=topicmetadata.getQuesBanklist_Detail();
				else
				{
					if(questiontype.equals("mcq"))
                        			collect=topicmetadata.getQuesBank_Detail();
					else
                        			collect=topicmetadata.getQuesBank_Detail1();
				}
                        	if(collect!=null)
                        	{
                                	for(int i=0;i<collect.size();i++)
                                	{//for
						if(deltype.equals("topicdel"))
						{
                                        		String topicname =((FileEntry) collect.elementAt(i)).getTopic();
                                        		String filename =((FileEntry) collect.elementAt(i)).getfileName();
                                        		if(topic.equals(topicname))
							{
                        					str=DeleteEntry(filepath,"QBtopiclist.xml",topic,deltype,questiontype,data);
								File file=new File(filepath+"/"+filename);
                                        			file.delete();
							}
						}
						else
						{
                                        		String questionid =((FileEntry) collect.elementAt(i)).getquestionid();
                                        		String imgurl =((FileEntry) collect.elementAt(i)).getUrl();
                                        		if(questionid.equals(quesid))
							{
                        					str=DeleteEntry(filepath,fulltopic+".xml",quesid,deltype,questiontype,data);
								if(!imgurl.equals(""))
								{
									String imagepath=TurbineServlet.getRealPath("/images"+"/QuestionBank");
                                                       			File imgPath=new File(imagepath+"/"+username+"/"+topic+"/"+imgurl);
									imgPath.delete();
								//	ErrorDumpUtil.ErrorLog("imagepath===="+imagepath+"\nimgPath"+imgPath);
								}
							}
						}
                                        }
                                }
				if(deltype.equals("topicdel"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_qus",LangFile)+" "+MultilingualUtil.ConvertedString("oles_bank",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
				else
					data.setMessage(MultilingualUtil.ConvertedString("brih_qus",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
			}//try
			catch(Exception e)
                	{
				 ErrorDumpUtil.ErrorLog("The exception in On Line Examination Action - doDeleteTopic "+e);
                        	 data.setMessage("Error in action[OLES:doDeleteTopic]"+e);
                	}
		}
		 /** This method is responsible for update the question data
 	           * @param data RunData instance
                   * @param context Context instance
                   * @exception Exception, a generic exception
                   */
		public void doEditQuestion(RunData data, Context context)
		{
	        	try
		 	{//try
				LangFile=(String)data.getUser().getTemp("LangFile");
                                ParameterParser pp=data.getParameters();
				User user=data.getUser();
				String username=data.getUser().getName();
				String topic=pp.getString("topic","");
				String ques=pp.getString("Question","");
				String quesid=pp.getString("quesid","");
				String opt1=pp.getString("op1","");
				String opt2=pp.getString("op2","");
				String opt3=pp.getString("op3","");
				String opt4=pp.getString("op4","");
				String Answer=pp.getString("Answer","");
				String Desc=pp.getString("hint","");
				String difflevel=pp.getString("difflevel","");
				String questiontype=pp.getString("questype","");
				String ImgUrl="";
				XmlWriter xmlWriter=null;
                                Vector collect=new Vector();
                                Vector str=new Vector();
				String deltype="quesdel";
				String fulltopic=topic+"_"+difflevel+"_"+questiontype;
				String filepath=QuestionBankPath+"/"+username;
                                TopicMetaDataXmlReader topicmetadata=null;
                                topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+fulltopic+".xml");
                                if(questiontype.equals("mcq"))
                                	collect=topicmetadata.getQuesBank_Detail();
                                else
                                	collect=topicmetadata.getQuesBank_Detail1();
                                if(collect!=null)
                                {
                                        for(int i=0;i<collect.size();i++)
                                        {//for
                                        	String questionid =((FileEntry) collect.elementAt(i)).getquestionid();
                                                if(quesid.equals(questionid))
						{
							if(questiontype.equals("mcq"))
							xmlWriter=TopicMetaDataXmlWriter.Ques_BankXml(filepath,fulltopic+".xml");
							else
							xmlWriter=TopicMetaDataXmlWriter.Ques_BankXml1(filepath,fulltopic+".xml");
							if(questiontype.equals("mcq"))
							{
                                                        	TopicMetaDataXmlWriter.appendQues_Bank(xmlWriter,quesid,ques,opt1,opt2,opt3,opt4,Answer,Desc,ImgUrl);
							}
							else
							{
                                                        	TopicMetaDataXmlWriter.appendQues_Bank1(xmlWriter,quesid,ques,Answer,Desc,ImgUrl);
							}
                                                        xmlWriter.writeXmlFile();
                                                        str=DeleteEntry(filepath,fulltopic+".xml",quesid,deltype,questiontype,data);
							data.setMessage(MultilingualUtil.ConvertedString("brih_qus",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeenedit",LangFile));
						}
                                        }
                                }
				Vector str3=Arrangeseq(filepath,fulltopic+".xml",questiontype,deltype,data);
                                setTemplate(data,"call,OLES,View_QB.vm");

			}//try
			catch(Exception e)
                	{
				 ErrorDumpUtil.ErrorLog("The exception in On Line Examination Action - doEditQuestion "+e);
                        	 data.setMessage("Error in action[OLES:doEditQuestion]"+e);
                	}
		}
	/**
 	  * This method is invoked when no button corresponding to 
 	  * Action is found
 	  * @param data RunData
  	  * @param context Context
 	  * @exception Exception, a generic exception
 	  */
	public void doPerform(RunData data,Context context) throws Exception{
		String action=data.getParameters().getString("actionName","");
		context.put("actionName",action);
		if(action.equals("eventSubmit_doUploadQues_Bank"))
			doUploadQues_Bank(data,context);
		else if(action.equals("eventSubmit_doSetPage"))
                        doSettemplate_QB(data,context);
		else if(action.equals("eventSubmit_doInserQuestion"))
                        doInserQuestion(data,context,"More");
		else if(action.equals("eventSubmit_doFinishQuestion"))
                        doInserQuestion(data,context,"Finish");
		else if(action.equals("eventSubmit_dodeletetopic"))
                        doDeleteTopic(data,context);
		else if(action.equals("eventSubmit_doEditQuestion"))
                        doEditQuestion(data,context);
		else
		data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));
			
		
	}
		 /** This method is responsible for creating xml file for topic 
 	           * @param filepath String 
                   * @param topicname String Name of topic in QBR
                   * @param Questiontype String Type of question 
                   * @param Difflevel String
                   * @param CreationDate String
                   * @param QBpath String
                   * @param data RunData instance
                   * @exception Exception, a generic exception
                   */
	 public void xmlwritetopiclist(String filepath,String topicname,String Questiontype,String Difflevel,String Filename,String CreationDate,String QBpath,RunData data)
        {
                try
                {
			ParameterParser pp=data.getParameters();
                        LangFile=data.getUser().getTemp("LangFile").toString();
                        XmlWriter xmlWriter=null;
                        boolean found=false;
                        String topic="";
                        File QBxmls=new File(filepath+"/"+QBpath);
                        TopicMetaDataXmlReader topicmetadata=null;
                        if(!QBxmls.exists())
                        {
				TopicMetaDataXmlWriter.OLESRootOnly(QBxmls.getAbsolutePath());
                        }
                        /**
                        *Checking for  the existing topic
                        *@see TopicMetaDataXmlReader in Util.
                        */
                        else
                        {
                                topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+QBpath);
                                Vector collect=topicmetadata.getQuesBanklist_Detail();
                                if(collect!=null)
                                {
                                        for(int i=0;i<collect.size();i++)
                                        {//for
                                                topic=((FileEntry) collect.elementAt(i)).getTopic();
                                                String Questiontype1=((FileEntry) collect.elementAt(i)).getTypeofquestion();
                                                String Difflevel1=((FileEntry) collect.elementAt(i)).getDifflevel();
                                                if((topicname.equals(topic))&&(Questiontype.equals(Questiontype1))&&(Difflevel.equals(Difflevel1)))
						{
                                                        found=true;
                                                data.setMessage(MultilingualUtil.ConvertedString("brih_This",LangFile) +" "+MultilingualUtil.ConvertedString("GrpmgmtGroup",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile)+" " +"!!");
						}
                                          }//for
                                }//if
                        }//else
                        if(found==false)
                        {
                                xmlWriter=new XmlWriter(filepath+"/"+QBpath);
                                xmlWriter=TopicMetaDataXmlWriter.Ques_BankXmlist(filepath,QBpath);
                                TopicMetaDataXmlWriter.appendQues_Banklist(xmlWriter,topicname,Questiontype,Difflevel,Filename,CreationDate);
                                xmlWriter.writeXmlFile();
                        }
                }//try
                catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in Action[OnlineExaminationSystem] method:xmlwritetopiclist !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
        }//method save
		 /** This method is responsible for writing question in xml file  
 	           * @param filepath String 
                   * @param Questiontype String Type of question 
                   * @param opt1 String 
                   * @param opt2 String 
                   * @param opt3 String 
                   * @param opt4 String 
                   * @param Question String 
                   * @param Answer String
                   * @param Quesid String
                   * @param Description String
                   * @param QBtopicpath String
                   * @param ImgUrl String
                   * @param data RunData instance
                   * @exception Exception, a generic exception
                   */
	 public void xmlwriteQues(String filepath,String Questiontype,String opt1,String opt2,String opt3,String opt4,String Question,String Answer,String Quesid,String Description,String QBtopicpath,String ImgUrl,RunData data)
	{
		try
		{
			
			XmlWriter xmlWriter=null;
			ParameterParser pp=data.getParameters();
			String typeques=pp.getString("typeques","");
			/**
                        *Creating the blank xml for the maintaining the record
                        *and also mainting the record in this xmlfile
                        *@see TopicMetaDataXmlWriter in Util.
                        */
			boolean found=false;
                        TopicMetaDataXmlReader topicmetadata=null;
                        File QBpathxml=new File(filepath+"/"+QBtopicpath);
                        if(!QBpathxml.exists())
                        {
                                TopicMetaDataXmlWriter.OLESRootOnly(QBpathxml.getAbsolutePath());
                        	xmlWriter=new XmlWriter(filepath+"/"+QBtopicpath);
                        }//if
                        /**
                        *Checking for  the existing topic
                        *@see TopicMetaDataXmlReader in Util.
                        */
                        else
                        {
                        	topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+QBtopicpath);
                                Vector collect=topicmetadata.getQuesBank_Detail();
                                if(collect!=null)
                                {
                                	for(int i=0;i<collect.size();i++)
                                        {//for
                                                String Ques=((FileEntry)collect.elementAt(i)).getquestion();
                                                if(Question.equals(Ques))
                                                {
                                                        found=true;
                                                data.setMessage(MultilingualUtil.ConvertedString("brih_This",LangFile) +" "+MultilingualUtil.ConvertedString("GrpmgmtGroup",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile)+" " +"!!");
                                                }
                                          }//for
                                }//if
                        }//else
                        if(found==false)
                        {
                        	if(Questiontype.equals("mcq"))
				{
                        		xmlWriter=new XmlWriter(filepath+"/"+QBtopicpath);
                               		TopicMetaDataXmlWriter.appendQues_Bank(xmlWriter,Quesid,Question,opt1,opt2,opt3,opt4,Answer,Description,ImgUrl);
                       		}
				else
        	        	{
                        		xmlWriter=new XmlWriter(filepath+"/"+QBtopicpath);
                       	        	TopicMetaDataXmlWriter.appendQues_Bank1(xmlWriter,Quesid,Question,Answer,Description,ImgUrl);
                         	}
                		xmlWriter.writeXmlFile();
				data.setScreenTemplate("call,OLES,Oles_QB.vm");
				if((typeques.equals("obo_ques"))||(typeques.equals("imgtypeques")))
				data.setMessage(MultilingualUtil.ConvertedString("oles_questions",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Added",LangFile)+" "+MultilingualUtil.ConvertedString("oles_msg2",LangFile)+" "+MultilingualUtil.ConvertedString("oles_bank",LangFile));
				else
				data.setMessage(MultilingualUtil.ConvertedString("oles_questions",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Uploaded",LangFile)+" "+MultilingualUtil.ConvertedString("oles_msg2",LangFile)+" "+MultilingualUtil.ConvertedString("oles_bank",LangFile));
			}
                }//try
                catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in Action[OnlineExaminationSystem] method:xmlwriteQues !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
	}
		 /** This method is responsible for delete entry in  xml file 
 	           * @param filepath String 
                   * @param xmlfile String 
                   * @param tname String Type of question 
                   * @param deltype String
                   * @param questiontype String
                   * @param data RunData instance
                   * @exception Exception, a generic exception
                   */
	public  Vector DeleteEntry(String filePath,String xmlfile,String tname,String deltype,String questiontype,RunData data)
        {
                Vector Read=null;
                try
                {
                        XmlWriter xmlWriter=null;
                        int seq=-1;
                        String topicname="";
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filePath+"/"+xmlfile);
			if(deltype.equals("topicdel"))
                        	Read=tr.getQuesBanklist_Detail();
                        else
                        {
                        	if(questiontype.equals("mcq"))
                                	Read=tr.getQuesBank_Detail();
                                else
                                	Read=tr.getQuesBank_Detail1();
                        }
                        if(Read != null)
                        {
                                for(int n=0;n<Read.size();n++)
                                {
					if(deltype.equals("topicdel"))
                                        topicname =((FileEntry)Read.elementAt(n)).getTopic();
					else
                                        topicname =((FileEntry)Read.elementAt(n)).getquestionid();
                                        if(tname.equals(topicname))
                                        {
                                                seq=n;
                                                break;
                                        }
                                }
                        }
                        /**
                        Here we deleting the particular entry from the "xml" file
                        */
			if(deltype.equals("topicdel"))
                        	xmlWriter=TopicMetaDataXmlWriter.Ques_BankXmlist(filePath,xmlfile);
			else
			{
				if(questiontype.equals("mcq"))	
                       			xmlWriter=TopicMetaDataXmlWriter.Ques_BankXml(filePath,xmlfile);
				else
                       			xmlWriter=TopicMetaDataXmlWriter.Ques_BankXml1(filePath,xmlfile);
			}
                        xmlWriter.removeElement("Question",seq);
                        xmlWriter.writeXmlFile();
                }//try
                catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method: DeleteEntry in online Examination action!!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
        return Read;
        }//methodDeleteEntry

	public  String getMaxQuesid(String filePath,String xmlfile,String questiontype,RunData data)
        {
		String Quesid="";
		try
		{
			XmlWriter xmlWriter=null;
			Vector Read=new Vector();
                        int tmp=0;
			String quesid="";
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filePath+"/"+xmlfile);
                        if(questiontype.equals("mcq"))
                        	Read=tr.getQuesBank_Detail();
                        else
                                Read=tr.getQuesBank_Detail1();
                        if(Read != null)
                        {
                                for(int n=0;n<Read.size();n++)
                                {
                                        quesid =((FileEntry)Read.elementAt(n)).getquestionid();
					if(tmp>Integer.parseInt(quesid))
					tmp=Integer.parseInt(quesid);
                                }
				tmp=Integer.parseInt(quesid);
                        }
			Quesid=Integer.toString(tmp+1);
                }//try
                catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method: getMaxQuesid in on line examination action!!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
        return Quesid;
	}
	public Vector Arrangeseq(String filepath,String xmlfile,String questiontype,String deltype,RunData data)
	{
		Vector Read=null;
		try
		{
			XmlWriter xmlWriter=null;
                        int tmp=0;
                        String quesid="";
			//int num []= new [5];
			Vector rr=new Vector();
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filepath+"/"+xmlfile);
                        if(questiontype.equals("mcq"))
			{
                                Read=tr.getQuesBank_Detail();
			}
                        else
                        Read=tr.getQuesBank_Detail1();
                        if(Read != null)
                        {
                                for(int n=0;n<Read.size();n++)
                                {
                                        quesid =((FileEntry)Read.elementAt(n)).getquestionid();
					rr.add(quesid);
                                }
				Comparator comparator = Collections.reverseOrder();
				Collections.sort(rr);
				for(int k=0;k<rr.size();k++)
				{
					String qid=(String)rr.get(k); 
                               		 for(int n=0;n<Read.size();n++)
					{
                                        	quesid =((FileEntry)Read.elementAt(n)).getquestionid();
                                        	String ques =((FileEntry)Read.elementAt(n)).getquestion();
                                        	String opt1 =((FileEntry)Read.elementAt(n)).getoptionA();
                                        	String opt2 =((FileEntry)Read.elementAt(n)).getoptionB();
                                        	String opt3 =((FileEntry)Read.elementAt(n)).getoptionC();
                                        	String opt4 =((FileEntry)Read.elementAt(n)).getoptionD();
                                        	String Answer =((FileEntry)Read.elementAt(n)).getAnswer();
                                        	String Desc =((FileEntry)Read.elementAt(n)).getDescription();
                                        	String ImgUrl=((FileEntry)Read.elementAt(n)).getUrl();
					
						if(quesid.equals(qid))
						{
							if(questiontype.equals("mcq"))
								xmlWriter=new XmlWriter(filepath+"/"+xmlfile);
                        				else
							xmlWriter=new XmlWriter(filepath+"/"+xmlfile);
                        				if(questiontype.equals("mcq"))
                        				TopicMetaDataXmlWriter.appendQues_Bank(xmlWriter,quesid,ques,opt1,opt2,opt3,opt4,Answer,Desc,ImgUrl);
                        				else
                        				TopicMetaDataXmlWriter.appendQues_Bank1(xmlWriter,quesid,ques,Answer,Desc,ImgUrl);
                        				xmlWriter.writeXmlFile();
                                			Vector str=DeleteEntry(filepath,xmlfile,quesid,deltype,questiontype,data);
						}
					}
				}
			}
		}
                catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:Arrangeseq in on line Examination Action !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
	return Read;
	}
}

