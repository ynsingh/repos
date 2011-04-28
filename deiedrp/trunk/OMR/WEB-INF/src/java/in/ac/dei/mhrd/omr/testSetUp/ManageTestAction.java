/*
 * @(#) ManageTestAction.java
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

package in.ac.dei.mhrd.omr.testSetUp;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import in.ac.dei.mhrd.omr.dbConnection.Connect;


import java.sql.*;
import java.util.ArrayList;

/** 
 * This class defines the method that retrieves the existing details
 * of the test selected by the user for editing.
 * MyEclipse Struts
 * Creation date: 11-25-2010
 * @version 1.0
 * @author Anshul
 * 
 * XDoclet definition:
 * @struts.action path="/manage" name="manageForm" input="/form/manage.jsp" scope="request" validate="true"
 */
public class ManageTestAction extends Action {
	
	private static Logger log = Logger.getLogger(ManageTestAction.class);

	
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ManageTestForm manageForm = (ManageTestForm) form;// TODO Auto-generated method stub
			
			HttpSession session = request.getSession(true);
			Connection con=null;
			String testName=null;
			ResultSet rs=null;
			PreparedStatement ps=null;
			ArrayList<SectionDetail> sectionDetail=new ArrayList<SectionDetail>();
			ResultSet rsSectionDetail;
			int sec;		
			//variable for storing section
			
			int testId;
			//get the test name selected by the user
			testName=manageForm.getTestName(); 
			
			if(isCancelled(request)){
				return mapping.findForward("home");
			}

			try{
				con=Connect.prepareConnection();
				con.setAutoCommit(false);
				ps=con.prepareStatement("select Total_section,TestId,TestNo,Total_question,Conduct_date from testheader where Test_name=?");
				ps.setString(1,testName);
				rs=ps.executeQuery();
				rs.next();
				sec=rs.getInt(1);
				testId=rs.getInt(2);
				session.setAttribute("sec", sec);
				session.setAttribute("testName", testName);
				session.setAttribute("testId", testId);
				manageForm.setTestNo(rs.getString(3));
				manageForm.setTestName(testName);
				manageForm.setTestId(rs.getInt(2));
				manageForm.setTotalQuestion(rs.getInt(4));
				manageForm.setTotalSection(rs.getInt(1));
				manageForm.setDate(rs.getDate(5));
				ps=null;
				ps=con.prepareStatement("select Section_number, No_of_question, Marks_each_question, Neg_Marks from testsectiondetail where TestId=?");
				ps.setInt(1, testId);
				rsSectionDetail=ps.executeQuery();

						while(rsSectionDetail.next())
						{
							System.out.println("marks : ");
							sectionDetail.add(new SectionDetail(rsSectionDetail.getInt(1),rsSectionDetail.getInt(2), rsSectionDetail.getFloat(3), rsSectionDetail.getFloat(4)));
						}
				manageForm.setSectionDetail(sectionDetail);		
				con.commit();
				
			}catch(Exception e){
			
				log.error("Exception in Manage Action "+e.getMessage());
			}
			finally{
	        	Connect.freeConnection(con);
	        
	        }
		return mapping.findForward("manage");
	}
}