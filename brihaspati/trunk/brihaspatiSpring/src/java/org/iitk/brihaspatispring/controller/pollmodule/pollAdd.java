package org.iitk.brihaspatispring.controller.pollmodule;
/*
 * @(#)pollAdd.java
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
 *
 */
/**
         *  Add poll.
         *  Institute admin poll
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
import java.util.HashMap;
import org.iitk.brihaspati.om.PollPeer;
import org.iitk.brihaspati.om.Poll;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspatispring.utils.ErrorDumpUtil;
import javax.servlet.ServletContext;
import org.apache.torque.TorqueException;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.apache.torque.Torque;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class pollAdd  extends SimpleFormController{
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
                        * Get poll question
                        */
	       	        String Ques=request.getParameter("question");
			ErrorDumpUtil.ErrorLog("tesssst"+Ques);
			if(StringUtils.isNotEmpty(Ques)){
				if (Ques.indexOf('Â') > -1){
			 		Ques=Ques.replaceAll("Â","");
				}
			}
			/**
                        * Get Institute Id
                        */
                        String Id=request.getParameter("institute_Id");
			int i = Integer.parseInt(Id);
                        myModel.put("i",Id);

			/**
                        * Get poll initialise date & time
                        */
                	String sDate=request.getParameter("sel_Date");
			 /**
                        * Get user id
                        */
                	String userId=request.getParameter("user_Id");
			 /**
                        *  Expiry of the question.
                        */
                	int valDate = Integer.parseInt(sDate);
                	Date date = new Date();
                	Calendar cal = Calendar.getInstance();  
                	cal.setTime(date); 
                	cal.add(Calendar.DATE,valDate); 
                	date = cal.getTime(); 
			Date dateNow = new Date();
                        Calendar calNow = Calendar.getInstance();
                        cal.setTime(dateNow);
			dateNow = calNow.getTime();
			 /**
                        * FCK editor fix.
                        */

			if (Ques.equals("&nbsp;")){
				 myModel.put("vacheck","vacheck");
			}		
			 /**
                        * Insert poll question,userid,questiontype(default -"1"),Instituteid,institutename,date.
                        */      
			else if (StringUtils.isNotEmpty(Ques)) {
                        	String now = (new java.util.Date()).toString();
		      		Criteria crit=new Criteria();
        	      		crit.add(PollPeer.QUESTION,Ques);
				crit.add(PollPeer.USER_ID,userId);
				crit.add(PollPeer.QUESTION_TYPE,1);
				crit.add(PollPeer.INSTITUTE_ID,i);
				crit.add(PollPeer.INSERT_DATE,dateNow);
	       			crit.add(PollPeer.VALID_TILL,date);
               	      		crit.setDistinct();
                      		PollPeer.doInsert(crit);
				myModel.put("l","check");
			}
             }
        catch(Exception es){ErrorDumpUtil.ErrorLog("The error in poll --pollAdd "+es);}
        return new ModelAndView("pollmodule/pollAdd", "model", myModel);
        }
}
                                               
