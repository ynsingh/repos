package org.iitk.brihaspati.modules.screens.call.Root_Admin;
/* 
 * @(#)UpdateInstituteAdmin.java
 *
 *  Copyright (c) 2010,2012 ETRG,IIT Kanpur.
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;

 /**
 * This screen called when SysAdmin update the details of Institute Admin. 
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>09may2012
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>09may2012
 */

public class UpdateInstituteAdmin extends SecureScreen_Admin 
{
	public void doBuildTemplate(RunData data, Context context)
	{
		try{
			/**
			 *Get the name and InstituteId which details has to be updated.
			 *select all details from database and  
			 *set to context for use in templates.
			 */
			String iid=data.getParameters().getString("Institute_Id");
			context.put("Institute_Id",iid);
			String iname=data.getParameters().getString("username");
			context.put("iadname",iname);
			String status=data.getParameters().getString("status");
			context.put("status",status);
			String mode=data.getParameters().getString("mode");
			context.put("mode",mode);
			String count=data.getParameters().getString("count","");
                        context.put("tdcolor",count);
			Criteria crit=new Criteria();
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,iid);
			List listAreg=InstituteAdminRegistrationPeer.doSelect(crit);
			context.put("detAregis",listAreg);
			Vector instuser=getInstAdmUserDetail(iid,iname);
			context.put("detAuser",instuser);
		}
		catch(Exception e) { data.setMessage("Error in Update Institute Admin screen!!" +e);}
	}
	/** Getting detail of InstituteAdmin from 'InstituteAdminUser and TurbineUser table'
	*/
	public Vector getInstAdmUserDetail(String Instid,String Uname)
	{
		Vector instuser=new Vector();
		try{
			Criteria crit=new Criteria();  
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,Instid);
                        crit.add(InstituteAdminUserPeer.ADMIN_UNAME,Uname);
			List admindetail=InstituteAdminUserPeer.doSelect(crit);
                        for(int k=0;k<admindetail.size();k++)
                        {
                                InstituteAdminUser instadminuser=(InstituteAdminUser)admindetail.get(k);
                                int Id=instadminuser.getId();
                                String email=instadminuser.getAdminUname();
                                String ADesg=instadminuser.getAdminDesignation();
                                int Apstatus=instadminuser.getAdminPermissionStatus();
                                crit=new Criteria();
                                crit.add(org.iitk.brihaspati.om.TurbineUserPeer.LOGIN_NAME,email);
                                List tulist=org.iitk.brihaspati.om.TurbineUserPeer.doSelect(crit);
                                org.iitk.brihaspati.om.TurbineUser udetail=(org.iitk.brihaspati.om.TurbineUser)tulist.get(0);
                                String fname=udetail.getFirstName();
                                String lname=udetail.getLastName();
                                String temail=udetail.getEmail();
                                String uname=udetail.getLoginName();
                                InstituteFileEntry InstfileEntry=new InstituteFileEntry();
                                InstfileEntry.setInstituteFName(fname);
                                InstfileEntry.setInstituteLName(lname);
                                InstfileEntry.setInstituteEmail(temail);
                                InstfileEntry.setInstituteUserName(uname);
                                InstfileEntry.setID(Id);
                                InstfileEntry.setInstituteDesignation(ADesg);
                                InstfileEntry.setInstituteAdminStatus(Apstatus);
                                instuser.add(InstfileEntry);
			}
		}
		catch(Exception e) { ErrorDumpUtil.ErrorLog("Error in Update Institute Admin screen!!" +e);}
		return instuser;
	}//method
}

