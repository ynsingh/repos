package org.iitk.brihaspatispring.controller.pollmodule;
/*
 * @(#)pollResultSingle.java
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
 *  PollSingle Result .
 *  User - Institute Admin,Instructor,student
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
import org.iitk.brihaspatispring.utils.PollUtility;

public class pollResultSingle  extends  SimpleFormController{
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
                 * Get poll question id
                 */
		 String modeSingle=request.getParameter("modeSingle");
                 myModel.put("modeSingle",modeSingle);

		int Id=Integer.parseInt(request.getParameter("Id"));
		Criteria crit1=new Criteria();
                crit1.add(PollPeer.QUESTION_ID,Id);
                List ls=PollPeer.doSelect(crit1);
                myModel.put("listvalue",ls);
		 /**
                 * Methode poll result% update 
                 */
	        PollUtility.getPollResultList(ls);
	}
        catch(Exception es){ ErrorDumpUtil.ErrorLog("The error in poll --pollResultSingle "+es);}
        return new ModelAndView("pollmodule/pollResultSingle", "model", myModel);
        }
}

