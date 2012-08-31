package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("TRDataService")
public interface SetupTRDataService extends RemoteService
{
public CM_ProgramInfoGetter[] getPrograms(String userID);
public CM_ProgramInfoGetter[] getComponents(String userID,String programID);
public CM_ProgramInfoGetter[] getSubComponents(String userID,String programID,String compID);
public CM_ProgramInfoGetter[] getCalculationBasis(String userID);
public CM_ProgramInfoGetter[] getLogic(String userID);
public Integer checkTR(String userID,String programID,String compID,String paperCode,String calBasis);
public Integer checkSequenceNumber(String userID,String programID,String compID,String paperCode,String sequence,String calBasis);
public void insertTRDetails(String userID,String programID,String compID,String paperCode,String calBasis,String logic,String sequenceNo);
public CM_ProgramInfoGetter[] getTR_DetailsWithoutProgram(String userID);
public CM_ProgramInfoGetter[] getTR_DetailsWithProgram(String userID,String programID);
public Integer deleteTR(String userID,String[] param);
public CM_ProgramInfoGetter[] getPrograms_FromTR(String userID);


public CM_ProgramInfoGetter[] getUserDetails(String userID);
public CM_ProgramInfoGetter[] getFormDetails(String userID,String user_id);
public void setFormAuthority(String userID,String formNumber);
public CM_ProgramInfoGetter[] getFormAuthorityDetails(String userID);
public Integer deleteFRMAuthority(String[] param);


}
