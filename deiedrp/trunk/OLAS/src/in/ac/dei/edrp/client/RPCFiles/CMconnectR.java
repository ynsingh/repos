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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;


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
        String dateSelected, String dateSelected1, String uniid);

    CM_ProgramInfoGetter[] callwithentitytype(String entitytype,
        String settings, String university_id);

    String methodupdatecutoffdetails(String text, String text2, String value,
        String value2, String startdate, String enddate, String string2,
        String uniid);

    CM_ProgramInfoGetter[] callwithentityname(String entityname,
        String settings, String university_id);

    CM_ProgramInfoGetter[] callwithprogramname(String value1, String value2,
        String university_id);

    String methodDeleterecord(String[] Univ, String university_id);

    CM_ProgramInfoGetter[] getcosvalue(String programid, String branch_code,
        String string);

    String methodinsertdefaultsettings(String[][] arr, String category,
        int actualseats, String dateSelected, String dateSelected1, String uniid);

    CM_ProgramInfoGetter[] methodgetprograms(String entitytype,
        String university_id);

    CM_ProgramInfoGetter[] methodprogrammaster(String university_id,
        String university_id2);

    CM_ProgramInfoGetter[] methodgetrules();

    String insertcomponentdetails(String programid, String branchcode,
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

    CM_ProgramInfoGetter[] getcomponent(String programid, int x,
        String branchcode);

    CM_ProgramInfoGetter[] getsequencenumbers(String programid,
        String branchcode);

    CM_ProgramInfoGetter[] getrecords(String uniid, String branchcode);

    CM_ProgramInfoGetter[] getmaxid(String uniid, String branchcode);

    String methodinsertfinalmeritdetails(String programid, String branchcode,
        String offered, String componentid, String description, String marks,
        String attendflag, String uniid);

    CM_ProgramInfoGetter[] getdescription(String uniid, String branchcode);

    String methodDeletefromfinalmerit(String[] Univ, String university_id);

    String methodinsertfirstdegreecomponents(String programid,
        String componentname, String uniid);

    CM_ProgramInfoGetter[] degreewithentitytype(String entitytype,
        String university_id);

    CM_ProgramInfoGetter[] degreewithentityname(String entityname,
        String university_id);

    CM_ProgramInfoGetter[] degreewithprogramname(String entityname,
        String university_id);

    CM_ProgramInfoGetter[] methodgetdetails(String programid, String branchcode);

    String methodinsertcalldetails(String programid, String branchcode,
        String[][] arr, String uniid);

    CM_ProgramInfoGetter[] getdefaultdetails(String[][] arr);

    CM_ProgramInfoGetter[] getcalledprograms(String entity, String university_id);

    CM_ProgramInfoGetter[] getcos_cosdes(String programid, String branch_code);

    CM_ProgramInfoGetter[] methodgetdescription(String university_id);

    CM_ProgramInfoGetter[] methodgetentitytypeforprogramcomponent(
        String entitytype, int systemvalue, String university_id);

    CM_ProgramInfoGetter[] methodgetentitytypeforfirstdegree(
        String entitytype, int systemvalue, String university_id);

    CM_ProgramInfoGetter[] methodgetentitydetailsfromprgregis(String entitytype);

    String methodupdateprgcomponents(String text, String text2, String text3,
        String text4, String tag, String boardflag, String eligibleflag,
        String special, String weightageflag, String weightage, String rule,
        String univ_id);

    CM_ProgramInfoGetter[] meritwithentitytype(String entitytype,
        String university_id);

    String methodupdatemeritcomponents(String text, String text2, String text3,
        String boardflag, String rawValue, String uniid);

    CM_ProgramInfoGetter[] meritwithentityname(String entityname,
        String university_id);

    CM_ProgramInfoGetter[] meritwithprogramname(String entityname,
        String university_id);

    CM_ProgramInfoGetter[] methodgetprgsfromfinalmerit(String entitytype,
        int systemvalue, String university_id);

    CM_ProgramInfoGetter[] methodgetprogramses(String entity, String uniid);

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
        String offered_by);

    CM_ProgramInfoGetter[] gettotalseats(String programid, String branch_code,
        String string);

    CM_ProgramInfoGetter[] getSeatsCos(String string, String string2,
        String university_id, String string3);

    CM_ProgramInfoGetter[] getseats(String text, String text2);
}
