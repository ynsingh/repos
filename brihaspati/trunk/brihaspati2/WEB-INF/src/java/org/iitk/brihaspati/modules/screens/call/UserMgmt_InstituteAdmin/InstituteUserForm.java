package org.iitk.brihaspati.modules.screens.call.UserMgmt_InstituteAdmin;

/*
 * @(#)InstituteUserForm.java	
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
import java.util.List;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
/**
  * This class contains code for edit user profile according specific username
  * Grab all the records in a table using a Peer, and
  * place the List of data objects in the context
  * where they can be displayed by a #foreach loop.
  *
  * @author  <a href="singh_jaivir@rediffmail.com">Jaivir Singh</a>
  * @author  <a href="sharad23nov@yahoo.com">Sharad Singh</a>
  * @author  <a href="richa.tandon1@gmail.com">Richa Tandon</a>
  * @modified date:20-10-2010, 05-08-2011
  */
public class InstituteUserForm extends SecureScreen_Institute_Admin{
	 /**
	   * Place all the data object in the context for use in the template.
	   * @param data RunData instance
	   * @param context Context instance
	   * @exception Exception, a generic exception
	   */ 
	public void doBuildTemplate(RunData data, Context context){
		try{
			ParameterParser pp=data.getParameters();
			String userName=pp.getString("username");
			String modetype=pp.getString("Process");
			String mode=pp.getString("mode");
			String stat=pp.getString("status");
			String counter=pp.getString("count","");
			String type=pp.getString("type","");
			context.put("tdcolor",counter);
			int uid=UserUtil.getUID(userName);
			List userList=UserManagement.getUserDetail(Integer.toString(uid));
			/**
 			 * Get detail of user rollno   
 			 */ 		
			List userRollNo=UserManagement.getUserRollNo(userName);
			int rlsize = userRollNo.size();
			Vector UsDetail = new Vector();
			/**
 			 * Loop for getting all detail of user  
 			 * then context put to display in vm 
 			 */ 		
			for(int j=0;j<userRollNo.size();j++)
                	{
				StudentRollno element = (StudentRollno)userRollNo.get(j);
	                        int rlinstid = Integer.parseInt(element.getInstituteId());
	                        String RlIname=InstituteIdUtil.getIstName(rlinstid);
	                        String rlprgcode = element.getProgram();
				int sturlid = element.getId();
        	                String pName =InstituteIdUtil.getPrgName(rlprgcode);
	                        String rlrollno = element.getRollNo();
				String email = element.getEmailId();
	                        CourseUserDetail cDetails=new CourseUserDetail();
				cDetails.setStudsrid(sturlid);
	                        cDetails.setInstName(RlIname);
	                        cDetails.setEmail(email);
	                        cDetails.setInstId(rlinstid);
	                        cDetails.setPrgCode(rlprgcode);
	                        cDetails.setPrgName(pName);
	                        cDetails.setRollNo(rlrollno);
	                        UsDetail.add(cDetails);
        	                context.put("UDetail",UsDetail);
	                 }
				context.put("counter",rlsize);
	                if(rlsize==0)
        	                context.put("sizecount",rlsize);
			/**
 			 * getting institute id from temp & list of program for that institute 
 			 */ 
			int InstId = Integer.parseInt((String)data.getUser().getTemp("Institute_id"));
			Criteria crit=new Criteria();
	                crit.add(InstituteProgramPeer.INSTITUTE_ID,InstId);
	                List Instplist= InstituteProgramPeer.doSelect(crit);
	                Vector PrgDetail = new Vector();
	                for(int i=0;i<Instplist.size();i++)
	                {
		                InstituteProgram element = (InstituteProgram)Instplist.get(i);
	                        String PrgCode = element.getProgramCode();
	                        String prgName = InstituteIdUtil.getPrgName(PrgCode);
	                        CourseUserDetail cDetails=new CourseUserDetail();
	                        cDetails.setPrgName(prgName);
	                        cDetails.setPrgCode(PrgCode);
				PrgDetail.add(cDetails);
	                        context.put("PrgDetail",PrgDetail);
			}
			context.put("udetail",userList);
			context.put("urollno",userRollNo);
			context.put("Process",modetype);
			context.put("mode",mode);
			context.put("status",stat);
			context.put("InstId",InstId);
			String from=pp.getString("from","");
			context.put("from",from);
			context.put("type",type);
		}
		catch (Exception e){
			data.setMessage("The error in user id :- "+e);
		}
	}
}

