package in.ac.dei.edrp.client.RPCFiles;
import com.google.gwt.user.client.rpc.AsyncCallback;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
public interface FinalMeritDataServiceAsync 
{
	public void getEntityTypes(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getEntityNames(String userId,String entityType,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getProgramNames(String userId,String entityID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getFinalMeritComponents(String userID,String programID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void insertFinalMeritComponent(String programID,String entityID,String componentID,String attendanceImpact,String total_marks,String userID,String weightagePercentage,String academicImpact,AsyncCallback callback);
	public void checkFMCDetails(String programID,String entityID,String componentID,AsyncCallback<Boolean> callback);
	public void getFMCDetails(String userID,String entityID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void deleteFMCDetails(String[] param,AsyncCallback<Integer> callback);
	public void getFMCDetailsWithoutEntityType(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getFMCDetailsWithEntityType(String userID,String entityType,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void updateFMC(String entityID,String programID,String compID,String attendanceImpact,String totalMarks,String weightagePercentage, AsyncCallback<Integer> callback);
}
