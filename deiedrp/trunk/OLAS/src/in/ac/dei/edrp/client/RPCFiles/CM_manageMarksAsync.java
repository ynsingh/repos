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
	//Updated by Devendra May 3rd
    void methodProgramList(String university_id, String entity_type,
        String entity_name, String program_name,AsyncCallback<List<GridDataBean>> callback);
    //Updated by Devendra May 3rd
    void methodAddFinalMarks(String user_id, String entity_id, String prog_id,String reg_no, String testnumber, String callMerit,
        String[] evalComp, String[] markslist, String[] attList,AsyncCallback<String> callback);
    //Updated by Devendra May 3rd
    void methodEditGridDataList(String university_id, String entity_id,
        String program_id, String crieteria_value,
        String search_value,AsyncCallback<List<GridDataBean>> callback);
  //Updated by Devendra May 3rd
    void methodEditFinalMarks(String user_id, String entity_id, String prog_id,String reg_no, String testnumber, String callMerit,
        String[] evalComp, String[] markslist, String[] attList,AsyncCallback<String> callback);

    //updated by Devendra May 3rd
    void methodPopulateSuggestion(String criteria, String program_id,String entity_id,AsyncCallback<String[]> callback);

    void methodEntityListEdit(String user_id, String entity_type,
        AsyncCallback<CM_entityInfoGetter[]> callback);

    void methodProgListEdit(String entity_id,
        AsyncCallback<CM_progMasterInfoGetter[]> callback);

    void methodBranchListEdit(String entity_id, String prog_id,
        AsyncCallback<CM_progMasterInfoGetter[]> callback);

    void methodBranchListAdd(String entity_id, String prog_id,
        AsyncCallback<CM_progMasterInfoGetter[]> callback);

    void methodProgListAdd(String entity_id,
        AsyncCallback<CM_progMasterInfoGetter[]> callback);

    void methodSpecializationListEdit(String value, String value2,
        String value3, AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);

	void methodGetPrograms(String userid,
			AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);
	
	void methodBranchListAdd(String value,
			AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);

	void methodSpecializationListEdit(String value, String value2,
			AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);

	//updated by devendra May 3rd
	void getProgramComponents(String userid, String value,
			String name, String facregnum, AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);


	void getApplicantsDetails(String registration_number,
			AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);

	void getcomponentWeightage(String registration_number, String component_id,
			AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);

	void getPaperCodes(String registration_number,
			AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);

	void getExcelComponents(String value,
			int value2, AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);

	void defineComponents(String[] univ, String value,
			AsyncCallback<String> asyncCallback);

	void deleteComponents(String[] univ, String value,
			AsyncCallback<String> asyncCallback);

	void methodGetDefinedPrograms(String userId,
			AsyncCallback<CM_progMasterInfoGetter[]> asyncCallback);
}
