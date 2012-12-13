package org.IGNOU.ePortfolio.Model;
// Generated Feb 28, 2012 10:51:27 AM by Hibernate Tools 3.2.1.GA

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Conference generated by hbm2java
 *
 * @author IGNOU Team
 * @version 1
 * @since 28-02-2012
 */
public class Conference implements java.io.Serializable {

    private Long conferenceId;
    private String userId;
    private String confType;
    private String researchArea;
    private String assoProject;
    private String projectName;
    private String role;
    private String presentationType;
    private String paperTitle;
    private Integer noCoauthor;
    private Integer pfrom;
    private Integer pto;
    private String conferenceName;
    private String dfrom;
    private String dto;
    private String orgName;
    private String venue;
    private String state;
    private String country;
    private String language;
    private String url;
    private String affiliation;
    private String abstract_;
    private String key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String key6;
    private Set<ConferenceAuthors> conferenceAuthorses = new HashSet<ConferenceAuthors>(0);
    private ArrayList<String> fname;
    private ArrayList<String> lname;

    public Conference() {
    }

    public Conference(String userId, String confType, String researchArea, String assoProject, String projectName, String role, String presentationType, String paperTitle, Integer noCoauthor, Integer pfrom, Integer pto, String conferenceName, String dfrom, String dto, String orgName, String venue, String state, String country, String language, String url, String affiliation, String abstract_, String key1, String key2, String key3, String key4, String key5, String key6, Set<ConferenceAuthors> conferenceAuthorses, ArrayList<String> fname, ArrayList<String> lname) {
        this.userId = userId;
        this.confType = confType;
        this.researchArea = researchArea;
        this.assoProject = assoProject;
        this.projectName = projectName;
        this.role = role;
        this.presentationType = presentationType;
        this.paperTitle = paperTitle;
        this.noCoauthor = noCoauthor;
        this.pfrom = pfrom;
        this.pto = pto;
        this.conferenceName = conferenceName;
        this.dfrom = dfrom;
        this.dto = dto;
        this.orgName = orgName;
        this.venue = venue;
        this.state = state;
        this.country = country;
        this.language = language;
        this.url = url;
        this.affiliation = affiliation;
        this.abstract_ = abstract_;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.key5 = key5;
        this.key6 = key6;
        this.conferenceAuthorses = conferenceAuthorses;
        this.fname = fname;
        this.lname = lname;
    }

    public Long getConferenceId() {
        return this.conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConfType() {
        return this.confType;
    }

    public void setConfType(String confType) {
        this.confType = confType;
    }

    public String getResearchArea() {
        return this.researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public String getAssoProject() {
        return this.assoProject;
    }

    public void setAssoProject(String assoProject) {
        this.assoProject = assoProject;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPresentationType() {
        return this.presentationType;
    }

    public void setPresentationType(String presentationType) {
        this.presentationType = presentationType;
    }

    public String getPaperTitle() {
        return this.paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public Integer getNoCoauthor() {
        return this.noCoauthor;
    }

    public void setNoCoauthor(Integer noCoauthor) {
        this.noCoauthor = noCoauthor;
    }

    public Integer getPfrom() {
        return this.pfrom;
    }

    public void setPfrom(Integer pfrom) {
        this.pfrom = pfrom;
    }

    public Integer getPto() {
        return this.pto;
    }

    public void setPto(Integer pto) {
        this.pto = pto;
    }

    public String getConferenceName() {
        return this.conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getDfrom() {
        return this.dfrom;
    }

    public void setDfrom(String dfrom) {
        this.dfrom = dfrom;
    }

    public String getDto() {
        return this.dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAffiliation() {
        return this.affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getAbstract_() {
        return this.abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    public String getKey1() {
        return this.key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return this.key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return this.key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey4() {
        return this.key4;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public String getKey5() {
        return this.key5;
    }

    public void setKey5(String key5) {
        this.key5 = key5;
    }

    public String getKey6() {
        return this.key6;
    }

    public void setKey6(String key6) {
        this.key6 = key6;
    }

    public Set<ConferenceAuthors> getConferenceAuthorses() {
        return this.conferenceAuthorses;
    }

    public void setConferenceAuthorses(Set<ConferenceAuthors> conferenceAuthorses) {
        this.conferenceAuthorses = conferenceAuthorses;
    }

    public ArrayList<String> getFname() {
        return fname;
    }

    public void setFname(ArrayList<String> fname) {
        this.fname = fname;
    }

    public ArrayList<String> getLname() {
        return lname;
    }

    public void setLname(ArrayList<String> lname) {
        this.lname = lname;
    }
}