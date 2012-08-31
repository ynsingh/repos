package in.ac.dei.edrp.client.RPCFiles;

import com.google.gwt.user.client.rpc.AsyncCallback;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;

public interface SetupTRDataServiceAsync 
{
	public void getPrograms(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getComponents(String userID,String programID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getSubComponents(String userID,String programID,String compID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getCalculationBasis(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getLogic(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void checkTR(String userID,String programID,String compID,String paperCode,String calBasis,AsyncCallback<Integer> callback);
	public void checkSequenceNumber(String userID,String programID,String compID,String paperCode,String sequence,String calBasis,AsyncCallback<Integer> callback);
	public void insertTRDetails(String userID,String programID,String compID,String paperCode,String calBasis,String logic,String sequenceNo,AsyncCallback callback);
	public void getTR_DetailsWithoutProgram(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getTR_DetailsWithProgram(String userID,String programID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void deleteTR(String userID,String[] param,AsyncCallback<Integer> callback);
	public void getPrograms_FromTR(String userID,AsyncCallback<CM_ProgramInfoGetter[]> callback);
	
	
	public void getUserDetails(String userID, AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void getFormDetails(String userID,String user_id, AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void setFormAuthority(String userID,String formNumber, AsyncCallback callback);
	public void getFormAuthorityDetails(String userID, AsyncCallback<CM_ProgramInfoGetter[]> callback);
	public void deleteFRMAuthority(String[] param, AsyncCallback<Integer> callback);
}
