/**
 * @(#) UpdatePrestagingController.java
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
package in.ac.dei.edrp.cms.controller.updateprestaging;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.updateprestaging.UpdatePrestagingConnect;
import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;
import in.ac.dei.edrp.cms.domain.studentremedial.StudentRemedialInfoGetter;
import in.ac.dei.edrp.cms.domain.updateprestaging.UpdatePrestagingInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UpdatePrestagingController extends MultiActionController {
	
/**
* this is Server side controller class for Update Pre Registration Data
* @version 1.0 3 AUG 2011
* @author ROHIT
*/
	
@SuppressWarnings("unused")
/** creating object of UpdatePrestaging interface */
private UpdatePrestagingConnect updatePrestagingConnect;

/** defining setter method for object of UpdatePrestaging interface */
public void setUpdatePrestagingConnect(UpdatePrestagingConnect updatePrestagingConnect) {
	this.updatePrestagingConnect = updatePrestagingConnect;
}

/**
 * This method Fetch the record needs updation and map to a jsp
 * @param request
 * @param response
 * @return ModelAndView containing Error records
 */
public ModelAndView getRecords(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
			
	UpdatePrestagingInfoGetter input = new UpdatePrestagingInfoGetter();

	 HttpSession session = request.getSession(true);
    String userId = (String) session.getAttribute("userId");
    String universityId = (String) session.getAttribute("universityId");		
		if(userId == null)
     {
     return new ModelAndView("general/SessionInactive","sessionInactive",true);
     }
	
    input.setUniversityId(universityId);
    System.out.println(input.getUniversityId()+"rrrrr cehck");
	List<UpdatePrestagingInfoGetter> resultDetails = updatePrestagingConnect
			.getRecords(input);
	
	return new ModelAndView("UpdatePrestaging/PrestagingDetails",
			"resultObject", resultDetails);

}

