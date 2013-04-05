package org.iitk.brihaspatispring.controller.pollmodule;
/*
 * @(#)pollAttemptIndex.java
 *
 *  Copyright (c) 2012-2013 ETRG,IIT Kanpur.
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
 */

/**
 *  Attempt Poll .
 *  User - Institute Admin,Instructor,student
 *  @author: <a href="piyushm45@gmail.com">Piyush Mishra</a>
 */
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.SimpleFormController;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.Torque;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineGroupPeer;
import org.apache.turbine.services.security.torque.om.TurbineGroup;
import org.apache.torque.TorqueException;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.util.ArrayList;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.Poll;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.PollPeer;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspatispring.utils.ErrorDumpUtil;
import org.iitk.brihaspatispring.utils.PollUtility;
import org.iitk.brihaspatispring.utils.XMLWriter_Poll;

public class pollAttemptIndex  extends  SimpleFormController{
        private Torque set=null;
        private ServletContext context=null;
        
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        	Map myModel = new HashMap();
          	try{ 
			/**
                        * Retrieve  real path of  "torque.properties" by ServletContext()
                        * Initialize Torque : Turbine properties
                        */
                	set=new Torque(); 
                        String tempFileName = getServletContext().getRealPath("Torque.properties"); 
                        set.init(tempFileName); 
			/**
                        * Initialise list
                        */
			List ls=new java.util.ArrayList();
			/**
                        * set path for Xmlfile(restriction poll) & txtfile(outdated poll).
                        */
			File pollrecords=new File(getServletContext().getRealPath("/pollRecords/"));
			boolean exists = pollrecords.exists();
			if (!exists){
			pollrecords.mkdirs();
			}
			String FileSurveyPathxml=getServletContext().getRealPath("/pollRecords")+"/pollAttemptedbyUser.xml";
			 /**
                        * Get username
                        */
			String username=request.getParameter("usrNme");
			myModel.put("usrNme",username);
			 /**
                        * Get Username
                        */
			String uid=request.getParameter("user_Id");
			myModel.put("UserId",uid);
			  /**
                        * Get mode for multi poll in popup.
                        */
			String modev=request.getParameter("modeview");
			  if(StringUtils.isEmpty(modev)){
				myModel.put("mod",2);
			}
			myModel.put("modev",modev);
			 /**
                        * Get list of institute name (user).
                        */
			int Uid=Integer.parseInt(uid);
			Vector cId=new Vector();
			List  ntAttempted = new java.util.ArrayList();
			List lsDa = new java.util.ArrayList();
			String instName="";
			int istId ;
			List listIns = new java.util.ArrayList();
			cId=(InstituteIdUtil.getAllInstId(Uid));
			for(int inst = 0; inst < cId.size(); inst ++){
        			instName = InstituteIdUtil.getIstName(Integer.parseInt(cId.get(inst).toString()));
				List inmL=PollUtility.getIstNListInfo(Integer.parseInt(cId.get(inst).toString()));
				if(!listIns.containsAll(inmL)){
					listIns.addAll(inmL);
				}//if
			}//for
 			myModel.put("lstData",listIns);
			myModel.put("lsSize",listIns.size());
			 /**
                        * get Institutename
                        */
                        int IdVal;
                        String instId=request.getParameter("instNme");
                        IdVal=Integer.parseInt(instId);
                        myModel.put("instidv",IdVal);
                        String inst_Name=InstituteIdUtil.getIstName(IdVal);
                        myModel.put("inst",inst_Name);
			 /**
                        * As post poll to template.
                        */
			ntAttempted = PollUtility.getPollList(FileSurveyPathxml,username,inst_Name, IdVal);
			myModel.put("fgt",ntAttempted);
                        myModel.put("Nins",inst_Name);
			 /**
                        * Get value of mode (YES,NO orCAN'T) and Id(QUESTION_ID)
                        */                     
                        String Mode=request.getParameter("mode"); 
                        int Id=Integer.parseInt(request.getParameter("Id"));
			 /**
                        * Increament of polling value(YES,NO orCAN'T)
                        */    
                        List li=null; 
                        Criteria crit1=new Criteria(); 
                        crit1.add(PollPeer.QUESTION_ID,Id); 
                        List ls1=PollPeer.doSelect(crit1); 
                        int incr_Yes; 
                        int incr_No;
                        int incr_Can;
                        int id_Value;
			int incr_Yeszz;
               		/**
                        * check condition of Mode (Yes ,No or can't)....increment in value of Result_yes,No,Can
                        * result also add in Result_yes or Result_no and Result_cant==null
                        */
                        if(ls1.size()>0){ 
                        	if(Mode.equals("Yes")){
                                	Poll element=(Poll)(ls1.get(0)); 
                                  	incr_Yes=(element.getResultYes()); 
                                  	id_Value=(element.getQuestionId()); 
			          	incr_Yes=incr_Yes+1; 
                                  	Criteria crit2=new Criteria(); 
                                  	crit2.add(PollPeer.QUESTION_ID,id_Value); 
                                  	crit2.add(PollPeer.RESULT_YES,incr_Yes); 
                                  	PollPeer.doUpdate(crit2);
                                } //if (yes)
                          	else if(Mode.equals("No")){
                                	Poll element=(Poll)(ls1.get(0));
                                  	incr_No=(element.getResultNo()); 
                                  	id_Value=(element.getQuestionId());
		                  	incr_No=incr_No+1;
			          	Criteria crit2=new Criteria();
                                  	crit2.add(PollPeer.QUESTION_ID,id_Value);
                                  	crit2.add(PollPeer.RESULT_NO,incr_No);
                                  	PollPeer.doUpdate(crit2); 
				}//else if(No)
				else if(Mode.equals("Cant")){
                                	Poll element=(Poll)(ls1.get(0));
                                  	incr_Can=(element.getResultCan());
                                  	id_Value=(element.getQuestionId());
                                  	incr_Can=incr_Can+1;
				  	Criteria crit2=new Criteria();
                                  	crit2.add(PollPeer.QUESTION_ID,id_Value);
                                  	crit2.add(PollPeer.RESULT_CAN,incr_Can);
                                  	PollPeer.doUpdate(crit2); 
                              	}//else if (can't)
			/**
                         * Methode for Xml(use to write xml)
                         */
			String attemptedInfo = XMLWriter_Poll.PollXml(FileSurveyPathxml,Mode,username+"_"+inst_Name+"("+IdVal+")"+"_"+Id,"");
			/**
                         * Methode for Not attempted poll list 
                         */
			ntAttempted = PollUtility.getPollList(FileSurveyPathxml,username,inst_Name, IdVal);
			myModel.put("fgt",ntAttempted);
			myModel.put("Nins",inst_Name);
			}//if(ls1.size())
       		}
        	catch(Exception es){ ErrorDumpUtil.ErrorLog("The error in poll -- pollAttemptIndex"+es);}
                return new ModelAndView("pollmodule/pollAttemptIndex", "model", myModel);
        }
}


