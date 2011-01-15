package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_boardNormalizationGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;



/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("board")
public interface CM_boardConnect extends RemoteService{
	CM_entityInfoGetter[] methodGetProgOfferingEntityList(String user_id,String program_id) throws Exception;
	CM_progMasterInfoGetter[] methodprogList(String user_id);
	CM_progMasterInfoGetter[] methodbranchList(String program_id,String entity_id);
	CM_boardNormalizationGetter[] methodComponentList(String program_id,String entity_id,String branch_code);
	CM_boardNormalizationGetter[] methodBoardList();
	 void methodAddBoardNormalizationFactor(CM_boardNormalizationGetter object) throws Exception;
	 CM_progMasterInfoGetter[] methodprogListForManage(String user_id);
	CM_boardNormalizationGetter[] methodboardListForManage(CM_boardNormalizationGetter object);
	void methodNormalizationFactorDelete(CM_boardNormalizationGetter object);
	void methodNormalizationFactorUpdate(CM_boardNormalizationGetter object);
}
