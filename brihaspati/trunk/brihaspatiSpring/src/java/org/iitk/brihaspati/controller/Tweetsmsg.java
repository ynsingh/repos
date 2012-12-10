/*
 * @(#)Tweetmsg.java
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
 *  @author: <a href="sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
 */  
package org.iitk.brihaspati.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import org.apache.torque.Torque;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.HashMap;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.utils.ErrorDumpUtil;
import java.util.List;
import org.iitk.brihaspati.om.Tweets;
import org.iitk.brihaspati.om.TweetsPeer;


//this class is used for reading and deleting tweet msg


public class Tweetsmsg extends SimpleFormController {

        private Torque set=null;
        private ServletContext context=null;
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{

        Map myModel = new HashMap();
        try{	
                set=new Torque();
                String tempFileName = getServletContext().getRealPath("Torque.properties");
                set.init(tempFileName);
		String id = request.getParameter("Id");
		String username=request.getParameter("username");
                myModel.put("username",username);
		// this is used to delete tweets by corresponding user id.
		try{
			if(!id.equals("")){
        	       	Criteria del = new Criteria();
	                del.add(TweetsPeer.ID,id);
        	        TweetsPeer.doDelete(del);
                	}
		}catch(Exception exp){
			ErrorDumpUtil.ErrorLog("Exception in Tweetsmsg.java when try to delete"+exp);
		}

		// this is used to show all the Tweets
                Criteria criteria = new Criteria();
                criteria.addDescendingOrderByColumn(TweetsPeer.ID);
                List tweets = TweetsPeer.doSelect(criteria);
                myModel.put("tweets",tweets);
		

        }
        catch(Exception ex){
                ErrorDumpUtil.ErrorLog("Exception in Tweetsmsg.java"+ex);
        }
        return new ModelAndView("tweetsmsg", "model", myModel);
    }
}
