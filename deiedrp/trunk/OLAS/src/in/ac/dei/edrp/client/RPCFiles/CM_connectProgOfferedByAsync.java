package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_BranchSpecializationInfoGetter;
import in.ac.dei.edrp.client.CM_employeeInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CM_connectProgOfferedByAsync {
	
	void methodProgSpecList(CM_progMasterInfoGetter object,AsyncCallback<CM_progMasterInfoGetter[]> callback);
	void methodBranchSpecList(CM_progMasterInfoGetter object,String spec,AsyncCallback<CM_progMasterInfoGetter[]> callback);
	void methodMentorPopulate(String user_id,AsyncCallback<CM_BranchSpecializationInfoGetter[]> callback);
	void methodLocationPopulate(AsyncCallback<CM_employeeInfoGetter[]> callback);
	void methodProgramOfferedByPopulate(AsyncCallback<CM_BranchSpecializationInfoGetter[]> callback);
	void methodAssignProgramsToEntity(String programName, String[] branchName,
			String[] specializationName, String collegeCenter, String location,
			String seats, String mentor, String user_id,AsyncCallback<CM_BranchSpecializationInfoGetter[]> callback);
	void methodProgramOfferedByProgramList(String programOfferedBy, String value,AsyncCallback<CM_BranchSpecializationInfoGetter[]> callback);
	void methodManageProgramList(String selectedProgramOfferedColumn,AsyncCallback<CM_BranchSpecializationInfoGetter[]> callback);
	void methodEditProgramOfferedBy(CM_BranchSpecializationInfoGetter editProgramOfferedBy,AsyncCallback<CM_BranchSpecializationInfoGetter[]> callback);
	void methodDeleteProgramOfferedBy(CM_BranchSpecializationInfoGetter deleteProgramOfferedBy,AsyncCallback<String> callback); 

}
