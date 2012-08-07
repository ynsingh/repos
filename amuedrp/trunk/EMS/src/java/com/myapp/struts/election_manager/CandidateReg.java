/*
 * ACTION FORM
 */
package com.myapp.struts.election_manager;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import javax.imageio.ImageIO;
public class CandidateReg
{
        private String e_election_id;
        private byte[] v_image;
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


    public String getNoc() {
        return noc;
    }

    public void setNoc(String noc) {
        this.noc = noc;
    }
private String noc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
private String status;

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

    public String getE_description() {
        return e_description;
    }

    public void setE_description(String e_description) {
        this.e_description = e_description;
    }

private java.awt.image.BufferedImage image;

    public BufferedImage getImage() {

        if(v_image!=null)
        { InputStream in = new ByteArrayInputStream(this.v_image);
	try{
        image = ImageIO.read(in);
        }catch(Exception e){}

        return image;
    }else{
    return null;
    }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getI_institute_name() {
        return i_institute_name;
    }

    public void setI_institute_name(String i_institute_name) {
        this.i_institute_name = i_institute_name;
    }


    public String getC1_attendence() {
        return c1_attendence;
    }

    public void setC1_attendence(String c1_attendence) {
        this.c1_attendence = c1_attendence;
    }

    public String getC1_back() {
        return c1_back;
    }

    public void setC1_back(String c1_back) {
        this.c1_back = c1_back;
    }

    public String getC1_criminal() {
        return c1_criminal;
    }

    public void setC1_criminal(String c1_criminal) {
        this.c1_criminal = c1_criminal;
    }

    public String getC1_marks() {
        return c1_marks;
    }

    public void setC1_marks(String c1_marks) {
        this.c1_marks = c1_marks;
    }

    public String getE_election_name() {
        return e_election_name;
    }

    public void setE_election_name(String e_election_name) {
        this.e_election_name = e_election_name;
    }

    public String getP_position_name() {
        return p_position_name;
    }

    public void setP_position_name(String p_position_name) {
        this.p_position_name = p_position_name;
    }

    public String getV_admisionDate() {
        return v_admisionDate;
    }

    public void setV_admisionDate(String v_admisionDate) {
        this.v_admisionDate = v_admisionDate;
    }

    public String getV_birthdate() {
        return v_birthdate;
    }

    public void setV_birthdate(String v_birthdate) {
        this.v_birthdate = v_birthdate;
    }

    public String getV_c_address() {
        return v_c_address;
    }

    public void setV_c_address(String v_c_address) {
        this.v_c_address = v_c_address;
    }

    public String getV_city() {
        return v_city;
    }

    public void setV_city(String v_city) {
        this.v_city = v_city;
    }

    public String getV_city1() {
        return v_city1;
    }

    public void setV_city1(String v_city1) {
        this.v_city1 = v_city1;
    }

    public String getV_country() {
        return v_country;
    }

    public void setV_country(String v_country) {
        this.v_country = v_country;
    }

    public String getV_country1() {
        return v_country1;
    }

    public void setV_country1(String v_country1) {
        this.v_country1 = v_country1;
    }

    public String getV_course() {
        return v_course;
    }

    public void setV_course(String v_course) {
        this.v_course = v_course;
    }

    public String getV_department() {
        return v_department;
    }

    public void setV_department(String v_department) {
        this.v_department = v_department;
    }

    public String getV_duration() {
        return v_duration;
    }

    public void setV_duration(String v_duration) {
        this.v_duration = v_duration;
    }

    public String getV_email() {
        return v_email;
    }

    public void setV_email(String v_email) {
        this.v_email = v_email;
    }

    public String getV_enrollment() {
        return v_enrollment;
    }

    public void setV_enrollment(String v_enrollment) {
        this.v_enrollment = v_enrollment;
    }

    public String getV_father() {
        return v_father;
    }

    public void setV_father(String v_father) {
        this.v_father = v_father;
    }

    public String getV_gender() {
        return v_gender;
    }

    public void setV_gender(String v_gender) {
        this.v_gender = v_gender;
    }

    public String getV_mobile_number() {
        return v_mobile_number;
    }

    public void setV_mobile_number(String v_mobile_number) {
        this.v_mobile_number = v_mobile_number;
    }

    public String getV_mother() {
        return v_mother;
    }

    public void setV_mother(String v_mother) {
        this.v_mother = v_mother;
    }

    public String getV_peradd() {
        return v_peradd;
    }

    public void setV_peradd(String v_peradd) {
        this.v_peradd = v_peradd;
    }

    public String getV_session() {
        return v_session;
    }

    public void setV_session(String v_session) {
        this.v_session = v_session;
    }

    public String getV_state() {
        return v_state;
    }

    public void setV_state(String v_state) {
        this.v_state = v_state;
    }

    public String getV_state1() {
        return v_state1;
    }

    public void setV_state1(String v_state1) {
        this.v_state1 = v_state1;
    }

    public String getV_voter_name() {
        return v_voter_name;
    }

    public void setV_voter_name(String v_voter_name) {
        this.v_voter_name = v_voter_name;
    }

    public String getV_year() {
        return v_year;
    }

    public void setV_year(String v_year) {
        this.v_year = v_year;
    }

    public String getV_zip() {
        return v_zip;
    }

    public void setV_zip(String v_zip) {
        this.v_zip = v_zip;
    }

    public String getV_zip1() {
        return v_zip1;
    }

    public void setV_zip1(String v_zip1) {
        this.v_zip1 = v_zip1;
    }

    public String getE_election_id() {
        return e_election_id;
    }

    public void setE_election_id(String e_election_id) {
        this.e_election_id = e_election_id;
    }

    public byte[] getV_image() {
        return v_image;
    }

    public void setV_image(byte[] v_image) {
        this.v_image = v_image;
    }


}
