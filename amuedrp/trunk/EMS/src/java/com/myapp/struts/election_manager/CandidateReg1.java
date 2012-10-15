/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
/**
 *
 * @author edrp01
 */
public class CandidateReg1 {
       private String e_election_id;
       private String e_description;
        private Timestamp e_nomistart;
        private Timestamp e_nomiend;
        private Timestamp e_scrstart;
        private Timestamp e_scrend;
        private Timestamp e_withstart;
        private Timestamp e_withend;
        private Timestamp e_start;
        private Timestamp e_end;
        private String e_election_name;
        private String p_position_name;
        private String i_institute_name;
        private String v_voter_name;
	private String v_gender;
	private String v_birthdate;
	private String v_mobile_number;
	private String v_c_address;
	private String v_city;
	private String v_state;
	private String v_email;
	private String v_course;
	private String v_department;
	private String v_enrollment;
	private String v_admisionDate;
	private String v_duration;
	private String v_year;
	private String v_session;
	private String c1_attendence;
	private String c1_marks;
	private String c1_back;
	private String c1_criminal;
	private String v_father;
	private String v_mother;
	private String v_country;
	private String v_peradd;
	private String v_city1;
	private String v_state1;
	private String v_zip1;
	private String v_country1;
	private String v_zip;
        private String status;

    public String getC1_attendence() {
        return c1_attendence;
    }

    public void setC1_attendence(String c1_attendence) {
       if(c1_attendence==null || c1_attendence.isEmpty())
           this.c1_attendence="NA";else
        this.c1_attendence = c1_attendence;
    }

    public String getC1_back() {
        return c1_back;
    }

    public void setC1_back(String c1_back) {
       if(c1_back==null ||c1_back.isEmpty())
           this.c1_back="NA";else
        this.c1_back = c1_back;
    }

    public String getC1_criminal() {
        return c1_criminal;
    }

    public void setC1_criminal(String c1_criminal) {
        if(c1_criminal==null || c1_criminal.isEmpty())
            this.c1_criminal="NA";else
        this.c1_criminal = c1_criminal;
    }

    public String getC1_marks() {
        return c1_marks;
    }

    public void setC1_marks(String c1_marks) {
      if(c1_marks==null ||c1_marks.isEmpty())
          this.c1_marks="NA";else
        this.c1_marks = c1_marks;
    }

    public String getE_description() {
        return e_description;
    }

    public void setE_description(String e_description) {
       if(e_description==null||e_description.isEmpty())
           this.e_description="NA";else
        this.e_description = e_description;
    }

    public String getE_election_id() {
        return e_election_id;
    }

    public void setE_election_id(String e_election_id) {
       if(e_election_id==null ||e_election_id.isEmpty())
           this.e_election_id="NA";else
        this.e_election_id = e_election_id;
    }

    public String getE_election_name() {
        return e_election_name;
    }

    public void setE_election_name(String e_election_name) {
        if(e_election_name==null||e_election_name.isEmpty())
            this.e_election_name="NA";else
        this.e_election_name = e_election_name;
    }

    public Timestamp getE_end() {
        return e_end;
    }

    public void setE_end(Timestamp e_end) {
     
          this.e_end = e_end;
      
    }

    public Timestamp getE_nomiend() {
        return e_nomiend;
    }

    public void setE_nomiend(Timestamp e_nomiend) {
        this.e_nomiend = e_nomiend;
    }

    public Timestamp getE_nomistart() {
        return e_nomistart;
    }

    public void setE_nomistart(Timestamp e_nomistart) {
        this.e_nomistart = e_nomistart;
    }

    public Timestamp getE_scrend() {
        return e_scrend;
    }

    public void setE_scrend(Timestamp e_scrend) {
        this.e_scrend = e_scrend;
    }

    public Timestamp getE_scrstart() {
        return e_scrstart;
    }

    public void setE_scrstart(Timestamp e_scrstart) {
        this.e_scrstart = e_scrstart;
    }

    public Timestamp getE_start() {
        return e_start;
    }

    public void setE_start(Timestamp e_start) {
        this.e_start = e_start;
    }

    public Timestamp getE_withend() {
        return e_withend;
    }

    public void setE_withend(Timestamp e_withend) {
        this.e_withend = e_withend;
    }

    public Timestamp getE_withstart() {
        return e_withstart;
    }

    public void setE_withstart(Timestamp e_withstart) {
        this.e_withstart = e_withstart;
    }

    public String getI_institute_name() {
        return i_institute_name;
    }

    public void setI_institute_name(String i_institute_name) {
        if(i_institute_name==null||i_institute_name.isEmpty())
        this.i_institute_name="NA";else
        this.i_institute_name = i_institute_name;
    }

    public String getP_position_name() {
        return p_position_name;
    }

