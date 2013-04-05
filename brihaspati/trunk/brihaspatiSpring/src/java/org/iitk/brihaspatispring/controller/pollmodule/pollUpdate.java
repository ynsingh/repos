package org.iitk.brihaspatispring.controller.pollmodule;
/*
 * @(#)pollUpdate.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
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
 * Update Poll .
 *  User - Institute Admin.
 *  @author: <a href="piyushm45@gmail.com">Piyush Mishra</a>
 */

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import org.iitk.brihaspati.om.PollPeer;
import org.iitk.brihaspati.om.Poll;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspatispring.utils.Pagination;
import org.iitk.brihaspatispring.utils.ErrorDumpUtil;
import javax.servlet.ServletContext;
import org.apache.torque.TorqueException;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.apache.torque.Torque;
import org.iitk.brihaspatispring.utils.PollUtility;
import org.apache.commons.lang.StringUtils;


public class pollUpdate  extends  SimpleFormController{
        private Torque set=null;
        private ServletContext context=null;
        
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    			 
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
			List ls = new java.util.ArrayList();
			List  view_Val= new java.util.ArrayList();
			List  lsinst= new java.util.ArrayList();
			/**
                        * list content size.
                        */
			String AdminConfVal=request.getParameter("AdminConf");
			int z = Integer.parseInt(AdminConfVal);
			myModel.put("AdminConf",z);
			/**
                        * Get institute id
                        */
                        String Id_institute=request.getParameter("institute_Id");
                        int i = Integer.parseInt(Id_institute);
                        myModel.put("i",i);
			/**
                        * Get poll queston & update.
                        */
			String up_DateQues=request.getParameter("question");
			String up_Date_Id=request.getParameter("up_Date");
			String UpDate=request.getParameter("UpDate");
			if(StringUtils.isNotEmpty(up_DateQues)){
				if (up_DateQues.indexOf('Â') > -1){
                        	 	up_DateQues=up_DateQues.replaceAll("Â","");
                                }
			}
			/**
                        * Update, delete and View poll
                        */
			try{
                       		String Mode=request.getParameter("mode");
                         	int Id=Integer.parseInt(request.getParameter("Id"));
                       		List li=null;
                        	Criteria crit1=new Criteria();
                        	crit1.add(PollPeer.QUESTION_ID,Id);
                        	List ls1=PollPeer.doSelect(crit1);
				int incr_Yes;
                        	int id_Value;
               			   
                        	if(ls1.size()>0){
               				if(Mode.equals("Delete")){
                                		Poll element=(Poll)(ls1.get(0));
                                   	  	id_Value=(element.getQuestionId());
                                          	Criteria crit2=new Criteria();
                                          	crit2.add(PollPeer.QUESTION_ID,id_Value);
                                          	PollPeer.doDelete(crit2);
                                          	String delete_Survey="deleted";
                                          	myModel.put("note",delete_Survey);
                                        }//if
                           		if(Mode.equals("Update")){
                                        	String update_Survey="update" ;
                                          	myModel.put("note",update_Survey);
                                          	myModel.put("ques",ls1);
                                        }//if
                                	if(UpDate.equals("UpDate")){
                                        	Criteria crit2=new Criteria();
                                         	crit2.add(PollPeer.QUESTION_ID,up_Date_Id);
                                         	crit2.add(PollPeer.QUESTION,up_DateQues);
                                         	PollPeer.doUpdate(crit2);
                                         	String s_Update="updated";
                                         	myModel.put("note",s_Update);
                                        }//if
			      	}//if(ls1)

			}//try1
			catch(Exception ex){}
			/**
                        * Pagination of poll list.
                        */  			 
			ls=PollUtility.getfilterlstpoll(i);
			if(ls.size()==0){
				String v_Val="null";
				myModel.put("Value",v_Val);
			}
			Vector  splitlist = new Vector(); 
			Vector lst = new Vector(ls);
                        int AdminConf =  z;
                        myModel.put("AdminConf",new Integer(AdminConf));
                        myModel.put("AdminConf_str",Integer.toString(AdminConf));
                        String Index=request.getParameter("startIndex");
                        int startIndex;
                        myModel.put("AdminConf_str11",Index);
                        if(Index==null){
                        	startIndex=0;
                        }
			else{
                                startIndex=Integer.parseInt(Index);;
                        }//if
                        String status=new String();
                        int t_size=(ls.size());
                        if(ls.size()!=0){
                        	status="Noblank";
                               	int value[]=new int[7];
                                value=Pagination.linkVisibility(startIndex,t_size,AdminConf);
                                int k=value[6];
                                myModel.put("k",String.valueOf(k));
                                Integer total_size=new Integer(t_size);
                                myModel.put("total_size",total_size);
                                int eI=value[1];
                                Integer endIndex=new Integer(eI);
                                myModel.put("stIndex",value[0]);
                                myModel.put("endIndex",endIndex);
                                int check_first=value[2];
                                myModel.put("check_first",String.valueOf(check_first));
                                int check_pre=value[3];
                                myModel.put("check_pre",String.valueOf(check_pre));
                                int check_last1=value[4];
                                myModel.put("check_last1",String.valueOf(check_last1));
                                int check_last=value[5];
                                myModel.put("check_last",String.valueOf(check_last));
                                myModel.put("startIndex",String.valueOf(eI));
                                splitlist=Pagination.listDivide(lst,startIndex,AdminConf);
                                myModel.put("dirList",splitlist);
                                }//if
                        else
                                {
                                        status="blank";
                                }//else
	       }
     	  catch(Exception es){ErrorDumpUtil.ErrorLog("The error in poll--pollUpdate "+es);}
          return new ModelAndView("pollmodule/pollUpdate", "model", myModel);
        }
}
                                                                                                                                                                                                                                                                                                   
