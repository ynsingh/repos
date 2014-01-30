/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Group.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.PeerGroupDao;
import org.IGNOU.ePortfolio.Model.UserList;

/**
 *
 * @author Vinay
 */
public class ProfileDetailsAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private String emailId;
    private PeerGroupDao dao = new PeerGroupDao();
    private UserList userlistModel = new UserList();
    private List<UserList> ProfileDetailsList;
    private String AcademicList, PublicationList, EmployeeList, CertificateList, ContactList, profileProAffiliations, profileCertifications, profileHonorAwards;
    private String profileInterests, projectses, profileSocials, extraActivitieses;
//    patents
//    journals
//    conferences 
//    consultancies 
//    studentExchanges

    public ProfileDetailsAction() {
    }

    public String PeerGroupProfileDetails() throws Exception {
        ProfileDetailsList = dao.UserListDetailByUserId(emailId);
        if (ProfileDetailsList.iterator().next().getProfileAcademics().isEmpty()) {
            AcademicList = "NULL";
        }
        if (ProfileDetailsList.iterator().next().getConferences().isEmpty() || ProfileDetailsList.iterator().next().getJournals().isEmpty() || ProfileDetailsList.iterator().next().getPatents().isEmpty()) {
            PublicationList = "NULL";
        }
        return SUCCESS;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the userlistModel
     */
    public UserList getUserlistModel() {
        return userlistModel;
    }

    /**
     * @param userlistModel the userlistModel to set
     */
    public void setUserlistModel(UserList userlistModel) {
        this.userlistModel = userlistModel;
    }

    /**
     * @return the ProfileDetailsList
     */
    public List<UserList> getProfileDetailsList() {
        return ProfileDetailsList;
    }

    /**
     * @param ProfileDetailsList the ProfileDetailsList to set
     */
    public void setProfileDetailsList(List<UserList> ProfileDetailsList) {
        this.ProfileDetailsList = ProfileDetailsList;
    }

    /**
     * @return the AcademicList
     */
    public String getAcademicList() {
        return AcademicList;
    }

    /**
     * @param AcademicList the AcademicList to set
     */
    public void setAcademicList(String AcademicList) {
        this.AcademicList = AcademicList;
    }

    /**
     * @return the PublicationList
     */
    public String getPublicationList() {
        return PublicationList;
    }

    /**
     * @param PublicationList the PublicationList to set
     */
    public void setPublicationList(String PublicationList) {
        this.PublicationList = PublicationList;
    }

    /**
     * @return the EmployeeList
     */
    public String getEmployeeList() {
        return EmployeeList;
    }

    /**
     * @param EmployeeList the EmployeeList to set
     */
    public void setEmployeeList(String EmployeeList) {
        this.EmployeeList = EmployeeList;
    }

    /**
     * @return the CertificateList
     */
    public String getCertificateList() {
        return CertificateList;
    }

    /**
     * @param CertificateList the CertificateList to set
     */
    public void setCertificateList(String CertificateList) {
        this.CertificateList = CertificateList;
    }

    /**
     * @return the ContactList
     */
    public String getContactList() {
        return ContactList;
    }

    /**
     * @param ContactList the ContactList to set
     */
    public void setContactList(String ContactList) {
        this.ContactList = ContactList;
    }

    /**
     * @return the profileProAffiliations
     */
    public String getProfileProAffiliations() {
        return profileProAffiliations;
    }

    /**
     * @param profileProAffiliations the profileProAffiliations to set
     */
    public void setProfileProAffiliations(String profileProAffiliations) {
        this.profileProAffiliations = profileProAffiliations;
    }

    /**
     * @return the profileCertifications
     */
    public String getProfileCertifications() {
        return profileCertifications;
    }

    /**
     * @param profileCertifications the profileCertifications to set
     */
    public void setProfileCertifications(String profileCertifications) {
        this.profileCertifications = profileCertifications;
    }

    /**
     * @return the profileHonorAwards
     */
    public String getProfileHonorAwards() {
        return profileHonorAwards;
    }

    /**
     * @param profileHonorAwards the profileHonorAwards to set
     */
    public void setProfileHonorAwards(String profileHonorAwards) {
        this.profileHonorAwards = profileHonorAwards;
    }

    /**
     * @return the profileInterests
     */
    public String getProfileInterests() {
        return profileInterests;
    }

    /**
     * @param profileInterests the profileInterests to set
     */
    public void setProfileInterests(String profileInterests) {
        this.profileInterests = profileInterests;
    }

    /**
     * @return the projectses
     */
    public String getProjectses() {
        return projectses;
    }

    /**
     * @param projectses the projectses to set
     */
    public void setProjectses(String projectses) {
        this.projectses = projectses;
    }

    /**
     * @return the profileSocials
     */
    public String getProfileSocials() {
        return profileSocials;
    }

    /**
     * @param profileSocials the profileSocials to set
     */
    public void setProfileSocials(String profileSocials) {
        this.profileSocials = profileSocials;
    }

    /**
     * @return the extraActivitieses
     */
    public String getExtraActivitieses() {
        return extraActivitieses;
    }

    /**
     * @param extraActivitieses the extraActivitieses to set
     */
    public void setExtraActivitieses(String extraActivitieses) {
        this.extraActivitieses = extraActivitieses;
    }
}
