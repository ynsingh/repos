package org.iitk.brihaspatispring.utils;	

/*@(#)TweetThread.java
 *  Copyright (c) 2013 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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

import java.util.List;
import java.util.Vector;
import java.util.Hashtable;

import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.Tweets;
import org.iitk.brihaspati.om.TweetsPeer;
import org.iitk.brihaspatispring.utils.ErrorDumpUtil;

/**
 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu Singh</a>
 * modified@date 13-06-2013
 */

public class TweetThread implements Runnable {
        
	private static TweetThread tThread=null;
        private Thread runner = null;		
	private List selectedtweets= null;
	private String store_curentdate="";
	private Vector login_name=new Vector();
	private Hashtable userId_tweetsize = new Hashtable();
	

	/**
         * Controller for this class to use as a singleton.
         */
	
	public static TweetThread getController() throws Exception {
                if (tThread==null){
                        tThread=new TweetThread();
                }
                return tThread;
        }
		
	/**
	 * Start TWEET Thread.
 	 */
	
        private void startThread() {
	     	if (runner == null ) {
        	        runner = new Thread(this);
                        runner.start();
                }
        }
	
	/**
	 * Stop Tweet Thread .
	 */

        private void stopThread() {
	       	if (runner != null) {
	                runner = null;
                }
        }

	/**
	 * add username into vector login_name	
	 * @param username
	 */
 
 	public void setLoginId(String username) {
		if(selectedtweets==null) {
			startThread();
		}
		login_name.add(username.trim());
	}
	
	/**
	 * get value of selected tweets in list 
	 * return value of selected tweets
	 */

	public List getSelectTweet() throws Exception {
	        return selectedtweets;
        }
	
	/**
 	 * get all tweets in a list  
 	 */
  
	public List getAllTweet() throws Exception {
		try {
			Criteria criteria = new Criteria();
        	        criteria.addDescendingOrderByColumn(TweetsPeer.ID);
			List alltweets = TweetsPeer.doSelect(criteria);
			return alltweets;
		} catch(Exception e) { ErrorDumpUtil.ErrorLog("Exception in getAllTweet method in TweetThread class"+e.getMessage()); }
		return null;
	}

	/**
	 * get number of tweets according to user 
 	 * @param username 
 	 */  

	public int getSize(String username) throws Exception {	
		if(!(userId_tweetsize.containsKey(username.trim()))) {
			login_name.add(username.trim());
			startThread();
		}
		
		if(userId_tweetsize.get(username.trim()) != null)
		        return (Integer)userId_tweetsize.get(username.trim());
		else
			return 0;
        }
	
	/**
 	* This method inserts the submitted
 	* tweet along with username and tweet's 
 	* expiry date into the database. 
 	* @param username 
 	* @param tweetmsg tweet submitted by the user
 	* @param expdate date on which tweet will be expired
 	*/
 
	public void addComposTweetMsg(String username,String tweetmsg ,String expdate) {
		try {
			java.sql.Date Cdate = new java.sql.Date((new java.util.Date()).getTime());
			Criteria crit = new Criteria();
			crit.add(TweetsPeer.USER_NAME,username);
                	crit.add(TweetsPeer.TWEET,tweetmsg);
		        crit.add(TweetsPeer.TWEET_DATE,Cdate);
	        	crit.add(TweetsPeer.EXPIRY_DATE,expdate);
			TweetsPeer.doInsert(crit); 
			login_name.add(username.trim());
			startThread();
			runner.sleep(100);
		} catch(Exception e) { ErrorDumpUtil.ErrorLog("Exception in inserting tweets !!"+e.getMessage()); }
	}
	/**
 	* This method is used to delete Tweets from the database
 	*/ 
	public void deleteTweetMsg(String id,String username) {
                try {
			Criteria crit= new Criteria();
		        crit.add(TweetsPeer.ID,id);
                	TweetsPeer.doDelete(crit); 	
			login_name.add(username.trim());
			startThread();	
			runner.sleep(100);
                } catch(Exception e) { ErrorDumpUtil.ErrorLog("Exception in deleting tweets !!"+e.getMessage()); }
        }
	
	/**
 	* This method creates lists of tweets
 	* The list contains current five tweets in the database and 
 	* total number of tweets submitted by the user. 
 	*/
 
        public void run() {
               	try {
			/**
			 * This peice of code is used to match the current date to clear hashtable
			 * and exixuted once in a day   
			 */ 
			String cur_date=DateTimeUtil.getCurrentDate("-");
			if(!(store_curentdate.equals(cur_date))) {
				store_curentdate=cur_date;
				userId_tweetsize.clear();	
			}
			/**
 			*access database and get list of tweets
 			*/ 
			Criteria criteria = new Criteria();
	        	criteria.addDescendingOrderByColumn(TweetsPeer.ID);
			criteria.setLimit(5);
			selectedtweets= TweetsPeer.doSelect(criteria);
			while(login_name.size() > 0) {
				String loginname=login_name.get(0).toString();	
				login_name.remove(0);
				Criteria crit = new Criteria();
				crit.add(TweetsPeer.USER_NAME,loginname);
				List tweets_for_userid = TweetsPeer.doSelect(crit);
				userId_tweetsize.put(loginname,tweets_for_userid.size());
		
				/** 
				 * removing  all entries from vector 
				 */  		
				tweets_for_userid.clear();
				tweets_for_userid=null;
			}
			stopThread();
		} catch(Exception e) {  ErrorDumpUtil.ErrorLog("Exception in TweetThread class !! "+e); }
        }
} //end of class


