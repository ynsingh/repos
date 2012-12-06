/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.PatentDao;
import org.IGNOU.ePortfolio.Model.Inventor;
import org.IGNOU.ePortfolio.Model.Patent;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 28-01-2012
 *
 */
public class PatentAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private Patent patentModel = new Patent();
    private Inventor InventorModel = new Inventor();
    private PatentDao dao = new PatentDao();
    private List<Patent> PatentListList = null;
    private Long patentId;
    private String userId;
    private String patentType;
    private String country;
    private String patentTitle;
    private String assignee;
    private Integer applNo;
    private String field;
    private String patentDate;
    private Integer patentNo;
    private String affiliation;
    private String language;
    private String url;
    private String abstract_;
    private Integer api;
    private ArrayList<String> fname;
    private ArrayList<String> lname;
    private ArrayList<String> address;
    private Set<Inventor> inventors = new HashSet<Inventor>(0);
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public PatentAction() {
    }

    public String ShowPatentInfo() throws Exception {
        setPatentListList(getDao().ShowPatentInfomration(getUser_id()));
        if (getPatentListList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditPatentInfo() throws Exception {
        setPatentListList(getDao().EditInfo(getPatentId()));
        return SUCCESS;
    }

    public String UpdaPatentInfo() throws Exception {
        dao.UpdatePatent(patentId, userId, patentType, country, patentTitle, assignee, applNo, field, patentDate, patentNo, affiliation, language, url, abstract_, api, fname, lname, address, inventors);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeletePatentInfo() throws Exception {
        getDao().DeletePatent(getPatentId());
        msg = infoDeleted;
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
     * @return the patentModel
     */
    public Patent getPatentModel() {
        return patentModel;
    }

    /**
     * @param patentModel the patentModel to set
     */
    public void setPatentModel(Patent patentModel) {
        this.patentModel = patentModel;
    }

    /**
     * @return the InventorModel
     */
    public Inventor getInventorModel() {

        return InventorModel;
    }

    /**
     * @param InventorModel the InventorModel to set
     */
    public void setInventorModel(Inventor InventorModel) {
        this.InventorModel = InventorModel;
    }

    /**
     * @return the dao
     */
    public PatentDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(PatentDao dao) {
        this.dao = dao;
    }

    /**
     * @return the PatentListList
     */
    public List<Patent> getPatentListList() {
        return PatentListList;
    }

    /**
     * @param PatentListList the PatentListList to set
     */
    public void setPatentListList(List<Patent> PatentListList) {
        this.PatentListList = PatentListList;
    }

    /**
     * @return the patentId
     */
    public Long getPatentId() {
        return patentId;
    }

    /**
     * @param patentId the patentId to set
     */
    public void setPatentId(Long patentId) {
        this.patentId = patentId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the patentType
     */
    public String getPatentType() {
        return patentType;
    }

    /**
     * @param patentType the patentType to set
     */
    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the patentTitle
     */
    public String getPatentTitle() {
        return patentTitle;
    }

    /**
     * @param patentTitle the patentTitle to set
     */
    public void setPatentTitle(String patentTitle) {
        this.patentTitle = patentTitle;
    }

    /**
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the applNo
     */
    public Integer getApplNo() {
        return applNo;
    }

    /**
     * @param applNo the applNo to set
     */
    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the patentDate
     */
    public String getPatentDate() {
        return patentDate;
    }

    /**
     * @param patentDate the patentDate to set
     */
    public void setPatentDate(String patentDate) {
        this.patentDate = patentDate;
    }

    /**
     * @return the patentNo
     */
    public Integer getPatentNo() {
        return patentNo;
    }

    /**
     * @param patentNo the patentNo to set
     */
    public void setPatentNo(Integer patentNo) {
        this.patentNo = patentNo;
    }

    /**
     * @return the affiliation
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * @param affiliation the affiliation to set
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the abstract_
     */
    public String getAbstract_() {
        return abstract_;
    }

    /**
     * @param abstract_ the abstract_ to set
     */
    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    /**
     * @return the api
     */
    public Integer getApi() {
        return api;
    }

    /**
     * @param api the api to set
     */
    public void setApi(Integer api) {
        this.api = api;
    }

    /**
     * @return the fname
     */
    public ArrayList<String> getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(ArrayList<String> fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public ArrayList<String> getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(ArrayList<String> lname) {
        this.lname = lname;
    }

    /**
     * @return the address
     */
    public ArrayList<String> getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(ArrayList<String> address) {
        this.address = address;
    }

    /**
     * @return the inventors
     */
    public Set<Inventor> getInventors() {
//       for(int i=0;i<fname.size();i++){
//          
//       }
        Set<Inventor> inventors = new HashSet();
        return inventors;
    }

    /**
     * @param inventors the inventors to set
     */
    public void setInventors(Set<Inventor> inventors) {
        this.inventors = inventors;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the infoDeleted
     */
    public String getInfoDeleted() {
        return infoDeleted;
    }

    /**
     * @param infoDeleted the infoDeleted to set
     */
    public void setInfoDeleted(String infoDeleted) {
        this.infoDeleted = infoDeleted;
    }

    /**
     * @return the infoUpdated
     */
    public String getInfoUpdated() {
        return infoUpdated;
    }

    /**
     * @param infoUpdated the infoUpdated to set
     */
    public void setInfoUpdated(String infoUpdated) {
        this.infoUpdated = infoUpdated;
    }
}
