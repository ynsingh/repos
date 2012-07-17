/**
 * @(#) ConsolidateMarksheetController.java
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
 * Redistribution in binary form must reproducuce the above copyright
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
package in.ac.dei.edrp.cms.controller.consolidatedchart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.consolidatedchart.ConsolidatedMarksheetService;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedMarkSheetInfo;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ConsolidatedMarksheetController extends MultiActionController{
	
/**
* this is Server side controller class for Generate Consolidated Marksheet
* @version 1.0 07 SEP 2011
* @author ROHIT
*/	

	/** creating object of ConsolidatedMarksheetService interface */
private ConsolidatedMarksheetService marksheetService;

/** Defining setter method for object of ConsolidatedMarksheetService Interface */
	public void setMarksheetService(ConsolidatedMarksheetService marksheetService) {
		this.marksheetService = marksheetService;
	}
	private ConsolidatedMarkSheetGeneration consolidatedMarkSheetGeneration;
	public void setConsolidatedMarkSheetGeneration(ConsolidatedMarkSheetGeneration consolidatedMarkSheetGeneration){
		this.consolidatedMarkSheetGeneration = consolidatedMarkSheetGeneration;
	}

	/**
	 * This method get entity list from database and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing entity list
	 */
	public ModelAndView getEntityLists(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		String univId=session.getAttribute("universityId").toString()+"%";
		
		ConsolidatedMarkSheetInfo input = new ConsolidatedMarkSheetInfo();
		input.setUniversityId(univId);
		
		List<ConsolidatedMarkSheetInfo> dataList = marksheetService
		.getEntitiesList(input);
		
		return new ModelAndView("gradeReportConsolidated/idDescription","resultObject",
				dataList);
	}

	/**
	 * This method get program list from database and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing program list
	 */
	public ModelAndView getProgramList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		ConsolidatedMarkSheetInfo input = new ConsolidatedMarkSheetInfo();
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setEntityId(request.getParameter("entityId"));
		
		List<ConsolidatedMarkSheetInfo> dataList = marksheetService
		.getProgramList(input);
		
		return new ModelAndView("gradeReportConsolidated/idDescription", "resultObject",
				dataList);
	}

	/**
	 * This method get branch list from database and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing branch list
	 */
	public ModelAndView getBranchList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		ConsolidatedMarkSheetInfo input = new ConsolidatedMarkSheetInfo();
		
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setEntityId(request.getParameter("entityId"));
		input.setProgramId(request.getParameter("programId"));

		List<ConsolidatedMarkSheetInfo> dataList = marksheetService
		.getBranchList(input);
		
		return new ModelAndView("gradeReportConsolidated/idDescription", "resultObject",
				dataList);
	}

	/**
	 * This method get specialization list from database and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing specialization list
	 */
	public ModelAndView getSpecializationList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		ConsolidatedMarkSheetInfo input = new ConsolidatedMarkSheetInfo();
		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setEntityId(request.getParameter("entityId"));
		input.setProgramId(request.getParameter("programId"));
		input.setBranchId(request.getParameter("branchId"));
		List<ConsolidatedMarkSheetInfo> dataList = marksheetService
		.getSpecializationList(input);
		
		return new ModelAndView("gradeReportConsolidated/idDescription", "resultObject",
				dataList);
				
	}

	/**
	 * This method get Marksheet data from database and map to a Pdf Generater
	 * @param request
	 * @param response
	 * @return ModelAndView containing data
	 */
	public ModelAndView getConsolidatedMarkSheetData(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("inside getConsolidatedMarkSheetData");
		 String message ="";
//		ConsolidatedMarkSheetGeneration consolidatedMarkSheetGeneration = new ConsolidatedMarkSheetGeneration();
		 HttpSession session = request.getSession(true);
		 if (session.getAttribute("universityId") == null) {
			 return new ModelAndView("general/SessionInactive","sessionInactive", true);
		 }
		ConsolidatedMarkSheetInfo input = new ConsolidatedMarkSheetInfo();
		String prgCode=request.getParameter("programCode");
		if(prgCode.equalsIgnoreCase("HC")||prgCode.equalsIgnoreCase("IC")||prgCode.equalsIgnoreCase("IM")||prgCode.equalsIgnoreCase("HS") ){
			input.setEntityId(request.getParameter("entityId"));
			 input.setProgramId(request.getParameter("programId"));
			 input.setBranchId(request.getParameter("branchId"));
			 input.setSpecializationId(request.getParameter("specializationId"));
			 input.setEntityName(request.getParameter("entityName"));
			 input.setProgramName(request.getParameter("programName"));
			 input.setBranchName(request.getParameter("branchName"));
			 input.setSpecializationName(request.getParameter("specializationName"));
			 input.setPassFromDate(request.getParameter("fromSession")+"%");
			 input.setPassToDate(request.getParameter("toSession")+"%");
			 /*input.setPassFromDate(request.getParameter("passFromDate")+"%");
			 input.setPassToDate(request.getParameter("passToDate")+"%");*/
			 input.setUniversityName(session.getAttribute("universityName").toString());
			 input.setUniversityId(session.getAttribute("universityId").toString());
			 String[] universitytokens = input.getUniversityName().toString().split("\\(");
			 String universityType="";
			 if(universitytokens.length==2){
				 universityType="("+universitytokens[1]+"\n";
			 }
			 String headerText1 = universitytokens[0]+"\n"+universityType+session.getAttribute("address").toString()+", "+session.getAttribute("city").toString()+
			 					"-"+session.getAttribute("pin").toString()+" ("+session.getAttribute("country").toString()+")\n";
			 
			 input.setMarksheetHeader(headerText1);
			 List<ConsolidatedMarkSheetInfo> personalDetails = marksheetService.getStudentDetails(input);		
			 List<List<ConsolidatedMarkSheetInfo>> outputDataList=new ArrayList<List<ConsolidatedMarkSheetInfo>>();
			 System.out.println("personal detail size "+personalDetails.size());
			 for (int i = 0; i < personalDetails.size(); i++) {			
				ConsolidatedMarkSheetInfo output = personalDetails.get(i);				
				output.setUniversityName(input.getUniversityName());
				output.setMarksheetHeader(input.getMarksheetHeader());
				output.setEntityId(input.getEntityId());
				output.setEntityName(input.getEntityName());
				output.setProgramId(input.getProgramId());
				output.setProgramName(input.getProgramName());
				output.setBranchId(input.getBranchId());
				output.setBranchName(input.getBranchName());
				output.setSpecializationId(input.getSpecializationId());
				output.setSpecializationName(input.getSpecializationName());
				output.setSessionStartDate(input.getSessionStartDate());
				output.setSessionEndDate(input.getSessionEndDate());
				output.setPassFromDate(input.getPassFromDate());
				output.setPassToDate(input.getPassToDate());
			/*
			 * added by noopur to get the entity address detail
			 */
				ConsolidatedMarkSheetInfo addressInfo = marksheetService.getAddressInfo(output);
				if(addressInfo!=null){
					output.setAddress(addressInfo.getAddress());
		            output.setCity(addressInfo.getCity());
		            output.setState(addressInfo.getState());
		            output.setPinCode(addressInfo.getPinCode());
		            output.setPhoneNumber(addressInfo.getPhoneNumber());
		            output.setFax(addressInfo.getFax());
				}
					
			/*output.setAddress(request.getParameter("address"));
            output.setCity(request.getParameter("city"));
            output.setState(request.getParameter("state"));
            output.setPinCode(request.getParameter("pinCode"));
            output.setPhoneNumber(request.getParameter("phoneNumber"));
            output.setFax(request.getParameter("fax"));	*/
				List<ConsolidatedMarkSheetInfo> courseDetails = marksheetService.getMarksDetails(output);
				courseDetails.add(output);
				
				outputDataList.add(courseDetails);
				System.out.println("size before sending " +outputDataList.size());
			}	
			 if(personalDetails.size()==0){
				 message ="false-No Records of student available to generate the grade report";
			 }
			 else{
				 String  processStatus = consolidatedMarkSheetGeneration.generateGradeReport(outputDataList,request);
				 if(processStatus=="true"){
					message = "true";
					}
					else{
						message = "false-There is some error in PDF Generation kindly view the error log for more information";
					}
			 }	
		}

		else{
			message = "false-Sorry this report is meant only for Highschool and Intermediate program";
		}		 		 			
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);			 
	}
	
}
