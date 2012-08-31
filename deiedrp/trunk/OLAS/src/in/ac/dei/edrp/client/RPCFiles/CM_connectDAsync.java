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


/**
 * The async counterpart of <code>CMconnectD</code>.
 */
public interface CM_connectDAsync {

	void methodAddUniversityAdmin(boolean flagd, String universityName, String userGroupName, String userName,
			String password, String regTimeStamp, boolean activated, AsyncCallback<String> asyncCallback);

	void methodUniversityList(AsyncCallback<CM_UniversityInfoGetter[]> asyncCallback);

	void methodAddUniversity(String universityCode, Date sessionStartDate,
			Date sessionEndDate, String universityName,
			String universityAddress, String universityCity,
			String universityState, String universityPincode,
			String universityPhoneNumber, String universityOtherPhoneNumber,
			String universityFaxNumber, String universityInsertTime,
			String universityCreatorID,
			AsyncCallback<String> asyncCallback);
	
	void methodManageUniversityList(String criteria, String value, AsyncCallback<CM_UniversityInfoGetter[]> callback);

	void methodProgramList(AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodBranchList(String programValue,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodSpecializationList(String programName, String branchName,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodBranchSpecializationList(String programName,
			String branchName, String specializationName,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);
//	
//	void methodSpecializationWithProgramList(String programValue,
//			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodManageProgramList(String criteria, AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodEntityPopulate(AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);
	
	void methodMentorPopulate(AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);
	
	void methodLocationPopulate(AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodAssignProgramsToEntity(String programName, String branchName,
			String specializationName, String collegeCenter, String location,
			String seats, String mentor,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodProgramOfferedByPopulate(
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodProgramOfferedByProgramList(String programOfferedBy,
			String value,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);
	
	void methodEditProgramOfferedBy(CM_BranchSpecializationInfoGetter editProgramOfferedBy,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodDeleteProgramOfferedBy(
			CM_BranchSpecializationInfoGetter deleteProgramOfferedBy,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodShowdetailsofsubjects(String coursename, AsyncCallback<String> callback);

	void methodWeightageDescriptionPopulate(
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodGroupsPopulate(
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodAddSpecialWeightage(String weightageDescription, String group,
			String weightagePercentage, String university_id, String code,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodSpecialWeightagePopulate(String weightageDescription,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodUpdateSpecialWeightage(String grouping,
			String weightagePercentage, String weightageDescription,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodDeleteSpecialWeightage(String weightageDescription,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodWeightageDescriptionFromSpecialWeightagePopulate(
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodAddProgramFirstDegree(String programOfferedBy, String program,
			String descriptionText, String firstDegreeCodeNumber,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

//	void methodFirstDegreeDescriptionPopulate(
//			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodProgramFirstDegreePopulate(String programOfferedBy, String programDescription,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodEntityFromProgramFirstDegreePopulate(
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodProgramFromProgramFirstDegreePopulate(String programOfferedBy,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodDeleteProgramFirstDegree(
			CM_BranchSpecializationInfoGetter deleteProgramFirstDegree,
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);
	
	void methodPapersPopulate(
			AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback); 

	void methodInsertProgramPaperCode(String program, String programOfferedBy,
			String groupText, String paperCode,
			String uniid, AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodProgramPaperCodePopulate(AsyncCallback<CM_BranchSpecializationInfoGetter[]> asyncCallback);

	void methodDeleteFromProgramPaperCode(
			CM_BranchSpecializationInfoGetter deleteProgramPaperCode,
			AsyncCallback<String> asyncCallback);

//	void methodInstituteList(Object object, Object object2,
//			AsyncCallback<CM_instituteInfoGetter[]> asyncCallback);
	
	void methodGetUniversityDetail(String universityId, AsyncCallback<CM_UniversityInfoGetter[]> callback);
	
}
