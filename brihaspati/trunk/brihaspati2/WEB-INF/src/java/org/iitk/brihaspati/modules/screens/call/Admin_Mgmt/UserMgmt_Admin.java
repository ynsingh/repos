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
import java.util.Collections;
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
		 String loginname=user.getName();
		 String LangFile=(String)user.getTemp("LangFile");
		 String mode=data.getParameters().getString("mode","");
                 context.put("mode",mode);
		 String counter=data.getParameters().getString("count"," ");
                 context.put("tdcolor",counter);
                 String institute_id=user.getTemp("Institute_id").toString();
		 context.put("Institute_Id",institute_id);
		 int inst_ID = Integer.parseInt(institute_id);
		 String iname = InstituteIdUtil.getIstName(inst_ID);
		 context.put("Institute_Name", iname);
		 Vector permission_status=new Vector();
		 Vector userList=new Vector();
		 List detail=null;
		 int perms = -1;
		 Vector vect=new Vector();
		 List List_Id=null;

                                
		try {
			/*
			 * Code for Setting User Permission. 
			 * Primary Admin User value is set 1.
			 * Secondary Admin User value 0.
                         */	
			
			Criteria crit = new Criteria();
			crit.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
			crit.add(InstituteAdminUserPeer.ADMIN_UNAME,loginname);
			detail=InstituteAdminUserPeer.doSelect(crit);
			for(int p=0;p<detail.size();p++)
			{
				InstituteAdminUser adminuser=(InstituteAdminUser)detail.get((p));
				String uname1=adminuser.getAdminUname();
				perms=adminuser.getAdminPermissionStatus();
                                        
				if(perms != 0){
				context.put("permission","1");
				context.put("flag",loginname);
				}else{
				context.put("permission","0");
				context.put("flag","0");
				}
                                
			}
                                
		}catch(Exception e){}
				
		   
		try{
			/*
			 * Select All Id of an Institute.  
			 */
				//Institutewise Select All Id of an Institute. Id's are Stored in a Vector and Find Minimum Id of an Institute. minimum Id user is Primary Admin of an Institute. Set ADMIN_PERMISSION_STATUS for Primary Admin is 1. and Other user is Set 0. 	

                        Criteria crit = new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
                        List idlist=InstituteAdminUserPeer.doSelect(crit);
                        for(int i=0;i<idlist.size();i++)
                        {
                                InstituteAdminUser idlistobj = (InstituteAdminUser)(idlist.get(i));
                                int id = idlistobj.getId();
                                String min_Id = Integer.toString(id);
                                vect.add(min_Id);
                        }
			
			/*
			 * Get Minimum Id from Vector vect.
			 */
		
                        Object obj = Collections.min(vect);
                        String I_D = obj.toString();
                        int minid = Integer.parseInt(I_D);

                        crit = new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
                        crit.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,1);
                        List pstatus1 = InstituteAdminUserPeer.doSelect(crit);

			if(pstatus1.size()==1) {
				crit = new Criteria();
        	                crit.add(InstituteAdminUserPeer.ID,minid);
	                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);	
                	        crit.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,1);
                        	InstituteAdminUserPeer.doUpdate(crit);
			  } 
			    else {
				 	for(int p=0;p<vect.size();p++) {
					int admin_Id = Integer.parseInt(vect.elementAt(p).toString());
					if(minid != admin_Id) {
						crit = new Criteria();
		                                crit.add(InstituteAdminUserPeer.ID,admin_Id);
		                                crit.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
                	        	        crit.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,0);
                        	        	InstituteAdminUserPeer.doUpdate(crit);
						}
					}
				}
			
                        /*
                         * Get All Id of Institute Admin.
                         */
			String uname = "";
                        Vector vect_allid = new Vector();
                        crit = new Criteria();
                        crit.addAscendingOrderByColumn(InstituteAdminUserPeer.ID);
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
                        List_Id=InstituteAdminUserPeer.doSelect(crit);
                        for(int i=0;i<List_Id.size();i++) {
                                InstituteAdminUser instadminusers=(InstituteAdminUser)(List_Id.get(i));
                                int adm_id = instadminusers.getId();
                                String adm_Id = Integer.toString(adm_id);
				uname = instadminusers.getAdminUname();
                                if(minid == adm_id) {
                                        vect_allid.add(0,adm_Id);
                                        } else{
                                        vect_allid.add(adm_Id);
                                        }

                                }

				for(int i=0;i<vect_allid.size();i++) {
				int id_u = Integer.parseInt(vect_allid.elementAt(i).toString());
				Criteria crit1=new Criteria();
                                crit1.add(InstituteAdminUserPeer.ID,id_u);
                                crit1.add(InstituteAdminUserPeer.INSTITUTE_ID,institute_id);
                                List lst1=InstituteAdminUserPeer.doSelect(crit1);	
				for(int k=0;k<lst1.size();k++) {
				InstituteAdminUser instadminusers1=(InstituteAdminUser)(lst1.get(k));
				String uname1 = instadminusers1.getAdminUname();
				//}

		
				org.iitk.brihaspati.modules.screens.call.Root_Admin.UpdateInstituteAdmin UInstAdm=new org.iitk.brihaspati.modules.screens.call.Root_Admin.UpdateInstituteAdmin();
				Vector l=UInstAdm.getInstAdmUserDetail(institute_id,uname1);
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
