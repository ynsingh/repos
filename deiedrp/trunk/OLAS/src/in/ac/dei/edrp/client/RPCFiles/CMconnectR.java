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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.FormApplicationInfoGetter;
import in.ac.dei.edrp.client.ProgramDocumentInfoGetter;
import in.ac.dei.edrp.client.ProgramSearchInfoGetter;
import in.ac.dei.edrp.client.summarysheet.SummarySheetBean;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("helloR")
public interface CMconnectR extends RemoteService {
    //***************************my methods**********************************
    CM_ProgramInfoGetter[] methodgetprogramlist() throws Exception;
    
    CM_ProgramInfoGetter[] methodgetterms(String name)
        throws Exception;

    String methodSetRegistrationDeadlines(String id, int semester,
        String gracePeriod, String dateSelected, String dateSelected1);

    CM_ProgramInfoGetter[] getsemester(String id);

    CM_ProgramInfoGetter[] methodentitypopulate(String university_id)
        throws Exception;

    CM_ProgramInfoGetter[] methodgetentity(String entity, String university_id)
        throws Exception;

    CM_ProgramInfoGetter[] methodgetentitydetails(String entitytype,
        String settings, int systemvalue, String university_id)
        throws Exception;

    CM_ProgramInfoGetter[] getprgdetails(String value1, String value2,
        String value3) throws Exception;

    String methodupdateprgdetails(String name, int sem, String value,
        String value1, String value2) throws Exception;

    CM_ProgramInfoGetter[] getentitytype(String entitytype);

    CM_ProgramInfoGetter[] methodgetentitytype(String entitytype,
        String university_id);

    CM_ProgramInfoGetter[] getallprograms(String entitytype);

    CM_ProgramInfoGetter[] getprogramswithoutvalue(String entityname);

    CM_ProgramInfoGetter[] getdistinctprograms(String entityname,
        String university_id);

    CM_ProgramInfoGetter[] getindivisualprograms(String entity,
        String settings, String university_id);

    String methodinsertcutoffdetails(String cosvalue, String factor,
        String seats, String programid, String offered, String branch_code,
        String dateSelected, String dateSelected1, String uniid,
        String specialization_id);

    CM_ProgramInfoGetter[] callwithentitytype(String entitytype,
        String settings, String university_id);

    String methodupdatecutoffdetails(String text, String text2, String value,
          String string2,
        String uniid,  String settings);

    CM_ProgramInfoGetter[] callwithentityname(String entityname,
        String settings, String university_id);

    CM_ProgramInfoGetter[] callwithprogramname(String value1, String value2,
        String university_id);

    String methodDeleterecord(String[] Univ, String university_id,
        String settings);

    CM_ProgramInfoGetter[] getcosvalue(String programid, String branch_code,
        String string, String specialization_id);

    String methodinsertdefaultsettings(String[][] arr, String category,
        int actualseats, String dateSelected, String dateSelected1, String uniid);

    CM_ProgramInfoGetter[] methodgetprograms(String entitytype,
        String university_id, String entityname);

    CM_ProgramInfoGetter[] methodprogrammaster(String university_id,
        String university_id2);

    CM_ProgramInfoGetter[] methodgetrules();

//updated by Upasana 2 May
    String insertcomponentdetails(String programid, 
        String offered, String component, String type, String weightage,
        String sequence, String rule, String rawvalue, String special,
        String boardflag, String weightageflag, String eligibleflag,
        String university_id);

    CM_ProgramInfoGetter[] componentwithentitytype(String entitytype,
        String university_id);

    String methodDeletecomponentrecord(String[] Univ, String university_id);

    CM_ProgramInfoGetter[] componentwithentityname(String entityname,
        String university_id);

    CM_ProgramInfoGetter[] componentwithprogramname(String value,
        String university_id);

//updated by Upasana 2 May
    CM_ProgramInfoGetter[] getcomponent(String programid, int x);
//updated by Upasana 2 May
    CM_ProgramInfoGetter[] getsequencenumbers(String programid);

    //update by devendra 19 April
    CM_ProgramInfoGetter[] getrecords(String uniid);

    //update by devendra 19 April
    CM_ProgramInfoGetter[] getmaxid(String uniid);

    //update by devendra 19 April
    String methodinsertfinalmeritdetails(String programid,
        String offered, String componentid, String description, String marks,
        String attendflag, String uniid);

    //update by devendra 19 April
    CM_ProgramInfoGetter[] getdescription(String uniid);

    String methodDeletefromfinalmerit(String[] Univ, String university_id);

    String methodinsertfirstdegreecomponents(String programid,
        String componentname, String uniid);

    CM_ProgramInfoGetter[] degreewithentitytype(String entitytype,
        String university_id);

    CM_ProgramInfoGetter[] degreewithentityname(String entityname,
        String university_id);

    CM_ProgramInfoGetter[] degreewithprogramname(String entityname,
        String university_id);

    //update by Devebndra May 3rd
    CM_ProgramInfoGetter[] methodgetdetails(String programid,String entity);

     //update by Devebndra May 3rd
    String methodinsertcalldetails(String programid,
        String[][] arr, String uniid,String entity);  

    CM_ProgramInfoGetter[] getdefaultdetails(String[][] arr);

    CM_ProgramInfoGetter[] getcalledprograms(String entity, String university_id);

    CM_ProgramInfoGetter[] getcos_cosdes(String programid, String branch_code,
        String specialization_id);

    CM_ProgramInfoGetter[] methodgetdescription(String university_id);

