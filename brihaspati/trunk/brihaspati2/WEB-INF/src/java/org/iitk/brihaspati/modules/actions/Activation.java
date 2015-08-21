package org.iitk.brihaspati.modules.actions;

/**
 * @(#)Activation.java  
 *  
 *  Copyright (c) 2012, 2015 ETRG,IIT Kanpur. 
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


import java.util.List;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.UserPref;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.ForgotpassPeer;
import org.iitk.brihaspati.om.Forgotpass;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;		
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;	
import org.iitk.brihaspati.modules.utils.XmlWriter;
import java.io.File;

/**
 * Action class for updating the activation status 
 * of user, when user clicks activation link.
 *  @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 *  @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 *  modified date : 01-10-2012, 15-10-2012,06-11-2012(Priyanka)
 */

public class Activation extends VelocityAction{
        private Log log = LogFactory.getLog(this.getClass());
	String str, msg;	
	MultilingualUtil mu = new MultilingualUtil();	

	/**
 	 * This method is called when user clicks on activation link
 	 * @param data RunData instance
 	 * @param context Context instance
 	 */
	public void doPerform(RunData rundata,Context context) throws Exception
        {
                System.gc();
                String u_mode=rundata.getParameters().getString("mode");         
	
		if(u_mode.equals("forgotpass")){
			changePass(rundata,context);
		}	
		if(u_mode.equals("cnfrm_mail"))
		{
			EmailVerification everify = new EmailVerification(rundata);
			everify.profileUpdation(rundata);
		}			
                if(u_mode.equals("cnfrm_u") || u_mode.equals("cnfrm_c") || u_mode.equals("cnfrm_i"))
		{
                        mailConfirmation(rundata,context);
                }
                if(u_mode.equals("act") || u_mode.equals(""))
                {
                        mailActivation(rundata,context);
                }
        }
	
	public void changePass(RunData data, Context context)
	{
		Criteria crit = null;
                String lang=data.getParameters().getString("lang");
                String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
                String e_mail=data.getParameters().getString("email");
                String a_key=data.getParameters().getString("key");
                String u_mode=data.getParameters().getString("mode");
		
		try{
                        // select  key corresponding to user id in d_key
                        crit = new Criteria();
                        crit.add(ForgotpassPeer.USER_NAME,e_mail);
                        List list = ForgotpassPeer.doSelect(crit);
                        String d_key =((Forgotpass)list.get(0)).getRkey();
                        if ((a_key == d_key) || (a_key.equalsIgnoreCase(d_key)))
                        {
				context.put("rky",a_key);
				context.put("uid",UserUtil.getUID(e_mail));
				setTemplate(data,"UpdatePass_User.vm");
                        }
			else{
				try{
                                              str=MultilingualUtil.ConvertedString("forgotPwd_msg11",LangFile);
                                              data.setMessage(str);
                                              data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                        }
                                        catch (Exception ex){
                                                String msg1 = "ERROR IN getting / matching data";
                                                ErrorDumpUtil.ErrorLog("User's data is not matched "+ex);
						data.setMessage("Error in chnage pass forgot pass "+ msg1 +" and Ex "+ex);
						setTemplate(data,"BrihaspatiLogin.vm");
                 //                                throw new RuntimeException(msg1,ex);
                                        }
	
			}
		}
		catch(IndexOutOfBoundsException e){
                                String message1 = "Error in matching data (either user name or key ) ";
				data.setMessage("Error in chnage pass forgot pass "+ message1 +" and Ex "+e);
				setTemplate(data,"BrihaspatiLogin.vm");
                                //throw new RuntimeException(message1, e);
                        }
                catch(Exception ex){
                                data.setMessage("Error in setting Key:- "+ex);
				setTemplate(data,"BrihaspatiLogin.vm");
                        }
	}

