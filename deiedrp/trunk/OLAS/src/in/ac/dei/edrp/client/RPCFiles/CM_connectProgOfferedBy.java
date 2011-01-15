package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_BranchSpecializationInfoGetter;
import in.ac.dei.edrp.client.CM_employeeInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("progOfferedBy")
public interface CM_connectProgOfferedBy extends RemoteService {
	
	public CM_progMasterInfoGetter[] methodProgSpecList(CM_progMasterInfoGetter object)throws Exception;
	public CM_progMasterInfoGetter[] methodBranchSpecList(CM_progMasterInfoGetter object, String spec)throws Exception;
	public CM_BranchSpecializationInfoGetter[] methodMentorPopulate(String user_id)throws Exception;
	public CM_employeeInfoGetter[] methodLocationPopulate()throws Exception;
	public CM_BranchSpecializationInfoGetter[] methodProgramOfferedByPopulate()throws Exception;
	public CM_BranchSpecializationInfoGetter[] methodAssignProgramsToEntity(String programName, String[] branchName,
			String[] specializationName, String collegeCenter, String location,
			String seats, String mentor, String user_id) throws Exception;
	public CM_BranchSpecializationInfoGetter[] methodProgramOfferedByProgramList(
			String programOfferedBy, String value) throws Exception;
	 public CM_BranchSpecializationInfoGetter[] methodManageProgramList(String selectedProgramOfferedColumn)throws Exception;
	    public void methodEditProgramOfferedBy(CM_BranchSpecializationInfoGetter editProgramOfferedBy) throws Exception ;
		public void methodDeleteProgramOfferedBy(CM_BranchSpecializationInfoGetter deleteProgramOfferedBy) throws Exception; 

}
