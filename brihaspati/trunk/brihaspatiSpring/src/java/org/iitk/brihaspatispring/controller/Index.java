package org.iitk.brihaspatispring.controller;

/*
 * @(#)Index.java
 *
 *  Copyright (c) 2012,2013 ETRG,IIT Kanpur.
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
                        

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.iitk.brihaspati.om.Tweets;
import org.iitk.brihaspati.om.TweetsPeer;

import org.iitk.brihaspatispring.utils.TweetThread;
import org.iitk.brihaspatispring.utils.ErrorDumpUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.Torque;

/**                     
 *  @author: <a href="sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
 *  modified@date 13-06-2013
 */  

/**
*class for handling all functions for  tweet deletion and selection for show tweets
*class file use parameters to show and delete tweets
*/
public class Index extends SimpleFormController {
	private Torque set=null;
	
	/**
	 *this mathod is used for initilize torque to access database  
	 *and process response according to http request
	 */
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Map myModel = new HashMap();
		try{
			try {
				set=new Torque();
                        	String tempFileName = getServletContext().getRealPath("Torque.properties");
   				set.init(tempFileName);
			} catch(Exception e) { }
			String mode = request.getParameter("mode");
			String username=request.getParameter("username");
			try {
				String uid=request.getParameter("Uid");
				String id = request.getParameter("Id");
			       	String tweetmsg=request.getParameter("tweets");
        	                String expdate=request.getParameter("expdate");
                	        String name=request.getParameter("name");
	                	myModel.put("username",username);
				myModel.put("Uid",uid);
				myModel.put("mode",mode);
                	        myModel.put("name",name);
	                	myModel.put("expdate",expdate);
					
                        	if(StringUtils.isNotEmpty(tweetmsg))
                                	TweetThread.getController().addComposTweetMsg(username,tweetmsg ,expdate);
				if(StringUtils.isNotEmpty(id)) 
					TweetThread.getController().deleteTweetMsg(id,username);
	                } catch(Exception e) { ErrorDumpUtil.ErrorLog("Exception in insertion of tweets"+e); }
			
			try {
				if (mode == null) {
	                		TweetThread.getController().setLoginId(username);
        	        		List tweets =TweetThread.getController().getSelectTweet();
			                myModel.put("tweets",tweets);
				}
			} catch(Exception exp) { ErrorDumpUtil.ErrorLog("Exception in Index.java  "+exp); }
			try {
				if (mode != null) {
					if (mode.equals("all")) {
			                	List tweets =TweetThread.getController().getAllTweet();
			        	        myModel.put("tweets",tweets);
					}
				}
			} catch(Exception exp) { ErrorDumpUtil.ErrorLog("Exception in showing list "+exp); }
			try {
                                int tsize=TweetThread.getController().getSize(username);
                                myModel.put("tsize",tsize);
                        } catch(Exception exc) { ErrorDumpUtil.ErrorLog("Exception in getting number of message"+exc); }
		} catch(Exception ex){ 	ErrorDumpUtil.ErrorLog("Exception in Index.java"+ex); }
	        return new ModelAndView("index", "model", myModel);
    	}
}
