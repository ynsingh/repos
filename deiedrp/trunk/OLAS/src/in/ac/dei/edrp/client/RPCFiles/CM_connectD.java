/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
package in.ac.dei.edrp.client.RPCFiles;


/**
 * @author Dayal Sharan Sukhdhami
 */
import in.ac.dei.edrp.client.CM_BranchSpecializationInfoGetter;
import in.ac.dei.edrp.client.CM_UniversityInfoGetter;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("helloD")
public interface CM_connectD extends RemoteService {
	
	String methodAddUniversity(String universityCode, Date sessionStartDate, Date sessionEndDate,
    		String universityName, String universityAddress, String universityCity, String universityState,
    		String universityPincode, String universityPhoneNumber, String universityOtherPhoneNumber,
            String universityFaxNumber, String universityInsertTime, String universityCreatorID) throws Exception;
	

	void methodAddUniversityAdmin(boolean flagd, String universityName, String userGroupName, String userName,
			String password, String regTimeStamp, boolean activated);
	
	CM_UniversityInfoGetter[] methodUniversityList() throws Exception;
	
	CM_UniversityInfoGetter[] methodManageUniversityList(String criteria, String value) throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodProgramList() throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodBranchList(String programValue) throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodSpecializationList(String programValue, String branchValue) throws Exception;

	CM_BranchSpecializationInfoGetter[] methodBranchSpecializationList(String programName, String branchName, String specializationName) throws Exception;

	CM_BranchSpecializationInfoGetter[] methodManageProgramList(String criteria) throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodEntityPopulate() throws Exception;
    
	CM_BranchSpecializationInfoGetter[] methodMentorPopulate() throws Exception;

	CM_BranchSpecializationInfoGetter[] methodLocationPopulate() throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodAssignProgramsToEntity(String programName, String branchName,
			String specializationName, String collegeCenter, String location, String seats, String mentor)
					throws Exception;	
	
	CM_BranchSpecializationInfoGetter[] methodProgramOfferedByPopulate() throws Exception; 
	
	CM_BranchSpecializationInfoGetter[] methodProgramOfferedByProgramList(String programOfferedBy,
			String value) throws Exception;


	void methodEditProgramOfferedBy(CM_BranchSpecializationInfoGetter editProgramOfferedBy) throws Exception; 
	
	void methodDeleteProgramOfferedBy(CM_BranchSpecializationInfoGetter deleteProgramOfferedBy) throws Exception; 
	
	String methodShowdetailsofsubjects(String coursename);

	CM_BranchSpecializationInfoGetter[] methodWeightageDescriptionPopulate()
			throws Exception;


	CM_BranchSpecializationInfoGetter[] methodGroupsPopulate() throws Exception;
	
	void methodAddSpecialWeightage(String weightageDescription, String group, String weightagePercentage, String university_id, String code) throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodSpecialWeightagePopulate(String weightageDescription) throws Exception;

	void methodUpdateSpecialWeightage(
			String grouping, String weightagePercentage,
			String weightageDescription) throws Exception;

	void methodDeleteSpecialWeightage(
			String weightageDescription) throws Exception;

	CM_BranchSpecializationInfoGetter[] methodWeightageDescriptionFromSpecialWeightagePopulate()
			throws Exception;
	
	void methodAddProgramFirstDegree(String programOfferedBy, String program, String descriptionText,
			String firstDegreeCodeNumber) throws Exception;
	
//	CM_BranchSpecializationInfoGetter[] methodFirstDegreeDescriptionPopulate() throws Exception;	
	
	CM_BranchSpecializationInfoGetter[] methodProgramFirstDegreePopulate(String programOfferedBy, String programDescription)
	throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodEntityFromProgramFirstDegreePopulate() throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodProgramFromProgramFirstDegreePopulate(String programOfferedBy) throws Exception;

	CM_BranchSpecializationInfoGetter[] methodPapersPopulate() throws Exception; 
	
	void methodDeleteProgramFirstDegree(
			CM_BranchSpecializationInfoGetter deleteProgramFirstDegree)
			throws Exception;
	
	void methodInsertProgramPaperCode(String uniid, String program, String programOfferedBy,
			String groupText, String paperCode) throws Exception;
	
	CM_BranchSpecializationInfoGetter[] methodProgramPaperCodePopulate() throws Exception;


	void methodDeleteFromProgramPaperCode(
			CM_BranchSpecializationInfoGetter deleteProgramPaperCode)
			throws Exception;
	
	CM_UniversityInfoGetter[] methodGetUniversityDetail(String universityId);
	
}
