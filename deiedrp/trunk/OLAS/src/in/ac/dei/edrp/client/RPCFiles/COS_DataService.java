package in.ac.dei.edrp.client.RPCFiles;

import java.util.Date;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.SubjectCode;
import in.ac.dei.edrp.client.SystemTableTwo;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("cos_service")
public interface COS_DataService extends RemoteService{
	public CM_ProgramInfoGetter[] getEntityTypes(String userID);
	public CM_ProgramInfoGetter[] getEntityNames(String userID,String entityType);
	public CM_ProgramInfoGetter[] getPrograms(String entityID,String settings,String userID);
	 public Integer getTotalSeats(String programID,String entityID);//Method added by Arjun
	 public CM_ProgramInfoGetter[] getSessionDates(String university_id);
	 public CM_ProgramInfoGetter[] getCOSRecords(String program_id,String entityID,String cos_value);
	 public  SystemTableTwo[] getCategories(String university_id);
	 public  SystemTableTwo[] getGender(String university_id);//This method is added by Arjun
	 public String insertCOS_Details(String cosvalue, String factor,
		        String seats, String programid, String offered, 
		        Date dateSelected, Date dateSelected1, String uniid
		        ); 
	 public SubjectCode[] getSubjectCodes(String university_id, String programID);//Method added by Arjun
	 
	 
	 public String getEntityName(String userID,String entityID);
	 public String getProgramName(String programID);
	 public CM_ProgramInfoGetter[] getCAT_GEN(String userID,String programID,String entityID,String subjectCode);
	 public CM_ProgramInfoGetter[] getCAT(String userID,String programID,String entityID,String subjectCode);
	 public String getSubjectName(String userID,String subjectCode);
	 public void setCOS(String[] category,String[] gender, String subjectCode, String[] seats,String[] xFactor, String entityID, String programID,String userID,Date startDate, Date endDate,int counter);
	 public void setCOS_WithoutGender(String[] category, String subjectCode, String[] seats,String[] xFactor, String entityID, String programID,String userID,Date startDate, Date endDate,int counter);
	 public Integer checkCOSWithoutGender(String entityID,String programID,String subjectCode);
	 public Integer checkCOSWithGender(String entityID,String programID,String subjectCode);
	 
	 
	 //code for manage COS
	 public CM_ProgramInfoGetter[] getCOS_Details(String userID);
	 public CM_ProgramInfoGetter[] getCOS_DetailsWithET(String userID,String entityType);
	 public CM_ProgramInfoGetter[] getCOS_DetailsWithEN(String userID,String entityID);
	 public Integer deleteCOSDetails(String[] param);
	 //Code for manage cos ends
	 
	 
	 public CM_ProgramInfoGetter[] getProgramNames(String userID,String entityID);
	 public SystemTableTwo[] getEntranceCenter(String userID);
	 public void insertEntranceCenter(String userID,String entityID,String programID,String center_code);
	 public CM_ProgramInfoGetter[] getEntranceDetails(String userID,String entityID); 
	 public Integer deleteEntranceCenter(String[] param,String entityID,String userID);
	 public CM_ProgramInfoGetter[] getExaminationCenter(String entityID,String programID,String examinationCenter);
}
