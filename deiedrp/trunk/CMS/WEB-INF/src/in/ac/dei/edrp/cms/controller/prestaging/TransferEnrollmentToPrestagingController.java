/**
 * @(#) TransferEnrollmentToPrestagingController.java
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
package in.ac.dei.edrp.cms.controller.prestaging;

import in.ac.dei.edrp.cms.dao.prestaging.TransferEnrollmentToPrestagingService;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.prestaging.PrestagingInfoGetter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for tranfer enrollment data to
 * prestaging
 * 
 * @version 1.0 12 AUGUST 2011
 * @author MOHD AMIR
 */
public class TransferEnrollmentToPrestagingController extends
		MultiActionController {
	/** creating object of transferEnrollmentToPrestagingService interface */
	private TransferEnrollmentToPrestagingService transferEnrollmentToPrestagingService;
	
	/** defining setter method for object of interface */
	public void setTransferEnrollmentToPrestagingService(
			TransferEnrollmentToPrestagingService transferEnrollmentToPrestagingService) {
		this.transferEnrollmentToPrestagingService = transferEnrollmentToPrestagingService;
	}

	/**
	 * This method transfer enrollment data to prestaging table
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing no of records transfered
	 */
	public ModelAndView transferEnrollmentToPrestaging(
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
	
		List<PrestagingInfoGetter> sucessList=new ArrayList<PrestagingInfoGetter>();
		List<PrestagingInfoGetter> errorList=new ArrayList<PrestagingInfoGetter>();
		try {
			List<EnrollmentInfo> personalData = transferEnrollmentToPrestagingService
					.getStudentPersonalData(session
							.getAttribute("universityId").toString());

			for (int i = 0; i < personalData.size(); i++) {
				
				EnrollmentInfo inputTemp = personalData.get(i);

				ConsolidatedChartBean inputForSem = new ConsolidatedChartBean();
				inputForSem.setSemSeqNo("1");
				inputForSem.setSemesterCode("%");
				inputForSem.setProgramCode(inputTemp.getProgramCode());
				List<ConsolidatedChartBean> semSeqList = transferEnrollmentToPrestagingService
						.getSemesterAndSeqNo(inputForSem);
				if (semSeqList.size() > 0) {
					inputTemp.setSemesterCode(semSeqList.get(0)
							.getSemesterCode());
				}

				List<EnrollmentInfo> addressData = transferEnrollmentToPrestagingService
						.getStudentAddressData(inputTemp.getStudentId());

				PrestagingInfoGetter dateData = transferEnrollmentToPrestagingService
						.getRegistrationDueDate(inputTemp);

				PrestagingInfoGetter dataRow = new PrestagingInfoGetter();
				dataRow.setStudentId(inputTemp.getStudentId());
				dataRow.setEnrollmentNo(inputTemp.getEnrollmentNo());
				dataRow.setRegistrationNo(inputTemp.getRegistrationNo());
				dataRow.setStudentFirstName(inputTemp.getStudentFirstName());
				dataRow.setStudentMiddleName(inputTemp.getStudentMiddleName());
				dataRow.setStudentLastName(inputTemp.getStudentLastName());
				dataRow.setFatherFirstName(inputTemp.getFatherFirstName());
				dataRow.setFatherMiddleName(inputTemp.getFatherMiddleName());
				dataRow.setFatherLastName(inputTemp.getFatherLastName());
				dataRow.setMotherFirstName(inputTemp.getMotherFirstName());
				dataRow.setMotherMiddleName(inputTemp.getMotherMiddleName());
				dataRow.setMotherLastName(inputTemp.getMotherLastName());
				dataRow.setCategory(inputTemp.getCategoryCode());
				dataRow.setDob(inputTemp.getDob());
				dataRow.setAdmissionMode("NEW");
				dataRow.setAttemptNo("1");
				dataRow.setEntityId(inputTemp.getEntityCode());
				dataRow.setProgramId(inputTemp.getProgramCode());
				dataRow.setBranchId(inputTemp.getBranchCode());
				dataRow.setSpecializationId(inputTemp.getSpecializationCode());
				dataRow.setSemesterId(inputTemp.getSemesterCode());
				dataRow.setUniversityId(inputTemp.getUniversityId());
				dataRow.setGender(inputTemp.getGender());
				dataRow.setRegistrationDueDate(dateData
						.getRegistrationDueDate());
				dataRow.setSemesterStartDate(dateData.getSemesterStartDate());
				dataRow.setSemesterEndDate(dateData.getSemesterEndDate());
				dataRow.setPrimaryEmail(inputTemp.getPrimaryMail());
				dataRow.setUserId(session.getAttribute("userId").toString());
				for (int j = 0; j < addressData.size(); j++) {
					if (addressData.get(j).getAddressKey()
							.equalsIgnoreCase("PER")) {
						dataRow.setPerAddress(addressData.get(j).getAddress());
						dataRow.setPerCity(addressData.get(j).getCity());
						dataRow.setPerState(addressData.get(j).getState());
						dataRow.setPerPinCode(addressData.get(j).getPinCode());
						dataRow.setOfficePhone(addressData.get(j)
								.getOfficePhone());
						dataRow.setHomePhone(addressData.get(j).getHomePhone());
						dataRow.setOtherPhone(addressData.get(j)
								.getOtherPhone());
						dataRow.setFax(addressData.get(j).getFax());
					} else if (addressData.get(j).getAddressKey()
							.equalsIgnoreCase("COR")) {
						dataRow.setCorAddress(addressData.get(j).getAddress());
						dataRow.setCorCity(addressData.get(j).getCity());
						dataRow.setCorState(addressData.get(j).getState());
						dataRow.setCorPinCode(addressData.get(j).getPinCode());
					}
				}
				dataRow.setRollNoGroupCode(inputTemp.getRollNoGroupCode());
				dataRow.setLongField(inputTemp.getLongField());
				
				if( transferEnrollmentToPrestagingService
						.setPrestagingData(dataRow)){
					sucessList.add(dataRow);
					
				}else{
					errorList.add(dataRow);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("systemtabletwo/countInfo", "count",
					e.getMessage());
		}
		return new ModelAndView("prestaging/insertInfo", "dataList",
				sucessList);
	}

}