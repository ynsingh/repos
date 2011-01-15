/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package in.ac.dei.mhrd.omr;

import in.ac.dei.mhrd.omr.img.*;
import in.ac.dei.mhrd.omr.Grace;
import java.io.*;
import in.ac.dei.mhrd.omr.CollectData;
import java.sql.*;
import java.util.ArrayList;
import in.ac.dei.mhrd.omr.img.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * MyEclipse Struts Creation date: 10-03-2010
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/result" name="resultForm" input="/SelectResult.jsp"
 *                scope="session" validate="true"
 * @struts.action-forward name="marks" path="/Marks.jsp"
 */
public class ResultAction extends Action {
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
	private static Logger log = Logger.getLogger(ResultAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ResultForm resultForm = (ResultForm) form;// TODO Auto-generated
			int testid=0;										// method stub
			int section=0;
			int totalSection=0;
			int quesSection=0;
			float marks=0;
			int i=0;
			PreparedStatement ps=null;
			ResultSet rsGraceMarks=null;
			ArrayList<Integer> wrongQues=new ArrayList<Integer>();
			ArrayList<String> status=new ArrayList<String>();
			ArrayList<CollectData> wrongDetail=new ArrayList<CollectData>();
		try{
			java.sql.Connection con = Connect.prepareConnection();
			con.setAutoCommit(false);
			PreparedStatement psTestid = con.prepareStatement("select  TestId from testheader where Test_name = ?");
	psTestid.setString(1, resultForm.getTestName());
	ResultSet rs = psTestid.executeQuery();
	rs.next();
	testid = rs.getInt(1);
	rs.close();
	/*
	 * 
	 */
	ps=con.prepareStatement("select WrongQuestionNo,status from wrongquestion where TestId=?");
	  ps.setInt(1, testid);
 	 rsGraceMarks=ps.executeQuery();
 	 System.out.println("in result.do ");
 	 		while(rsGraceMarks.next())
 	 			{
 	 			wrongQues.add(rsGraceMarks.getInt("WrongQuestionNo"));
 	 			status.add(rsGraceMarks.getString("status"));
 	 			}
 	 		rsGraceMarks=null;
 	 		ps=con.prepareStatement("select Total_section from testheader where TestId=?");
				ps.setInt(1, testid);
				rsGraceMarks=ps.executeQuery();
				rsGraceMarks.next();
				 totalSection = rsGraceMarks.getInt("total_section");
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
 	 							if(wrongQues.get(i)<=(quesSection+rsGraceMarks.getInt("No_of_question")) && wrongQues.get(i)>quesSection)
 	 							{
 	   	 						System.out.println("for section "+section+" for wrong "+wrongQues.get(i)+" status "+status.get(i));
 	   	 						wrongDetail.add(new CollectData(section,wrongQues.get(i),status.get(i)));
 	 							}
 	 						}
 	 					quesSection=quesSection+rsGraceMarks.getInt("No_of_question");
 	 					}
 	 				
 	 					request.setAttribute("wrongDetail", wrongDetail);
 	 					rsGraceMarks.close(); 
 	 		}	
 	 
	/*
	 * 
	 */
		log.info("test Id retrieved : " + testid);
con.commit();
con.close();
		}catch(Exception e){
			log.error("Error while retrieving test id in Result "+e);
		}
		
		
		 String imgPath= getServlet().getServletContext().getRealPath("/")+ "processedFolder" +"/"+testid+ "/";
		 request.setAttribute("imagePath", imgPath);
		 request.setAttribute("testid", testid);
		
		
		 /*
		 int testid = Integer.parseInt(resultForm.getTest());
		 float marks;
		 int correctAttempt=0;
		 int wrongAttempt=0;
		 int unAttempt=0;
		 
		 int totalQues=0;
		 float marksOfEachQues=0;
		 float negMarks=0;
		 
		 float grace=0;
		ArrayList<HoldMarks> markList = new ArrayList<HoldMarks>();
		
		HttpSession session = request.getSession(true);
		try{
				
			 Connection con = (Connection) Connect.prepareConnection();
	            
             PreparedStatement ps = con.prepareStatement(
                     "Select a.RollNo, a.Correct_attempt, a.Wrong_attempt, a.Unattempt from attempt_info a, candidate_detail cd where ((a.TestId=?) AND (a.TestId=cd.TestId) AND (cd.Faculty=?) AND (cd.Semester=?) AND (cd.Qt=?) AND (a.RollNo= cd.RollNo))");
              ps.setInt(1, testid);
              ps.setString(2, resultForm.getFaculty());
              ps.setInt(3, Integer.parseInt(resultForm.getSemester()));
              ps.setString(4, resultForm.getQt());
             ResultSet rsAttempt = ps.executeQuery(); 
             session.setAttribute("faculty",resultForm.getFaculty());
             System.out.println("correct " + correctAttempt);
             
             ps=con.prepareStatement("select Test_name, Total_question, Marks_of_each_ques, Negative_Marks from testheader where TestId =?");
             ps.setInt(1, testid);
             ResultSet rsMarks = ps.executeQuery();
             rsMarks.next();
             session.setAttribute("TestName", rsMarks.getString(1));
             System.out.println("marks : " +rsMarks.getFloat(2));
             totalQues=rsMarks.getInt(2);
             marksOfEachQues=rsMarks.getFloat(3);
             negMarks = rsMarks.getFloat(4);
                          
             ps=con.prepareStatement("select count(*) from wrongQuestion where testId=?");
             ps.setInt(1, testid);
             ResultSet rsWrong = ps.executeQuery();
              rsWrong.next();
              System.out.println("Wrong Quesy" + rsWrong.getInt(1)); 
             
                        	  while(rsAttempt.next()){
                               correctAttempt=rsAttempt.getInt(2);
                               wrongAttempt = rsAttempt.getInt(3);
                               unAttempt= rsAttempt.getInt(4);
                               
                               
                              
                        	   System.out.println("inside loop");
                        	   grace=rsWrong.getInt(1)*marksOfEachQues;
                        	   System.out.println("grace : " + grace);
                        	   marks = (correctAttempt*marksOfEachQues)-(wrongAttempt*negMarks)+ grace;

                        	   markList.add(new HoldMarks(rsAttempt.getInt(1), correctAttempt, wrongAttempt, unAttempt, marks));
                        	  }
                           System.out.println("size" + markList.size());
                           
                   for(HoldMarks hm : markList){
                	   System.out.println("Roll : " + hm.rno + "correct : " +hm.correctAttempt + hm.marks);
                   }
                     session.setAttribute("markSheet", markList);    
                     session.setAttribute("TotalQues", totalQues);
                     session.setAttribute("Marks", marksOfEachQues);
                     session.setAttribute("NegativeMazrks", negMarks);
                     session.setAttribute("GracePoint", grace);
                   
               con.close();
               }catch (Exception e){
            	   System.out.println("Exception in evalution " +e);
               }
			 
	   */
		
		return mapping.findForward("markList");
	}
}