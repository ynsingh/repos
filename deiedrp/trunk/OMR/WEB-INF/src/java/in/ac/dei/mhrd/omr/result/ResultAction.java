/*
 * @(#) ResultAction.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.mhrd.omr.result;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * This class computes the marks obtained by the candidate based on 
 * their correct attempts, wrong attempts and unattempted questions
 * 
 * MyEclipse Struts Creation date: 10-03-2010
 * @author Anshul Agarwal
 * XDoclet definition:
 * 
 * @struts.action path="/result" name="resultForm" input="/SelectResult.jsp"
 *                scope="session" validate="true"
 * @struts.action-forward name="marks" path="/Marks.jsp"
 * @version 1.0
 */
public class ResultAction extends Action {
	
	private static Logger log = Logger.getLogger(ResultAction.class);

	
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ResultForm resultForm = (ResultForm) form;
			int testid=0;							
			int section=0;
			int totalSection=0;
			int quesSection=0;
			float marks=0;
			int i=0;
			Connection con =null;
			PreparedStatement ps=null;
			ResultSet rsGraceMarks=null;
			ArrayList<Integer> wrongQues=new ArrayList<Integer>();
			ArrayList<String> status=new ArrayList<String>();
			ArrayList<WrongQuesData> wrongDetail=new ArrayList<WrongQuesData>();
			if(isCancelled(request)){
				return mapping.findForward("home");
			}
			
		try{
			 con = Connect.prepareConnection();
			con.setAutoCommit(false);
			PreparedStatement psTestid = con.prepareStatement("select TestId from testheader where Test_name = ?");
	psTestid.setString(1, resultForm.getTestName());
	ResultSet rs = psTestid.executeQuery();
	rs.next();
	testid = rs.getInt(1);
	rs.close();
	/*
	 * 
	 */
	ps=con.prepareStatement("select WrongQuestionNo, status from wrongquestion where TestId=?");
	  ps.setInt(1, testid);
 	 rsGraceMarks=ps.executeQuery();
 	 System.out.println("in result.do ");
 	 		while(rsGraceMarks.next())
 	 			{
 	 			wrongQues.add(rsGraceMarks.getInt(1));
 	 			status.add(rsGraceMarks.getString(2));
 	 			}
 	 		rsGraceMarks=null;
 	 		ps=con.prepareStatement("select Total_section from testheader where TestId=?");
				ps.setInt(1, testid);
				rsGraceMarks=ps.executeQuery();
				rsGraceMarks.next();
				 totalSection = rsGraceMarks.getInt(1);
				 request.setAttribute("totalSection", totalSection);
				System.out.println("ts "+totalSection);
			
 	 		if(!wrongQues.isEmpty())
 	 		{
 	 				rsGraceMarks=null;
 	 				rsGraceMarks=null;
 	 				ps=con.prepareStatement("select Section_number,No_of_question,Neg_Marks,Marks_each_question from testsectiondetail " +
 	 										"where TestId=?");
 	 				ps.setInt(1, testid);
 	 				rsGraceMarks=ps.executeQuery();
 	 					while(rsGraceMarks.next())
 	 					{
 	 						section++;
 	 						for(i=0;i<wrongQues.size();i++)
 	 						{  
 	 							if(wrongQues.get(i)<=(quesSection+rsGraceMarks.getInt(2)) && wrongQues.get(i)>quesSection)
 	 							{
 	   	 						System.out.println("for section "+section+" for wrong "+wrongQues.get(i)+" status "+status.get(i));
 	   	 						wrongDetail.add(new WrongQuesData(section,wrongQues.get(i),status.get(i)));
 	 							}
 	 						}
 	 					quesSection=quesSection+rsGraceMarks.getInt(2);
 	 					}
 	 				
 	 					request.setAttribute("wrongDetail", wrongDetail);
 	 					rsGraceMarks.close(); 
 	 		}	
 	 
 	 		String imgPath= getServlet().getServletContext().getRealPath("/")+ "processedFolder" +"/"+testid+ "/";
 			 request.setAttribute("imagePath", imgPath);
 			 request.setAttribute("testid", testid);
 			
	/*
	 * 
	 */
		log.info("test Id retrieved : " + testid);
con.commit();
		}catch(Exception e){
			log.error("Error while retrieving test id in Result "+e);
		}
		finally{
        	Connect.freeConnection(con);
        
        }
		
			
		return mapping.findForward("markList");
	}
}