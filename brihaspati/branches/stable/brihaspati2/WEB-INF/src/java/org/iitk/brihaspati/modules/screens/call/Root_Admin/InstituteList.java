package org.iitk.brihaspati.modules.screens.call.Root_Admin;

/*
 * @(#)InstituteList.java	
 *
 *  Copyright (c) 2009 ETRG,IIT Kanpur. 
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

/**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
 */

import java.util.Vector;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;

import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;
import org.apache.turbine.services.servlet.TurbineServlet;
/**
*  Class for to display for pending , approved as well as rejected institute.
*/
public class InstituteList extends SecureScreen_Admin
{
	public void doBuildTemplate( RunData data, Context context )
    	{
		try{
			
			ParameterParser pp = data.getParameters();
			String file = (String)data.getUser().getTemp("LangFile");
			/**
			*  mode for approved(1) ,pending(0) as well as rejected(2) institute list
			*  tdcolor for tab view
			*  orphan(3) having no institute admin in an institute.
			*/
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String tdcolor=pp.getString("count","");
			context.put("tdcolor",tdcolor);
			String uname=(data.getUser()).getName();
			Criteria crit = new Criteria();
			

			if(mode.equals("pendinglist")){
				crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
				crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"0");
				List instdetail=InstituteAdminRegistrationPeer.doSelect(crit);
                                Vector inst_id=new Vector();
				Vector instuser=new Vector();
				 List admindetail=null;
                                if(instdetail.size() !=0){
                                	for(int i=0;i<instdetail.size();i++){
                                        	InstituteAdminRegistration inst=(InstituteAdminRegistration)(instdetail.get(i));
						int instid=inst.getInstituteId();
						inst_id.add(instid);
       	                                  }
                                }
				for(int j=0;j<inst_id.size();j++)
				{
					String Instid=(inst_id.get(j)).toString();
					int InstId=Integer.parseInt(Instid);
					crit=new Criteria();
					crit.add(InstituteAdminUserPeer.INSTITUTE_ID,InstId);
					try{
						admindetail=InstituteAdminUserPeer.doSelect(crit);
						for(int k=0;k<admindetail.size();k++)
						{
							InstituteAdminUser instadminuser=(InstituteAdminUser)admindetail.get(k);
							instuser.add(instadminuser);
						}
					}
					catch(Exception e){}
				}

				context.put("idetail",instdetail);
				context.put("idetail1",instuser);
				
			}
			if(mode.equals("approved")){
				String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                int list_conf=Integer.parseInt(conf);
                                context.put("userConf",new Integer(list_conf));
                                context.put("userConf_string",conf);
				String md=pp.getString("mod","");
				String query=pp.getString("queryList","");
				String values=pp.getString("valueString","");
				List instdetail=InstituteIdUtil.searchInst(md, query, values);
                               // List instdetail=InstituteIdUtil.getInstList();
				Vector vctr=new Vector(instdetail);
                                Vector vct=CommonUtility.PListing(data,context,vctr,list_conf);
                                context.put("approved",vct);
			}			
			if(mode.equals("reject")){
				crit=new Criteria();
				crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"2");
				List rejectedlist=InstituteAdminRegistrationPeer.doSelect(crit);
				context.put("rejectedlist",rejectedlist);
				Vector instId=new Vector();
				Vector instuser=new Vector();
				List userdetail=null;
				if(rejectedlist.size() !=0)
				{
                                	for(int i=0;i<rejectedlist.size();i++)
					{
						InstituteAdminRegistration inst=(InstituteAdminRegistration)(rejectedlist.get(i));
                                                int instid=inst.getInstituteId();
						instId.add(instid);
                                        }
					for(int k=0;k<instId.size();k++){
					String Instid=(instId.get(k)).toString();
					int InstId=Integer.parseInt(Instid);
					crit = new Criteria();
                        		crit.add(InstituteAdminUserPeer.INSTITUTE_ID,InstId);
                        		userdetail=InstituteAdminUserPeer.doSelect(crit);
					for(int j=0;j<userdetail.size();j++)
					{
						InstituteAdminUser instadminuser=(InstituteAdminUser)userdetail.get(j);
						instuser.add(instadminuser);
					}
					}	
                        		context.put("userdetail",instuser);
                                }
			}			
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception=====>"+e);
			
		}
	
    	}
}

