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

/*
   Author Name : Ruchi Bhati
 */
package in.ac.dei.edrp.client.RPCFiles;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.FormApplicationInfoGetter;
import in.ac.dei.edrp.client.ProgramDocumentInfoGetter;
import in.ac.dei.edrp.client.ProgramSearchInfoGetter;
import in.ac.dei.edrp.client.summarysheet.SummarySheetBean;



public interface CMconnectRAsync {
    //***************************my methods**********************************
    void methodgetprogramlist(
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetterms(String name,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodSetRegistrationDeadlines(String id, int sem, String gracePeriod,
        String dateSelected, String dateSelected1,
        AsyncCallback<String> asyncCallback);

    void getsemester(String id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodentitypopulate(String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentity(String entitytype, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitydetails(String entitytype, String settings,
        int systemvalue, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getprgdetails(String value1, String value2, String value3,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodupdateprgdetails(String name, int term, String graceBox,
        String dateSelected, String dateSelected1,
        AsyncCallback<String> asyncCallback);

    void getentitytype(String entitytype,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitytype(String entitytype, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getallprograms(String entitytype,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getprogramswithoutvalue(String entityname,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getdistinctprograms(String entity, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void callwithentitytype(String entitytype, String settings,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodupdatecutoffdetails(String text, String text2, 
        String string,  String string2,
        String university_id,  String settings, AsyncCallback<String> asyncCallback);

    void callwithentityname(String entityname, String settings,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void callwithprogramname(String type, String entityname,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getcosvalue(String programid, String branch_code, String string,
        String specialization_id, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodinsertdefaultsettings(String[][] arr, String category,
        int actualseats, String dateSelected, String dateSelected1,
        String university_id, AsyncCallback<String> asyncCallback);

    void getindivisualprograms(String entity, String settings,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetprograms(String entity, String university_id,
        String entityname, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodprogrammaster(String university_id, String university_id2,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetrules(AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    public void componentwithentitytype(String entitytype,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodDeletecomponentrecord(String[] Univ, String university_id,
        AsyncCallback<String> asyncCallback);

    void componentwithentityname(String entityname, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void componentwithprogramname(String type, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);
//updated by Upasana 2 May
    void getcomponent(String programid, int x, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);
//updated by Upasana 2 May
    void getsequencenumbers(String programid, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    
    //update by devendra 19 April
    void getrecords(String uniid, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    //update by devendra 19 April
    void getmaxid(String uniid,AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    //update by devendra 19 April
    void methodinsertfinalmeritdetails(String programid,
        String offered, String componentid, String description, String marks,
        String attendflag, String uniid, AsyncCallback<String> asyncCallback);

    //update by devendra 19 April
    void getdescription(String uniid,AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodDeletefromfinalmerit(String[] Univ, String university_id,
        AsyncCallback<String> asyncCallback);

    void methodinsertfirstdegreecomponents(String programid, String component,
        String university_id, AsyncCallback<String> asyncCallback);

    void degreewithentitytype(String entitytype, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void degreewithentityname(String entityname, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void degreewithprogramname(String entityname, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodinsertcutoffdetails(String cosvalue, String factor,
        String seats, String programid, String offered, String branch_code,
        String dateSelected, String dateSelected1, String university_id,
        String specialization_id, AsyncCallback<String> asyncCallback);
		
       //update by Devendra May 3rd
    void methodgetdetails(String programid,String entity, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

     //update by Devendra May 3rd
    void methodinsertcalldetails(String programid, String[][] arr, String university_id,
        String entity, AsyncCallback<String> asyncCallback);

    void getdefaultdetails(String[][] arr,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getcalledprograms(String entity, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getcos_cosdes(String programid, String branch_code,
        String specialization_id, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetdescription(String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitytypeforprogramcomponent(String entitytype,
        int systemvalue, String university_id,
        String type, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitytypeforfirstdegree(String entitytype, int systemvalue,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitydetailsfromprgregis(String entitytype,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);
//updated by Upasana 2 May
    void insertcomponentdetails(String programid, 
        String offered, String component, String type, String weightage,
        String sequence, String rule, String rawvalue, String special,
        String boardflag, String weightageflag, String eligibleflag,
        String university_id, AsyncCallback<String> asyncCallback);
//updated by Upasana 2 May
    void methodupdateprgcomponents(String text, String text3,
        String text4, String tag, String boardflag, String eligibleflag,
        String special, String weightageflag, String weightage, String rule,
        String university_id,AsyncCallback<String> asyncCallback);

    void meritwithentitytype(String entitytype, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    //update by devendra 20 April
    void methodupdatemeritcomponents(String text,String entityId, String text3,
        String boardflag, String rawValue, String university_id, AsyncCallback<String> asyncCallback);

    void meritwithentityname(String entityname, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    //update by devendra
    void meritwithprogramname(String programName,String entityName, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetprgsfromfinalmerit(String entitytype, int systemvalue,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetprogramses(String entity, String university_id,
        String entityname, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getspecialrecords(String uniid,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetdescriptions(String uniid,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetvalue(String uniid,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void insertspecialweightagedetails(String uniid, String component,
        String group, String marks, AsyncCallback<String> asyncCallback);

    void methodupdatespecialweightage(String text, String text2, String group,
        String weightage, String uniid, AsyncCallback<String> asyncCallback);

    void deletefromspecialweightage(String[] Univ,
        AsyncCallback<String> asyncCallback);

    void methodDeletedegreerecord(String[] univ, String university_id,
        AsyncCallback<String> asyncCallback);

    void methodDeleterecord(String[] univ, String university_id,
        String settings, AsyncCallback<String> asyncCallback);

    void getCosSeats(String programid, String branch_code, String offered_by,
        String specialization_id, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void gettotalseats(String programid, String branch_code, String string,
        String specialization_id, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getSeatsCos(String string,String university_id,
        String string3, String entityID,AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getseats(String programID,String entityID, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

	void executeBackup(AsyncCallback<String[]> asyncCallback);
	

	
	void getUniversityProgram(String userId,List<String> resultList, String entityIdDesc,AsyncCallback<List<FormApplicationInfoGetter>> asyncCallback);

	void setProgramForm(List<String> programId, List<String> programName, List<String> offeredByEntity, List<String> city, String formName,
			String userId,String entityID, AsyncCallback<Boolean> asyncCallback);

	void checkFormName(String appFormName, String userId, AsyncCallback<Integer> asyncCallback);

	void getFormList(String userId,String entity, AsyncCallback<List<FormApplicationInfoGetter>> asyncCallback);

	void getFormDetails(String forms, String userId,String entityID,
			AsyncCallback<List<FormApplicationInfoGetter>> asyncCallback);

	void deleteProgFormDetail(List<String> prgId, String selectedFormName,String userId, String entityID,AsyncCallback<Integer> asyncCallback);

	void getProgramsDetails(List<String> resultList, String selectedFormName,String entityId,
			String userId,
			AsyncCallback<List<FormApplicationInfoGetter>> asyncCallback);
	
	void addProgFormDetail(List<String> progId, List<String> offeredEntity, List<String> city, String selectedFormName,String formName,
			String universityId, String entityId,AsyncCallback<Integer> asyncCallback);

	void getDocumentList(String universityId,
			String pId, AsyncCallback<List<ProgramDocumentInfoGetter>> asyncCallback);
	
	void insertProgramDocument(List<String> documentList, String programId,
			String userId, AsyncCallback<Boolean> asyncCallback);

	void getAddedDocument(String userId, String programId,
			AsyncCallback<List<ProgramDocumentInfoGetter>> asyncCallback);

	void deleteProgramDocument(List<String> documents, String pName,
			String universityId, AsyncCallback<Integer> asyncCallback);

	void searchByEntity(
			String searchEntityValue,
			String userId,
			String locationValue, AsyncCallback<List<in.ac.dei.edrp.client.ProgramSearchInfoGetter>> asyncCallback);

	void searchByProgram(String searchProgramValue, String selectedEntity, 
			AsyncCallback<List<ProgramSearchInfoGetter>> asyncCallback);

	void getLocationDetails(String userId,
			AsyncCallback<List<ProgramSearchInfoGetter>> asyncCallback);

	void getEntityPrograms(String selectedEntity,
			String userId, AsyncCallback<List<ProgramSearchInfoGetter>> asyncCallback);

	void getUniversitySessionDate(String universityId,
			AsyncCallback<List<ProgramSearchInfoGetter>> asyncCallback);

	void getUniversityProgramDetails(String userId,
			AsyncCallback<List<CM_progMasterInfoGetter>> asyncCallback);
			
	void getUniversityEntities(String userId,
			AsyncCallback<List<FormApplicationInfoGetter>> asyncCallback);

 public void  getEntityNames(String university_id,AsyncCallback< CM_ProgramInfoGetter[]> callback);//Method added by Arjun
public void  getEntityPrograms(String entity_id,AsyncCallback<List<CM_progMasterInfoGetter>> callback);//Method added by Arjun


	void getFormNameProgram(String programId, String universityId,
			AsyncCallback<List<ProgramSearchInfoGetter>> asyncCallback);

	void getProgramAppliedApplicant(String userEmailId, String programId,
			AsyncCallback<Integer> asyncCallback);
			
	void setSubjectCode(String userId,String programId,String subjectCode,AsyncCallback<String>asyncCallback);//Add by Devendra May 18
	void getSubjectCode(String userId,String programId,AsyncCallback<List<CM_ProgramInfoGetter>> asyncCallback);//Add by Devendra May 18
	void deleteSubjectCode(String userId,String programId,String[] subjectCode,AsyncCallback<String>asyncCallback);//Add by Devendra May 18
	void getSubject(String userId,String programId,AsyncCallback<List<CM_ProgramInfoGetter>> asyncCallback);//Add by Devendra May 18
	void getCategorizationType(String university_id,
			AsyncCallback<List<CM_ProgramInfoGetter>> asyncCallback);

	void getEntityRelate(List<String> entityList1,
			AsyncCallback<List<FormApplicationInfoGetter>> asyncCallback);


	void getEntityProgramsLogin(String selectedEntity, String userId,
			AsyncCallback<List<ProgramSearchInfoGetter>> asyncCallback);

}
