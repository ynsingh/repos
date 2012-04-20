package bus;

import java.io.Serializable;

public class Directory implements Serializable {

    private int id;
    private int userid;
    private String mailid;
    private String name;
    private String address;
    private String mobileno;
    private String otherno;
    private String homeno;
    private String officeno;
    private String designation;
    private String department;
    private String state;
    private String country;

    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public void setUserid(int ud) {
        userid = ud;
    }

    public int getUserid() {
        return userid;
    }

    public void setMailid(String mi) {
        mailid = mi;
    }

    public String getMailid() {
        return mailid;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String ad) {
        address = ad;
    }

    public String getAddress() {
        return address;
    }

    public void setMobileno(String mn) {
        mobileno = mn;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setOtherno(String otn) {
        otherno = otn;
    }

    public String getOtherno() {
        return otherno;
    }

    public void setHomeno(String hn) {
        homeno = hn;
    }

    public String getHomeno() {
        return homeno;
    }

    public void setOfficeno(String ofn) {
        officeno = ofn;
    }

    public String getOfficeno() {
        return officeno;
    }

    public void setDesignation(String dgn) {
        designation = dgn;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDepartment(String dpt) {
        department = dpt;
    }

    public String getDepartment() {
        return department;
    }

    public void setState(String st) {
        state = st;
    }

    public String getState() {
        return state;
    }

    public void setCountry(String cn) {
        country = cn;
    }

    public String getCountry() {
        return country;
    }
}

