package org.iitk.brihaspatispring.utils;

/*@(#)PollUtility.java
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

/**
 * This class set some value and get in templates from XML file 
 * @author: <a href="mailto:piyushm45@gmail.com">Piyush Mishra</a>
 */

import java.util.List;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspatispring.utils.Pagination;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.apache.torque.TorqueException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.om.PollPeer;
import org.iitk.brihaspati.om.Poll;
import org.iitk.brihaspatispring.utils.XMLWriter_Poll;
import org.iitk.brihaspatispring.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;

public class PollUtility{
	private ServletContext context=null;
	/**
         *This method uses restrict user to attempt single poll to many times..
         *String FileSurveyPathxml,username,inst_Name
	 *return list ntAttempted
	 * String -  FileSurveyPathxml(XML path) ,username (User Email Id ),inst_Name(institute name)
	 *int - insNe(institute Id)
	 * Return : List - ntAttempted(Poll List).
         *prints the xml  document to file
         */
	public static List getPollList(String FileSurveyPathxml, String username, String inst_Name, int insNe){
		List  ntAttempted =  new java.util.ArrayList();
        	List  ntAttempted_Val=  new java.util.ArrayList();
			try{
                        	Criteria critpollntAttempted=new Criteria();
				List ls=PollUtility.getfilterlstpoll(insNe);
                        	for(int h=0;h<ls.size();h++){
                                	Poll elementSortAttempted=(Poll)(ls.get(h));
                                        int poll_Id=(elementSortAttempted.getQuestionId());
                                        String pollAttemptedCheck=XMLWriter_Poll.CheckElement(FileSurveyPathxml,username+"_"+inst_Name+"("+insNe+")"+"_"+poll_Id);
                                        if((pollAttemptedCheck.equals("NotExist")) || (pollAttemptedCheck.equals("null"))){
                                        	critpollntAttempted.add(PollPeer.QUESTION_ID,poll_Id);
                                                ntAttempted_Val=PollPeer.doSelect(critpollntAttempted);
                                                ntAttempted.addAll(ntAttempted_Val);//list create
                                        }//if               
                                }//for
			}//try
			catch(Exception e){ErrorDumpUtil.ErrorLog("Spring--The error in PollUtilitity,Methode--getPollList "+e);}
			return ntAttempted;
	}
	/**
	*This method uses update the result % of the poll
	*List -  Poll_list
	*list Poll_list.
        */
	public static boolean getPollResultList(List Poll_list){
		List Poll_List =  new java.util.ArrayList(Poll_list);
			try{
                 	//select with each row 
                		for(int i=0;i<Poll_List.size();i++){
                        		Poll element=(Poll)(Poll_List.get(i));
                                	int  id_Value=(element.getQuestionId());
                                	int value_Yes=(element.getResultYes());
                                	int value_No=(element.getResultNo());
                                	int value_Can=(element.getResultCan());
                                	int  total_Sum=value_Yes+value_No+value_Can;
                                	float prcnt_Yes=(value_Yes*100)/(total_Sum);
                                	float prcnt_No=(value_No*100)/(total_Sum);
                                	float prcnt_Can=(value_Can*100)/(total_Sum);
                                	//update percentage value
                                	Criteria crit2=new Criteria();
                                	crit2.add(PollPeer.QUESTION_ID,id_Value);
                                	crit2.add(PollPeer.RESULT_PERCENTAGE_YES,prcnt_Yes);
                                	crit2.add(PollPeer.RESULT_PERCENTAGE_NO,prcnt_No);
                                	crit2.add(PollPeer.RESULT_PERCENTAGE_CAN,prcnt_Can);
                                	PollPeer.doUpdate(crit2);
                          	}//for
			}//try
		catch(Exception e){
		ErrorDumpUtil.ErrorLog("Spring--The error in Poll Updation,methode--PollResultList !!"+e);
		}
		return true;
	}
	/** 
         * Get the Institute list for reterive information name and their institute id
	 * integer - instituteid(Institute Id)
         * Return : List -inmLst(Institute list)
         */
	public static List getIstNListInfo(int instituteid){
		List inmLst=null;
                Criteria critList=new Criteria();
                try{
                	critList.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
                        inmLst=InstituteAdminRegistrationPeer.doSelect(critList);
                }
                catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("Spring--The error in getIstList() - Institute Id Util class !!"+ex);
                }
                return inmLst;
        }

	/**
	* Get the filter list of poll for particular institute.
        * integer instituteid
	* @return : List - filterLst(Poll List by institute. )
        */
        public static List getfilterlstpoll(int instituteid){
		Criteria crit=new Criteria();
	       	Criteria crit_View=new Criteria();
	       	List lsinst= new java.util.ArrayList();
	       	List filterLst= new java.util.ArrayList();
	       	List view_Val= new java.util.ArrayList();
                try{
                	crit.addDescendingOrderByColumn(PollPeer.QUESTION_ID);
                        lsinst=PollPeer.doSelect(crit);
                        	for(int jz=0;jz<=lsinst.size();jz++){
                                	Poll element_View=(Poll)(lsinst.get(jz));
                                        int instId_Value_data=(element_View.getInstituteId());
                                        int instIdques_Value_data=(element_View.getQuestionId());
                                        if(instituteid==instId_Value_data){
                                        	crit_View.add(PollPeer.QUESTION_ID,instIdques_Value_data);
                                                view_Val=PollPeer.doSelect(crit_View);
                                                filterLst.addAll(view_Val);
                                        }//if     
                                }//for
			 
		}
                catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("Spring--The error in getIstList() - Institute Id Util class !!"+ex);
                }
                return filterLst;
        }
}