	public void mailActivation(RunData data, Context context)
        {
		Criteria crit = null;
		String lang=data.getParameters().getString("lang");
                String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
		String e_mail=data.getParameters().getString("email");
                String a_key=data.getParameters().getString("key");
                String u_mode=data.getParameters().getString("mode");
		// Get user id
		int cmpid=-1;
	        int uid=UserUtil.getUID(e_mail);
	        boolean Result= uid == cmpid;
              
		if(Result){
                          String url1=data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm";
              
                          try{
       				str=MultilingualUtil.ConvertedString("usr_doesntExist",LangFile);
                                data.setMessage(str);
	                        //data.setMessage("You are not registered in Brihaspati LMS");
                              	data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                          }
                          catch (Exception ex){
				String msg0 = "Error in activation	";
				ErrorDumpUtil.ErrorLog("User not registered in Brihaspati LMS inside 1st catch"+ex);
	                       	  throw new RuntimeException(msg0,ex);
			 }

                  }
		 else{

			try{
			// select activation key corresponding to user id in d_key
			crit = new Criteria();
			crit.add(UserPrefPeer.USER_ID,uid);
			List list = UserPrefPeer.doSelect(crit);
			String d_key =((UserPref)list.get(0)).getActivation();
		
				if ((a_key == d_key) || (a_key.equalsIgnoreCase(d_key)))
				{
					//Update the database to set Activation field
					crit = new Criteria();
					crit.add(UserPrefPeer.USER_ID,uid);
					crit.add(UserPrefPeer.ACTIVATION,"ACTIVATE");
					UserPrefPeer.doUpdate(crit);
	
					try{
						str=MultilingualUtil.ConvertedString("act_login",LangFile);
                                                data.setMessage(str);
                		                data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                          		}
                          		catch (Exception ex){
						msg = "ERROR IN ACCOUNT ACTIVATION 1";
						ErrorDumpUtil.ErrorLog("User's account  not activated inside 2nd catch "+ex);
                          			throw new RuntimeException(msg,ex);
					}
				}
				
				else
				{
		
					try{
					      str=MultilingualUtil.ConvertedString("oopsAct_msg",LangFile);
                                              data.setMessage(str);
                                              data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                        }
                                        catch (Exception ex){
						String msg1 = "ERROR IN ACCOUNT ACTIVATION 1";
                                                ErrorDumpUtil.ErrorLog("User's account  not activated inside 3rd catch "+ex);
						 throw new RuntimeException(msg1,ex);
				        }

				}	
 			} 
			catch(IndexOutOfBoundsException e){
				String message1 = "Error in activation	";
				throw new RuntimeException(message1, e);
			}
			catch(Exception ex){
				data.setMessage("Error in setting Activation Key:- "+ex);
			}

		 }
	}//method

