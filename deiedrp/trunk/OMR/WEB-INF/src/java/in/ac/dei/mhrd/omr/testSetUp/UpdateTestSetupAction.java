/*
 * @(#) UpdateTestSetUpAction.java
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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.*;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;

/** 
 * This class fetches the updated test Detail and 
 * updates the testheader table, testsectionDetail table and wrongQuestion table   
 *
 * MyEclipse Struts
 * Creation date: 11-25-2010
 * @version 1.0
 * @author Anshul Agarwal
 * XDoclet definition:
 * @struts.action path="/updateTestSetup" name="manageForm" input="/manageTest.jsp" scope="request" validate="true"
 * 
 */
public class UpdateTestSetupAction extends Action {
	/*
	 * Generated Methods
	 */
	private static Logger log = Logger.getLogger(UpdateTestSetupAction.class);


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
		/*
		 * This method updates the testheader table, testsectiondetail table and wrongQuestion table
		 * 
		 */
		
		ManageTestForm manageForm = (ManageTestForm) form;
		HttpSession session = request.getSession(true);
		
			int i=1;
			/** Total Sections before updating the test  **/
			int tempSection;
			
			int temp;
			Connection con=null;
			ResultSet rsTestHeader=null;
			ResultSet rsWrongQues=null;
			PreparedStatement ps=null;
			String testNo=manageForm.getTestNo();
			int testId=(Integer)session.getAttribute("testId");
			int totalQuestion=manageForm.getTotalQuestion();
			int totalSection=manageForm.getTotalSection();
			/*
			 *  Test conduct date               
			 *  
			 */
			Date date=manageForm.getDate();
			String qryTestHeader;
			String qryTestSecDetail;
			float negMarks;
			int questionNo;
			float marksEachQuestion;
		    int checkUpdate=0;
			try{
				con=Connect.prepareConnection();
				con.setAutoCommit(false);
				ps=con.prepareStatement("select Total_section from testheader where TestId=?");
				ps.setInt(1,testId);		
				rsTestHeader=ps.executeQuery();
				rsTestHeader.next();
					tempSection=rsTestHeader.getInt("Total_section");
					temp=tempSection;
					rsTestHeader.close();
					
			// Checking for wrong Question		
				ps=con.prepareStatement("select * from wrongquestion where TestId=?");
				ps.setInt(1, testId);
				rsWrongQues=ps.executeQuery();
				
				/*
				 * Delete wrong Question number that exceeds total questions
				 */
					if(rsWrongQues.next())
					{
						ps=con.prepareStatement("delete from wrongquestion where TestId=? and WrongQuestionNo>?");
						ps.setInt(1, testId);
						ps.setInt(2, totalQuestion);
						checkUpdate= ps.executeUpdate();
					}
				if(checkUpdate>=1){
					log.info("extra wrong Ques deleted");
				}
					//insert number of sections added by the user
					if(totalSection>tempSection)
						{
						ps=con.prepareStatement("insert into testsectiondetail(TestId,Section_number,No_of_question,Neg_Marks,Marks_each_question) values(?,?,?,?,?)");
						for(i=1;i<=(totalSection-tempSection);i++)
							{
								temp++;
								questionNo=Integer.parseInt(request.getParameter("noq"+temp));
								System.out.println("total Sec detail Q no :" +questionNo);
								marksEachQuestion=Float.parseFloat(request.getParameter("meq"+temp));
								negMarks=Float.parseFloat(request.getParameter("nm"+temp));
								System.out.println("neg marks : " + negMarks);
							
								ps.setInt(1, testId);
								ps.setInt(2, temp);
								ps.setInt(3, questionNo);
								ps.setFloat(4, negMarks);
								ps.setFloat(5,marksEachQuestion);
								//add query in batch
								ps.addBatch();
								
							}
						//execute batch
						int insertSecDetail[] = ps.executeBatch();
						System.out.println("insertSecDetail : " + insertSecDetail.length);
						}
					
					//deleting number of sections if user attempt to decrease the 
					//existing number of sections
						else if(totalSection<tempSection)
						{
							ps=con.prepareStatement("delete from testsectiondetail where Section_number=? and TestId=?");
							for(i=1;i<=(tempSection-totalSection);i++)
							{
								
								
								ps.setInt(1,temp);
								ps.setInt(2, testId);
								ps.addBatch();
								temp--;
							}
							tempSection=temp;
							int delTestSec[] = ps.executeBatch();
							System.out.println("del Section : " + delTestSec.length);
						}
				
					qryTestHeader="update testheader set TestNo=?,Conduct_date=?,Total_section=?,Total_question=? where TestId=?";
					ps=con.prepareStatement(qryTestHeader);
					ps.setString(1, testNo);
					ps.setDate(2,date);
					ps.setInt(3, totalSection);
					ps.setInt(4,totalQuestion);
					ps.setInt(5, testId);
					int countTestHeader=ps.executeUpdate();
				    System.out.println("count header :" + countTestHeader);
				i=1;
				qryTestSecDetail="update testsectiondetail set No_of_question=?,Marks_each_question=?,Neg_Marks=? where TestId=? and Section_number=?";
				ps=con.prepareStatement(qryTestSecDetail);
				//updating rest of the rows
				while(i<=tempSection)
					{	
						questionNo=Integer.parseInt(request.getParameter("noq"+i));
						marksEachQuestion=Float.parseFloat(request.getParameter("meq"+i));
						negMarks=Math.abs(Float.parseFloat(request.getParameter("nm"+i)));
						
						ps.setInt(1, questionNo);
						ps.setFloat(2, marksEachQuestion);
						ps.setFloat(3, negMarks);
						ps.setInt(4, testId);
						ps.setInt(5, i);
						ps.addBatch();
						i++;
					}
				//execute batsh
				int countSecDetail[] = ps.executeBatch();
				System.out.println("SEction Detail : " + countSecDetail.length);
				//check if query executed successfully or not
				if(countSecDetail.length>=1){
					log.info("Test Updated Successfully!!");
					request.setAttribute("updateMsg", "Test Updated Sucessfully");
				}
					con.commit();
			}catch(Exception e)
			{
				log.error("Error in updating the TEST " +e);
				request.setAttribute("updateMsg", "Test cannot be Updated Sucessfully");

				System.out.println("updateTestSetUp Action Exception:"+e.getMessage());
			}finally{
	        	Connect.freeConnection(con);
	            
	        }
		return mapping.findForward("success");
	}
}