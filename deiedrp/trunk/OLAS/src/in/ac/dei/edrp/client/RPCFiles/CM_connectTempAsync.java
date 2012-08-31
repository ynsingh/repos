package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_CourseInfoGetter;
import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_instituteInfoGetter;
import in.ac.dei.edrp.client.ReportInfoGetter;
import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CM_connectTempAsync {
    void Entity_Course(String univ, String courseID, String course,
        boolean status, AsyncCallback<String> callback);

    void getCourseCode(String univ, boolean status,
        AsyncCallback<CM_CourseInfoGetter[]> callback);

    void checkCourseCode(String univ, String CourseId,
        AsyncCallback<Boolean> callback);

    void getCourseName(String univ, String courseID,
        AsyncCallback<CM_CourseInfoGetter[]> callback);

    void deleteCourse(String univ, String courseID,
        AsyncCallback<String> callback);

    void CourseType(String univ, String courseTypeID, String coursetype,
        AsyncCallback<String> callback);

    void getCourseType(String univ,
        AsyncCallback<CM_CourseInfoGetter[]> callback);

    void checkCourseCodeType(String univ, String CourseTypeId,
        AsyncCallback<Boolean> callback);

    void CourseTypeName(String univ,
        AsyncCallback<CM_CourseInfoGetter[]> callback);

    void getCourseTypeName(String univ, String coursetypeID,
        AsyncCallback<CM_CourseInfoGetter[]> callback);

    void TypeID(String univ, String coursetypeName,
        AsyncCallback<CM_CourseInfoGetter[]> callback);

    void deleteCourseType(String univ, String coursetypeID,
        AsyncCallback<String> callback);

    void getProgramme(AsyncCallback<CM_ProgramInfoGetter[]> callback);

    //update by devendra 18 April
    void CheckComponents(String Entity, String Program ,AsyncCallback<Boolean> callback); //

    void getProgrammeOff(String entity,
        int counter, AsyncCallback<CM_ProgramInfoGetter[]> callback);

    void CourseName(String univ, AsyncCallback<CM_CourseInfoGetter[]> callback);

    void getCourseCode(String univ, String courseName,
        AsyncCallback<CM_CourseInfoGetter[]> callback);

    void getProgramId(String progName,
        AsyncCallback<CM_ProgramInfoGetter[]> callback);

    void getComponentId(String ComponentName,
        AsyncCallback<CM_ProgramInfoGetter[]> callback);

    void getProgramOffId(AsyncCallback<CM_ProgramInfoGetter[]> callback); //

    void programCourse(String[] program, AsyncCallback<String> asyncCallback);

    void programDetails(String[] progdetails,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void FirstDegreeCode(String progId, String Type,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback); //

    void getBranch(String progId, String entity_id, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback); //

    void getBranchID(String progId, String branchName,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback); //

    void getcos_value(String progId, String Entity_Id,
        String catCode,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void papercodeGroupCount(String progId,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void DistinctPaperGroupingCount(String ProgID, String group,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void editProg(String[] editProg, AsyncCallback<String> asyncCallback);

    void disableProg(String courseCode, AsyncCallback<String> asyncCallback);

    //Update by Devendra June 8 
    void deleteProgAge(List<CM_ProgramInfoGetter> delProg, AsyncCallback<String> asyncCallback);

    void deleteStudentFD(String RegNo, String ProgId,
        AsyncCallback<String> asyncCallback);

    void deleteProgCompElig(String[] delProgComp,
        AsyncCallback<String> asyncCallback);

    void checkProgrammeCourse(String univ, String ProgrmId, String CourseId,
        String sem, AsyncCallback<Boolean> callback); //

    /////////////////////////////////
    void methodgetDesignation(AsyncCallback<CM_instituteInfoGetter[]> callback);

    void Entity_Description(String univID,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void Entity_Name(String univID, String Type,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void defaultEntityType(String univID,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void defaultEntityName(String univID, String entity_type,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void checkForDefaultView(AsyncCallback<CM_entityInfoGetter[]> asyncCallback);

    void getEnrolledStudentPersonalInfo(String enrollNumber,
        AsyncCallback<CM_StudentInfoGetter> asyncCallback);

    //update by devendra 18 April
    void getProgramComponents(String ProgID, String entity_id, AsyncCallback<String[][]> asyncCallback);

    void getProgRegNumber(String univID, String entity_id, String ProgId,
        AsyncCallback<String[]> callback);

    void updateProgRegNumber(String univID, String entity_id, String ProgId,
        String branchID, String value, String specializationId,
        AsyncCallback<String> callback);

    void PersonalInfo(String[] student, String creatorId,
        AsyncCallback<String> asyncCallback);

    void addressInfo(String[] address, String creatorId,
        AsyncCallback<String> asyncCallback);

    void programEligibility(String[] details,
        AsyncCallback<Integer> asyncCallback);

    void componentEligibility(String[] compdetails,
        AsyncCallback<Integer> asyncCallback);

    //Update by Devendra June 8
    void editProgAgeLimit(CM_ProgramInfoGetter input,AsyncCallback<String> asyncCallback); //

    void editProgCompEligibility(String[] ProgAgeDetails, String modifierID,
        AsyncCallback<String> asyncCallback);

    //Update by Devendra June 8
    void getprogAgeElig(String entity_id,AsyncCallback<List<CM_ProgramInfoGetter>> callback);

    void getprogCompElig(String entity_id,
        AsyncCallback<CM_ProgramInfoGetter[]> callback);

    void paperCodeInfo(String ProgId, String RegNo, String FormNo,
        String PaperCode, String entity_id, String creatorId, String grouping,
        AsyncCallback<String> asyncCallback);

    void StudentcallListInfo(String entity_id, String ProgId, String branchId,
        String specializationId, String newRegNo, String[] data,
        String creatorId, AsyncCallback<String> asyncCallback);

    void studentSpWt(String ProgId, String RegNo, String WtId,
        String entity_id, String creatorid_modifierid,
        AsyncCallback<String> asyncCallback);

    void getStudentProgBranch(String RegNo,
        AsyncCallback<CM_ProgramInfoGetter> callback);

    void getStudentPersonalInfo(String RegNo,
        AsyncCallback<CM_StudentInfoGetter[]> callback);

    void getStudentAddressInfo(String RegNo,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void getEnrolledStudentAddressInfo(String stuId,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void getStudentPaperInfo(String RegNo, String ProgId,
        AsyncCallback<CM_StudentInfoGetter[]> callback);

    void getStudentFD(String RegNo, String ProgId, String ug_pg,
        AsyncCallback<CM_StudentInfoGetter[]> callback);

    void getStudentSpWt(String RegNo, String ProgId,
        AsyncCallback<CM_StudentInfoGetter[]> callback);

    void getStudentCallListInfo(String RegNo,
        AsyncCallback<CM_StudentInfoGetter[]> callback);

    void getStudentValue(String UnivId,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void checkDuplicacy(String[] checkData,
        AsyncCallback<CM_StudentInfoGetter> asyncCallback);

    void StudentFD(String ProgId, String RegNo, String FormNo, String FirstDeg,
        String creatorId, AsyncCallback<String> asyncCallback);

    void studentReg(String StuId, String RegNo, String FormNo, String Stu_Cos,
        String entity_id, String creatorId, AsyncCallback<String> asyncCallback);

    void checkProgIdRegNo(String ProgId, String branchId, String RegNo,
        String ExistingRegNo, String specializationId,
        AsyncCallback<CM_StudentInfoGetter> asyncCallback);

    void checkRegNo(String RegNo, String FormNo,
        AsyncCallback<boolean[]> asyncCallback);

    void checkReg(String RegNo, AsyncCallback<Integer> asyncCallback);

    void Category(String userId,
        AsyncCallback<CM_StudentInfoGetter[]> asyncCallback);

    void getEnrolledStuCategory(String category_code,
        AsyncCallback<CM_StudentInfoGetter[]> asyncCallback);

    void getBoard(AsyncCallback<CM_StudentInfoGetter[]> asyncCallback);

    /*
     * Update Student Info
     */
    void UpdatePersonalInfo(String[] student, String modifierId,
        AsyncCallback<String> asyncCallback);

    void UpdateaddressInfo(String[] address, String modifierId,
        AsyncCallback<String> asyncCallback);

    void UpdatepaperCode(String RegNo, String OldPaperCode,
        String NewPaperCode, String modifierid, String PaperGroup,
        AsyncCallback<String> asyncCallback);

    void deleteStudentSpWt(String Wtid, AsyncCallback<String> asyncCallback);

    void UpdateStudentFD(String RegNo, String FirstDegree, String type,
        String modifierid, AsyncCallback<String> asyncCallback);

    void UpdatecallListInfo(String RegNo, String[] data, String modifierid,
        AsyncCallback<String> asyncCallback);

    void UpdateStudentReg(String RegNo, String Stu_Cos, String modifierid,
        AsyncCallback<String> asyncCallback);

    void getSpecializationDetails(String entity_id, String progID,
        String branchId, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void checkFirstSemSpecialization(String progID, String branchId,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getSpecialization(String[] speciaId, String progID, String branchId,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

	void getOfferedByDetails(String string, String string2, AsyncCallback<CM_entityInfoGetter[]> asyncCallback);

	void setApplicantRecord(CM_StudentInfoGetter studentInfoGetter,
			String[] spwt, String[][] hello, String[][] paperCodes, String firstDeg, AsyncCallback<String> asyncCallback);

	void checkStudentValidation(CM_StudentInfoGetter studentInfoGetter,
			AsyncCallback<Boolean> asyncCallback);

	void getRegistrationNumber(String programId,
			String facultyRegno, AsyncCallback<String> asyncCallback);

	void deleteStudentRecord(String regNo,
			AsyncCallback<Boolean> asyncCallback);

	 void getSubject(String progId, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback); //add by Devendra	
	 void getStateData(AsyncCallback<String[][]> asyncCallback);//add by Devendra
	 void getCityData(String state,AsyncCallback<String[][]> asyncCallback);//add by Devendra
	 
	 void getComponentList(String programId,String entityId,AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);//Add by Devendra
	 void getOmrTestId(String date,AsyncCallback<List<ReportInfoGetter>> asyncCallback);//Add by Devendra May 10
    //////////////////////////////////
}
