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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("hello1")
public interface CM_connect extends RemoteService {
    
//    String methodShowOldSession(String userid);
//
//  
//
//    String methodAddInstitute(String name, String id, String address,
//        String city, String state, String pin) throws Exception;
//
//    String methodInstitutePopulate() throws Exception;
//
//    String methodAddInstituteAdmin(String instituteName, String uID,
//        String password) throws Exception;
//
//    CM_instituteInfoGetter[] methodInstituteList(String criteria, String value)
//        throws Exception;
//
//    void changePassword(String userid, String currentPassword,
//        String newPassword) throws Exception;
//
//    void changeUserPassword(String userid, String newPassword)
//        throws Exception;

    
    
    /************************************************************************************************/
    
    CM_passwordPolicyGetter[] methodPasswordPolicy() throws Exception;
    
    void methodAddEntity(String user_id,CM_entityInfoGetter entityInfo)throws Exception;
    CM_entityInfoGetter[] methodEntityList(String user_id)throws Exception;
    CM_entityInfoGetter[] methodParentEntityList(String user_id,CM_entityInfoGetter entityInfo)throws Exception;
    CM_entityInfoGetter[] methodPopulateEntitySuggest(String user_id,String entityType,String criteria)throws Exception ; 
   	CM_entityInfoGetter[] methodPopulateEntity(String user_id,String entityType,String criteria,String value)throws Exception;  
   	String methodEntityNameFromEntityID(String entity_id)throws Exception;
   	String methodUniversityName(String uni_id)throws Exception;
   	 void methodUpdateEntity(String user_id,CM_entityInfoGetter entityInfo)throws Exception;
   	void methodDeleteEntity(String entity_id)throws Exception;
   	CM_ProgramInfoGetter[] methodUniversityProgramMode(String user_id)throws Exception;
   	CM_ProgramInfoGetter[] methodUniversityProgramType(String user_id)throws Exception;
   	
   void methodAddProgDetails(String user_id, CM_progMasterInfoGetter progInfo) throws Exception;
   CM_progMasterInfoGetter[] methodprogList(String user_id);

   CM_progMasterInfoGetter[] methodProgMasterDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception;
   CM_progMasterInfoGetter[] methodProgDurationDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception;
   CM_progMasterInfoGetter[] methodProgBranchDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception;
    CM_progMasterInfoGetter[] methodProgSpecDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception;
   CM_progMasterInfoGetter[] methodProgReserveDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception;

   CM_progMasterInfoGetter[] methodBranchList(String user_id)throws Exception;
   CM_progMasterInfoGetter[] methodSpecList(String user_id)throws Exception;
   CM_progMasterInfoGetter[] methodCategoryList(String user_id)throws Exception;

   void methodUpdateProgBasicDetails(String user_id,CM_progMasterInfoGetter object) throws Exception;
   void methodDeleteProg(String program_id) throws Exception;
   void methodProgDurationDelete(String program_id,String startdate)throws Exception;
    void methodSpecDelete(String program_id,String specialization_code)throws Exception;
  void methodBranchDelete(String program_id,String branchcode)throws Exception;
  void methodSeatReservationDelete(String program_id,String category_code)throws Exception;
  void methodUpdateProgDurationDetails(String user_id,CM_progMasterInfoGetter object) throws Exception;
  void methodUpdateProgReserveDetails(String user_id,CM_progMasterInfoGetter object) throws Exception;
  
  void methodAddStartDate(String user_id,CM_progMasterInfoGetter object) throws Exception;
  void methodAddAnotherBranch(String user_id,CM_progMasterInfoGetter progInfo,String branch) throws Exception;
 void methodAddAnotherSpec(String user_id,CM_progMasterInfoGetter progInfo) throws Exception;
void methodAddAnotherCategory(String user_id,CM_progMasterInfoGetter progInfo) throws Exception;
Integer methodNumberOfTerms(String program_id) throws Exception;
void methodAddTermDetails(String user_id,CM_progTermDetailGetter object) throws Exception;
CM_progTermDetailGetter[] methodGetTermDetails(CM_progTermDetailGetter object) throws Exception;
void methodUpdateTermDetails(String user_id,CM_progTermDetailGetter object) throws Exception;
void methodDeleteProgTermDetail(CM_progTermDetailGetter object) throws Exception;

   	
}
