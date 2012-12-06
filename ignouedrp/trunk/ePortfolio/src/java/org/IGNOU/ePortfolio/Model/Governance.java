package org.IGNOU.ePortfolio.Model;
// Generated Dec 26, 2011 12:58:34 PM by Hibernate Tools 3.2.1.GA

/**
 * Governance generated by hbm2java
 * @author IGNOU Team
 * @version 1
 * @since 25 December 2011
 */
public class Governance implements java.io.Serializable {

    private long governanceId;
    private String userId;
    private String nameCommittee;
    private String nameUniversity;
    private String durationFrom;
    private String durationTo;
    private String responsibility;
    private String url;
    private String summary;
    private String address;

    public Governance() {
    }

    public Governance(long governanceId) {
        this.governanceId = governanceId;
    }

    public Governance(long governanceId, String userId, String nameCommittee, String nameUniversity, String durationFrom, String durationTo, String responsibility, String url, String summary, String address) {
        this.governanceId = governanceId;
        this.userId = userId;
        this.nameCommittee = nameCommittee;
        this.nameUniversity = nameUniversity;
        this.durationFrom = durationFrom;
        this.durationTo = durationTo;
        this.responsibility = responsibility;
        this.url = url;
        this.summary = summary;
        this.address = address;
    }

    public long getGovernanceId() {
        return this.governanceId;
    }

    public void setGovernanceId(long governanceId) {
        this.governanceId = governanceId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNameCommittee() {
        return this.nameCommittee;
    }

    public void setNameCommittee(String nameCommittee) {
        this.nameCommittee = nameCommittee;
    }

    public String getNameUniversity() {
        return this.nameUniversity;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public String getDurationFrom() {
        return this.durationFrom;
    }

    public void setDurationFrom(String durationFrom) {
        this.durationFrom = durationFrom;
    }

    public String getDurationTo() {
        return this.durationTo;
    }

    public void setDurationTo(String durationTo) {
        this.durationTo = durationTo;
    }

    public String getResponsibility() {
        return this.responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