/**
 * Method for deleting records
 * @param request
 * @param response
 * @return ModelAndView containing String of sucess or failure
 * @throws Exception
 */
 public ModelAndView deleteRecords(HttpServletRequest request,
     HttpServletResponse response) throws Exception {
     
     HttpSession session = request.getSession(true);

     String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
     {
     return new ModelAndView("general/SessionInactive","sessionInactive",true);
     }
		StringTokenizer rollNo =new StringTokenizer(request.getParameter("regRollNo"),"|");
		StringTokenizer entNo =new StringTokenizer(request.getParameter("newEntityId"),"|");
		StringTokenizer prgNo =new StringTokenizer(request.getParameter("newProgramId"),"|");
		StringTokenizer brnNo =new StringTokenizer(request.getParameter("newBranchId"),"|");
		StringTokenizer spcNo =new StringTokenizer(request.getParameter("newSpecializationId"),"|");
		StringTokenizer semNo =new StringTokenizer(request.getParameter("newSemesterId"),"|");
		StringTokenizer admNo =new StringTokenizer(request.getParameter("admissionMode"),"|");
		StringTokenizer atmNo =new StringTokenizer(request.getParameter("attemptNumber"),"|");
		StringTokenizer enrNo =new StringTokenizer(request.getParameter("enrollNo"),"|");
		List<UpdatePrestagingInfoGetter> inputs=new ArrayList<UpdatePrestagingInfoGetter>();
		while(rollNo.hasMoreTokens())
		{
			UpdatePrestagingInfoGetter input = new UpdatePrestagingInfoGetter();        
		    
			input.setRegRollNo(rollNo.nextToken());
			input.setNewEntityId(entNo.nextToken());
			input.setNewProgramId(prgNo.nextToken());
			input.setNewBranchId(brnNo.nextToken());
			input.setNewSpecializationId(spcNo.nextToken());
			input.setNewSemesterId(semNo.nextToken());
			input.setAdmissionMode(admNo.nextToken());
			input.setAttemptNumber(atmNo.nextToken());
			input.setEnrollNo(enrNo.nextToken());
			
			inputs.add(input);
		}
     
     String resultDeleteDetails = updatePrestagingConnect.deletePrestagingRecords(inputs);

     return new ModelAndView("preProcessChecks/preProcessResultlist",
         "resultObject", resultDeleteDetails);
 }

 /**
  * This method is used for update the record
  * @param request
  * @param response
  * @return ModelAndView containing String of sucess or failure
  * @throws Exception
  */
 public ModelAndView updateRecords(HttpServletRequest request,
     HttpServletResponse response) throws Exception {
     UpdatePrestagingInfoGetter detail = new UpdatePrestagingInfoGetter();

     HttpSession session = request.getSession(true);

     String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
     {
     return new ModelAndView("general/SessionInactive","sessionInactive",true);
     }
		
		detail.setUserId(userId);
		detail.setRegRollNo(request.getParameter("regRollNo"));
		detail.setEnrollNo(request.getParameter("enrollNo"));
		detail.setStudentfname(request.getParameter("studentfname"));
		detail.setStudentmname(request.getParameter("studentmname"));
		detail.setStudentlname(request.getParameter("studentlname"));
		detail.setFatherfname(request.getParameter("fatherfname"));
		detail.setFathermname(request.getParameter("fathermname"));
		detail.setFatherlname(request.getParameter("fatherlname"));
		detail.setMotherfname(request.getParameter("motherfname"));
		detail.setMothermname(request.getParameter("mothermname"));
		detail.setMotherlname(request.getParameter("motherlname"));
		detail.setPrimaryMail(request.getParameter("primaryMail"));
		detail.setDob(request.getParameter("dob"));
		detail.setGender(request.getParameter("gender"));
		detail.setCategoryId(request.getParameter("category"));
		detail.setNewEntityId(request.getParameter("newEntityId"));
		detail.setNewProgramId(request.getParameter("newProgramId"));
		detail.setNewBranchId(request.getParameter("newBranchId"));
		detail.setNewSpecializationId(request.getParameter("newSpecializationId"));
		detail.setNewSemesterId(request.getParameter("newSemesterId"));
		detail.setAttemptNumber(request.getParameter("attemptNumber"));
		detail.setAdmissionMode(request.getParameter("admissionMode"));
		//detail.setProcesssedFlag(request.getParameter("processsedFlag"));
		detail.setRegistrationDueDate(request.getParameter("registrationDueDate"));
		detail.setSemesterStartDate(request.getParameter("semesterStartDate"));
		detail.setSemesterEndDate(request.getParameter("semesterEndDate"));
		detail.setProcessStatus(null);
		//detail.setReasoncode(request.getParameter("reasoncode"));
		//detail.setDescription(request.getParameter("description"));
		detail.setPerAddress(request.getParameter("perAddress"));
		detail.setPerCity(request.getParameter("perCity"));
		detail.setPerState(request.getParameter("perState"));
		detail.setPerPincode(request.getParameter("perPincode"));
		detail.setCorAddress(request.getParameter("corAddress"));
		detail.setCorCity(request.getParameter("corCity"));
		detail.setCorState(request.getParameter("corState"));
		detail.setCorPincode(request.getParameter("corPincode"));
		detail.setOfficePhone(request.getParameter("officePhone"));
		detail.setExtraPhone(request.getParameter("extraPhone"));
		detail.setOtherPhone(request.getParameter("otherPhone"));
		detail.setFax(request.getParameter("fax"));
		detail.setUpdRegRollNo(request.getParameter("updRegRollNo"));
		detail.setUpdEnrollNo(request.getParameter("updEnrollNo"));
		detail.setUpdNewEntity(request.getParameter("updNewEntity"));
		detail.setUpdNewProgram(request.getParameter("updNewProgram"));
		detail.setUpdNewBranch(request.getParameter("updNewBranch"));
		detail.setUpdNewSpecialization(request.getParameter("updNewSpecialization"));
		detail.setUpdNewSemester(request.getParameter("updNewSemester"));
		detail.setUpdAttemptNumber(request.getParameter("updAttemptNumber"));
		detail.setUpdAdmissionMode(request.getParameter("updAdmissionMode"));


     String resultSetSuccess = updatePrestagingConnect.updatePrestagingDetails(detail);
     	
     return new ModelAndView("preProcessChecks/preProcessResultlist",
         "resultObject", resultSetSuccess);
 }



}
