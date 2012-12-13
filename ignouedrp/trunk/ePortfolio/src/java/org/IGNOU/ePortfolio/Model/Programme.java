package org.IGNOU.ePortfolio.Model;
// Generated May 15, 2012 12:26:54 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Programme generated by hbm2java
 */
public class Programme implements java.io.Serializable {

    private int programmeId;
    private Department department;
    private Institute institute;
    private String programmeName;
    private String programmeCode;
    private Integer duration;
    private String overview;
   // private Set courses = new HashSet(0);
   // private Set users = new HashSet(0);

    public Programme() {
    }

    public Programme(int programmeId) {
        this.programmeId = programmeId;
    }

    public Programme(int programmeId, Department department, Institute institute, String programmeName, String programmeCode, Integer duration, String overview/*, Set courses*/) {
        this.programmeId = programmeId;
        this.department = department;
        this.institute = institute;
        this.programmeName = programmeName;
        this.programmeCode = programmeCode;
        this.duration = duration;
        this.overview = overview;
      //  this.courses = courses;
    }

    public int getProgrammeId() {
        return this.programmeId;
    }

    public void setProgrammeId(int programmeId) {
        this.programmeId = programmeId;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Institute getInstitute() {
        return this.institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getProgrammeName() {
        return this.programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getProgrammeCode() {
        return this.programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * @return the overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview the overview to set
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }
//
//    public Set getCourses() {
//        return this.courses;
//    }
//
//    public void setCourses(Set courses) {
//        this.courses = courses;
//    }

    /**
     * @return the users
     */
//    public Set getUsers() {
//        return users;
//    }
//
//    /**
//     * @param users the users to set
//     */
//    public void setUsers(Set users) {
//        this.users = users;
//    }
}