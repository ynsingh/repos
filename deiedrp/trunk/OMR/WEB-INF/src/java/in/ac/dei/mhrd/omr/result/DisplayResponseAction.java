/*
 * @(#) DisplayResponseAction.java
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

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.Coordinates;
import in.ac.dei.mhrd.omr.img.RotateImg;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;


/** 
 * This class defines the method that retrieves the responses of the candidate selected by the user
 * and the correct answers for that test from the database
 * MyEclipse Struts
 * Creation date: 01-01-2011
 * 
 * XDoclet definition:
 * @struts.action path="/displayResponse" name="resultForm123" input="/SectionDetail.jsp" scope="request" validate="true"
 */
public class DisplayResponseAction extends Action {
	
	private static Logger log = Logger.getLogger(Coordinates.class);

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
		ResultForm resultForm123 = (ResultForm) form;// TODO Auto-generated method stub
		ResultSet rsResponse=null;
		ResultSet rsCorrectResponse=null;
		  
		  String qry;
		  int testid = 0;
		  String fileName=null; 
		    int rollno=0;
		    int row=0;
		    int i=0;
		    byte [] responseAnswer;
		    byte [] correctAnswer;
		    ArrayList<String> resAns=new ArrayList<String>();
		    ArrayList<String> corrAns=new ArrayList<String>();
		    ArrayList<Integer> corrQues=new ArrayList<Integer>();
		    ArrayList<Integer> responseQues=new ArrayList<Integer>();
			int j=0;
		    Connection con=null;
		    testid = Integer.parseInt(request.getParameter("testid"));
		    request.setAttribute("testid", testid);
		    fileName=request.getParameter("filename");
		    request.setAttribute("filename", fileName);
		    File sheetName = new File(fileName);						
		    //file name to be extracted from previous page
			RotateImg ri=new RotateImg();
		    //selecting roll no. corresponding to file name
		    try{
		    	con=Connect.prepareConnection();
		    qry="select distinct RollNo from response where TestId="+testid+" AND FileName='"+sheetName.getName()+"'";
		    PreparedStatement ps = con.prepareStatement(qry);
		       rsResponse=ps.executeQuery();
		       while(rsResponse.next()){      
		       rollno=rsResponse.getInt(1);
		       }
		       request.setAttribute("rollNo", rollno);
			
			   ps=con.prepareStatement("select count(*),total_question from testheader where TestId=? and group_exists=? ");
		       ps.setInt(1, testid);
		       ps.setString(2, "Y");
		       ResultSet rsCount=ps.executeQuery();
		       rsCount.next();
		     
		       int total_ques=rsCount.getInt(2);
		       
		       if(rsCount.getInt(1)==1){
		    	   correctAnswer=new byte[total_ques];
		    	   ps=con.prepareStatement("select sectionNumber, group_code from student_result_info where TestId=? and RollNo=? and FileName=? order by SectionNumber");
		    	   ps.setInt(1, testid);
		    	   ps.setInt(2, rollno);
		    	   ps.setString(3, sheetName.getName());
			       ResultSet rstset=ps.executeQuery();
			       while(rstset.next()){
			    	  
			    	   ps=con.prepareStatement("select Ques_no,answer from correctans where TestId=? and SectionNumber=? and group_code=? order by Ques_no");
				       ps.setInt(1, testid);
				       ps.setInt(2, rstset.getInt(1));
				       ps.setString(3, rstset.getString(2));
				       ResultSet correctansrs=ps.executeQuery();
				       row=correctansrs.getRow();
				       
				       
				       while(correctansrs.next()){
				    	   correctAnswer[j]=(byte)correctansrs.getByte("answer");
				    	   corrQues.add(j+1);
				    	   j++;
				    	   
				       }
			       }
			       
		    	corrAns = ri.convertToString(correctAnswer);
		    	 
		       }
		       
		       else{
					ps=con.prepareStatement("select Ques_no,answer from correctans where TestId=? order by Ques_no");
					ps.setInt(1, testid);
					rsCorrectResponse=ps.executeQuery();
					rsCorrectResponse.last();
					row=rsCorrectResponse.getRow();
					rsCorrectResponse.beforeFirst();
					correctAnswer=new byte[row];
					while(rsCorrectResponse.next())
					{
						correctAnswer[i]=(byte)rsCorrectResponse.getByte("answer");
						corrQues.add(rsCorrectResponse.getInt("Ques_no"));
						i++;
					}
				
					i=0;
					corrAns=ri.convertToString(correctAnswer);
				}
			request.setAttribute("corrAns", corrAns);
			request.setAttribute("questionNo", corrQues);
				rsCorrectResponse=null;
				ps=con.prepareStatement("select ques_no, ans from response where TestId=? and FileName=? order by Ques_no");
				ps.setInt(1, testid);
				ps.setString(2, sheetName.getName());
				rsCorrectResponse=ps.executeQuery();
				rsCorrectResponse.last();
				
				row=rsCorrectResponse.getRow();
				
				responseAnswer=new byte[row];
				rsCorrectResponse.beforeFirst();
					while(rsCorrectResponse.next())
					{
						responseAnswer[i]=(byte)rsCorrectResponse.getByte("ans");
						responseQues.add(rsCorrectResponse.getInt("ques_no"));
						i++;
					}
					resAns=ri.convertToString(responseAnswer);
					request.setAttribute("resQues", responseQues);
					request.setAttribute("responseAns",resAns);
					i=0;
						}catch (Exception e) {
		    	log.error("Exception in display response action"+e.getMessage());
				// TODO: handle exception
			}
			finally{
				    Connect.freeConnection(con);
		  }

		return mapping.findForward("response");
	}
}