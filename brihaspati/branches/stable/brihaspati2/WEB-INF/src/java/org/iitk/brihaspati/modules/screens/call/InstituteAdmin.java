package org.iitk.brihaspati.modules.screens.call;

/*
 * @(#)InstituteAdmin.java	
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
 * 
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import java.util.Vector;
import java.util.List;

/**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 */

public class InstituteAdmin extends SecureScreen{
	public void doBuildTemplate( RunData data, Context context ){
		try{
			User user=data.getUser();
		        System.gc();	
			String username=user.getName();
			String fname=user.getFirstName();
			String lname=user.getLastName();
		
                        context.put("username",username);
                        context.put("firstname",fname);
                        context.put("lastname",lname);
			
			int u_id=UserUtil.getUID(username);
			String id=Integer.toString(u_id);
			if(user.getName().equals("guest")){
				context.put("guest_login","true");
			}
			else{
				context.put("guest_login","false");
			}
			ParameterParser pp=data.getParameters();

			String Uid=pp.getString("Uid","");
			context.put("Uid",Uid);
  			String minststat=pp.getString("minststat","");
			context.put("minststat",minststat);
			user.setTemp("mInststat",minststat);
			String Role="";
				Role=pp.getString("role","");
			if(Role.equals(""))
				Role=(String)user.getTemp("role");
			context.put("user_role",Role);
			context.put("role",Role);		
			// This is check for set temp variables
			user.setTemp("Uid",Uid);
			if(minststat.equals("1"))
			user.setTemp("mInststat",minststat);
			user.setTemp("role",Role);
			Criteria crit=new Criteria();
			crit.add(InstituteAdminUserPeer.ADMIN_UNAME,username);
			List list=InstituteAdminUserPeer.doSelect(crit);
			Vector list1=new Vector();
			List list2=null;
			Vector list3=new Vector();
                        if(list.size() !=0){
	                        for(int i=0;i<list.size();i++){
        	                        InstituteAdminUser inst=(InstituteAdminUser)(list.get(i));
                                        int instid=inst.getInstituteId();
                                        //String instid=instidtemp.toString();
                                        list1.add(instid);
                                }
                       	}
			for(int j=0;j<list1.size();j++)
			{
				String inst_id=(list1.get(j)).toString();
				int instid=Integer.parseInt(inst_id);
				crit=new Criteria();
				crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instid);
				crit.and(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,1);
				try{
	                                list2=InstituteAdminRegistrationPeer.doSelect(crit);
                                        for(int k=0;k<list2.size();k++)
                                        {
        	                                InstituteAdminRegistration instadminreg=(InstituteAdminRegistration)list2.get(k);
                                                list3.add(instadminreg);
                                        }
                                 }
                                 catch(Exception e){}

			}
			String rldpage=pp.getString("reload"," ");
			if(rldpage.equals(" ")){
			context.put("list",list);
			context.put("list1",list3);
			
			}
			else{
			String iname=pp.getString("iname","");
			context.put("list",iname);
			}
			context.put("rldpage",rldpage);
		}
		catch(Exception e){data.setMessage("The error in InstituteAdmin !!"+e);}
	}
}

