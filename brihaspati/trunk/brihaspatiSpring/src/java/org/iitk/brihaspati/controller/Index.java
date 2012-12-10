/*
 * @(#)Index.java
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
import org.apache.torque.Torque;
import javax.servlet.ServletContext;
import org.iitk.brihaspati.om.Tweets;
import org.iitk.brihaspati.om.TweetsPeer;
//import org.iitk.brihaspati.om.TweetFollowers;
//import org.iitk.brihaspati.om.TweetFollowersPeer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.utils.ErrorDumpUtil;
import java.util.List;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class Index extends SimpleFormController {
        private Torque set=null;
        private ServletContext context=null;
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{
                Map myModel = new HashMap();
                try{
                        String tweetmsg=request.getParameter("tweets");
                        String username=request.getParameter("username");
                        String uname=request.getParameter("uname");
                        String name=request.getParameter("name");
                        myModel.put("username",username);
                        myModel.put("name",name);
                        Date date = new Date();
                        java.sql.Date Cdate = new java.sql.Date(date.getTime());
                        set=new Torque();
                        String tempFileName = getServletContext().getRealPath("Torque.properties");
                        set.init(tempFileName);
			//inserting tweets into database
			try{
				Criteria crit = new Criteria();
        	                crit.add(TweetsPeer.USER_NAME,username);
                	        crit.add(TweetsPeer.TWEET,tweetmsg);
                        	crit.add(TweetsPeer.TWEET_DATE,Cdate);
	                        crit.setDistinct();
        	                TweetsPeer.doInsert(crit);
			}catch(Exception exp){}
			// getting tweets info and Tweets from database
                        Criteria criteria = new Criteria();
                        criteria.addGroupByColumn(TweetsPeer.ID);
                        criteria.add(TweetsPeer.USER_NAME,username);
                        List tweets = TweetsPeer.doSelect(criteria);
                        myModel.put("tweets",tweets);
			/*
                        Criteria flrs = new Criteria();
                        flrs.addGroupByColumn(TweetFollowersPeer.ID);
                        flrs.add(TweetFollowersPeer.FOLLOWING_ID,uname);
                        List fls = TweetFollowersPeer.doSelect(flrs);
                        myModel.put("fls",fls);

                        Criteria fling = new Criteria();
                        fling.addGroupByColumn(TweetFollowersPeer.ID);
                        fling.add(TweetFollowersPeer.FOLLOWERS_ID,uname);
                        List fing = TweetFollowersPeer.doSelect(fling);
                        myModel.put("fing",fing);
                       */
			 }
			
                        catch(Exception e){
                                ErrorDumpUtil.ErrorLog("Exception in Index.java"+e);
                        }
                        return new ModelAndView("index", "model", myModel);
    }
}