    public void setP_position_name(String p_position_name) {
       if(p_position_name==null||p_position_name.isEmpty())
           this.p_position_name="NA";else
        this.p_position_name = p_position_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status==null||status.isEmpty())
            this.status="NA";else
        this.status = status;
    }

    public String getV_admisionDate() {
        return v_admisionDate;
    }

    public void setV_admisionDate(String v_admisionDate) {
        if(v_admisionDate==null ||v_admisionDate.isEmpty())
            this.v_admisionDate="NA";else
        this.v_admisionDate = v_admisionDate;
    }

    public String getV_birthdate() {
        return v_birthdate;
    }

    public void setV_birthdate(String v_birthdate) {
        if(v_birthdate==null || v_birthdate.isEmpty())
            this.v_birthdate="NA";else
        this.v_birthdate = v_birthdate;
    }

    public String getV_c_address() {
        return v_c_address;
    }

    public void setV_c_address(String v_c_address) {
      if(v_c_address==null ||v_c_address.isEmpty())
         this.v_c_address="NA";else
        this.v_c_address = v_c_address;
    }

    public String getV_city() {
        return v_city;
    }

    public void setV_city(String v_city) {
        if(v_city==null ||v_city.isEmpty())
        this.v_city="NA"; else
        this.v_city = v_city;
    }

    public String getV_city1() {
        return v_city1;
    }

    public void setV_city1(String v_city1) {
         if(v_city1==null ||v_city1.isEmpty())
        this.v_city1="NA";else
        this.v_city1 = v_city1;
    }

    public String getV_country() {
        return v_country;
    }

    public void setV_country(String v_country) {
        if(v_country==null || v_country.isEmpty())
            this.v_country="NA";else
        this.v_country = v_country;
    }

    public String getV_country1() {
        return v_country1;
    }

    public void setV_country1(String v_country1) {
        if(v_country1==null || v_country1.isEmpty())
            this.v_country1="NA";else
        this.v_country1 = v_country1;
    }

    public String getV_course() {
        return v_course;
    }

    public void setV_course(String v_course) {
        if(v_course==null || v_course.isEmpty())
            this.v_course="NA";else
        this.v_course = v_course;
    }

    public String getV_department() {
        return v_department;
    }

    public void setV_department(String v_department) {
        if(v_department==null ||v_department.isEmpty())
            this.v_department="NA";else
        this.v_department = v_department;
    }

    public String getV_duration() {
        return v_duration;
    }

    public void setV_duration(String v_duration) {
        if(v_duration==null || v_duration.isEmpty())
            this.v_duration="NA";else
        this.v_duration = v_duration;
    }

    public String getV_email() {
        return v_email;
    }

    public void setV_email(String v_email) {
        if(v_email==null || v_email.isEmpty())
            this.v_email="NA";else
        this.v_email = v_email;
    }

    public String getV_enrollment() {
        return v_enrollment;
    }

    public void setV_enrollment(String v_enrollment) {
        if(v_enrollment==null || v_enrollment.isEmpty())
            this.v_enrollment="NA";else
        this.v_enrollment = v_enrollment;
    }

    public String getV_father() {
        return v_father;
    }

    public void setV_father(String v_father) {
        if(v_father==null || v_father.isEmpty())
            this.v_father="NA";else
        this.v_father = v_father;
    }

    public String getV_gender() {
        return v_gender;
    }

    public void setV_gender(String v_gender) {
        if(v_gender==null || v_gender.isEmpty())
            this.v_gender="NA";else
        this.v_gender = v_gender;
    }

    public String getV_mobile_number() {
        return v_mobile_number;
    }

    public void setV_mobile_number(String v_mobile_number) {
        if(v_mobile_number==null || v_mobile_number.isEmpty())
            this.v_mobile_number="NA";else
        this.v_mobile_number = v_mobile_number;
    }

    public String getV_mother() {
        return v_mother;
    }

    public void setV_mother(String v_mother) {
        if(v_mother==null || v_mother.isEmpty())
            this.v_mother="NA";else
        this.v_mother = v_mother;
    }

    public String getV_peradd() {
        return v_peradd;
    }

    public void setV_peradd(String v_peradd) {
        if(v_peradd==null || v_peradd.isEmpty())
            this.v_peradd="NA";else
        this.v_peradd = v_peradd;
    }

    public String getV_session() {
        return v_session;
    }

    public void setV_session(String v_session) {
        if(v_session==null || v_session.isEmpty())
            this.v_session="NA";else
        this.v_session = v_session;
    }

    public String getV_state() {
        return v_state;
    }

    public void setV_state(String v_state) {
       if(v_state==null || v_state.isEmpty())
           this.v_state="NA";else
        this.v_state = v_state;
    }

    public String getV_state1() {
        return v_state1;
    }

    public void setV_state1(String v_state1) {
        if(v_state1==null || v_state1.isEmpty())
           this.v_state1="NA";else
        this.v_state1 = v_state1;
    }

    public String getV_voter_name() {
        return v_voter_name;
    }

    public void setV_voter_name(String v_voter_name) {
        if(v_voter_name==null || v_voter_name.isEmpty())
            this.v_voter_name="NA";else
        this.v_voter_name = v_voter_name;
    }

    public String getV_year() {
        return v_year;
    }

    public void setV_year(String v_year) {
        if(v_year==null || v_year.isEmpty())
            this.v_year="NA";else
        this.v_year = v_year;
    }

    public String getV_zip() {
        return v_zip;
    }

    public void setV_zip(String v_zip) {
      if(v_zip==null || v_zip.isEmpty())
          this.v_zip="NA";else
        this.v_zip = v_zip;
    }

    public String getV_zip1() {
        return v_zip1;
    }

    public void setV_zip1(String v_zip1) {
         if(v_zip1==null || v_zip1.isEmpty())
          this.v_zip1="NA";else
        this.v_zip1 = v_zip1;
    }



}
