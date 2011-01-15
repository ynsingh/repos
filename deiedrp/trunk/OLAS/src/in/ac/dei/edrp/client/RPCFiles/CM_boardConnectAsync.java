package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_boardNormalizationGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;

import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The async counterpart of <code>CM_boardConnect</code>.
 */

public interface CM_boardConnectAsync {

void methodGetProgOfferingEntityList(String user_id,String program_id,AsyncCallback<CM_entityInfoGetter[]> callback);
void methodprogList(String user_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodbranchList(String program_id,String entity_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodComponentList(String program_id,String entity_id,String branch_code,AsyncCallback<CM_boardNormalizationGetter[]> callback);
void methodBoardList(AsyncCallback<CM_boardNormalizationGetter[]> callback);
void methodAddBoardNormalizationFactor(CM_boardNormalizationGetter object,AsyncCallback<String> callback);
void methodprogListForManage(String user_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodboardListForManage(CM_boardNormalizationGetter object,AsyncCallback<CM_boardNormalizationGetter[]> callback);
void methodNormalizationFactorDelete(CM_boardNormalizationGetter object,AsyncCallback<String> callback);
void methodNormalizationFactorUpdate(CM_boardNormalizationGetter object,AsyncCallback<String> callback);



}
