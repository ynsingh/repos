package org.iitk.brihaspati.modules.screens;

/*
 * @(#)Index.java	
 *
 *  Copyright (c) 2004, 2009,2010 ETRG,IIT Kanpur. 
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
*/

import java.util.Date;
import java.util.Vector;
import java.util.List;
import java.text.DateFormat;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.om.UserConfiguration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import org.apache.turbine.services.session.TurbineSession;
//import org.apache.turbine.services.session.SessionTool;

/**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @ mdified date 05-05-2010,13-07-2010,5-10-2010(Smita),23-12-2010
 * @ mdified date 04-04-2011 (Shaista)
 */

public class Index extends SecureScreen{
	public void doBuildTemplate( RunData data, Context context ){
		try{
                        /*
                         * getting the current user 
			 * & check current user is superAdmin,InsAdmin,Instructor,student or guest
                         */
			Vector instNameList = new Vector();
			String instName = "";
			User user=data.getUser();
                        String username=user.getName();
                        int uid=UserUtil.getUID(username);
			Vector cId=new Vector();
			if(uid==1){
                                cId.add("mainA");
			}else if(uid==0){
                                cId.add("guest");
                        }
			else{
				/**
				 * Getting all institute id in a Vector in which logged in user is registered.
				 * Adding unique name of the institute according to institute id in a Vector.
				 * Putting the vector of institute name in context.
				**/
				cId=(InstituteIdUtil.getAllInstId(uid));
				for(int inst = 0; inst < cId.size(); inst ++){
					instName = InstituteIdUtil.getIstName(Integer.parseInt(cId.get(inst).toString()));
					if(!instNameList.contains(instName)){
						instNameList.add(instName);
					}
				}
				context.put("instNameList",instNameList);
			}
			/**
			 * code for Active User list
                         */
			List < String >  actlst = new ArrayList < String >  (  ) ;
                        Collection au=TurbineSession.getActiveUsers();
                        actlst.addAll(au);
			Iterator it=au.iterator();
                        Vector ve=new Vector();
                                while(it.hasNext()){
                                       String ss=it.next().toString();
                                       ve.add(ss.substring(0,(ss.length()-3)));
                                }

                        //send list to vm
                        /*context.put("activelist", actlst);
			String role=data.getParameters().getString("role");
			context.put("role",role);*/
			 /**
                         * code for Active User list With Time
                         */
			Vector ve1=new Vector();
			Vector ve2=new Vector();

                        Collection aul=TurbineSession.getActiveSessions();
                         for(Iterator i=aul.iterator();i.hasNext();)
                         {
				HttpSession session=(HttpSession) i.next();
                                User un =TurbineSession.getUserFromSession(session);
                                String u=un.getName();
                                //check for current session is not null
                                if(ve.contains(u)){
                                	int userid=UserUtil.getUID(u);
				        Vector lId=new Vector();
					if((userid!=1) && (userid!=0)){
						  lId=InstituteIdUtil.getAllInstId(userid);
						}
                                //time calculation for given userid
					String time=InstituteIdUtil.getTimeCalculation(userid);
					for (int x = 0; x < cId.size(); x++)
				 	{
						Object e=cId.get(x);
						if(e.equals("mainA")){
							  String h=u+" "+"("+time+")";
                                        		ve2.add(h);
						}else if(e.equals("guest")){
							if(u.equals(e)){
								String h=u+" "+"("+time+")";
                                                 		ve2.add(h);
                                       			}
						}else if(lId.contains(e)){
							String h=u+" "+"("+time+")";
							Vector ve3=new Vector();
                                                	ve3.add(h);
							for(int m=0;m<ve3.size();m++){
								String instid=(String)ve3.get(m);
								if(!ve2.contains(instid)){
                        			                         ve2.add(instid);
                                        				}
                                				}
							
						}
					}//end of for loop for cId value
                                               
			 	}//end of first if loop
			}//end of first for loop
                                                context.put("uList",ve2);
						
			/*User user=data.getUser();
			String username=user.getName();
			int uid=UserUtil.getUID(username);*/
			context.put("Uid",uid);
			/** 
			 *	code for Photo display 
			 */
				Criteria crt=new Criteria();
			        crt.add(UserConfigurationPeer.USER_ID,uid);
			        List qu=UserConfigurationPeer.doSelect(crt);
				context.put("Image",qu);

			String fname=user.getFirstName();
			String lname=user.getLastName();
			String lang=user.getTemp("lang").toString();
                        context.put("username",username);
                        context.put("firstname",fname);
                        context.put("lastname",lname);
                        context.put("lang",lang);
			lang = "";
			user.setTemp("role","");
			user.setTemp("mInststat","");
			user.setTemp("course_id","");
			user.setTemp("Institute_id","");
			user.setTemp("DomainName","");
		// check for Admin Role
			Vector Admin_Role=UserGroupRoleUtil.getGID(uid,1);
		// check for Instructor Role
			Vector Instructor_Role=UserGroupRoleUtil.getGID(uid,2);
		// check for Student Role
			Vector Student_Role=UserGroupRoleUtil.getGID(uid,3);
		// check for GroupAdmin Role
			Vector GAdmin_Role=UserGroupRoleUtil.getGID(uid,4);
		// check for ContentAuthor Role
			Vector Author_Role=UserGroupRoleUtil.getGID(uid,5);
		// check for InstituteAdmin Role
			Vector InstituteAdmin_Role=UserGroupRoleUtil.getGID(uid,7);
			if(Admin_Role.size()!=0)
			{
	                        context.put("Role1","AdminRole");
				//user.setTemp("role","turbine_root");
			}

			if(Instructor_Role.size()!=0)
			{
                        	context.put("Role2","InstructorRole");
			}
			
			if(Student_Role.size()!=0)
			{
                        	context.put("Role3","StudentRole");
			}

			if(GAdmin_Role.size()!=0)
			{
                        	context.put("Role4","GAdminRole");
			}

			if(Author_Role.size()!=0)
			{
                        	context.put("Role5","AuthorRole");
			}
			if(InstituteAdmin_Role.size()!=0)
			{
				context.put("Role7","InstituteAdminRole");
			}
			
			if(user.getName().equals("guest")){
				context.put("guest_login","true");
			}
			else{
				context.put("guest_login","false");
			}
			/**
                          * Getting message if any required field is empty 
                          */
                        String LangFile=(String)data.getUser().getTemp("LangFile");
                        String Mssg = CommonUtility.CheckData(username,LangFile);
                        context.put("message",Mssg);


		}
		catch(Exception e){data.setMessage("The error is :- "+e);}
	}
}
