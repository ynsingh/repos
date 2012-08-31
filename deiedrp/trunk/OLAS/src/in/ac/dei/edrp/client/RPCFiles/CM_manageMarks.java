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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("helloManage")
public interface CM_manageMarks extends RemoteService {
	//Updated By Devendra May 3rd
    List<GridDataBean> methodProgramList(String university_id,
        String entity_type, String entity_name, String program_name) throws Exception;
  //Updated By Devendra May 3rd
    void methodAddFinalMarks(String user_id, String entity_id, String prog_id,String reg_no, String testnumber, String callMerit,
        String[] evalComp, String[] markslist, String[] attList);
  //Updated By Devendra May 3rd
    List<GridDataBean> methodEditGridDataList(String university_id,
        String entity_id, String program_id,String crieteria_value, String search_value)throws Exception;
  //Updated By Devendra May 3rd
    void methodEditFinalMarks(String user_id, String entity_id, String prog_id,String reg_no, String testnumber, String callMerit,
        String[] evalComp, String[] markslist, String[] attList);

    //updated by Devendra May 3rd
    String[] methodPopulateSuggestion(String criteria, String program_id,String entity_id);

    CM_entityInfoGetter[] methodEntityListEdit(String user_id,
        String entity_type) throws Exception;

    CM_progMasterInfoGetter[] methodProgListEdit(String entity_id)
        throws Exception;

    CM_progMasterInfoGetter[] methodBranchListEdit(String entity_id,
        String prog_id) throws Exception;

    CM_progMasterInfoGetter[] methodBranchListAdd(String entity_id,
        String prog_id) throws Exception;

    CM_progMasterInfoGetter[] methodProgListAdd(String entity_id)
        throws Exception;

    CM_progMasterInfoGetter[] methodSpecializationListEdit(String entity_id,
        String program_id, String branch_id);
    
    
    CM_progMasterInfoGetter[] methodGetPrograms(String userid);
    
    CM_progMasterInfoGetter[] methodBranchListAdd(String value);
    
    CM_progMasterInfoGetter[] methodSpecializationListEdit(String value, String value2);
    
    //updated by devendra May 3rd
    CM_progMasterInfoGetter[] getProgramComponents(String userid, String value,String name, String facregnum);    
    
    CM_progMasterInfoGetter[] getApplicantsDetails(String registration_number);
    
    CM_progMasterInfoGetter[] getcomponentWeightage(String registration_number, String component_id);
    
    CM_progMasterInfoGetter[] getPaperCodes(String registration_number);
    
    CM_progMasterInfoGetter[] getExcelComponents(String program_id,int value);
    
    String defineComponents(String[] univ, String value);
    
    String deleteComponents(String[] univ, String value);
    
    CM_progMasterInfoGetter[] methodGetDefinedPrograms(String userId);
}
