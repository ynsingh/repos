package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_CourseInfoGetter;
import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_instituteInfoGetter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("helloTemp")
public interface CM_connectTemp extends RemoteService {

	
	String Entity_Course(String univ,String courseID, String course,boolean status);//
	
	CM_CourseInfoGetter[] getCourseCode(String univ,boolean status);//
	
	boolean checkCourseCode(String univ, String CourseId);//
	
	CM_CourseInfoGetter[] getCourseName(String univ,String courseID);//
	
	String deleteCourse(String univ,String courseID);//
	
	String CourseType(String univ,String courseTypeID, String coursetype);//
	
	CM_CourseInfoGetter[] getCourseType(String univ);//
    
    boolean checkCourseCodeType(String univ, String CourseTypeId);//
    
    CM_CourseInfoGetter[] CourseTypeName(String univ);//
    
    CM_CourseInfoGetter[] getCourseTypeName(String univ,String coursetypeID);//
    
    CM_CourseInfoGetter[] TypeID(String univ,String coursetypeName);//
	
	String deleteCourseType(String univ,String coursetypeID);//
	
	CM_ProgramInfoGetter[] getProgramme();//
	
    boolean CheckComponents(String Entity,String Program, String Branch) throws Exception;//
	
	CM_ProgramInfoGetter[] getProgrammeOff(String entity) throws Exception;
	
	CM_CourseInfoGetter[] CourseName(String univ);//
	
	CM_CourseInfoGetter[] getCourseCode(String univ,String courseName);//
	
	CM_ProgramInfoGetter[] getProgramId(String progName) throws Exception;//
	
	CM_ProgramInfoGetter[] getComponentId(String ComponentName) throws Exception;
	
	CM_ProgramInfoGetter[] getProgramOffId() throws Exception;//
	
	String programCourse(String[] program);//
	
	CM_ProgramInfoGetter[] programDetails(String[] progdetails);//
	
	String editProg(String[] editProg);//
	
	String disableProg(String courseCode);//
	
	String deleteProgAge(String[] delProg) throws Exception;
	
	String deleteStudentFD(String RegNo, String ProgId);
	
	String deleteProgCompElig(String[] delProgComp) throws Exception;
	
	boolean checkProgrammeCourse(String univ, String ProgrmId,String CourseId,String sem);//
	
	CM_ProgramInfoGetter[] FirstDegreeCode(String progId,String Type) throws Exception;//
	
	CM_ProgramInfoGetter[] getBranch(String progId, String entity_id) throws Exception;//
	
	CM_ProgramInfoGetter[] getBranchID(String progId, String branchName) throws Exception;//
	
	CM_ProgramInfoGetter[] getcos_value(String progId,String branchId, String Entity_Id ,String catCode) throws Exception;
	
	
	CM_ProgramInfoGetter[] DistinctPaperGroupingCount(String Program, String group) throws Exception;
	
	CM_ProgramInfoGetter[]  papercodeGroupCount(String progID) throws Exception;
	///////////////////////////////////
	
	 CM_instituteInfoGetter[] methodgetDesignation();
	 
	 CM_entityInfoGetter[] Entity_Description(String univID) throws Exception;
	 
	 CM_entityInfoGetter[] Entity_Name(String univID,String Type) throws Exception;
	 
	 
	 CM_entityInfoGetter[] defaultEntityType(String univID) throws Exception;
	 CM_entityInfoGetter[] defaultEntityName(String univID, String entity_type) throws Exception;
	 CM_StudentInfoGetter getEnrolledStudentPersonalInfo(String enrollNumber) throws Exception;
	 
	 
	 String checkForDefaultView() throws Exception;
	 
	 String[][] getProgramComponents(String ProgId,String branchID) throws Exception;
	    
	 String[] getProgRegNumber(String univID,String entity_id,String ProgId,String branchID) throws Exception;
	 
	 String updateProgRegNumber(String univID,String entity_id,String ProgId,String branchID,String value) throws Exception;
	
	 //////////////////////STUDENT//////////////////////
	 
