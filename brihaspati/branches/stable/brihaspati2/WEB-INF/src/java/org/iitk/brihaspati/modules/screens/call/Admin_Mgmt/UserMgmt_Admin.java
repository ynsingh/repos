package org.iitk.brihaspati.modules.screens.call.Admin_Mgmt;

/*
 * @(#)UserMgmt_Instructor.java      
 *
 *  Copyright (c) 2005-2006 ETRG,IIT Kanpur. 
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
 */


import java.io.File;
import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;

import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;


/**
 *   This class contains code for listing of all Institute Admin including Primary and Secondary.
 *   @author  <a href="mail2sunil00@gmail.com">Sunil Yadav</a>
 *   @author  <a href="palseema@rediffmail.com">Manorama Pal</a>09may2012
 *   @author  <a href="singh_jaivir@rediffmail.com">Jaivir Singh</a>09may2012
 */



public class UserMgmt_Admin extends SecureScreen_Institute_Admin{

	 public void doBuildTemplate( RunData data, Context context ) {
	
		 /**
                 * getting property file According to selection of Language in temporary variable
                 * getting the values of configuration parameter.
                 */
		 User user = data.getUser();
		 String LangFile=(String)user.getTemp("LangFile");
		 String mode=data.getParameters().getString("mode","");
		 String counter=data.getParameters().getString("count"," ");
		 String loginname=user.getName();
                 context.put("tdcolor",counter);
                 context.put("mode",mode);
                 String institute_id=user.getTemp("Institute_id").toString();
		 context.put("institute_id",institute_id);
		 int inst_ID = Integer.parseInt(institute_id);
		 String iname = InstituteIdUtil.getIstName(inst_ID);
		 context.put("iname", iname);
		 Vector permission_status=new Vector();
		 int primary_uid=-1;
		 Vector UID=UserGroupRoleUtil.getUID(3,7);
		 Vector all_uid = new Vector();
		 Vector userList=new Vector();
		 List admindetail=null;
		 List detail=null;
		 int perms = -1;
                                
		try {
                           
			Criteria crit3 = new Criteria();
			crit3.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
			crit3.add(InstituteAdminUserPeer.ADMIN_UNAME,loginname);
			detail=InstituteAdminUserPeer.doSelect(crit3);
			for(int p=0;p<detail.size();p++)
			{
				InstituteAdminUser adminuser=(InstituteAdminUser)detail.get((p));
				String uname1=adminuser.getAdminUname();
				perms=adminuser.getAdminPermissionStatus();
                                        
				if(perms != 0){
				//context.put("permission","3");
				context.put("permission","1");
				context.put("flag",loginname);
				}else{
				context.put("permission","0");
				context.put("flag","0");
				}
                                
			}
                                
		}catch(Exception e){}
				
		   
		try{
			 /**
                         * This code for getting User Name of Primary Admin.
                         * Using UserUtil get the userid of Primary Admin.
                         */
	
			Criteria crit = new Criteria();
			crit.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
			crit.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,1);
			admindetail=InstituteAdminUserPeer.doSelect(crit);
			for(int k=0;k<admindetail.size();k++)
			{
				InstituteAdminUser instadminuser=(InstituteAdminUser)admindetail.get((k));
				String uname=instadminuser.getAdminUname();
				primary_uid=UserUtil.getUID(uname);
			}
			
			/**
                         * Using UserGroupRoleUtil get all userid.
                         */
	
				for(int i=0;i<UID.size();i++) {
                                        int uid=Integer.parseInt(UID.elementAt(i).toString());

					/**
                         		 * Using This Condition we find the userid of Primary Admin.
                         		 * The userid of Primary admin is added to the Vector at index Position 0.
                         		 */

                                        if(primary_uid == uid){
                                                all_uid.add(0,UID.elementAt(i).toString());
                                        }else{
                                                all_uid.add(UID.elementAt(i).toString());
					}
			
                                }
				for(int i=0;i<all_uid.size();i++) {
				int uid=Integer.parseInt(all_uid.elementAt(i).toString());
				String uname=UserUtil.getLoginName(uid);
				/*Criteria crit1=new Criteria();
                                crit1.add(InstituteAdminUserPeer.ADMIN_UNAME,uname);
                                crit1.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
                                List l=InstituteAdminUserPeer.doSelect(crit1);*/
				org.iitk.brihaspati.modules.screens.call.Root_Admin.UpdateInstituteAdmin UInstAdm=new org.iitk.brihaspati.modules.screens.call.Root_Admin.UpdateInstituteAdmin();
				Vector l=UInstAdm.getInstAdmUserDetail(institute_id,uname);
				if(l.size()>0) {
                                        for(int j=0;j<l.size();j++) {
                                                //InstituteAdminUser element=(InstituteAdminUser)(l.get(j));
						InstituteFileEntry ifdetail=(InstituteFileEntry)l.elementAt(j);
                                                if(i==0){
                                                        //permission_status.add(Integer.toString(element.getAdminPermissionStatus()));
                                                        permission_status.add(Integer.toString(ifdetail.getInstituteAdminStatus()));
                                                        userList.add(l);
                                                }else {
							//permission_status.add(1,Integer.toString(element.getAdminPermissionStatus()));
                                                        permission_status.add(1,Integer.toString(ifdetail.getInstituteAdminStatus()));
                                                        userList.add(1,l);
                                                }
                                        }
                                } 
			}
			 String status=new String();
                        if(userList.isEmpty()){
                                String msg1=MultilingualUtil.ConvertedString("adm_msg",LangFile);
                                if(LangFile.endsWith("hi.properties"))
                                        data.setMessage(msg1);
                                else
                                        data.setMessage(msg1);
                                status="empty";
                        } else{
                                //status="notempty";
                                String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                int AdminConf = Integer.valueOf(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));

                                context.put("AdminConf",new Integer(AdminConf));
                                context.put("AdminConf_str",Integer.toString(AdminConf));
                                ParameterParser pp=data.getParameters();
                                int startIndex=pp.getInt("startIndex",0);
                                int t_size=userList.size();
                                if(userList.size()!=0){
					//status="notempty";
	
                                         int value[]=new int[7];
                                        value=ListManagement.linkVisibility(startIndex,t_size,AdminConf);

                                        int k=value[6];
                                        context.put("k",String.valueOf(k));

                                        Integer total_size=new Integer(t_size);
                                        context.put("total_size",total_size);

                                        int eI=value[1];
                                        Integer endIndex=new Integer(eI);
                                        context.put("endIndex",endIndex);
					 int check_first=value[2];
                                        context.put("check_first",String.valueOf(check_first));

                                        int check_pre=value[3];
                                        context.put("check_pre",String.valueOf(check_pre));
                                        int check_last1=value[4];
                                        context.put("check_last1",String.valueOf(check_last1));

                                        int check_last=value[5];
                                        context.put("check_last",String.valueOf(check_last));
                                        context.put("startIndex",String.valueOf(eI));
                                        Vector splitlist=ListManagement.listDivide(userList,startIndex,AdminConf);
                                        context.put("adminlist",splitlist);
                                        Vector userpermiss=ListManagement.listDivide(permission_status,startIndex,AdminConf);
                                        context.put("usrprms",userpermiss);
                                }
                        }
	
			  }catch(Exception e){  data.setMessage("The exception is :- "+e);}
			
			}
	}