	public void mailConfirmation(RunData data, Context context)
	{
		String e_mail=data.getParameters().getString("email");
                String a_key=data.getParameters().getString("key");
		String mode=data.getParameters().getString("mode");
		String lang=data.getParameters().getString("lang");
                String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
		String path="";
		File filepath=null;
		File file1=null;
		boolean set;
		XmlWriter xmlWriter=null;
		
		if(mode.equals("cnfrm_i"))
		{
			// Getting Activation key FROM xml_file 
			/** getting path for InstituteRegistration xml*/
			String filePath=TurbineServlet.getRealPath("/InstituteRegistration");
			filePath=filePath+"/InstituteRegistrationList.xml";
			set = XMLWriter_InstituteRegistration.SetFlag(filePath,e_mail,a_key);
	
			if(set)
			{
				ParameterParser pp = data.getParameters();
				pp.setString("mode","act");
				InstituteRegistration instituteregister = new InstituteRegistration();
				instituteregister.InstituteRegister(data,context);
		
			}
			else
			{
	                       	 try{
					str=MultilingualUtil.ConvertedString("oopsCnfrm_msg",LangFile);
                                        data.setMessage(str);
                                	//data.setMessage("Oops ! Your email id could not be confirmed. Please recheck the Confirmation link.");
                                	data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?"+str);
                           	 }
                         	catch (Exception ex){
                                	String msg1 = "ERROR IN EMAIL ID CONFIRMATION 1";
                                	ErrorDumpUtil.ErrorLog("User's account  not confirmed inside 3rd catch "+ex);
                                	throw new RuntimeException(msg1,ex);
                            	}
			}
		}//1st if
		
		if(mode.equals("cnfrm_c"))
		{
			try{
				// Getting Activation key FROM xml_file 
			//	XmlWriter xmlWriter=null;
        			path=data.getServletContext().getRealPath("/OnlineUsers");
	                        filepath=new File(path);
        		        if(!filepath.exists())
                		{
                        		filepath.mkdirs();
                		}
                		file1=new File(path+"/courses.xml");

                		if(!file1.exists())
                		{
                        		TopicMetaDataXmlWriter.WriteOnlineReqRootOnly(file1.getAbsolutePath());
                        		xmlWriter=new XmlWriter(path+"/courses.xml");
                		}
                	//	TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(path+"/OnlineUser.xml");

	                //	String path = data.getServletContext().getRealPath("/OnlineUsers");
                      //  	TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(path+"/courses.xml");
                        	set = TopicMetaDataXmlWriter.WriteXml_OnlineCourse(path,"/courses.xml",e_mail,a_key);
			//	String LangFile = MultilingualUtil.LanguageSelectionForScreenMessage("english");			
		
                        if(set)
                        {
                               // String mode="act";
                                ParameterParser pp = data.getParameters();
                                pp.setString("mode","act");
				OnlineRegistration onlineregister = new OnlineRegistration();
                                onlineregister.CourseRegister(data,context);
                                String str=MultilingualUtil.ConvertedString("online_msg6",LangFile);
                                data.setMessage(str);
                                data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                              
                        }
                        else
                        {
                      		 str=MultilingualUtil.ConvertedString("oopsCnfrm_msg",LangFile);
                                 data.setMessage(str); 
                                 //data.setMessage("Oops ! Your email id could not be confirmed. Please recheck the Confirmation link.");
                                 data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                        }

			}//try
			catch(Exception e)
                        {
                                msg = "ERROR IN EMAIL VERIFICATION ";
                                ErrorDumpUtil.ErrorLog("User's email  not confirmed inside 2nd catch "+e);
                                throw new RuntimeException(msg,e);
                        }//catch


		}//2nd if
		
		if (mode.equals("cnfrm_u"))
		{

			try{
				//Getting Activation key FROM xml_file
			//	XmlWriter xmlWriter=null;
				path=data.getServletContext().getRealPath("/OnlineUsers");
	                        filepath=new File(path);
		                if(!filepath.exists())
                		{
                        		filepath.mkdirs();
                		}
                		file1=new File(path+"/OnlineUser.xml");

                		if(!file1.exists())
                		{
                        		TopicMetaDataXmlWriter.WriteOnlineReqRootOnly(file1.getAbsolutePath());
                        		xmlWriter=new XmlWriter(path+"/OnlineUser.xml");
                		}
                	//	TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(path+"/OnlineUser.xml");

			//	String path = data.getServletContext().getRealPath("/OnlineUsers");
			//	TopicMetaDataXmlWriter topicmetadata=new TopicMetaDataXmlReader(path+"/OnlineUser.xml");
				set = TopicMetaDataXmlWriter.WriteXml_OnlineUser(path,"/OnlineUser.xml",e_mail,a_key);

                        	if(set)
                        	{
                               	// String mode="act";
                                	ParameterParser pp = data.getParameters();
                                //	pp.add("mode","act");
					pp.setString("mode","act");
		//			ErrorDumpUtil.ErrorLog("activation	"+pp.getString("mode"));
                                	OnlineRegistration onlineregister = new OnlineRegistration();
                                	onlineregister.UserRegister(data,context);
				  	String str=MultilingualUtil.ConvertedString("online_msg5",LangFile);
                                  	data.setMessage(str);
                                	data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);

                        	}
                        	else
                        	{
                  //              	 ErrorDumpUtil.ErrorLog("INSIDE ELSE.....");
                 			str=MultilingualUtil.ConvertedString("oopsCnfrm_msg",LangFile);
                                        data.setMessage(str);
                                 	//data.setMessage("Oops ! Your email id could not be confirmed. Please recheck the Confirmation link.");
                                 	data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                        	}
			}//try        
			catch (Exception ex){
                                        String msg1 = "ERROR IN EMAIL VERIFICATION 1";
                                        ErrorDumpUtil.ErrorLog("User's email  not confirmaed inside 3rd catch "+ex);
                                        throw new RuntimeException(msg1,ex);
                        }//catch
                        	
		}//3rd if
}//method

}//class
