package org.iitk.brihaspati.modules.actions;

/*
 * @(#)ProfileUser.java	
 *
 *  Copyright (c) 2006-2007, 2010, 2012 ETRG,IIT Kanpur. 
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
 *  
 */
import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer; 

import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.om.security.peer.TurbineUserPeer; 
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.apache.turbine.services.security.TurbineSecurity; 
import org.apache.commons.fileupload.FileItem; 
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;

import org.iitk.brihaspati.om.UserConfigurationPeer; 
import org.iitk.brihaspati.modules.utils.UserUtil;  
import org.iitk.brihaspati.modules.utils.CourseProgramUtil;  
import org.iitk.brihaspati.modules.utils.StringUtil;  
import org.iitk.brihaspati.modules.actions.SecureAction;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
 
/**
 *
 * Action class for Changing a profile of User in the system.
 *
 * @author <a href="mailto:singhnk@iitk.ac.in ">Nagendra Kumar Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com ">Jaivir Singh</a>
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in ">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:satyapal@iitk.ac.in ">Satyapal Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com ">Richa Tandon</a>
 * @modified date:3-11-2010,23-12-2010
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 */

public class ProfileUser extends SecureAction
{
	public boolean isAuthorized(RunData data)
	{
		boolean authorised=false;
		try
		{
		User user=data.getUser();
		if(user.hasLoggedIn() && !user.getName().equals("guest"))
		{
			authorised=true;
		}
		else
		{
			data.setScreenTemplate(Turbine.getConfiguration().getString("screen.login"));
			authorised=false;
		}
		}
		catch(Exception e){}
		return authorised;
	}		
    	/**
      	 * Update EmailId of User in TurbineUser
      	 * @param data RunData
      	 * @param context Context
      	 */
    	public void doUpdate(RunData data, Context context) throws Exception { 
		ParameterParser pp=data.getParameters();  	   
	  	String fName=pp.getString("FName");
	  	String lName=pp.getString("LName");
	  	String eMail=pp.getString("EMAIL");
	  	String qid=pp.getString("que","");
          	String ans=pp.getString("ANSWER");	 		
		String name=fName+lName;  
		String address=pp.getString("address");
		String state=pp.getString("state");
		String country=pp.getString("country");
		String department=pp.getString("department");
		String designation=pp.getString("designation");

		String officeno=pp.getString("Offprefix","x")+"-"+pp.getString("Offccode","x")+"-"+pp.getString("Offrcode","x")+"-"+pp.getString("Offphnumber","x");
                String mobileno=pp.getString("Mobprefix","x")+"-"+pp.getString("Mobccode","x")+"-"+pp.getString("Mobrcode","x")+"-"+pp.getString("Mobphnumber","x");
                String homeno=pp.getString("Homeprefix","x")+"-"+pp.getString("Homeccode","x")+"-"+pp.getString("Homercode","x")+"-"+pp.getString("Homephnumber","x");
                String otherno=pp.getString("Othprefix","x")+"-"+pp.getString("Othccode","x")+"-"+pp.getString("Othrcode","x")+"-"+pp.getString("Othphnumber","x");
		
		String offdirectory=pp.getString("Offdirectory");
		String mobdirectory=pp.getString("Mobdirectory");
		String homedirectory=pp.getString("Homedirectory");
		String othdirectory=pp.getString("Othdirectory");
	  	int count = Integer.parseInt(pp.getString("count"));
	  	//ErrorDumpUtil.ErrorLog("count in profile action-------->"+count);
	  	User user=data.getUser();
         	String mail_msg="";
          	String LangFile=(String)user.getTemp("LangFile");


	        		
	  	Criteria crit=new Criteria();
		if(!eMail.equals("")) {
			//int i=eMail.indexOf("@");
			if(eMail.indexOf("@")<0) {
                		String local_domain=Turbine.getConfiguration().getString("local.domain.name","");
                        	if (local_domain.equals(""))
				{
          				eMail="";
					//mail_msg=MultilingualUtil.ConvertedString("Profile_domain",LangFile);
                                	//data.setMessage(mail_msg);
					//setTemplate(data,"call,UserMgmt_User,Profile.vm");
                        	}
                        	else
				{
                        		eMail=eMail.concat(local_domain);
                        	}
          		}
	  	}
          	String loginName=user.getName();
	  	int uid=UserUtil.getUID(loginName);  	
	  	StringUtil S=new StringUtil();
	  /**
            * Checking for the illegal characters in the data entered
            * If found then the error message is given
            */
	  String rollmsg="";
	  String msg1=""; 
          if(S.checkString(fName)==-1 && S.checkString(lName)==-1)//if1
	  {
		user.setFirstName(fName);
		user.setLastName(lName);
		user.setEmail(eMail);
		TurbineSecurity.saveUser(user);

          	String imagesRealPath=TurbineServlet.getRealPath("/images");	
	  	FileItem fileItem=pp.getFileItem("PHOTO");
		String configuration=pp.getString("Conf");
		 String tconf=pp.getString("TaskConf");
                int taskconf=Integer.parseInt(tconf);
          	if(fileItem.getSize() >0)
 		{		
	  		long size=fileItem.getSize();	
	  		Long size1=new Long(size);
	  		byte Filesize=size1.byteValue();		

			String PhotoName=new String();
                        String temp=fileItem.getName();
                        int index=temp.lastIndexOf("\\");
                        String tempFile=temp.substring(index+1);
                        StringTokenizer st=new StringTokenizer(tempFile,".");
                        String fileExt=null;
                        for(int a=0;st.hasMoreTokens();a++)
                        { //first 'for' loop
                                fileExt=st.nextToken();
                                String uName=user.getName();
                                PhotoName=uName;
                                context.put("ImageName1",Byte.toString(Filesize));
                        }
	  	      if(fileExt.equals("jpg")|| fileExt.equals("gif")|| fileExt.equals("png"))//if3
			{
       				int i=Integer.parseInt(Byte.toString(Filesize));
				if(i>0 && i<100)//if4
				{
		        		/**
	        			 * This is the updation in table USER_CONFIGURATION in the database.
		        	         */ 	
        	        		crit=new Criteria();
	                		crit.add(UserConfigurationPeer.USER_ID,uid);
        	       	 		crit.add(UserConfigurationPeer.PHOTO,PhotoName);
        	       	 		crit.add(UserConfigurationPeer.LIST_CONFIGURATION,configuration);
                			crit.add(UserConfigurationPeer.QUESTION_ID,qid);
                			crit.add(UserConfigurationPeer.ANSWER,ans);
                			UserConfigurationPeer.doUpdate(crit);



				       /**
	        			* This is the updation in table TURBINE_USER in the database.
				        */
					 	
					crit=new Criteria();
                			crit.add(TurbineUserPeer.FIRST_NAME,fName);
	                		crit.add(TurbineUserPeer.LAST_NAME,lName);
        	        		crit.add(TurbineUserPeer.EMAIL,eMail);
                			TurbineUserPeer.doUpdate(crit);
				       /**
	        			* This is the updation in table STUDENT_ROLLNO in the database.
				        */
					for(int k=1;k<=count;k++) 
					{
						String InstName = pp.getString("instName"+k,"");
						String PrgName = pp.getString("prg"+k,"");
						String rollno = pp.getString("rollno"+k,"").trim();
						/**
				                  * check if rollno have any special character then return message
		                                  */
						if(StringUtil.checkString(rollno) != -1)
			                        {
							data.addMessage(MultilingualUtil.ConvertedString("quiz_msg8",LangFile));
			                                data.addMessage(MultilingualUtil.ConvertedString("ProxyuserMsg3",LangFile));
			                                return;
			                        }
					
					List Rlrecord = new Vector();
	                                String RlNo ="";
	                                Rlrecord = CourseProgramUtil.getUserRollNo(loginName);	
					//Rollno = CourseProgramUtil.getUserPrgRollNo(loginName,PrgName,InstName);
	                                if((Rlrecord.size()>0)&&(!rollno.equals(""))){
        	                                StudentRollno element = (StudentRollno)Rlrecord.get(0);
                	                        int id = element.getId();
                        	                RlNo = element.getRollNo();
					}
					if(!rollno.equals(RlNo)){
                        	                //rollmsg=CourseProgramUtil.InsertRollNo(loginName,rollno,LangFile);
                        	                //rollmsg=CourseProgramUtil.InsertPrgRollNo(loginName,rollno,PrgName,InstName,LangFile);
                                	}
					}
					
					File filePath=new File(imagesRealPath+"/Photo/");
					filePath.mkdirs();
					filePath=new File(filePath+"/"+PhotoName);
					fileItem.write(filePath);
					msg1=MultilingualUtil.ConvertedString("usr_prof",LangFile);
		  			data.setMessage(msg1);
				}//close4
				else
				{
					msg1=MultilingualUtil.ConvertedString("Profile_PhotoMsg1",LangFile);
	                                data.setMessage(msg1);
					setTemplate(data,"call,UserMgmt_User,Profile.vm");
                	        }
	                } //close3      
			else     	
           		{     
				msg1=MultilingualUtil.ConvertedString("Profile_PhotoMsg2",LangFile);
				data.setMessage(msg1); 
				setTemplate(data,"call,UserMgmt_User,Profile.vm");
			}
		}//close2
       		else
                {	 
                		crit=new Criteria();
                		crit.add(UserConfigurationPeer.USER_ID,uid);
        	       	 	crit.add(UserConfigurationPeer.LIST_CONFIGURATION,configuration);
				crit.add(UserConfigurationPeer.TASK_CONFIGURATION,taskconf);
                		crit.add(UserConfigurationPeer.QUESTION_ID,qid);
                		crit.add(UserConfigurationPeer.ANSWER,ans);
               	 		UserConfigurationPeer.doUpdate(crit);
				
				crit=new Criteria();
                		crit.add(TurbineUserPeer.FIRST_NAME,fName);
                		crit.add(TurbineUserPeer.LAST_NAME,lName);
                		crit.add(TurbineUserPeer.EMAIL,eMail);
                		TurbineUserPeer.doUpdate(crit);

		//-------------------------Telephone Directory-------------------------------
		/* Insert or update value into database
		* Insert value in table "TELEPHONE_DIRECTORY" when corresponding data are not present
		* and update value in table "TELEPHONE_DIRECTORY" when corresponding data are present
		*/
				Vector instid1=InstituteIdUtil.getAllInstId(uid);
				String str11="";
				String instid="";
				String str33="";
				try{
					for(int j=0;j<=instid1.size();j++){
						str11=instid1.elementAt(j).toString();
						if(!str11.equals(str33)){
							instid=instid+"/"+str11;
						}
					str33=str11;
					}
				}catch(Exception ex){ErrorDumpUtil.ErrorLog("The error in getting institute Id in Profile User Action "+ex);};


				List li=null;
				Criteria tele = new Criteria();
				
                                tele.add(TelephoneDirectoryPeer.USER_ID,uid);
				{
                                               li = TelephoneDirectoryPeer.doSelect(tele);
                                                if(li.size()>0) {
                                                        TelephoneDirectory element=(TelephoneDirectory)(li.get(0));
                                                        int id=(element.getId());
                                                        tele.add(TelephoneDirectoryPeer.ID,id);
                                                }
                                }
				
                                tele.add(TelephoneDirectoryPeer.MAIL_ID,eMail);
                                tele.add(TelephoneDirectoryPeer.NAME, name);
                                tele.add(TelephoneDirectoryPeer.ADDRESS, address);
                                tele.add(TelephoneDirectoryPeer.STATE, state);
                                tele.add(TelephoneDirectoryPeer.COUNTRY, country);
                                tele.add(TelephoneDirectoryPeer.DEPARTMENT, department);
                                tele.add(TelephoneDirectoryPeer.DESIGNATION, designation);
				tele.add(TelephoneDirectoryPeer.INSTITUTE_ID,instid);

                                if(offdirectory.equals("Public")){
                                        String PubOffNo="1-"+officeno;
                                        tele.add(TelephoneDirectoryPeer.OFFICE_NO, PubOffNo);
                                        }
                                        else if(offdirectory.equals("Protected")){
                                        String ProOffNo="2-"+officeno;
                                        tele.add(TelephoneDirectoryPeer.OFFICE_NO, ProOffNo);
                                        }
                                        else{
                                        String PriOffNo="3-"+officeno;
                                        tele.add(TelephoneDirectoryPeer.OFFICE_NO, PriOffNo);
                                        }
                                        if(mobdirectory.equals("Public")){
                                        String PubMobNo="1-"+mobileno;
                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, PubMobNo);
                                        }
                                        else if(mobdirectory.equals("Protected")){
                                        String ProMobNo="2-"+mobileno;
                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, ProMobNo);
                                        }
                                        else{
                                        String PriMobNo="3-"+mobileno;
                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, PriMobNo);
                                        }
                                        if(homedirectory.equals("Public")){
                                        String PubHomeNo="1-"+homeno;
                                        tele.add(TelephoneDirectoryPeer.HOME_NO, PubHomeNo);
                                        }
                                        else if(homedirectory.equals("Protected")){
					String ProHomeNo="2-"+homeno;
                                        tele.add(TelephoneDirectoryPeer.HOME_NO, ProHomeNo);
                                        }
                                        else{
                                        String PriHomeNo="3-"+homeno;
                                        tele.add(TelephoneDirectoryPeer.HOME_NO, PriHomeNo);
                                        }
                                        if(othdirectory.equals("Public")){
                                        String PubOthNo="1-"+otherno;
                                        tele.add(TelephoneDirectoryPeer.OTHER_NO, PubOthNo);
                                        }
                                        else if(othdirectory.equals("Protected")){
                                        String ProOthNo="2-"+otherno;
                                        tele.add(TelephoneDirectoryPeer.OTHER_NO, ProOthNo);
                                        }
                                        else{
                                        String PriOthNo="3-"+otherno;
                                        tele.add(TelephoneDirectoryPeer.OTHER_NO, PriOthNo);
                                        }
					if(li.size()==0)
	                                	TelephoneDirectoryPeer.doInsert(tele);
					else
						TelephoneDirectoryPeer.doUpdate(tele);
			//---------------------------Telephone Directory----------------------------
				/**
				 * Getting values of template from parameterparser 
				 */	
				for(int k=1;k<=count;k++)
                                {
					String InstName = pp.getString("instName"+k,"");
                        	        String PrgName = pp.getString("prg"+k,"");
                                	String rollno = pp.getString("rollno"+k,"").trim();
					/**
                                         * check if rollno have any special character then return message
                                         */
					if(StringUtil.checkString(rollno) != -1)
                        		{
						data.addMessage(MultilingualUtil.ConvertedString("quiz_msg8",LangFile));
                                                data.addMessage(MultilingualUtil.ConvertedString("ProxyuserMsg3",LangFile));
                                                return;
		                        }	

				/**
 				 * Getting all detail of username from database 	
 				 */ 		
				List Rlrecord = new Vector();
				String RlNo ="";
                                Rlrecord = CourseProgramUtil.getUserRollNo(loginName);
				//Rollno = CourseProgarmUtil.getUserPrgRollNo(loginName,PrgName,InstName);
				//ErrorDumpUtil.ErrorLog("Rollno record in action file------>"+Rlrecord);
				if((Rlrecord.size()>0)&&(!rollno.equals(""))){
               		                StudentRollno element = (StudentRollno)Rlrecord.get(0);
                        	        int id = element.getId();
                        	        RlNo = element.getRollNo();
                                	/*crit=new Criteria();
	                                crit.add(StudentRollnoPeer.ID,id);
        	                        //crit.add(StudentRollnoPeer.EMAIL_ID,loginName);
                	                crit.add(StudentRollnoPeer.ROLL_NO,rollno);
                        	        StudentRollnoPeer.doUpdate(crit);*/
				}
				/**
 				 * Check if existing rollno is not equal to present rollno 
 				 * then insert value in database
 				 */ 		
				if(!rollno.equals(RlNo)){
					//rollmsg=CourseProgramUtil.InsertRollNo(loginName,rollno,LangFile);
					//rollmsg=CourseProgramUtil.InsertPrgRollNo(loginName,rollno,PrgName,InstName,LangFile);
				}
				}
				msg1=MultilingualUtil.ConvertedString("Profile_PhotoMsg3",LangFile);
           			data.setMessage(msg1+" "+rollmsg);

                }
	  }//if1	
          else
	  {
		msg1=MultilingualUtil.ConvertedString("ProxyuserMsg3",LangFile);
	  	data.setMessage(msg1); 
		setTemplate(data,"call,UserMgmt_User,Profile.vm");
	  }	 
}
    /**
     * This is used in the event that the doPerform
     * above fails.
     */
     
	public void doPerform(RunData data, Context context) throws Exception {
		String action=data.getParameters().getString("actionName");
		if(action.equals("eventSubmit_doUpdate")){
			doUpdate(data,context);
		}
		else{
	        	data.setMessage("Can't find the method in Profile User Action!");
		}
    	}
}
