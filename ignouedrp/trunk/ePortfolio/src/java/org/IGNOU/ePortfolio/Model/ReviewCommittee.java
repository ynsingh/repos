package org.IGNOU.ePortfolio.Model;
// Generated Feb 17, 2012 12:35:35 PM by Hibernate Tools 3.2.1.GA

/**
 * ReviewCommittee generated by hbm2java
 *
 * @author IGNOU Team
 * @version 1
 * @since 17-02-2012
 */
public class ReviewCommittee implements java.io.Serializable {

    private long reviewCommitteeId;
    private String userId;
    private String committeeType;
    private String role;
    private String committeeName;
    private String date;
    private String frequency;
    private String url;
    private String review;
    private byte[] minutesFile;

    public ReviewCommittee() {
    }

    public ReviewCommittee(long reviewCommitteeId) {
        this.reviewCommitteeId = reviewCommitteeId;
    }

    public ReviewCommittee(long reviewCommitteeId, String userId, String committeeType, String role, String committeeName, String date, String frequency, String url, String review, byte[] minutesFile) {
        this.reviewCommitteeId = reviewCommitteeId;
        this.userId = userId;
        this.committeeType = committeeType;
        this.role = role;
        this.committeeName = committeeName;
        this.date = date;
        this.frequency = frequency;
        this.url = url;
        this.review = review;
        this.minutesFile = minutesFile;
    }

    public long getReviewCommitteeId() {
        return this.reviewCommitteeId;
    }

    public void setReviewCommitteeId(long reviewCommitteeId) {
        this.reviewCommitteeId = reviewCommitteeId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommitteeType() {
        return this.committeeType;
    }

    public void setCommitteeType(String committeeType) {
        this.committeeType = committeeType;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCommitteeName() {
        return this.committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public byte[] getMinutesFile() {
        return this.minutesFile;
    }

    public void setMinutesFile(byte[] minutesFile) {
        this.minutesFile = minutesFile;
    }
}