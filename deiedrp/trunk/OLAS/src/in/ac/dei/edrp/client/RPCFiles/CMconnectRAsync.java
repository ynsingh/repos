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

import com.google.gwt.user.client.rpc.AsyncCallback;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;


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

    void methodupdatecutoffdetails(String text, String text2, String text3,
        String string, String startdate, String enddate, String string2,
        String university_id, AsyncCallback<String> asyncCallback);

    void callwithentityname(String entityname, String settings,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void callwithprogramname(String type, String entityname,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getcosvalue(String programid, String branch_code, String string,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodinsertdefaultsettings(String[][] arr, String category,
        int actualseats, String dateSelected, String dateSelected1,
        String university_id, AsyncCallback<String> asyncCallback);

    void getindivisualprograms(String entity, String settings,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetprograms(String entity, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

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

    void getcomponent(String programid, int x, String branchcode,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getsequencenumbers(String programid, String branchcode,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getrecords(String uniid, String branchcode,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getmaxid(String uniid, String branchcode,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodinsertfinalmeritdetails(String programid, String branchcode,
        String offered, String componentid, String description, String marks,
        String attendflag, String uniid, AsyncCallback<String> asyncCallback);

    void getdescription(String uniid, String branchcode,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

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
        AsyncCallback<String> asyncCallback);

    void methodgetdetails(String programid, String branchcode,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodinsertcalldetails(String programid, String branchcode,
        String[][] arr, String university_id,
        AsyncCallback<String> asyncCallback);

    void getdefaultdetails(String[][] arr,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getcalledprograms(String entity, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getcos_cosdes(String programid, String branch_code,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetdescription(String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitytypeforprogramcomponent(String entitytype,
        int systemvalue, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitytypeforfirstdegree(String entitytype, int systemvalue,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetentitydetailsfromprgregis(String entitytype,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void insertcomponentdetails(String programid, String branchcode,
        String offered, String component, String type, String weightage,
        String sequence, String rule, String rawvalue, String special,
        String boardflag, String weightageflag, String eligibleflag,
        String university_id, AsyncCallback<String> asyncCallback);

    void methodupdateprgcomponents(String text, String text2, String text3,
        String text4, String tag, String boardflag, String eligibleflag,
        String special, String weightageflag, String weightage, String rule,
        String university_id, AsyncCallback<String> asyncCallback);

    void meritwithentitytype(String entitytype, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodupdatemeritcomponents(String text, String text2, String text3,
        String boardflag, String rawValue, String university_id,
        AsyncCallback<String> asyncCallback);

    void meritwithentityname(String entityname, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void meritwithprogramname(String entityname, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetprgsfromfinalmerit(String entitytype, int systemvalue,
        String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void methodgetprogramses(String entity, String university_id,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

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
        AsyncCallback<String> asyncCallback);

    void getCosSeats(String programid, String branch_code, String offered_by,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void gettotalseats(String programid, String branch_code, String string,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getSeatsCos(String string, String string2, String university_id,
        String string3, AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);

    void getseats(String text, String text2,
        AsyncCallback<CM_ProgramInfoGetter[]> asyncCallback);
}
