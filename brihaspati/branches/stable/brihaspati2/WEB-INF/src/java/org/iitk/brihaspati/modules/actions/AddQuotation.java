package org.iitk.brihaspati.modules.actions;
/*
 *@(#) AddQuotation.java    
 *
 *Copyright (c) 2013 ETRG,IIT Kanpur. 
 *All Rights Reserved.
 *
 *Redistribution and use in source and binary forms, with or 
 *without modification, are permitted provided that the following 
 *conditions are met:
 * 
 *Redistributions of source code must retain the above copyright  
 *notice, this  list of conditions and the following disclaimer.
 * 
 *Redistribution in binary form must reproducuce the above copyright 
 *notice, this list of conditions and the following disclaimer in 
 *the documentation and/or other materials provided with the 
 *distribution.
 * 
 * 
 *THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.Program;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iitk.brihaspati.modules.utils.PasswordUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_Quotation;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.io.File;
import java.io.BufferedReader;
import java.io.StringReader;


/**
 * This class contains methods to add,
 * modify and delete quotations from
 * the xml file. 
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a> 
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @date 25-04-2013
 */

public class AddQuotation extends SecureAction
{
	private String LangFile=null;
        private Log log = LogFactory.getLog(this.getClass());
	String str, msg;
	MultilingualUtil mu = new MultilingualUtil();

	/** 
 	 * Adds the quotation to the xml 
	 * @param data RunData instance
  	 * @param context Context instance
  	 */
        public void doInsert(RunData data, Context context) 
        {
                try{
			/**
                          *Getting Language value from temporary variable
                          *getting parameters by using ParameterParser
                          */

			//Criteria crit=new Criteria();
			XMLWriter_Quotation xmlQ = new XMLWriter_Quotation();
			LangFile = data.getUser().getTemp("LangFile").toString();
			//int InstId =Integer.parseInt((String)data.getUser().getTemp("Institute_id"));
			ParameterParser pp = data.getParameters();
			String quot_message = pp.getString("message");
			if(!(quot_message.length()>10))
			{
				try{
					str=mu.ConvertedString("quotation",LangFile)+" "+mu.ConvertedString("brih_empty",LangFile);
                                        data.setMessage(str);
				}
				catch (Exception ex){
                                        msg = "ERROR IN ADDING QUOTATION 1";
                                        ErrorDumpUtil.ErrorLog("Quotation could not be added "+ex);
                                        throw new RuntimeException(msg,ex);
                                }
			}
			else
			{
				String current_date = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
			
				//Calculate quotation id
				String randm_n = PasswordUtil.randmPass();
				String quot_id=EncryptionUtil.createDigest("SHA1",randm_n);
			
				/** 
 				 * getting path for creating Quotation directory
 				 */
				String filepath=TurbineServlet.getRealPath("/Quotation");
                       		File f=new File(filepath);
                      		if(!f.exists())
                        		f.mkdirs();
                        	filepath=filepath+"/Quotation.xml";

				//delete expired quotations
				//int cnt = xmlQ.expiredQuotes(filepath);

				//Check whether quotation already exists
				boolean flag = XMLWriter_Quotation.quoteExists(filepath, quot_message);
				if(flag==false)
				{
					//Add quotation to xml
					String update = XMLWriter_Quotation.QuotationXml(filepath,quot_id,quot_message,current_date);
					if(update.equals("Successfull"))
					{
						try{
	                        	                str=mu.ConvertedString("quot_msg1",LangFile);
                        	       		        data.setMessage(str);
                                	      		//data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                		}	
                                		catch (Exception ex){
                                        		msg = "ERROR IN ADDING QUOTATION 1";
                                        		ErrorDumpUtil.ErrorLog("Quotation could not be added "+ex);
                                       		 	throw new RuntimeException(msg,ex);
                                		}
					}
					else
					{
						try{
                                       		         str=mu.ConvertedString("quot_msg2",LangFile);
                                               		 data.setMessage(str);
						}
                                       		catch (Exception ex){
                                              		msg = "ERROR IN ADDING QUOTATION 2";
                                                	ErrorDumpUtil.ErrorLog("Quotation could not be added "+ex);
                                                	throw new RuntimeException(msg,ex);
                                        	}
					}
				}
				else
				{
					try{
						str=mu.ConvertedString("quot_msg3",LangFile);
                               		         data.setMessage(str);
					}
                                	catch (Exception ex){
                                       		 msg = "ERROR IN ADDING QUOTATION 3";
                                       		 ErrorDumpUtil.ErrorLog("Quotation could not be added "+ex);
                                       		 throw new RuntimeException(msg,ex);
                                	}
				}
				//ErrorDumpUtil.ErrorLog("----------message----"+quot_message);
					setTemplate(data,"call,Quotation,Quotation.vm");
			}
		}//try
		
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("An error occurred while adding quotation !!"+e);
		}
	}

