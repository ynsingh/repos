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
import java.text.SimpleDateFormat;
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
* @ mdified date 05-05-2010,13-07-2010,5-10-2010(Smita)
 */

public class Index extends SecureScreen{
	public void doBuildTemplate( RunData data, Context context ){
		try{
                        /*
                         * getting the current user 
			 * & check current user is superAdmin,InsAdmin,Instructor,student or guest
                         */
			User user=data.getUser();
                        String username=user.getName();
                        int uid=UserUtil.getUID(username);
                        String cInsId=null;
                        if(uid==1){
                                cInsId="mainA";
                        }else if(uid==0){
                                cInsId="guest";
                        }
                        else{
                                cInsId=InstituteIdUtil.getSearch(uid);
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
                        Collection aul=TurbineSession.getActiveSessions();
                         for(Iterator i=aul.iterator();i.hasNext();)
                                        {
					 HttpSession session=(HttpSession) i.next();
                                                User un =TurbineSession.getUserFromSession(session);
                                                String u=un.getName();
                                                //check for current session is not null
                                                if(ve.contains(u)){
                                                        int userid=UserUtil.getUID(u);
                                                  String lInsId="";
								  if((userid!=1) && (userid!=0)){
                                                                       lInsId=InstituteIdUtil.getSearch(userid);
                                                                }
                                                //time calculation from session
                                                Date creationTime = new Date(session.getCreationTime( ));
                                                Date de=new Date();
                                                long diff = de.getTime() - creationTime.getTime();
                                                long diffHours = diff/(60 * 60 * 1000);
                                                long diffHour = diff%(60 * 60 * 1000);
                                                long diffMin=diffHour/(60*1000);
                                                        if(cInsId.equals("mainA")){
                                                                String h=u+" "+"("+diffHours+"Hrs"+" "+diffMin+"Min"+")";
                                                                ve1.add(h);
                                                        }else if(cInsId.equals("guest")){
                                                                if(u.equals(cInsId)){
                                                                        String h=u+" "+"("+diffHours+"Hrs"+" "+diffMin+"Min"+")";
                                                                        ve1.add(h);
                                                                        }
                                                        }
                                                        else if(lInsId.equals(cInsId))
                                                        {
                                                                String h=u+" "+"("+diffHours+"Hrs"+" "+diffMin+"Min"+")";
                                                                ve1.add(h);
                                                        }
                                                }


                                               }
                                                context.put("uList",ve1);

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
		}
		catch(Exception e){data.setMessage("The error is :- "+e);}
	}
}
