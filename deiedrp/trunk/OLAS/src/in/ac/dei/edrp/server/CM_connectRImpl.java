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

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.sql.SQLException;

import java.util.List;


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
    public CM_ProgramInfoGetter[] methodentitypopulate(String university_id)
        throws Exception {
        String user_id = university_id.substring(1, 5);
        List l1;

        try {
            l1 = (List) client.queryForList("getentities", user_id);

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
            cmp.setentity_id(entity);

            String uni_id;

            uni_id = university_id.substring(1, 5);

            cmp.setuniversity_code(uni_id);

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
        String university_id) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setEntity_description(entitytype);

            String uni_id;

            uni_id = university_id.substring(1, 5);

            cmp.setuniversity_code(uni_id);

            System.out.println("here22" + uni_id);

            l1 = (List) client.queryForList("getentityty", cmp);

            System.out.println("size" + l1.size());

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("getentityty" + e);
            System.out.println("here" + e);
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
    public CM_ProgramInfoGetter[] getdistinctprograms(String entityname,
        String university_id) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setEntity_description(entityname);

            String uni_id;

            uni_id = university_id.substring(1, 5);

            cmp.setuniversity_code(uni_id);
            l1 = (List) client.queryForList("getdistinctprograms", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getdistinctprograms" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getindivisualprograms(String entityname,
        String settings, String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        String uni_id;

        uni_id = university_id.substring(1, 5);

        try {
            cmp.setEntity_type(entityname);
            cmp.setSettings(settings);
            cmp.setuniversity_code(uni_id);

            l1 = (List) client.queryForList("getindivisualprograms", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in indivisual programs = " + e);
        }

        return null;
    }

    public String methodinsertcutoffdetails(String cosvalue, String factor,
        String seats, String programid, String offered, String branch_code,
        String dateSelected, String dateSelected1, String uniid) {
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

    public String methodupdatecutoffdetails(String text, String text2,
        String value, String value2, String startdate, String enddate,
        String string2, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String univ_id = uniid.substring(1, 5);

        try {
            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);

            cmp.setProgram_name(text);
            cmp.setCos_value(text2);
            cmp.setNo_of_times(value);
            cmp.setNo_of_seats(value2);
            cmp.setSession_start_date(startdate);
            cmp.setSession_end_date(enddate);
            cmp.setModification_time(date);
            cmp.setBranch_name(string2);
            cmp.setModifier_id(uniid);
            cmp.setUniversity_id(univ_id);

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
            cmp.setEntity_name(entityname);
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
            //            cmp.setEntity_type(value1);
            //            cmp.setEntity_name(value2);
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

    //    public String methodDeleterecord(String program_name,
    //        String category_value, String branch) {
    //        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
    //
    //        try {
    //            cmp.setProgram_name(program_name);
    //            cmp.setCos_value(category_value);
    //            cmp.setBranch_name(branch);
    //
    //            client.delete("deleterecord", cmp);
    //        } catch (Exception e) {
    //            logObj.logger.error("methodDeleterecord" + e);
    //        }
    //
    //        return null;
    //    }
    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getcosvalue(String programid,
        String branch_code, String string) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setProgram_id(programid);
            cmp.setBranch_code(branch_code);
            cmp.setEntity_id(string);

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
        logObj.logger.error("length of the array" + arr.length);

        try {
            for (int i = 0; i < arr.length; i++) {
                cpm.setProgram_id(arr[i][0]);
                cpm.setBranch_code(arr[i][1]);
                cpm.setEntity_id(arr[i][2]);
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

                logObj.logger.error("array in insert function =" + arr[i][0] +
                    arr[i][1] + arr[i][2]);
                client.insert("insertdefaultdetails", cpm);
            }
        } catch (Exception e) {
            logObj.logger.error("exception in default settings " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetprograms(String entitytype,
        String university_id) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        List l1;

        String uni_id = university_id.substring(1, 5);

        try {
            cpm.setEntity_description(entitytype);
            cpm.setUniversity_id(uni_id);
            System.out.println("value" + entitytype + uni_id);

            l1 = (List) client.queryForList("methodgetprograms", cpm);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            System.out.println("here" + e);
            logObj.logger.error("exception in methodgetprograms" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodprogrammaster(String university_id,
        String university_id2) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        List l1;

        String uni_id = university_id2.substring(1, 5);

        try {
            cpm.setprogram_id(university_id);
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

    public String insertcomponentdetails(String programid, String branchcode,
        String offered, String component, String type, String weightage,
        String sequence, String rule, String rawvalue, String special,
        String boardflag, String weightageflag, String eligibleflag,
        String univ_id) {
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        try {
            cpm.setProgram_id(programid);
            cpm.setBranch_code(branchcode);
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
            cmp.setProgram_name(Univ[0]);
            cmp.setComponent(Univ[1]);
            cmp.setBranch_name(Univ[2]);
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
            cpm.setentity_name(entityname);
            cpm.setUniversity_id(uni_id);

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
            l1 = (List) client.queryForList("componentwithallvalues", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in  componentwithprogramname =" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getcomponent(String programid, int x,
        String branchcode) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;

        try {
            if (x == 0) {
                cmp.setprogram_id(programid);
                cmp.setBranch_code(branchcode);

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
    public CM_ProgramInfoGetter[] getsequencenumbers(String programid,
        String branchcode) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setprogram_id(programid);
            cmp.setBranch_code(branchcode);

            l1 = (List) client.queryForList("getsequencenumber", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("getsequencenumbers" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getrecords(String uniid, String branchcode) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setprogram_id(uniid);
            cmp.setBranch_code(branchcode);

            l1 = (List) client.queryForList("getrecordsfromfinalmerit", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("getrecords" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getmaxid(String uniid, String branchcode) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setprogram_id(uniid);
            cmp.setBranch_code(branchcode);

            l1 = client.queryForList("getmaxfromfinalmerit", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in get max id" + e);
        }

        return null;
    }

    public String methodinsertfinalmeritdetails(String programid,
        String branchcode, String offered, String componentid,
        String description, String marks, String attendflag, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        try {
            cmp.setprogram_id(programid);
            cmp.setBranch_code(branchcode);
            cmp.setentity_id(offered);
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
    public CM_ProgramInfoGetter[] getdescription(String uniid, String branchcode) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;

        try {
            cmp.setprogram_id(uniid);
            cmp.setBranch_code(branchcode);

            l1 = client.queryForList("getdescription", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in get description" + e);
        }

        return null;
    }

    public String methodDeletefromfinalmerit(String[] Univ, String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setprogram_name(Univ[0]);
            cmp.setComponent(Univ[2]);
            cmp.setBranch_name(Univ[1]);
            cmp.setUniversity_id(uni_id);

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
            cmp.setentity_name(entityname);
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

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] methodgetdetails(String programid,
        String branchcode) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List l1;

        try {
            cmp.setProgram_id(programid);
            cmp.setBranch_code(branchcode);

            l1 = client.queryForList("detailsofcos", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in method get details" + e);
        }

        return null;
    }

    public String methodinsertcalldetails(String programid, String branchcode,
        String[][] arr, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        try {
            cmp.setProgram_id(programid);
            cmp.setBranch_code(branchcode);

            String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                           .substring(0, 19);
            String uni_id = uniid.substring(1, 5);

            for (int i = 0; i < arr.length; i++) {
                cmp.setCos_value(arr[i][0].trim());
                cmp.setNo_of_times(arr[i][1].trim());
                cmp.setNo_of_times_active(arr[i][2].trim());
                cmp.setCut_off_number(Float.parseFloat(arr[i][3]));
                cmp.setCut_off_number_active(arr[i][4].trim());
                cmp.setCut_off_percentage(Float.parseFloat(arr[i][5]));
                cmp.setCut_off_percentage_active(arr[i][6].trim());
                cmp.setModification_time(date.trim());
                cmp.setModifier_id(uniid);
                cmp.setUniversity_id(uni_id);

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
            cmp.setEntity_description(entity);
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
        String branch_code) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        List l1;

        try {
            cmp.setProgram_id(programid);
            cmp.setBranch_code(branch_code);

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
        String entitytype, int systemvalue, String university_id) {
        List l1;
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        String uni_id = university_id.substring(1, 5);

        try {
            if (systemvalue == 0) {
                System.out.println("in 0");

                System.out.println("here +" + entitytype);

                cmp.setEntity_description(entitytype);
                cmp.setUniversity_id(uni_id);

                l1 = client.queryForList("getprogramnameforprogramcomponent",
                        cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }

            if (systemvalue == 1) {
                System.out.println("in 1");
                System.out.println("here ++" + entitytype);

                cmp.setentity_name(entitytype);
                cmp.setUniversity_id(uni_id);

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
                cmp.setentity_name(entitytype);

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

    public String methodupdateprgcomponents(String text, String text2,
        String text3, String text4, String tag, String boardflag,
        String eligibleflag, String special, String weightageflag,
        String weightage, String rule, String univ_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        String uni_id = univ_id.substring(1, 5);

        try {
            cmp.setprogram_name(text);
            cmp.setBranch_name(text2);
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

    public String methodupdatemeritcomponents(String text, String text2,
        String text3, String boardflag, String rawValue, String uniid) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();
        String date = (new java.sql.Timestamp(new java.util.Date().getTime())).toString()
                       .substring(0, 19);

        String uni_id = uniid.substring(1, 5);

        try {
            cmp.setprogram_name(text);
            cmp.setBranch_name(text2);
            cmp.setComponent(text3);
            cmp.setBoard_flag(boardflag);
            cmp.setWeightage(Float.parseFloat(rawValue));
            cmp.setModification_time(date);
            cmp.setModifier_id(uniid);
            cmp.setUniversity_id(uni_id);

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
            cmp.setentity_name(entityname);
            cmp.setUniversity_id(uni_id);

            li = client.queryForList("meritwithentityname", cmp);

            return (CM_ProgramInfoGetter[]) li.toArray(new CM_ProgramInfoGetter[li.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in merit with entity type " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] meritwithprogramname(String entityname,
        String university_id) {
        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        List li = null;

        String uni_id = university_id.substring(1, 5);

        try {
            cmp.setProgram_name(entityname);
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
                cmp.setentity_name(entitytype);
                cmp.setUniversity_id(uni_id);

                l1 = client.queryForList("methodgetprgsfromfinalmerit", cmp);

                return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
            }

            if (systemvalue == 0) {
                cmp.setentity_name(entitytype);
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
        String uniid) {
        List l1;
        CM_ProgramInfoGetter cpm = new CM_ProgramInfoGetter();
        String uni_id = uniid.substring(1, 5);
        cpm.setEntity_description(entity);
        cpm.setUniversity_id(uni_id);

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

        try {
            l1 = (List) client.queryForList("getspecialrecords", user_id);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
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
            cmp.setcategory(text2);
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

            String uni_id = uniid.substring(1, 5);

            cmp1.setprogram_name(univ[0]);
            cmp1.setComponent(univ[1]);
            cmp1.setUniversity_id(uni_id);

            client.delete("deletefrm1stdegree", cmp1);
        } catch (Exception e) {
            logObj.logger.error("exception in deletefrm1stdegree " + e);
        }

        return null;
    }

    @Override
    public String methodDeleterecord(String[] Univ, String university_id) {
        String uni_id = university_id.substring(1, 5);

        try {
            CM_ProgramInfoGetter cmp1 = new CM_ProgramInfoGetter();

            cmp1.setprogram_name(Univ[0]);
            cmp1.setcos_value(Univ[1]);
            cmp1.setBranchName(Univ[2]);
            cmp1.setUniversity_id(uni_id);

            client.delete("deleterecord", cmp1);
        } catch (Exception e) {
            logObj.logger.error("exception in deleterecord " + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getCosSeats(String programid,
        String branch_code, String offered_by) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setprogram_id(programid);
        cmp.setBranch_code(branch_code);
        cmp.setEntity_description(offered_by);

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
        String branch_code, String string) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setprogram_id(programid);
        cmp.setBranch_code(branch_code);
        cmp.setEntity_description(string);

        try {
            l1 = (List) client.queryForList("gettotalseats", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getSeatsCos(String string, String string2,
        String university_id, String string3) {
        List l1;

        String uni_id = university_id.substring(1, 5);

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setProgram_name(string);
        cmp.setBranch_name(string2);
        cmp.setUniversity_id(uni_id);
        cmp.setcos_value(string3);

        try {
            l1 = client.queryForList("getseatsfromcco", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public CM_ProgramInfoGetter[] getseats(String text, String text2) {
        List l1;

        CM_ProgramInfoGetter cmp = new CM_ProgramInfoGetter();

        cmp.setProgram_name(text);
        cmp.setBranch_name(text2);

        try {
            l1 = client.queryForList("getseatsfrompob", cmp);

            return (CM_ProgramInfoGetter[]) l1.toArray(new CM_ProgramInfoGetter[l1.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in getspecialrecords" + e);
        }

        return null;
    }
}