	/** 
 	 * Updates the selected quotation
 	 * in the xml
         * @param data RunData instance
         * @param context Context instance
         */
	public void doUpdate(RunData data, Context context)
	{
		try{
			LangFile = data.getUser().getTemp("LangFile").toString();	
			String filePath=TurbineServlet.getRealPath("/Quotation");
			filePath=filePath+"/Quotation.xml";
			ParameterParser pp = data.getParameters();
			String quoteId = pp.getString("editQuotId","");
			String quoteValue = pp.getString("message");
			if(!(quoteValue.length()>10))
                        {
                                try{
                                        str=mu.ConvertedString("quotation",LangFile)+" "+mu.ConvertedString("brih_empty",LangFile);
                                        data.setMessage(str);
                                }
                                catch (Exception ex){
                                        msg = "ERROR IN ADDING QUOTATION 1";
                                        ErrorDumpUtil.ErrorLog("Quotation could not be updated "+ex);
                                        throw new RuntimeException(msg,ex);
                                }
                        }
                        else
                        {
				String updateMsg = XMLWriter_Quotation.UpdateQuotationXml(filePath,quoteId,quoteValue);
				if(updateMsg.equals("Successful"))
				{
					try{
                       	        	        str=mu.ConvertedString("quot_msg4",LangFile);
                       		                data.setMessage(str);
                                	}
                             		catch (Exception ex){
                                        	msg = "ERROR IN UPDATING QUOTATION";
                                        	ErrorDumpUtil.ErrorLog("Quotation could not be updated "+ex);
                                        	throw new RuntimeException(msg,ex);
                                	}
				}			
			}
		}//try
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("An error occurred while updating quotation !!"+e);
		}
	
	}

	/**
 	 * Deletes the selected quotation 	
 	 * from the xml
 	 * @param data RunData instance
 	 * @param context Context instance
	 */
	public void doDelete(RunData data, Context context)
	{
		try{
			LangFile = data.getUser().getTemp("LangFile").toString();
                        String filePath=TurbineServlet.getRealPath("/Quotation");
                        filePath=filePath+"/Quotation.xml";
                        ParameterParser pp = data.getParameters();
                        String quoteId = pp.getString("quotid","");
                        String quoteValue = pp.getString("message");
                        String updateMsg = XMLWriter_Quotation.deleteQuotation(quoteId,filePath);
                        if(updateMsg.equals("Successful"))
			{
				try{
                                        str=mu.ConvertedString("quot_msg5",LangFile);
                                        data.setMessage(str);
                                }
                                catch (Exception ex){
                                        msg = "ERROR IN DELETING QUOTATION";
                                        ErrorDumpUtil.ErrorLog("Quotation could not be deleted "+ex);
                                        throw new RuntimeException(msg,ex);
                                }
			}                        
                }
                catch(Exception e)
                {
			ErrorDumpUtil.ErrorLog("An error occurred while deleting quotation !!"+e);
                }

	}

	/**
         * Default action to perform if the specified action
         * cannot be executed.
         */
	public void doPerform(RunData data,Context context) throws Exception
	{
        	String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_doSubmit"))
                	doInsert(data,context);
		else if(action.equals("eventSubmit_doUpdate"))
                	doUpdate(data,context);
		else if(action.equals("eventSubmit_doDelete"))
                	doDelete(data,context);
                else
                	data.setMessage("Cannot find the button");
				
       }
}
	
