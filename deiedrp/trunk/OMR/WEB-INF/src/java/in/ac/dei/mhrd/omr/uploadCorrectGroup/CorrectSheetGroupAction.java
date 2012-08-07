/*
 * @(#) CorrectSheetGroupAction.java
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
 * 
 */

package in.ac.dei.mhrd.omr.uploadCorrectGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;

import in.ac.dei.mhrd.omr.SelectTestId;
import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.Resources;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class defines the method that uploads the scanned correct answer sheet,
 * reads the data from the sheet and finally deletes the sheet
 * 
 * MyEclipse Struts 
 * Creation date: 8-12-2011
 * @author UPASANA KULSHRESTHA
 * 
 */

public class CorrectSheetGroupAction extends Action {
	private Locale obj = new Locale("en", "US");
	private ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);
	private static Logger log = Logger.getLogger(CorrectSheetGroupAction.class);

	/**
	 * This method creates a folder named "correct"(if not created) on the
	 * server, upload the correct Answer sheet inside this folder, reads the
	 * answers marked in the sheet, and finally deletes the sheet from the
	 * server. Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorrectSheetGroupForm correctSheetGroup = (CorrectSheetGroupForm) form;
	    HttpSession hs = request.getSession(false);
        if (isCancelled(request)) {
			return mapping.findForward("home");
		}
        int testid = SelectTestId.getTestId(correctSheetGroup.getTestName());
		String testname=correctSheetGroup.getTestName();		
		byte[] correctAns = null;
		ArrayList<String> ansList;
		ArrayList<String> secList=new ArrayList<String>();
		ArrayList<String> quesList=new ArrayList<String>();
		FormFile correctSheet = correctSheetGroup.getCorrectPath();
		String fileName = "";
		File fileToCreate = null;
		Connection con = null;
		String groupCode="";
		try{
			/*
			 * Create a folder named "correct" on the server and upload the
			 * correct answer sheet in this folder
			 */
			File fol = new File(getServlet().getServletContext().getRealPath("/"), "correct");
			fol.mkdir();
			String filePath = getServlet().getServletContext().getRealPath("/")	+ "correct";
			fileName=correctSheet.getFileName();
			if (!fileName.equals("")) {
				fileToCreate = new File(filePath, fileName);
				if (!fileToCreate.exists()) {
					FileOutputStream fo = new FileOutputStream(fileToCreate);
					fo.write(correctSheet.getFileData());
					fo.flush();
					fo.close();
				}
			}
			
			RotateImg correctImg = new RotateImg();
			
			String str = filePath + "/" + fileName;
			hs.setAttribute("correctGroupSheetMsg", "NA");
			/*
			 * This method reads the group code written on the sheet,
			 * and returns the count of the group code from the table 
			 */
			con = Connect.prepareConnection();
			PreparedStatement getFormat = con.prepareStatement("SELECT sheet_format,group_exists FROM testheader WHERE testId=?");
			getFormat.setInt(1, testid);
			ResultSet getFormatResult=getFormat.executeQuery();
			getFormatResult.next();
			System.out.println("getFormatResult "+getFormatResult.getString(1)+" "+getFormatResult.getString(2));
			
			PreparedStatement ps1 = con.prepareStatement("SELECT count(group_code) FROM group_table where testId=?");
            ps1.setInt(1, testid);
			ResultSet count1=ps1.executeQuery();
			count1.next();
			/*If groups exists in the Test*/
			System.out.println("count "+count1.getInt(1));
			if(getFormatResult.getString(1).equalsIgnoreCase("GRC")){
				if(count1.getInt(1)>0){
					
					groupCode=correctImg.processSheetGroup(str, testid,count1.getInt(1));
					
					if(RotateImg.flag){
						/*request.setAttribute("confirmAnsMsg", message.getString("msg.ImageFormat"));
						RotateImg.flag=false;
						return mapping.findForward("confirmAnsMsg1");*/
						hs.setAttribute("correctGroupSheetMsg", message.getString("msg.ImageFormat"));
						RotateImg.flag=false;
						return mapping.getInputForward();
					}
					PreparedStatement psst = con.prepareStatement("select count(distinct(group_code)) from correctans where testId=? and group_code =?");
		            psst.setInt(1, testid);
					psst.setString(2,groupCode);
					ResultSet countresult=psst.executeQuery();
					countresult.next();
					if(countresult.getInt(1)==1){
						/*request.setAttribute("confirmAnsMsg", message.getString("msg.GroupCodeExists"));
						RotateImg.flag=false;
						return mapping.findForward("confirmAnsMsg1");*/
						
						hs.setAttribute("correctGroupSheetMsg", message.getString("msg.GroupCodeExists"));
						return mapping.getInputForward();
					}
					if(!((groupCode==null)||groupCode.contains("NA")))
					{
						PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM testsectiongroups where testId=? and group_code in (SELECT group_code FROM group_table where group_code=?)");
			            ps.setInt(1, testid);
						ps.setString(2,groupCode);
						ResultSet count=ps.executeQuery();
						count.next();
						if(count.getInt(1)>0){
						// get total number of questions and section number in the test
							ps = con.prepareStatement("SELECT section_number,No_of_question FROM testsectiondetail where testId=? and section_number in (select section_no from testsectiongroups where testId=? and group_code=?)");
							ps.setInt(1, testid);
							ps.setInt(2, testid);
							ps.setString(3,groupCode);
							ResultSet rs = ps.executeQuery();
							int p=0; //counter to check no. of question in each section.
							while(rs.next())
							{
								secList.add(rs.getString(1));
								quesList.add(rs.getString(2));
			
								if(quesList.get(0).equals(quesList.get(p)))
								{
								/*
								 * This method reads the answers written on the sheet,
								 * and returns the arraylist containing the correct answers 
								 */
									correctAns = correctImg.processCorrectSheet(str, testid, (rs.getInt(2)));
									System.out.println("flag value in correctAns "+RotateImg.flag);
									if(!RotateImg.flag){
									/*
									 * this method returns the arraylist containg 
									 * the correct answers in user readable form
									 */
										ansList = correctImg.convertToString(correctAns);
										request.setAttribute("confirmAnsList", ansList);
										request.setAttribute("correctAnsId", String.valueOf(testid));
										request.setAttribute("path", filePath);
										request.setAttribute("Ques", quesList);
										request.setAttribute("SectionNum", secList);
										request.setAttribute("group",groupCode);
									}
									else{
										/*request.setAttribute("confirmAnsMsg", message.getString("msg.ImageFormat"));
										RotateImg.flag=false;
										return mapping.findForward("confirmAnsMsg1");*/
										RotateImg.flag=false;
										hs.setAttribute("correctGroupSheetMsg", message.getString("msg.ImageFormat"));
										return mapping.getInputForward();
									}
									
									p++;
								}
								else{
									log.error("No. of question should match for each section");
									/*request.setAttribute("confirmAnsMsg", message.getString("msg.EqualQuesInSection"));
									return mapping.findForward("confirmAnsMsg1");*/
									hs.setAttribute("correctGroupSheetMsg", message.getString("msg.EqualQuesInSection"));
									return mapping.getInputForward();
								}
							}//end while
						}
						else{
							log.error("Invalid Group Code test");
							/*request.setAttribute("confirmAnsMsg", message.getString("msg.InvalidGroupTest")+testname);
							return mapping.findForward("confirmAnsMsg1");*/
							hs.setAttribute("correctGroupSheetMsg", message.getString("msg.InvalidGroupTest")+testname);
							return mapping.getInputForward();
						}
					}
					else{
						log.error("Invalid Group Code ");
						/*request.setAttribute("confirmAnsMsg", message.getString("msg.InvalidGroup"));
						return mapping.findForward("confirmAnsMsg1");*/
						hs.setAttribute("correctGroupSheetMsg", message.getString("msg.InvalidGroup"));
						return mapping.getInputForward();
					}
				}
			}
			/*If groups do not exists in test*/
			if(getFormatResult.getString(1).equalsIgnoreCase("NGC"))
			{	
				if(count1.getInt(1)==0){
					
						PreparedStatement preparedstmnt = con.prepareStatement("select  Total_question from testheader where TestId = ?");
						preparedstmnt.setInt(1, testid);
						ResultSet results = preparedstmnt.executeQuery();
						results.next();
					
						/*
						 * This method reads the answers written on the sheet,
						 * and returns the arraylist containing the correct answers 
						 */
						correctAns = correctImg.processCorrectSheet(str, testid, (results.getInt(1)));
						/*
						 * this method returns the arraylist containg 
						 * the correct answers in user readable form
						 */
						groupCode="00";
						if(!RotateImg.flag){
							ansList = correctImg.convertToString(correctAns);
							request.setAttribute("confirmAnsList", ansList);
							request.setAttribute("correctAnsId", String.valueOf(testid));
							request.setAttribute("path", filePath);
							request.setAttribute("Ques", results.getString(1));
							request.setAttribute("group",groupCode);
							return mapping.findForward("confirmAnswers");
						}
						else{
							hs.setAttribute("correctGroupSheetMsg", message.getString("msg.ImageFormat"));
							RotateImg.flag=false;
							return mapping.getInputForward();
						}
					/*}
					else{
						request.setAttribute("confirmAnsMsg", message.getString("msg.GroupCodeZero"));
						return mapping.findForward("confirmAnsMsg1");
					}*/
				}
			}	
		}
		catch (Exception e) {
			log.error("Error in correct Sheet browse action " + e);
		} 
		finally {
			fileToCreate.delete();
			Connect.freeConnection(con);
		}
		return mapping.findForward("confirmGroupAns");
	}
}
