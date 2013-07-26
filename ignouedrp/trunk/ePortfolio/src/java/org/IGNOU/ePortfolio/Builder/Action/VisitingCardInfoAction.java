/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.VisitingCardDao;
import org.IGNOU.ePortfolio.Model.Vistingcard;

/**
 *
 * @author Amit
 */
public class VisitingCardInfoAction extends ActionSupport{
     private String user_id = new UserSession().getUserInSession();
    private VisitingCardDao vcDao = new VisitingCardDao();
   private List<Vistingcard> vcList;
    private Integer visitcardId;
    private String displayName;
    private String designation;
    private String company;
    private Long mobile;
    private Long officePh;
    private Integer fax;
    private String email;
    private String websiteOff;
    private String websitePer;
    
    
     public String VisitingcardDetail() {
        vcList = vcDao.VisitingCardDetailByUserId(user_id);
        if (vcList.isEmpty()) {
            return INPUT;
        }
        visitcardId=vcList.iterator().next().getVisitcardId();
        return SUCCESS;
    }

    public String VisitingCardEdit() {
        vcList = vcDao.VisitingCardEdit(visitcardId);
        return SUCCESS;
    }

    public String VisitingCardUpdate() {
        vcDao.VisitingCardUpdate(visitcardId,user_id, displayName, designation, company, mobile, officePh, fax, email, websiteOff, websitePer);
        return SUCCESS;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the vcDao
     */
    public VisitingCardDao getVcDao() {
        return vcDao;
    }

    /**
     * @param vcDao the vcDao to set
     */
    public void setVcDao(VisitingCardDao vcDao) {
        this.vcDao = vcDao;
    }

    
    /**
     * @return the vcList
     */
    public List<Vistingcard> getVcList() {
        return vcList;
    }

    /**
     * @param vcList the vcList to set
     */
    public void setVcList(List<Vistingcard> vcList) {
        this.vcList = vcList;
    }

    /**
     * @return the visitcardId
     */
    public Integer getVisitcardId() {
        return visitcardId;
    }

    /**
     * @param visitcardId the visitcardId to set
     */
    public void setVisitcardId(Integer visitcardId) {
        this.visitcardId = visitcardId;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the mobile
     */
    public Long getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the officePh
     */
    public Long getOfficePh() {
        return officePh;
    }

    /**
     * @param officePh the officePh to set
     */
    public void setOfficePh(Long officePh) {
        this.officePh = officePh;
    }

    /**
     * @return the fax
     */
    public Integer getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(Integer fax) {
        this.fax = fax;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the websiteOff
     */
    public String getWebsiteOff() {
        return websiteOff;
    }

    /**
     * @param websiteOff the websiteOff to set
     */
    public void setWebsiteOff(String websiteOff) {
        this.websiteOff = websiteOff;
    }

    /**
     * @return the websitePer
     */
    public String getWebsitePer() {
        return websitePer;
    }

    /**
     * @param websitePer the websitePer to set
     */
    public void setWebsitePer(String websitePer) {
        this.websitePer = websitePer;
    }
    
}
