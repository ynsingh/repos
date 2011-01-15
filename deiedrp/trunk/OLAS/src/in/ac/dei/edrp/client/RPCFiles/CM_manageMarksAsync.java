/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
package in.ac.dei.edrp.client.RPCFiles;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.GridDataBean;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The async counterpart of <code>CM_manageMarks</code>.
 */

public interface CM_manageMarksAsync {
	void methodProgramList(String university_id,
			String entity_type, String entity_name, String program_name,String branch_name,AsyncCallback<List<GridDataBean>> callback);
	
	 void methodAddFinalMarks(String user_id,String entity_id,String prog_id,String branch_id,String reg_no,
			String testnumber,String callMerit,String[] evalComp,String[] markslist,String[] attList,AsyncCallback<String> callback);

	void methodEditGridDataList(String university_id,
				String entity_id, String program_id,String branch_id, String crieteria_value, String search_value,AsyncCallback<List<GridDataBean>> callback);
	
	void methodEditFinalMarks(String user_id,String entity_id,String prog_id,String branch_id,String reg_no,
			String testnumber,String callMerit,String[] evalComp,String[] markslist,String[] attList,AsyncCallback<String> callback); 

	void methodPopulateSuggestion(String criteria,String program_id,String branch_id,String entity_id,AsyncCallback<String[]> callback);

	void methodEntityListEdit(String user_id,String entity_type,AsyncCallback<CM_entityInfoGetter[]> callback);
	void methodProgListEdit(String entity_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
	void methodBranchListEdit(String entity_id,String prog_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
	void methodBranchListAdd(String entity_id,String prog_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
	void methodProgListAdd(String entity_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);

}
