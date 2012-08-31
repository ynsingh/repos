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
   Author Name :Ashish Yadav
 */
package in.ac.dei.edrp.server;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;
import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.FormApplicationInfoGetter;
import in.ac.dei.edrp.client.ProgramDocumentInfoGetter;
import in.ac.dei.edrp.client.ProgramSearchInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.summarysheet.SummarySheetBean;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;
import in.ac.dei.edrp.server.summarysheet.SendEmail;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;


/* The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CM_connectRImpl extends RemoteServiceServlet implements CMconnectR {
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetprogramlist()
        throws Exception {
        List l1;

        try {
            l1 = (List) client.queryForList("selectprogramlist", null);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (SQLException e) {
            logObj.logger.error(e);
        } catch (Exception e1) {
            logObj.logger.error(e1);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetterms(String name)
        throws Exception {
        List l1;

        try {
            l1 = (List) client.queryForList("getno_terms", null);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (SQLException e) {
            logObj.logger.error(e);
        } catch (Exception e1) {
            logObj.logger.error(e1);
        }

        return null;
    }

    public String methodSetRegistrationDeadlines(String id, int sem,
        String gracePeriod, String dateSelected, String dateSelected1) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            cmp.setAdd_drop_period(gracePeriod);
            cmp.setEntity_program_term_id(sem);
            cmp.setProgram_id(id);
            cmp.setLast_date(dateSelected1);
            cmp.setRegistration_start_date(dateSelected);
            cmp.setInsert_time(date);

            client.insert("registrationdetails", cmp);
        } catch (SQLException e) {
            e.printStackTrace();
            logObj.logger.error("methodSetRegistrationDeadlines" + e);
        }

        return null;
    }

    public CM_ProgramInfoGetter[] getsemester(String id) {
        try {
            //            return (List) client.queryForList("selectterms", id);
        } catch (Exception e) {
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodentitypopulate(String user_id)
        throws Exception {
        CM_ProgramInfoGetter infoGetter = new CM_ProgramInfoGetter();

        infoGetter.setUniversity_id(user_id.substring(1, 5));
        infoGetter.setGroup_code("ENTTYP");
        List l1;

        try {
            l1 = (List) client.queryForList("getComponentsInfo", infoGetter);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodentitypopulate " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetentity(String entity,
        String university_id) throws Exception {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setEntity_id(entity);

            String uni_id;

            uni_id = university_id.substring(1, 5);

            cmp.setUniversity_code(uni_id);

            l1 = (List) client.queryForList("getentityname", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getentity" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetentitydetails(String entitytype,
        String settings, int systemvalue, String university_id)
        throws Exception {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;
        String uni_id = university_id.substring(1, 5);

        try {
            if (systemvalue == 1) {
                cmp.setEntity_type(entitytype);
                cmp.setSettings(settings);
                cmp.setUniversity_id(uni_id);

                l1 = (List) client.queryForList("getprogramname", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }

            if (systemvalue == 0) {
                cmp.setEntity_type(entitytype);
                cmp.setSettings(settings);
                cmp.setUniversity_id(uni_id);

                l1 = (List) client.queryForList("getprogramname1", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }
        } catch (Exception e) {
            logObj.logger.error("exception in get program names" + e);
        }

        return null;
    }

    public CM_ProgramInfoGetter[] getprgdetails(String value1, String value2,
        String value3) throws Exception {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setEntity_type(value1);
            cmp.setEntity_name(value2);
            cmp.setProgram_name(value3);

            //            return (List) client.queryForList("getprgdetails", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in getprgdetails" + e);
        }

        return null;
    }

    public String methodupdateprgdetails(String name, int sem,
        String gracePeriod, String date2, String date1)
        throws Exception {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            cmp.setAdd_drop_period(gracePeriod);
            cmp.setEntity_program_term_id(sem);
            cmp.setProgram_name(name);
            cmp.setLast_date(date1);
            cmp.setRegistration_start_date(date2);
            cmp.setModification_time(date);

            client.update("updateprgdetails", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in methodupdateprgdetails" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getentitytype(String entitytype) {
        List l1;

        try {
            l1 = (List) client.queryForList("getentitytype", entitytype);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodupdateprgdetails" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetentitytype(String entitytype,
        String userid) {
        List l1;
        

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id;

        try {
            cmp.setEntity_description(entitytype);
            
            System.out.println("on server" + entitytype + userid);

            uni_id = userid.substring(1, 5);
            
            System.out.println("here"+uni_id);            

            cmp.setUniversity_code(uni_id);

            l1 = (List) client.queryForList("getentityty", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("getentityty" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getallprograms(String entitytype) {
        @SuppressWarnings("unused")
        List l1;

        try {
            l1 = (List) client.queryForList("getallprograms", entitytype);

            //            return (List) client.queryForList("getallprograms", entitytype);
        } catch (Exception e) {
            logObj.logger.error("getentityty" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getprogramswithoutvalue(String entityname) {
        @SuppressWarnings("unused")
        List l1;

        try {
            l1 = (List) client.queryForList("getallprogramswithotuvalue",
                    entityname);

            //            return (List) client.queryForList("getallprogramswithotuvalue",
            //                entityname);
        } catch (Exception e) {
            logObj.logger.error("getprogramswithoutvalue" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getdistinctprograms(String entityid,
        String university_id) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setEntity_id(entityid);

            String uni_id;

            uni_id = university_id.substring(1, 5);

            cmp.setUniversity_code(uni_id);
            l1 = (List) client.queryForList("getdistinctprograms", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getdistinctprograms" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getindivisualprograms(String entityid,
        String settings, String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        String uni_id;

        uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_id(entityid);
            cmp.setSettings(settings);
            cmp.setUniversity_id(uni_id);

            l1 = (List) client.queryForList("getindivisualprograms", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in indivisual programs = " + e);
        }

        return null;
    }

    public String methodinsertcutoffdetails(String cosvalue, String factor,
        String seats, String programid, String offered, String branch_code,
        String dateSelected, String dateSelected1, String uniid,
        String specialization_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            cmp.setCos_value(cosvalue);
            cmp.setNo_of_times(factor);
            cmp.setNo_of_times_active("I");
            cmp.setNo_of_seats(seats);
            cmp.setInsert_time(date);
            cmp.setProgram_id(programid);
            cmp.setEntity_id(offered);
            cmp.setBranch_code(branch_code);
            cmp.setCut_off_number(0);
            cmp.setCut_off_number_active("A");
            cmp.setCut_off_percentage(0);
            cmp.setCut_off_percentage_active("I");
            cmp.setSettings("I");
            cmp.setSession_start_date(dateSelected);
            cmp.setSession_end_date(dateSelected1);
            cmp.setCreator_id(uniid);
            cmp.setSpecialization_id(specialization_id);

            client.insert("insertcutoffdetails", cmp);
        } catch (SQLException e) {
            e.printStackTrace();
            logObj.logger.error("exception in methodinsertcutoffdetails = " +
                e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] callwithentitytype(String entitytype,
        String settings, String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;
        String uni_id;

        uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_type(entitytype);
            cmp.setSettings(settings);
            cmp.setUniversity_id(uni_id);

            l1 = (List) client.queryForList("callwithentitytype", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error(" exception in callwithentitytype" + e);
        }

        return null;
    }

    public String methodupdatecutoffdetails(String program_id,
        String cos_value, String no_of_times, String no_of_seats,
          String uniid,
         String settings) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String univ_id = uniid.substring(1, 5);

        try {
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            cmp.setProgram_id(program_id);
            cmp.setCos_value(cos_value);
            cmp.setNo_of_times(no_of_times);
            cmp.setNo_of_seats(no_of_seats);
            
            cmp.setModification_time(date);
           
            cmp.setModifier_id(uniid);
            cmp.setUniversity_id(univ_id);
            
            cmp.setSettings(settings);

            client.update("updatewithentitytype", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in update call cut off =" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] callwithentityname(String entityname,
        String settings, String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;
        String uni_id;

        uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_id(entityname);
            cmp.setSettings(settings);
            cmp.setUniversity_id(uni_id);
            l1 = (List) client.queryForList("callwithentityname", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("callwithentityname" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] callwithprogramname(String value1,
        String value2, String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        logObj.logger.error("values" + value1 + value2);

        List l1;
        String uni_id;

        uni_id = university_id.substring(1, 5);

        try {
            cmp.setProgram_name(value1);
            cmp.setSettings(value2);
            cmp.setUniversity_id(uni_id);

            l1 = (List) client.queryForList("callwithallvalues", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("callwithprogramname" + e);
        }

        return null;
    }
    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getcosvalue(String programid,
        String branch_code, String string, String specialization_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setProgram_id(programid);
            cmp.setBranch_code(branch_code);
            cmp.setEntity_id(string);
            cmp.setSpecialization_id(specialization_id);

            l1 = client.queryForList("getcosvalue", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("getcosvalue" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getdefaultdetails(String[][] arr) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        List l1;

        try {
            for (int i = 0; i < arr.length; i++) {
                cpm.setProgram_id(arr[i][0]);
                cpm.setBranch_code(arr[i][1]);
                cpm.setEntity_id(arr[i][2]);
                cpm.setSpecialization_id(arr[i][3]);
                cpm.setUniversity_id(arr[i][2].substring(0, 4));
            }

            try {
                l1 = (List) client.queryForList("defaultdetails", cpm);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            } catch (Exception e1) {
                logObj.logger.error("getdefaultdetails" + e1);
            }
        } catch (Exception e) {
            logObj.logger.error("exception in getdefaultrecords" + e);
        }

        return null;
    }

    public String methodinsertdefaultsettings(String[][] arr, String category,
        int actualseats, String dateSelected, String dateSelected1, String uniid) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();

        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        String cos_value = category + "XX";

        try {
            for (int i = 0; i < arr.length; i++) {
                cpm.setProgram_id(arr[i][0]);
                cpm.setBranch_code(arr[i][1]);
                cpm.setEntity_id(arr[i][2]);
                cpm.setSpecialization_id(arr[i][3]);
                cpm.setCos_value(cos_value);
                cpm.setNo_of_times("1");
                cpm.setNo_of_times_active("I");
                cpm.setDefaultseats(actualseats);
                cpm.setCut_off_number(0);
                cpm.setCut_off_number_active("A");
                cpm.setCut_off_percentage(0);
                cpm.setCut_off_percentage_active("I");
                cpm.setSettings("D");
                cpm.setInsert_time(date);
                cpm.setSession_start_date(dateSelected);
                cpm.setSession_end_date(dateSelected1);
                cpm.setCreator_id(uniid);

                client.insert("insertdefaultdetails", cpm);
            }
        } catch (Exception e) {
            logObj.logger.error("exception in default settings " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetprograms(String entitytype,
        String university_id, String entityname) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        List l1;

        String uni_id = university_id.substring(1, 5);

        try {
            cpm.setEntity_description(entitytype);
            cpm.setUniversity_id(uni_id);
            cpm.setEntity_name(entityname);

            l1 = (List) client.queryForList("methodgetprograms", cpm);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodgetprograms" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodprogrammaster(String university_id,
        String university_id2) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        List l1;

        String uni_id = university_id2.substring(1, 5) + "%";

        try {
            cpm.setProgram_id(university_id);
            cpm.setUniversity_id(uni_id);

            l1 = client.queryForList("fromprogrammaster", cpm);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            logObj.logger.error("exception" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetrules() {
        List l1;

        try {
            l1 = client.queryForList("description", null);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("in rules " + e);
        }

        return null;
    }
//updated by Upasana 2 May
    public String insertcomponentdetails(String programid, 
        String offered, String component, String type, String weightage,
        String sequence, String rule, String rawvalue, String special,
        String boardflag, String weightageflag, String eligibleflag,
        String univ_id) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();

        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        try {
            cpm.setProgram_id(programid);
           // cpm.setBranch_code(branchcode);
            cpm.setEntity_id(offered);
            cpm.setComponent(component);
            cpm.setRule_no(rule);
            cpm.setType(type);
            cpm.setWeightage(Float.parseFloat(weightage));
            cpm.setSequence(sequence);
            cpm.setUGorPG(rawvalue);
            cpm.setBoard_flag(boardflag);
            cpm.setWeightage_flag(weightageflag);
            cpm.setEligibility_flag(eligibleflag);
            cpm.setSpecial_flag(special);
            cpm.setInsert_time(date);
            cpm.setCreator_id(univ_id);
           // cpm.setSpecialization_id(specializationCode);

            client.insert("insertcomponentdetails", cpm);
        } catch (Exception e) {
            logObj.logger.error("exception " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] componentwithentitytype(String entitytype,
        String university_id) {
        List li = null;
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            cpm.setEntity_description(entitytype);
            cpm.setUniversity_id(uni_id);
            cpm.setGroupCode("CATTYP");
            li = client.queryForList("componentwithentitytype", cpm);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in component with entity type " + e);
        }

        return null;
    }

    public String methodDeletecomponentrecord(String[] Univ,
        String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setProgram_id(Univ[0]);
            cmp.setComponent(Univ[1]);
           /* cmp.setBranch_id(Univ[2]);
            cmp.setSpecialization_id(Univ[3]);*/
            cmp.setUniversity_id(uni_id);

            client.delete("deletecomponents", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in deletecomponents" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] componentwithentityname(String entityname,
        String university_id) {
        List l1;

        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            cpm.setEntity_id(entityname);
            cpm.setUniversity_id(uni_id);
            cpm.setGroupCode("CATTYP");

            l1 = (List) client.queryForList("componentwithentityname", cpm);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in  componentwithentityname =" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] componentwithprogramname(String value,
        String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;
        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setProgram_name(value);
            cmp.setUniversity_id(uni_id);
            cmp.setGroupCode("CATTYP");
            l1 = (List) client.queryForList("componentwithallvalues", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in  componentwithprogramname =" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getcomponent(String programid, int x) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;

        try {
            if (x == 0) {
                cmp.setProgram_id(programid);
                
                l1 = (List) client.queryForList("getcomponents", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            } else if (x == 1) {
                l1 = (List) client.queryForList("getdegree", programid);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }
        } catch (Exception e) {
            logObj.logger.error("getcomponent" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getsequencenumbers(String programid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setProgram_id(programid);
            l1 = (List) client.queryForList("getsequencenumber", cmp);
            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("getsequencenumbers" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getrecords(String programid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setProgram_id(programid);
            l1 = (List) client.queryForList("getrecordsfromfinalmerit", cmp);
            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("getrecords" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getmaxid(String uniid) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setProgram_id(uniid);
            l1 = client.queryForList("getmaxfromfinalmerit", cmp);
            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in get max id" + e);
        }

        return null;
    }

    public String methodinsertfinalmeritdetails(String programid, String offered, String componentid,
        String description, String marks, String attendflag, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        try {
            cmp.setProgram_id(programid);
            cmp.setEntity_id(offered);
            cmp.setComponent(componentid);
            cmp.setEligibility_flag(attendflag);
            cmp.setDescription(description);
            cmp.setTotal_marks(Integer.parseInt(marks));
            cmp.setInsert_time(date);
            cmp.setCreator_id(uniid);           
            client.insert("insertfinalmerit", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getdescription(String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;

        try {
            cmp.setProgram_id(uniid);
            l1 = client.queryForList("getdescription", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in get description" + e);
        }

        return null;
    }

    public String methodDeletefromfinalmerit(String[] Univ, String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setProgram_id(Univ[0]);
            cmp.setComponent(Univ[1]);
            cmp.setEntity_id(Univ[2]);
            client.delete("deletefromfinalmerit", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in delete final merit" + e);
        }

        return null;
    }

    public String methodinsertfirstdegreecomponents(String programid,
        String componentname, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        try {
            cmp.setProgram_id(programid);
            cmp.setComponent(componentname);
            cmp.setInsert_time(date);
            cmp.setCreator_id(uniid);

            client.insert("insertfirstdegreecomponents", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in first degree components " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] degreewithentitytype(String entitytype,
        String university_id) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_description(entitytype);
            cmp.setUniversity_id(uni_id);

            l1 = client.queryForList("degreewithentitytype", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in degreewithentitytype" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] degreewithentityname(String entityname,
        String university_id) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_name(entityname);
            cmp.setUniversity_id(uni_id);

            l1 = client.queryForList("degreewithentityname", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in degree with entity name" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] degreewithprogramname(String entityname,
        String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;

        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setProgram_name(entityname);
            cmp.setUniversity_id(uni_id);

            l1 = client.queryForList("degreewithallvalues", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in degree with program name " + e);
        }

        return null;
    }

//Update by devendra May 3rd
    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetdetails(String programid,String entity) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;

        try {
            cmp.setProgram_id(programid);
            cmp.setEntity_id(entity);

            l1 = client.queryForList("detailsofcos", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in method get details" + e);
        }

        return null;
    }

    //Update By Devendra May 3rd
    public String methodinsertcalldetails(String programid,String[][] arr, String userId, String entity) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        try {
            cmp.setProgram_id(programid);   
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);
            for (int i = 0; i < arr.length; i++) {
                cmp.setCos_value(arr[i][0].trim());
                cmp.setNo_of_times(arr[i][1].trim());
                cmp.setNo_of_times_active(arr[i][2].trim());
                cmp.setCut_off_number(Float.parseFloat(arr[i][3]));
                cmp.setCut_off_number_active(arr[i][4].trim());
                cmp.setCut_off_percentage(Float.parseFloat(arr[i][5]));
                cmp.setCut_off_percentage_active(arr[i][6].trim());
                cmp.setModification_time(date.trim());
                cmp.setModifier_id(userId);
                cmp.setEntity_id(entity);               
                client.update("updatecalldetails", cmp);
            }
        } catch (Exception e) {
            logObj.logger.error("exception in method update call details" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getcalledprograms(String entity,
        String university_id) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_id(entity);
            cmp.setUniversity_id(uni_id);

            l1 = client.queryForList("getcalledprograms", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getcalledprograms" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getcos_cosdes(String programid,
        String branch_code, String specialization_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setProgram_id(programid);
            cmp.setBranch_code(branch_code);
            cmp.setSpecialization_id(specialization_id);

            l1 = client.queryForList("getcos_codess", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in method get cos codes" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetdescription(String university_id) {
        String user_id = university_id.substring(1, 5);
        List l1;

        try {
            l1 = client.queryForList("getcomponentdescription", user_id);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodgetdescription " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetentitytypeforprogramcomponent(
        String entitytype, int systemvalue, String university_id, String type) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            if (systemvalue == 0) {
                cmp.setEntity_description(entitytype);
                cmp.setUniversity_id(uni_id);

                l1 = client.queryForList("getprogramnameforprogramcomponent",
                        cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }

            if (systemvalue == 1) {
                cmp.setEntity_name(type);
                cmp.setUniversity_id(uni_id);
                cmp.setEntity_id(entitytype);

                l1 = client.queryForList("getentitytypeforprogramcomponent", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }
        } catch (Exception e) {
            logObj.logger.error(
                "exception in methodgetentitytypeforprogramcomponent" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetentitytypeforfirstdegree(
        String entitytype, int systemvalue, String university_id) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            if (systemvalue == 0) {
                cmp.setUniversity_id(uni_id);
                cmp.setEntity_name(entitytype);

                l1 = client.queryForList("getentitytypeforfirstdegree", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }

            if (systemvalue == 1) {
                cmp.setUniversity_id(uni_id);
                cmp.setEntity_description(entitytype);

                l1 = client.queryForList("getentitytypeforfirstdegree1", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }
        } catch (Exception e) {
            logObj.logger.error("exception in getentitytypeforfirstdegree" + e);
        }

        return null;
    }

    public CM_ProgramInfoGetter[] methodgetentitydetailsfromprgregis(
        String entitytype) {
        try {
            //            return client.queryForList("getentitytypefromprgregis", entitytype);
        } catch (Exception e) {
            logObj.logger.error("exception in getentitytypefromprgregis" + e);
        }

        return null;
    }

    public String methodupdateprgcomponents(String text, 
        String text3, String text4, String tag, String boardflag,
        String eligibleflag, String special, String weightageflag,
        String weightage, String rule, String univ_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        String uni_id = univ_id.substring(1, 5);

        try {
            cmp.setProgram_id(text);
            //cmp.setBranch_id(text2);
            cmp.setComponent(text3);
            cmp.setSequence(text4);
            cmp.setType(tag);
            cmp.setBoard_flag(boardflag);
            cmp.setEligibility_flag(eligibleflag);
            cmp.setSpecial_flag(special);
            cmp.setWeightage_flag(weightageflag);
            cmp.setWeightage(Float.parseFloat(weightage));
            cmp.setRule_no(rule);
            cmp.setModification_time(date);
            cmp.setModifier_id(univ_id);
            cmp.setUniversity_id(uni_id);
            //cmp.setSpecialization_id(specialization);

            client.update("updateprogramcomponents", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in update program components" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] meritwithentitytype(String entitytype,
        String university_id) {
        List li = null;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_description(entitytype);
            cmp.setUniversity_id(uni_id);

            li = client.queryForList("meritwithentitytype", cmp);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in component with entity type " + e);
        }

        return null;
    }

    public String methodupdatemeritcomponents(String text,String entity_id,
        String text3, String boardflag, String rawValue, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        String uni_id = uniid.substring(1, 5);

        try {
            cmp.setProgram_id(text);
            cmp.setEntity_id(entity_id);
            cmp.setComponent(text3);
            cmp.setBoard_flag(boardflag);
            cmp.setWeightage(Float.parseFloat(rawValue));
            cmp.setModification_time(date);
            cmp.setModifier_id(uniid);
            cmp.setUniversity_id(uni_id);
//            cmp.setSpecialization_id(specialization);

            client.update("updatemeritcomponents", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in update final merit components" +
                e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] meritwithentityname(String entityname,
        String university_id) {
        List li = null;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_name(entityname);
            cmp.setUniversity_id(uni_id);

            li = client.queryForList("meritwithentityname", cmp);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in merit with entity type " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] meritwithprogramname(String programName,String entityName,
        String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List li = null;

        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setProgram_name(programName);
            cmp.setEntity_id(entityName);//add by devendra
            cmp.setUniversity_id(uni_id);

            li = client.queryForList("meritwithprogramname", cmp);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in merit with program name " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetprgsfromfinalmerit(
        String entitytype, int systemvalue, String university_id) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            if (systemvalue == 1) {
                cmp.setEntity_name(entitytype);
                cmp.setUniversity_id(uni_id);

                l1 = client.queryForList("methodgetprgsfromfinalmerit", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }

            if (systemvalue == 0) {
                cmp.setEntity_name(entitytype);
                cmp.setUniversity_id(uni_id);

                l1 = client.queryForList("methodgetprgsfromfinalmerit1", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }
        } catch (Exception e) {
            logObj.logger.error("exception in methodgetprgsfromfinalmerit" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetprogramses(String entity,
        String uniid, String entityname) {
        List l1;
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        String uni_id = uniid.substring(1, 5);
        cpm.setEntity_description(entity);
        cpm.setUniversity_id(uni_id);
        cpm.setEntity_name(entityname);

        try {
            l1 = (List) client.queryForList("methodgetprogramss", cpm);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodgetprogramses" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetdescriptions(String uniid) {
        List l1;
        String user_id = uniid.substring(1, 5);

        try {
            l1 = (List) client.queryForList("methodgetdescriptions", user_id);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodgetdescriptions" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetvalue(String uniid) {
        List l1;
        String user_id = uniid.substring(1, 5);

        try {
            l1 = (List) client.queryForList("methodgetvalue", user_id);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodgetvalue" + e);
        }

        return null;
    }

    public String insertspecialweightagedetails(String uniid, String component,
        String group, String marks) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        String user_id = uniid.substring(1, 5);

        try {
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            cpm.setUniversity_id(user_id);
            cpm.setComponent_id(component);
            cpm.setBranch_code(group);
            cpm.setCut_off_number(Float.parseFloat(marks));
            cpm.setInsert_time(date);
            cpm.setCreator_id(uniid);

            client.insert("insertspecialweightagedetails", cpm);
        } catch (Exception e) {
            logObj.logger.error("exception in insertspecialweightagedetails" +
                e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getspecialrecords(String uniid) {
        List l1;
        String user_id = uniid.substring(1, 5);
        
        System.out.println("here"+user_id);

        try {
            l1 = (List) client.queryForList("getspecialrecords", user_id);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
        	System.out.println("exception"+e);
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }

    public String methodupdatespecialweightage(String text, String text2,
        String group, String weightage, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        try {
            cmp.setUniversity_id(text);
            cmp.setCategory(text2);
            cmp.setBranch_code(group);
            cmp.setCut_off_number(Float.parseFloat(weightage));
            cmp.setModification_time(date);
            cmp.setModifier_id(uniid);

            client.update("updatespecialweightage", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in updatespecialweightage" + e);
        }

        return null;
    }

    public String deletefromspecialweightage(String[] Univ) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setUniversity_id(Univ[0]);
            cmp.setComponent(Univ[1]);

            client.delete("deletefromspecialweightage", cmp);
        } catch (Exception e) {
            logObj.logger.error("exception in deletefromspecialweightage " + e);
        }

        return null;
    }

    @Override
    public String methodDeletedegreerecord(String[] univ, String uniid) {
        try {
            CM_ProgramInfoGetter cmp1 = new CM_ProgramInfoGetter();

            String uni_id = uniid.substring(1, 5) + "%";

            cmp1.setProgram_id(univ[0]);
            cmp1.setComponent_id(univ[1]);
            cmp1.setUniversity_id(uni_id);

            client.delete("deletefrm1stdegree", cmp1);
        } catch (Exception e) {
            logObj.logger.error("exception in deletefrm1stdegree " + e);
        }

        return null;
    }

    @Override
    public String methodDeleterecord(String[] Univ, String university_id,
        String settings) {
        String uni_id = university_id.substring(1, 5);

        try {
            CM_ProgramInfoGetter cmp1 = new CM_ProgramInfoGetter();

            cmp1.setProgram_id(Univ[0]);
            cmp1.setCos_value(Univ[1]);
            
            cmp1.setUniversity_id(uni_id);
            cmp1.setSettings(settings);

            client.delete("deleterecord", cmp1);
        } catch (Exception e) {
            logObj.logger.error("exception in deleterecord " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getCosSeats(String programid,
        String branch_code, String offered_by, String specialization_id) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setProgram_id(programid);
        cmp.setBranch_code(branch_code);
        cmp.setEntity_description(offered_by);
        cmp.setSpecialization_id(specialization_id);

        try {
            l1 = (List) client.queryForList("getcosseats", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CM_ProgramInfoGetter[] gettotalseats(String programid,
        String branch_code, String string, String specialization_id) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setProgram_id(programid);
        cmp.setBranch_code(branch_code);
        cmp.setEntity_description(string);
        cmp.setSpecialization_id(specialization_id);

        try {
            l1 = (List) client.queryForList("gettotalseats", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getSeatsCos(String string, 
        String university_id, String string2,String entityID ) {
        List l1;

        String uni_id = university_id.substring(1, 5);

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setProgram_id(string);
        cmp.setEntity_id(entityID);
        cmp.setUniversity_id(uni_id);
        cmp.setCos_value(string2);
        
        try {
            l1 = client.queryForList("getseatsfromcco", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getseats(String programID, String entityID
       ) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setProgram_id(programID);
        cmp.setEntity_id(entityID);

        try {
            l1 = client.queryForList("getseatsfrompob", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }

    public String[] executeBackup() {
        boolean flag = true;
        String[] object = new String[2];
        String fileName = null;

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssZ");
        Date date = new Date();

        try {
            int processComplete;

            Process runtimeProcess = Runtime.getRuntime()
                                            .exec("C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\mysqldump -u root -pmysql mhrd06may -r E:/" +
                    dateFormat.format(date) + ".sql");

            // call the mysqldump in terminal and execute it
            processComplete = runtimeProcess.waitFor(); //store the state in variable

            fileName = dateFormat.format(date);          
            
            System.out.println("here after counter" + dateFormat.format(date));

            if (processComplete == 1) { //if values equal 1 process failed
                flag = false;
            } else if (processComplete == 0) { //if values equal 0 process completed
                flag = true;
                
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }

        object[0] = flag+"";
        object[1] = fileName;
        return object;
    }

    
    /**
	 * This method get the Program details from the database
	 * @param userId
	 * @return List of type CM_progMasterInfoGettrer 
	 */
	@SuppressWarnings("unchecked")
	public List<FormApplicationInfoGetter> getUniversityProgram(String userId,List<String> resultList,String entityIdDesc) {
		List<FormApplicationInfoGetter> programList=null;
        String universityId = userId.substring(1, 5);
        FormApplicationInfoGetter info=new FormApplicationInfoGetter();
        try {
            /*programList = client.queryForList("getUnivProgram", universityId);*/
        	info.setUniversityCode(universityId);
        	info.setEntityIdList(resultList);
        	info.setEntityId(entityIdDesc);
            programList = client.queryForList("getUnivPrograms", info);
            
        } catch (Exception e) {
            logObj.logger.error("Exception in getUniversityProgram" + e);
        }
        return programList;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<FormApplicationInfoGetter> getUniversityEntities(String userId) {

		List<FormApplicationInfoGetter> entityList = null;
        String universityId = userId.substring(1, 5);
        try {
            entityList = client.queryForList("getUniversityEntities", universityId);
            
        } catch (Exception e) {
            logObj.logger.error("Exception in getUniversityProgram" + e);
        }
        return entityList;
	}

	/**
	 * This method insert the Program Form details into database
	 * @param programId
	 *            , List of programId selected by the user
	 * @param formName
	 *            , Name of the selected form
	 * @param userId
	 * @return value of type Boolean shows whether details inserted in database or not
	 */
	@SuppressWarnings("unchecked")
	public Boolean setProgramForm(List<String> programId, List<String> programName,List<String> offeredByEntity,List<String> city,String formName,
			String userId,String entityID) {
		Boolean flag=false;
		FormApplicationInfoGetter formApp = new FormApplicationInfoGetter();
        List<FormApplicationInfoGetter> programList;
        String universityId = userId.substring(1, 5);
        try {
        	formApp.setUniversityCode(universityId);
        	formApp.setCity(city.get(0));
        	programList=client.queryForList("getFormNumber",formApp);
        	String formDesc="Form For ";
        	String formNumber;
        	for(int i=0;i<programName.size();i++){
        		formDesc=formDesc+programName.get(i);
        		if(i!=programName.size()-1){
        			formDesc=formDesc+", ";
        		}
        	}
        	formApp.setFormDesc(formDesc);        	
        	
        	if((programList.size()==0)||(programList.get(0).getMaxFormNumber()==null)){
        		formNumber=String.format("%04d",0);
        	}
        	else{
        		formNumber=String.format("%04d",Integer.parseInt(programList.get(0).getMaxFormNumber())+1);
        	} 
        	for(int i=0;i<programId.size();i++){
        		flag=false;
        		formApp.setProgramId(programId.get(i));
        		formApp.setFormNumber(formNumber);
        		formApp.setFormName(formName);
        		formApp.setUserId(userId);
        		formApp.setEntityId(entityID);
        		formApp.setOfferedBy(offeredByEntity.get(i));
        		client.insert("setProgramForm", formApp);
        		flag=true;
        	}
        	
        } catch (Exception e) {
            logObj.logger.error("exception in setProgramForm" + e);
        }
        return flag;
	}


	/**
	 * This method check duplicate record for the given form name
	 * 
	 * @param formName
	 *            , Name of the form
	 * @param userId
	 *             
	 * @return value of type Integer containing count of the records 
	 */
	@SuppressWarnings("unchecked")
	public int checkFormName(String appFormName,String userId) {
		FormApplicationInfoGetter formApp = new FormApplicationInfoGetter();
		List<FormApplicationInfoGetter> count;
		String universityId = userId.substring(1, 5);
		try{
			formApp.setFormName(appFormName);
			formApp.setUniversityCode(universityId);
			
			count=client.queryForList("getFormName", formApp);
			
			return count.get(0).getCountFormName();
		}
		catch(Exception e){
			logObj.logger.error("exception in checkFormName" + e);
		}
		return 1;
	}

	/**
	 * This method get the Program Form name from the database
	 * 
	 * @param userId
	 *             
	 * @return list of type FormApplicationInfoGetter containing form details
	 */
	@SuppressWarnings("unchecked")
	public List<FormApplicationInfoGetter> getFormList(String userId,String entity) throws Exception {
		FormApplicationInfoGetter formInfoGetter = new FormApplicationInfoGetter();
        formInfoGetter.setUniversityCode(userId.substring(1, 5));
        List<FormApplicationInfoGetter> formDetails;
        try {
        	formInfoGetter.setEntityId(entity);
        	formDetails = client.queryForList("getFormNameList", formInfoGetter);
            return formDetails;
        } 
        catch (Exception e) {
            logObj.logger.error("exception in getFormLIST " + e);
        }
		return null;
	}
	
	/**
	 * This method get the Program names from the database for the selected form name
	 * @param form
	 * 				, form name
	 * @param userId
	 *             
	 * @return list of type FormApplicationInfoGetter containing program details
	 */
	@SuppressWarnings("unchecked")
    public List<FormApplicationInfoGetter> getFormDetails(String form,String userid,String entityID) {
        List<FormApplicationInfoGetter> programList;
        FormApplicationInfoGetter fInfoGetter=new FormApplicationInfoGetter();
        String uniId;
        try {
            uniId = userid.substring(1, 5);
            fInfoGetter.setFormNumber(form);
            fInfoGetter.setUniversityCode(uniId);
            fInfoGetter.setEntityId(entityID);
            programList =  client.queryForList("getProgramFormDetail", fInfoGetter);
        
            return programList;
        } catch (Exception e) {
            logObj.logger.error("Exception in getFormDetails" + e);
        }

        return null;
    }
	
	/**
	 * This method delete the Program from the database for the selected form name
	 * @param progFormDetails
	 * 				, List of programId
	 * @param selectedFormName
	 * @param userId
	 *             
	 * @return number of deleted rows
	 */
	@SuppressWarnings("unchecked")
	public int deleteProgFormDetail(List<String> progFormDetails, String selectedFormName, String userId,String entityID)
	        throws Exception {
	        FormApplicationInfoGetter formInfo=new FormApplicationInfoGetter();
			int num=0;
			List<FormApplicationInfoGetter> programList;
			String universityId=userId.substring(1, 5);

	        try {
	            formInfo.setFormNumber(selectedFormName);
	            formInfo.setUniversityCode(universityId);
	            formInfo.setEntityId(entityID);
	            for(int i=0;i<progFormDetails.size();i++){
	            	formInfo.setProgramId(progFormDetails.get(i));
	            	client.delete("deleteProgForm", formInfo);
	            	num++;
	            }
	            programList=client.queryForList("getProgramNameForForm",formInfo);
	        	String formDesc="Form For ";
	        	for(int i=0;i<programList.size();i++){
	        		formDesc=formDesc+programList.get(i).getProgramName();
	        		if(i!=programList.size()-1){
	        			formDesc=formDesc+", ";
	        		}
	        	}
	        	formInfo.setFormDesc(formDesc);
	        	client.update("updateFormDesc", formInfo);
	        	
	            return num;
	        } catch (Exception ex) {
	            logObj.logger.error(ex);
	            throw new Exception(ex);
	        }
	    }

	/**
	 * This method get the programs which are not in program form table for selected form
	 * 
	 * @param selectedFormName
	 * @param userId
	 *             
	 * @return List of Programs
	 */
	@SuppressWarnings("unchecked")
	public List<FormApplicationInfoGetter> getProgramsDetails(List<String> resultList,String selectedFormName,String entityId ,String userId) {
		List<FormApplicationInfoGetter> progList;
        String univId = userId.substring(1, 5);
        FormApplicationInfoGetter formInfo=new FormApplicationInfoGetter();
        try {
        	formInfo.setUniversityCode(univId);
        	formInfo.setEntityIdList(resultList);
        	formInfo.setEntityId(entityId);
        	
            progList =  client.queryForList("getUnivPrograms", formInfo);
        	return progList;
        } catch (Exception e) {
            logObj.logger.error("Exception in getProgramsDetails ManageApplicationForm" + e);
        }

        return null;
	}

	/**
	 * This method insert the programs in Program Form table
	 * 
	 * @param progFormDetails
	 * @param selectedFormName
	 * @param userId
	 *             
	 * @return List of Programs
	 */
	@SuppressWarnings("unchecked")
	public int addProgFormDetail(List<String> progFormDetails, List<String> offeredEntity, List<String> city,
			String selectedForm,String formName, String userId,String entityID) throws Exception {
		
		FormApplicationInfoGetter formInfo=new FormApplicationInfoGetter();
		int count=0;
		String universityId = userId.substring(1, 5);
		List<FormApplicationInfoGetter> programList;
		try {
            formInfo.setFormNumber(selectedForm);
            formInfo.setFormName(formName);
            formInfo.setUniversityCode(universityId);
            formInfo.setEntityId(entityID);
            formInfo.setUserId(userId);
            formInfo.setCity(city.get(0));
            for(int i=0;i<progFormDetails.size();i++){
            	formInfo.setProgramId(progFormDetails.get(i));
            	formInfo.setOfferedBy(offeredEntity.get(i));
              	client.insert("insertProgramForm", formInfo);
            	count++;
            }
            programList=client.queryForList("getProgramNameForForm",formInfo);
        	String formDesc="Form For ";
        	for(int i=0;i<programList.size();i++){
        		formDesc=formDesc+programList.get(i).getProgramName();
        		if(i!=programList.size()-1){
        			formDesc=formDesc+", ";
        		}
        	}
        	formInfo.setFormDesc(formDesc);
        	client.update("updateFormDesc", formInfo);
                        
        } catch (Exception ex) {
            logObj.logger.error("Exception in  addProgFormDetail " + ex);
        }
        return count;
	}


	/**
	 * This method get the document list form system table two table
	 * 
	 * @param progFormDetails
	 * @param selectedFormName
	 * @param userId
	 *             
	 * @return List of Programs
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramDocumentInfoGetter> getDocumentList(
			String userId, String pId) {
		ProgramDocumentInfoGetter pdinfo= new ProgramDocumentInfoGetter();
		List<ProgramDocumentInfoGetter> docList;
        String universityId = userId.substring(1, 5);

        
        try {
        	pdinfo.setProgramId(pId);
        	pdinfo.setUniversityId(universityId);
        	pdinfo.setGroupCode("DOCLST");
            docList = client.queryForList("selectDocumentList", pdinfo);

            return docList;
        } catch (Exception e) {
            logObj.logger.error("exception in getDocumentList" + e);
        }

        return null;
	}

	
	/**
	 * This method insert the document list in Program Document List for selected program
	 * 
	 * @param documentList
	 * @param selectedFormName
	 * @param userId
	 *             
	 * @return Boolean value
	 */
	public Boolean insertProgramDocument(List<String> documentList, String programId,
			String userId) {
	    ProgramDocumentInfoGetter programInfo = new ProgramDocumentInfoGetter();
        String universityId=userId.substring(1, 5);
        Boolean flag=false;
        try {
    
        	programInfo.setUniversityId(universityId);
        	programInfo.setProgramId(programId);
    		programInfo.setUserId(userId);
        	for(int i=0;i<documentList.size();i++){
        		flag=false;
        		programInfo.setDocId(documentList.get(i));
        		client.insert("setProgramDocument", programInfo);
        		flag=true;
        	}
        	
        } catch (Exception e) {
            logObj.logger.error("Exception in insertProgramDocument" + e);
        }

		return flag;
	}

	/**
	 * This method get the document list of added documents from Program Document List table for selected program
	 * 
	 * @param userId
	 * @param programId
	 *             
	 * @return List of documents
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramDocumentInfoGetter> getAddedDocument(
			String userId, String programId) {
		ProgramDocumentInfoGetter progInfo= new ProgramDocumentInfoGetter();
		List<ProgramDocumentInfoGetter> docList;
        
		String universityId = userId.substring(1, 5);
        
		try {
        	progInfo.setProgramId(programId);
        	progInfo.setUniversityId(universityId);
        	progInfo.setGroupCode("DOCLST");
            docList = client.queryForList("getDocumentList", progInfo);
            
            return docList;
        } catch (Exception e) {
            logObj.logger.error("exception in getAddedDocument" + e);
        }
        return null;
	}

	/**
	 * This method delete Document from database for selected program
	 * @param documents,
	 * 					list of doc id
	 * @param pId,
	 * 				programId 
	 * @param userId
	 * 
	 */
	public int deleteProgramDocument(List<String> docId, String pName,
			String userId) {
		ProgramDocumentInfoGetter pdig = new ProgramDocumentInfoGetter();
        int count=0;
        String universityId=userId.substring(1, 5);
        try{
        	pdig.setUniversityId(universityId);
        	pdig.setProgramId(pName);
        	for(int i=0; i<docId.size();i++){
        		pdig.setDocId(docId.get(i));
        		client.delete("deleteProgDocument", pdig);
        		count++;
        	}
        	return count;
        }
        catch(Exception e){
        	logObj.logger.error("exception in deleteProgramDocument" + e);
        }
        return 0;
	}

	

	/**
	 * This method get the List of entities offered by the university
	 * @param searchEntityValue,
	 * @param userId
	 * @param locationValue
	 * @return List of type ProgramSearchInfoGetter 
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramSearchInfoGetter> searchByEntity(
			String searchEntityValue, String userId,String locationValue) {
		String universityId=userId.substring(1,5);
		ProgramSearchInfoGetter progInfo=new ProgramSearchInfoGetter();
		List<ProgramSearchInfoGetter> entityDetails;
		try{
			progInfo.setUniversityId(universityId);
			progInfo.setEntityName(searchEntityValue);
			progInfo.setEntityLocation(locationValue);
			entityDetails=client.queryForList("searchByEntity", progInfo);
		
			return entityDetails;
		}
		catch(Exception ex){
			logObj.logger.error("exception in searchByEntity" + ex);
		}
		return null;
	}

	/**
	 * This method get the List of program offered by the university for selected entity
	 * @param searchProgramValue,
	 * @param selectedEntity
	 * @return List of type ProgramSearchInfoGetter 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramSearchInfoGetter> searchByProgram(
			String searchProgramValue, String selectedEntity) {

		ProgramSearchInfoGetter programInfo=new ProgramSearchInfoGetter();
		List<ProgramSearchInfoGetter> programList;
		
		try{
			programInfo.setEntityId(selectedEntity);
			programInfo.setProgramName(searchProgramValue);
			programList=client.queryForList("searchByProgram",programInfo);
			
			return programList;
		}
		catch(Exception e){
			logObj.logger.error("exception in searchByProgram" + e);
		}
		return null;
	}

	/**
	 * This method get the List of locations of the entities offered by the university 
	 *
	 * @param userId
	 * @return List of type ProgramSearchInfoGetter 
	 */	
	@SuppressWarnings("unchecked")
	public List<ProgramSearchInfoGetter> getLocationDetails(String userId) {

		String universityId=userId.substring(1,5);
		List<ProgramSearchInfoGetter> locationList;
		try{
			locationList=client.queryForList("getEntitiesLocation", universityId);
			
			return locationList;
		}
		catch(Exception ex){
			logObj.logger.error("exception in getLocationDetails" + ex);
		}
		return null;
	}

	/**
	 * This method get the list of programs for selected Entity
	 * @param selectedEntity
	 * @param userId
	 * @return List of type ProgramSearchInfoGetter
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramSearchInfoGetter> getEntityPrograms(String selectedEntity, String userId) {
		String universityId=userId.substring(1,5);
		ProgramSearchInfoGetter programInfo=new ProgramSearchInfoGetter();
		List<ProgramSearchInfoGetter> programList;
		try{
			programInfo.setUniversityId(universityId);
			programInfo.setEntityId(selectedEntity);
			programList=client.queryForList("getEntityProgram", programInfo);
			
			return programList;
		}
		catch(Exception e){
			logObj.logger.error("exception in getEntityPrograms" + e);
		}
		
		return null;
	}

	/**
	 * This method get the list of session for selected User
	 * 
	 * @param userId
	 * @return List of type ProgramSearchInfoGetter
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramSearchInfoGetter> getUniversitySessionDate(String universityId) {
		
		List<ProgramSearchInfoGetter> sessionDetails;
		ProgramSearchInfoGetter progInfo=new ProgramSearchInfoGetter();
		
		try{
			progInfo.setUniversityId(universityId);
			//progInfo.setProgramId(programId);
			sessionDetails=client.queryForList("getUniversitySession",progInfo);
			return sessionDetails;
		}
		catch(Exception ex){
			logObj.logger.error("exception in getUniversitySessionDate :" + ex);
		}
		return null;
	}

	
    /**
	 * This method get the Program details from the database
	 * @param userId
	 * @return List of type CM_progMasterInfoGettrer 
	 */
	@SuppressWarnings("unchecked")
	public List<CM_progMasterInfoGetter> getUniversityProgramDetails(String userId) {
		List<CM_progMasterInfoGetter> programList;
        String universityId = userId.substring(1, 5);
        try {
            programList = client.queryForList("getUniversityProgram", universityId);
            return programList;
        } catch (Exception e) {
            logObj.logger.error("Exception in getUniversityProgram" + e);
        }
        return null;
	}

@SuppressWarnings("unchecked")
	@Override
	public List<ProgramSearchInfoGetter> getFormNameProgram(String programId,
			String universityId) {

		ProgramSearchInfoGetter obj=new ProgramSearchInfoGetter();
		List<ProgramSearchInfoGetter> output;
		try {
			
			obj.setUniversityId(universityId);
			obj.setProgramId(programId);
            output = client.queryForList("getProgramFormEntity", obj);
            return output;
        } catch (Exception e) {
            logObj.logger.error("Exception in getUniversityProgram" + e);
        }
		
		return null;
	}











public CM_ProgramInfoGetter[] getEntityNames(String userID) {
		List<CM_ProgramInfoGetter> entityNameList=new ArrayList<CM_ProgramInfoGetter>();
		String university_id=userID.substring(1,5);
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setUniversity_id(university_id);
		try
		{
			entityNameList=client.queryForList("getEntityNames",cmpig);
			return (CM_ProgramInfoGetter[]) entityNameList.toArray(new CM_ProgramInfoGetter[entityNameList.size()]);
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in getEntityNames" + e);
		}
		return (CM_ProgramInfoGetter[]) entityNameList.toArray(new CM_ProgramInfoGetter[entityNameList.size()]);
	}


	public List<CM_progMasterInfoGetter> getEntityPrograms(String entity_id) {
		List<CM_progMasterInfoGetter> programList;
		CM_ProgramInfoGetter cmpig=new CM_ProgramInfoGetter();
		cmpig.setEntity_id(entity_id);
		try
		{
			programList=client.queryForList("getEntityPrograms",cmpig);
			if(programList.size()<1)
			{
				programList=client.queryForList("getEntityPrograms2",cmpig);
			}
			return programList;
		}
		catch(Exception e)
		{
			logObj.logger.error("Exception in getEntityNames" + e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Integer getProgramAppliedApplicant(String userEmailId,String programId) {
		int count=0;
		ProgramSearchInfoGetter progInfo=new ProgramSearchInfoGetter();
		List<ProgramSearchInfoGetter> countProgram;
		try{
			progInfo.setProgramId(programId);
			progInfo.setEmail(userEmailId);
			countProgram=client.queryForList("getProgramAppliedApplicant",progInfo);
			count=Integer.parseInt(countProgram.get(0).getCount());
		}
		catch(Exception ex){
			System.out.println("In getProgramAppliedApplicant"+ex.getStackTrace()+" "+ex);
		}
		
		return count;
	}
	
		//Add by Devendra May 18 For setup Subject Code
	@Override
	public String setSubjectCode(String userId, String programId,
			String subjectCode) {
		String msg="";
		try{
			CM_ProgramInfoGetter input=new CM_ProgramInfoGetter();
			input.setUniversity_id(userId.substring(1, 5));
			input.setSubject_code(subjectCode);
			input.setProgram_id(programId);
			client.insert("setUpSubjectCode", input);
			msg="success";
		}
		catch(Exception e){
			msg="error";
			logObj.logger.error("Exception in CM_connectRImpl inside method setSubjectCode:: "+e);
		}
		return msg;
	}
	
	//Add by Devendra May 18 For Get Subject Code List from database
	@SuppressWarnings("unchecked")
	@Override
	public List<CM_ProgramInfoGetter> getSubjectCode(String userId,
			String programId) {
		List<CM_ProgramInfoGetter>list=null;
		try{
			CM_ProgramInfoGetter input=new CM_ProgramInfoGetter();
			input.setUniversity_id(userId.substring(1, 5));
			input.setProgram_id(programId);
			list=client.queryForList("GetSubject", input);
		}
		catch(Exception e){
			logObj.logger.error("Exception in CM_connectRImpl inside method getSubjectCode:: "+e);
		}
		return list;
	}
	
	//Add by Devendra May 18 For Deleting SubjectCode
	@Override
	public String deleteSubjectCode(String userId, String programId,
			String[] subjectCode) {
		String msg="";
		try{
			CM_ProgramInfoGetter input=new CM_ProgramInfoGetter();
			input.setUniversity_id(userId.substring(1, 5));			
			input.setProgram_id(programId);
			int count=0;
			for(int i=0;i<subjectCode.length;i++){
				input.setSubject_code(subjectCode[i]);
				client.delete("deleteSubjectCode", input);
				count++;
			}			
			msg="success-"+count;
		}
		catch(Exception e){
			msg="error";
			logObj.logger.error("Exception in CM_connectRImpl inside method setSubjectCode:: "+e);
		}
		return msg;
	}
	//Add by Devendra 19 May
	@SuppressWarnings("unchecked")
	@Override
	public List<CM_ProgramInfoGetter> getSubject(String userId,String programId) {
		List<CM_ProgramInfoGetter>list=null;
		try{
			CM_ProgramInfoGetter input=new CM_ProgramInfoGetter();
			input.setUniversity_id(userId.substring(1, 5));		
			input.setProgram_id(programId);
			list=client.queryForList("GetSubjectList", input);
		}
		catch(Exception e){
			logObj.logger.error("Exception in CM_connectRImpl inside method getSubject:: "+e);
		}
		return list;
	}
	
	//Added by upasana
	@SuppressWarnings("unchecked")
	public List<CM_ProgramInfoGetter> getCategorizationType(String userId) {
		CM_ProgramInfoGetter pdinfo= new CM_ProgramInfoGetter();
		List<CM_ProgramInfoGetter> catList;
        String universityId = userId.substring(1, 5);

        
        try {
        	
        	pdinfo.setUniversity_id(universityId);
        	pdinfo.setGroupCode("CATTYP");
            catList = client.queryForList("selectCategorizationTypeList", pdinfo);

            return catList;
        } catch (Exception e) {
            logObj.logger.error("exception in getCategorizationType" + e);
        }

        return null;
	}
	
	@SuppressWarnings("unchecked")
	public  List<FormApplicationInfoGetter> getEntityRelate(List<String> entityList1){
		FormApplicationInfoGetter inputBean= new FormApplicationInfoGetter();
		inputBean.setEntityIdList(entityList1);
			List<FormApplicationInfoGetter>result=new ArrayList<FormApplicationInfoGetter>();
			try{
				result=(List<FormApplicationInfoGetter>)client.queryForList("getEntityTree",inputBean);
			}
			catch (Exception e) {
				System.out.println(e+"Exception");
			}
			 
			return result;
		}
	
	@SuppressWarnings("unchecked")
	public List<ProgramSearchInfoGetter> getEntityProgramsLogin(String selectedEntity, String userId) {
		
		String universityId=userId.substring(1,5);
		ProgramSearchInfoGetter programInfo=new ProgramSearchInfoGetter();
		List<ProgramSearchInfoGetter> programList=new ArrayList<ProgramSearchInfoGetter>();
		try{
			programInfo.setUniversityId(universityId);
			programInfo.setEntityId(selectedEntity);
			programList=client.queryForList("getEntityProgramLogin", programInfo);
		}
		catch(Exception e){
			logObj.logger.error("exception in getEntityProgramsLogin" + e);
		}
		
		return programList;
	}
	
}
