package org.iitk.brihaspati.modules.screens.call.UserMgmt_User;

/*
 * @(#)Profile.java	
 *
 *  Copyright (c) 2006-2007 ,2010ETRG,IIT Kanpur. 
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
//Java classes
import java.util.List;
import java.util.Vector;
//Apache classes
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
//Brihaspati classes
import org.iitk.brihaspati.om.HintQuestion;
import org.iitk.brihaspati.om.HintQuestionPeer;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.Program;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.UserConfiguration;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TelephoneDirectory;


/**
 * @author <a href="mailto:singhnk@iitk.ac.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date:3-11-2010,23-12-2010
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kuma Pal</a>
 */


public class Profile extends SecureScreen
{
    /**
     * Adds the information to the context
     */

    public void doBuildTemplate( RunData data, Context context )
    {
	User uName=data.getUser();
	String username=uName.getName();	
	int uid=UserUtil.getUID(username);
	try{
	
	Criteria crt=new Criteria();

	crt.add(TurbineUserPeer.USER_ID,uid);
	List unm=TurbineUserPeer.doSelect(crt);
	context.put("udetl",unm);

	crt=new Criteria();
	crt.addGroupByColumn(HintQuestionPeer.QUESTION_NAME);
	List qw=HintQuestionPeer.doSelect(crt);
	context.put("question",qw);		

	/******************************/
//---------------------------------Telephone Directory-----------------------
	crt=new Criteria();
        crt.add(TelephoneDirectoryPeer.USER_ID,uid);
        List Telelist=TelephoneDirectoryPeer.doSelect(crt);        
        context.put("size",Telelist.size());	
	try {	
	for(int i=0;i<Telelist.size();i++)
	{
		TelephoneDirectory td=(TelephoneDirectory)Telelist.get(i);
		context.put("address",td.getAddress());
		context.put("state",td.getState());
		context.put("country",td.getCountry());
		context.put("department",td.getDepartment());
		context.put("designation",td.getDesignation());
		String officeno=td.getOfficeNo();
		String[] temp;
		String delimiter = "-";
		temp = officeno.split(delimiter);
			context.put("offradio",temp[0]);
			context.put("offprefix",temp[1]);
			context.put("offccode",temp[2]);
			context.put("offrcode",temp[3]);
			context.put("offphone",temp[4]);
		String mobileno=td.getMobileNo();
		String[] mob;
		mob=mobileno.split(delimiter);
			context.put("mobradio",mob[0]);
                        context.put("mobprefix",mob[1]);
                        context.put("mobccode",mob[2]);
                        context.put("mobrcode",mob[3]);
                        context.put("mobphone",mob[4]);
		String homeno=td.getHomeNo();
		String[] home;
		home=homeno.split(delimiter);
			context.put("homeradio",home[0]);
                        context.put("homeprefix",home[1]);
                        context.put("homeccode",home[2]);
                        context.put("homercode",home[3]);
                        context.put("homephone",home[4]);
		String otherno=td.getOtherNo();
		String[] other;
		other=otherno.split(delimiter);
			context.put("othradio",other[0]);
                        context.put("othprefix",other[1]);
                        context.put("othccode",other[2]);
                        context.put("othrcode",other[3]);
                        context.put("othphone",other[4]);
	}
	}catch(Exception e){}
//--------------------------------Telephone Directory-------------------
	/******************************/

	crt=new Criteria();
	crt.add(UserConfigurationPeer.USER_ID,uid);
	List qu=UserConfigurationPeer.doSelect(crt);
	int qid=0;
	for(int i=0;i<qu.size();i++)
	{
		UserConfiguration uc=(UserConfiguration)qu.get(i);
		qid=uc.getQuestionId();
		String ans=uc.getAnswer();
		context.put("qid",qid);
		context.put("ans",ans);
		context.put("conf",uc.getListConfiguration());
		context.put("TaskConf",uc.getTaskConfiguration());
	}

	crt=new Criteria();
	crt.add(HintQuestionPeer.QUESTION_ID,qid);
	List qname=HintQuestionPeer.doSelect(crt);
	context.put("qname",qname);
	
	/**
	 * Get list of all registered institute 
	 */

	crt=new Criteria();
	int addnot[]={0,2};
        crt.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_NAME);
        crt.addNotIn(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,addnot);
        List list=InstituteAdminRegistrationPeer.doSelect(crt);
        context.put("instList",list);

	/**
	 * Get list of all registered program 
	 */
	crt=new Criteria();
	crt.addGroupByColumn(ProgramPeer.PROGRAM_CODE);
	List plist=ProgramPeer.doSelect(crt);
	context.put("prgList",plist);

	ParameterParser pp = data.getParameters();
	String status = pp.getString("status","");
	/**
	 * Getting list of all institute according to user id
	 * List of Rollno according to username
	 */
	//Vector InstId = InstituteIdUtil.getAllInstId(uid);
	//List rlrecord=UserManagement.getUserPrgRollNo(username,Prgcode,InstId);
	List rlrecord=UserManagement.getUserRollNo(username);
	int rlsize = rlrecord.size();
	Vector UsDetail = new Vector();
	/**
 	 * Make record of all details of that username 	
 	 */ 	
	for(int j=0;j<rlrecord.size();j++)
	{
		StudentRollno element4 = (StudentRollno)rlrecord.get(j);
		int rlinstid = Integer.parseInt(element4.getInstituteId());
		String RlIname=InstituteIdUtil.getIstName(rlinstid); 
		String rlprgcode = element4.getProgram();
		String pName =InstituteIdUtil.getPrgName(rlprgcode); 			
		String rlrollno = element4.getRollNo();	
		CourseUserDetail cDetails=new CourseUserDetail();
		cDetails.setInstName(RlIname);
		cDetails.setInstId(rlinstid);
		cDetails.setPrgCode(rlprgcode);
		cDetails.setPrgName(pName);
		cDetails.setRollNo(rlrollno);
		UsDetail.add(cDetails);
		context.put("UDetail",UsDetail);
	}
               	context.put("count",rlsize);
		if(rlsize==0)
			context.put("sizecount",rlsize);
	}

	catch(Exception e){data.setMessage("The error in profile"+e);}
    }

}

