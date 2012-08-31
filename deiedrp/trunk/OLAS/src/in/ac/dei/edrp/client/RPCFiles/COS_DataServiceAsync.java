package in.ac.dei.edrp.client.RPCFiles;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.SubjectCode;
import in.ac.dei.edrp.client.SystemTableTwo;

public interface COS_DataServiceAsync {
	public void  getEntityTypes(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void  getEntityNames(String userID,String entityType,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getPrograms(String entityID,String settings,String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void  getTotalSeats(String programID, String entityID, AsyncCallback<Integer> callback);//Method Added by Arjun
	public void getSessionDates(String university_id,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getCOSRecords(String program_id,String entityID,String cos_value, AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public  void getCategories(String university_id,AsyncCallback<SystemTableTwo[]> callback);//This method is added by Arjun
	public  void getGender(String university_id,AsyncCallback<SystemTableTwo[]> callback);//This method is added by Arjun
	public void insertCOS_Details(String cosvalue, String factor,
	        String seats, String programid, String offered,
	        Date dateSelected, Date dateSelected1, String university_id,
	        AsyncCallback<String> asyncCallback); //This method is modified by Arjun
	 public void getSubjectCodes(String university_id, String programID, AsyncCallback<SubjectCode[]> callback);//Method Added by Arjun
	 
	 
	 public void getEntityName(String userID,String entityID,AsyncCallback<String> callback);
	 public void getProgramName(String programID, AsyncCallback<String> callback);
	 public void getSubjectName(String userID,String subjectCode, AsyncCallback<String> callback);
	 public void getCAT_GEN(String userID,String programID,String entityID,String subjectCode, AsyncCallback<CM_ProgramInfoGetter[]> callback);
	 public void getCAT(String userID,String programID,String entityID,String subjectCode, AsyncCallback<CM_ProgramInfoGetter[]> callback);
	 public void setCOS(String[] category,String[] gender, String subjectCode, String[] seats,String[] xFactor, String entityID, String programID,String userID,Date startDate, Date endDate,int counter,AsyncCallback callback);
	 public void setCOS_WithoutGender(String[] category, String subjectCode, String[] seats,String[] xFactor, String entityID, String programID,String userID,Date startDate, Date endDate,int counter,AsyncCallback callback);
	 public void checkCOSWithoutGender(String entityID,String programID,String subjectCode,AsyncCallback<Integer> callback);
	 public void checkCOSWithGender(String entityID,String programID,String subjectCode,AsyncCallback<Integer> callback);
	 
	 
	 //code for manage COS
	 public void getCOS_Details(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	 public void deleteCOSDetails(String[] param,AsyncCallback<Integer> callback);
	 public void getCOS_DetailsWithET(String userID,String entityType,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	 public void getCOS_DetailsWithEN(String userID,String entityID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	 //Code for manage cos ends
	 
	 public void getProgramNames(String userID,String entityID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	 public void getEntranceCenter(String userID,AsyncCallback<SystemTableTwo[]> callback);
	 public void insertEntranceCenter(String userID,String entityID,String programID,String center_code,AsyncCallback callback);
	 public void  getEntranceDetails(String userID,String entityID,AsyncCallback<CM_ProgramInfoGetter[]> callback); 
	 public void deleteEntranceCenter(String[] param,String entityID,String userID,AsyncCallback<Integer> callback);
	 public void  getExaminationCenter(String entityID,String programID,String examinationCenter,AsyncCallback<CM_ProgramInfoGetter[]> callback);
}
