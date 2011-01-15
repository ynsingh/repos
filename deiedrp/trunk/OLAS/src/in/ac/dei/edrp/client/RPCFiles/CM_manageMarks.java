
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


/**
 * @author Manpreet Kaur
 */

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.GridDataBean;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("helloManage")

public interface CM_manageMarks extends RemoteService {

	List<GridDataBean> methodProgramList(String university_id,
			String entity_type, String entity_name, String program_name,String branch_name)throws Exception;
	
	 void methodAddFinalMarks(String user_id,String entity_id,String prog_id,String branch_id,
			String reg_no,String testnumber,String callMerit,String[] evalComp,String[] markslist, String[] attList);
	
	List<GridDataBean> methodEditGridDataList(String university_id,
				String entity_id, String program_id,String branch_id, String crieteria_value, String search_value) throws Exception;

	void methodEditFinalMarks(String user_id,String entity_id,String prog_id,String branch_id,
			String reg_no,String testnumber,String callMerit,String[] evalComp,String[] markslist, String[] attList);

	String[] methodPopulateSuggestion(String criteria,String program_id,String branch_id,String entity_id);
	
	CM_entityInfoGetter[] methodEntityListEdit(String user_id,String entity_type)throws Exception;
	CM_progMasterInfoGetter[] methodProgListEdit(String entity_id)throws Exception;
	CM_progMasterInfoGetter[] methodBranchListEdit(String entity_id,String prog_id)throws Exception;
	CM_progMasterInfoGetter[] methodBranchListAdd(String entity_id,String prog_id)throws Exception;
	CM_progMasterInfoGetter[] methodProgListAdd(String entity_id)throws Exception;

	
}