	 String PersonalInfo(String[] student,String creatorId) throws Exception;
	 
	 String addressInfo(String[] address,String creatorId) throws Exception;
	 
	 int programEligibility(String[] details) throws Exception;
	 
	 int componentEligibility(String[] compdetails) throws Exception;
	 
	 String editProgAgeLimit(String[] ProgAgeDetails, String modifierID) throws Exception;//
	 
	 String editProgCompEligibility(String[] ProgAgeDetails,String modifierID) throws Exception;
	 
	 CM_ProgramInfoGetter[] getprogAgeElig(String entity_id) throws Exception;
	 
	 CM_ProgramInfoGetter[] getprogCompElig(String entity_id) throws Exception;
	 
	 String paperCodeInfo(String ProgId,String RegNo, String FormNo,String PaperCode,String entity_id,String creatorId,String grouping) throws Exception;
	 
	 String StudentFD(String ProgId,String RegNo, String FormNo,String FirstDeg,String creatorId) throws Exception;
	 
	 String StudentcallListInfo(String entity_id, String ProgId,String branchId, String RegNo,String[] data, String creatorId) throws Exception;
	 
	 String studentSpWt(String ProgId,String RegNo, String WtId, String entity_id,String creatorid_modifierid) throws Exception;
	 
	 CM_ProgramInfoGetter getStudentProgBranch(String RegNo) throws Exception;
	 
	 CM_StudentInfoGetter[] getStudentPersonalInfo(String RegNo) throws Exception;
	 
	 CM_entityInfoGetter[]  getStudentAddressInfo(String RegNo) throws Exception;
	 
	 CM_entityInfoGetter[]  getEnrolledStudentAddressInfo(String stuId) throws Exception;
	 
	 CM_StudentInfoGetter[] getStudentPaperInfo(String RegNo,String ProgId) throws Exception;
	 
	 CM_StudentInfoGetter[] getStudentFD(String RegNo,String ProgId,String ug_pg) throws Exception;
	 
	 CM_StudentInfoGetter[] getStudentSpWt(String RegNo,String ProgId) throws Exception;
	 
	 CM_StudentInfoGetter[] getStudentCallListInfo(String RegNo) throws Exception;
	 
	 CM_entityInfoGetter[] getStudentValue(String UnivId) throws Exception;
	
	 CM_StudentInfoGetter  checkDuplicacy(String[] checkData) throws Exception;
	 
	 CM_StudentInfoGetter[] getBoard() throws Exception;
	 
	 String studentReg(String StuId,String RegNo,String FormNo,String Stu_Cos, String entity_id,String creatorId) throws Exception;
	 
	 boolean[] checkRegNo(String RegNo,String FormNo) throws Exception;
	 
	 int checkReg(String RegNo) throws Exception;
	
	 CM_StudentInfoGetter checkProgIdRegNo(String ProgId,String branchId, String RegNo, String ExistingRegNo) throws Exception;
	
	 CM_StudentInfoGetter[] Category()throws Exception;
	 
	 CM_StudentInfoGetter[] getEnrolledStuCategory(String category_code) throws Exception;
	 
	 
	 /*
	  * Update Student Info
	  */
	 
	 String UpdatePersonalInfo(String[] student,String modifierId) throws Exception;
	 
	 String UpdateaddressInfo(String[] address,String modifierId) throws Exception;
	 
	 String UpdatepaperCode(String RegNo, String OldPaperCode,String NewPaperCode,String modifierid,String PaperGroup) throws Exception;
	 
	 String deleteStudentSpWt(String Wtid,String RegNo) throws Exception;
	 
	 String UpdateStudentFD(String RegNo,String FirstDegree,String type,String modifierid) throws Exception;
	 
	 String UpdatecallListInfo(String RegNo,String[] data, String modifierid) throws Exception;
	 
	 String UpdateStudentReg(String RegNo,String Stu_Cos,String modifierid) throws Exception;
	 
	
	 /////////////////////STUDENT//////////////////////
	 
}
