package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("final-merit-data-service")
public interface FinalMeritDataService extends RemoteService
{
public CM_ProgramInfoGetter[] getEntityTypes(String userID);
public CM_ProgramInfoGetter[] getEntityNames(String userId,String entityType);
public CM_ProgramInfoGetter[] getProgramNames(String userId,String entityID);
public CM_ProgramInfoGetter[] getFinalMeritComponents(String userID,String programID);
public void insertFinalMeritComponent(String programID,String entityID,String componentID,String attendanceImpact,String total_marks,String userID,String weightagePercentage,String academicImpact);
public Boolean checkFMCDetails(String programID,String entityID,String componentID);
public CM_ProgramInfoGetter[] getFMCDetails(String userID,String entityID);
public Integer deleteFMCDetails(String[] param);
public CM_ProgramInfoGetter[] getFMCDetailsWithoutEntityType(String userID);
public CM_ProgramInfoGetter[] getFMCDetailsWithEntityType(String userID,String entityType);
public Integer updateFMC(String entityID,String programID,String compID,String attendanceImpact,String totalMarks,String weightagePercentage);
}
