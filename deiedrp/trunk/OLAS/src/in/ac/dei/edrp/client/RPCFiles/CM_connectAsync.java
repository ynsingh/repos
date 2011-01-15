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



import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_passwordPolicyGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.CM_progTermDetailGetter;

import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The async counterpart of <code>CMconnect</code>.
 */
public interface CM_connectAsync {
    
//    void methodShowOldSession(String userid, AsyncCallback<String> callback);
//
//     void methodAddInstitute(String name, String id, String address,
//        String city, String state, String pin, AsyncCallback<String> callback);
//
//    void methodInstitutePopulate(AsyncCallback<String> callback);
//
//    void methodAddInstituteAdmin(String instituteName, String uID,
//        String password, AsyncCallback<String> callback);
//
//    void methodInstituteList(String criteria, String value,
//        AsyncCallback<CM_instituteInfoGetter[]> callback);
//
//    void changePassword(String userid, String currentPassword,
//        String newPassword, AsyncCallback<String> callback);
//
//    void changeUserPassword(String userid, String newPassword,
//        AsyncCallback<String> callback);

    
    /*********************************************************************************************/
    
    void methodPasswordPolicy(AsyncCallback<CM_passwordPolicyGetter[]> callback);
    
    void methodAddEntity(String user_id,CM_entityInfoGetter entityInfo,AsyncCallback<String> callback);
    
    void methodEntityList(String user_id,AsyncCallback<CM_entityInfoGetter[]> callback);
    void methodParentEntityList(String user_id,CM_entityInfoGetter entityInfo,AsyncCallback<CM_entityInfoGetter[]> callback);
   void methodPopulateEntitySuggest(String user_id,String entityType,String criteria,AsyncCallback<CM_entityInfoGetter[]> callback);
   void methodPopulateEntity(String user_id,String entityType,String criteria,String value,AsyncCallback<CM_entityInfoGetter[]> callback);  
   void methodEntityNameFromEntityID(String entity_id, AsyncCallback<String> callback);
   void methodUniversityName(String uni_id, AsyncCallback<String> callback);
   void methodUpdateEntity(String user_id,CM_entityInfoGetter entityInfo, AsyncCallback<String> callback);
   void methodDeleteEntity(String entity_id, AsyncCallback<String> callback);
   void methodUniversityProgramMode(String user_id, AsyncCallback<CM_ProgramInfoGetter[]> callback);
   void methodUniversityProgramType(String user_id, AsyncCallback<CM_ProgramInfoGetter[]> callback);
  void methodAddProgDetails(String user_id, CM_progMasterInfoGetter progInfo,AsyncCallback<String> callback);
  void methodprogList(String user_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);

void methodProgMasterDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodProgDurationDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodProgBranchDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodProgSpecDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodProgReserveDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria,AsyncCallback<CM_progMasterInfoGetter[]> callback);

void methodBranchList(String user_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodSpecList(String user_id,AsyncCallback<CM_progMasterInfoGetter[]> callback); 
void methodCategoryList(String user_id,AsyncCallback<CM_progMasterInfoGetter[]> callback);
void methodUpdateProgBasicDetails(String user_id,CM_progMasterInfoGetter object, AsyncCallback<String> callback);
void methodDeleteProg(String program_id, AsyncCallback<String> callback);
void methodProgDurationDelete(String program_id,String startdate, AsyncCallback<String> callback);
void methodSpecDelete(String program_id,String specialization_code, AsyncCallback<String> callback);
void methodBranchDelete(String program_id,String branchcode, AsyncCallback<String> callback);
void methodSeatReservationDelete(String program_id,String category_code, AsyncCallback<String> callback);
void methodUpdateProgDurationDetails(String user_id,CM_progMasterInfoGetter object, AsyncCallback<String> callback);
void methodUpdateProgReserveDetails(String user_id,CM_progMasterInfoGetter object, AsyncCallback<String> callback);

void methodAddStartDate(String user_id,CM_progMasterInfoGetter object, AsyncCallback<String> callback);

void methodAddAnotherBranch(String user_id,CM_progMasterInfoGetter progInfo,String branch, AsyncCallback<String> callback);
void methodAddAnotherSpec(String user_id,CM_progMasterInfoGetter progInfo, AsyncCallback<String> callback);
void methodAddAnotherCategory(String user_id,CM_progMasterInfoGetter progInfo, AsyncCallback<String> callback);

void methodNumberOfTerms(String program_id,AsyncCallback<Integer> callback);
void methodAddTermDetails(String user_id,CM_progTermDetailGetter object,AsyncCallback<String> callback);
void methodGetTermDetails(CM_progTermDetailGetter object,AsyncCallback<CM_progTermDetailGetter[]> callback);
void methodUpdateTermDetails(String user_id,CM_progTermDetailGetter object,AsyncCallback<String> callback);
void methodDeleteProgTermDetail(CM_progTermDetailGetter object,AsyncCallback<String> callback);




}