    CM_ProgramInfoGetter[] methodgetentitytypeforprogramcomponent(
        String entitytype, int systemvalue, String university_id, String type);

    CM_ProgramInfoGetter[] methodgetentitytypeforfirstdegree(
        String entitytype, int systemvalue, String university_id);

    CM_ProgramInfoGetter[] methodgetentitydetailsfromprgregis(String entitytype);
//updated by Upasana 2 May
    String methodupdateprgcomponents(String text,String text3,
        String text4, String tag, String boardflag, String eligibleflag,
        String special, String weightageflag, String weightage, String rule,
        String univ_id);

    CM_ProgramInfoGetter[] meritwithentitytype(String entitytype,
        String university_id);

    //update by devendra 20 April
    String methodupdatemeritcomponents(String text,String entity_id, String text3,
        String boardflag, String rawValue, String uniid);

    CM_ProgramInfoGetter[] meritwithentityname(String entityname,
        String university_id);

    //update by devebndra 20 April
    CM_ProgramInfoGetter[] meritwithprogramname(String programName,String entityName,
        String university_id);

    CM_ProgramInfoGetter[] methodgetprgsfromfinalmerit(String entitytype,
        int systemvalue, String university_id);

    CM_ProgramInfoGetter[] methodgetprogramses(String entity, String uniid,
        String entityname);

    CM_ProgramInfoGetter[] methodgetdescriptions(String uniid);

    CM_ProgramInfoGetter[] methodgetvalue(String uniid);

    String insertspecialweightagedetails(String uniid, String component,
        String group, String marks);

    CM_ProgramInfoGetter[] getspecialrecords(String uniid);

    String methodupdatespecialweightage(String text, String text2,
        String group, String weightage, String uniid);

    String deletefromspecialweightage(String[] Univ);

    String methodDeletedegreerecord(String[] univ, String uniid);

    CM_ProgramInfoGetter[] getCosSeats(String programid, String branch_code,
        String offered_by, String specialization_id);

    CM_ProgramInfoGetter[] gettotalseats(String programid, String branch_code,
        String string, String specialization_id);

    CM_ProgramInfoGetter[] getSeatsCos(String string,
        String university_id, String string3,String entityID);

    CM_ProgramInfoGetter[] getseats(String programID,String entityID);
    
    /*
     * added for backup execution method
     */
    String[] executeBackup();
    
    
    List<FormApplicationInfoGetter>getUniversityProgram(String userId,List<String> resultList,String entityIdDesc);
    
    Boolean setProgramForm(List<String> programId,List<String> programName,List<String> offeredByEntity,List<String> city, String formName, String userId,String entityID);
    
    int checkFormName(String AppFormName, String universityId);
    
    List<FormApplicationInfoGetter> getFormList(String universityId,String entity) throws Exception;
    
    List<FormApplicationInfoGetter> getFormDetails(String forms,String universityId,String entityID);
    
    int deleteProgFormDetail(List<String> progFormDetails, String selectedFormName, String userId,String entityID) throws Exception;
    
    List<FormApplicationInfoGetter> getProgramsDetails(List<String> resultList,String selectedFormName,String entityId,String userId);
    
    int addProgFormDetail(List<String> ProgFormDetails, List<String> offeredEntity, List<String> city, String selectedFormName,String formName,String userId,String entityID) throws Exception;
    
    
    List<ProgramDocumentInfoGetter> getDocumentList(String userId,String pId);
    
    Boolean insertProgramDocument(List<String> documentList, String formNumber, String userId);
    
    List<ProgramDocumentInfoGetter> getAddedDocument(String userId, String pId);
    
    int deleteProgramDocument(List<String> docId, String pName,String universityId);
    
    
    List<ProgramSearchInfoGetter> searchByEntity(String searchEntityValue,String userId,String locationValue);
    
    List<ProgramSearchInfoGetter> searchByProgram(String searchProgramValue, String selectedEntity);
    
    List<ProgramSearchInfoGetter> getLocationDetails( String userId);
    
    List<ProgramSearchInfoGetter> getEntityPrograms(String selectedEntity,String userId);
    
    List<ProgramSearchInfoGetter> getUniversitySessionDate(String universityId);
    
    List<CM_progMasterInfoGetter>getUniversityProgramDetails(String uni);
    
    List<FormApplicationInfoGetter> getUniversityEntities(String userId);
    
	List<ProgramSearchInfoGetter> getFormNameProgram(String programId, String universityId);
	
	Integer getProgramAppliedApplicant(String userEmailId, String programId);

	public CM_ProgramInfoGetter[] getEntityNames(String university_id);//Method added by Arjun
	public List<CM_progMasterInfoGetter> getEntityPrograms(String entity_id);//Method added by Arjun
	
	String setSubjectCode(String userId,String programId,String subjectCode);//Add by Devendra May 18
    List<CM_ProgramInfoGetter>getSubjectCode(String userId,String programId);//Add by Devendra May 18
    String deleteSubjectCode(String userId,String programId,String[]subjectCode);//Add by Devendra May 18
    List<CM_ProgramInfoGetter>getSubject(String userId,String programId);//Add by Devendra May 18
	List<CM_ProgramInfoGetter> getCategorizationType(String university_id); 
	
	List<FormApplicationInfoGetter> getEntityRelate(List<String> entityList1); //Add by Upasana
	

	List<ProgramSearchInfoGetter> getEntityProgramsLogin(String selectedEntity, String userId);
 
}
