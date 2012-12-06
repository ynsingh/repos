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
import org.IGNOU.ePortfolio.DAO.ConsultancyDao;
import org.IGNOU.ePortfolio.Model.Consultancy;
import org.IGNOU.ePortfolio.Model.ConsultancyNature;

/**
 * @author IGNOU Team
 * @version 1
 * @since 25 December 2011
 */
public class ConsultancyInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private ConsultancyDao dao = new ConsultancyDao();
    private Consultancy Consult = new Consultancy();
    private Long consultancyId;
    private String userId;
    private String clientName;
    private String DFrom;
    private String DTo;
    private Integer noOfConsultancy;
    private String revenue;
    private String service;
    private String url;
    private String summary;
    private ArrayList<String> nameConsultancy;
    private ArrayList<String> natureWork;
    private Set<ConsultancyNature> consultancyNatures = new HashSet<ConsultancyNature>(0);
    private List<Consultancy> ConsultList = null;
    private ArrayList<Long> CNatureId;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public ConsultancyInfoAction() {
    }

    public String ShowConsultInfo() throws Exception {
        setConsultList(getDao().ShowConsultancyInfo(getUser_id()));
        if (getConsultList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String DeleteConsultInfo() throws Exception {
        getDao().DeleteConsultancyInfo(getConsultancyId());
        msg = infoDeleted;
        return SUCCESS;
    }

    public String EditConsult() throws Exception {
        setConsultList(getDao().EditConsultancyInfo(getConsultancyId()));
        return SUCCESS;
    }

    public String UpdateConsult() throws Exception {
        // getDao().UpdateConsultancyInfo(getConsultancyId(), nameClient, durationFrom, durationTo, facultyInvolved, getRevenue(), fasilitiesUsed, getUrl(), getSummary(), getUserId());
        getDao().UpdateConsultancyInfo(getCNatureId(), consultancyId, userId, clientName, DFrom, DTo, noOfConsultancy, revenue, service, url, summary, nameConsultancy, natureWork, consultancyNatures);

        msg = infoUpdated;
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
     * @return the dao
     */
    public ConsultancyDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ConsultancyDao dao) {
        this.dao = dao;
    }

    /**
     * @return the Consult
     */
    public Consultancy getConsult() {
        return Consult;
    }

    /**
     * @param Consult the Consult to set
     */
    public void setConsult(Consultancy Consult) {
        this.Consult = Consult;
    }

    /**
     * @return the consultancyId
     */
    public Long getConsultancyId() {
        return consultancyId;
    }

    /**
     * @param consultancyId the consultancyId to set
     */
    public void setConsultancyId(Long consultancyId) {
        this.consultancyId = consultancyId;
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
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the DFrom
     */
    public String getDFrom() {
        return DFrom;
    }

    /**
     * @param DFrom the DFrom to set
     */
    public void setDFrom(String DFrom) {
        this.DFrom = DFrom;
    }

    /**
     * @return the DTo
     */
    public String getDTo() {
        return DTo;
    }

    /**
     * @param DTo the DTo to set
     */
    public void setDTo(String DTo) {
        this.DTo = DTo;
    }

    /**
     * @return the noOfConsultancy
     */
    public Integer getNoOfConsultancy() {
        return noOfConsultancy;
    }

    /**
     * @param noOfConsultancy the noOfConsultancy to set
     */
    public void setNoOfConsultancy(Integer noOfConsultancy) {
        this.noOfConsultancy = noOfConsultancy;
    }

    /**
     * @return the revenue
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
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
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the nameConsultancy
     */
    public ArrayList<String> getNameConsultancy() {
        return nameConsultancy;
    }

    /**
     * @param nameConsultancy the nameConsultancy to set
     */
    public void setNameConsultancy(ArrayList<String> nameConsultancy) {
        this.nameConsultancy = nameConsultancy;
    }

    /**
     * @return the natureWork
     */
    public ArrayList<String> getNatureWork() {
        return natureWork;
    }

    /**
     * @param natureWork the natureWork to set
     */
    public void setNatureWork(ArrayList<String> natureWork) {
        this.natureWork = natureWork;
    }

    /**
     * @return the consultancyNatures
     */
    public Set<ConsultancyNature> getConsultancyNatures() {
        return consultancyNatures;
    }

    /**
     * @param consultancyNatures the consultancyNatures to set
     */
    public void setConsultancyNatures(Set<ConsultancyNature> consultancyNatures) {
        this.consultancyNatures = consultancyNatures;
    }

    /**
     * @return the ConsultList
     */
    public List<Consultancy> getConsultList() {
        return ConsultList;
    }

    /**
     * @param ConsultList the ConsultList to set
     */
    public void setConsultList(List<Consultancy> ConsultList) {
        this.ConsultList = ConsultList;
    }

    /**
     * @return the CNatureId
     */
    public ArrayList<Long> getCNatureId() {
        return CNatureId;
    }

    /**
     * @param CNatureId the CNatureId to set
     */
    public void setCNatureId(ArrayList<Long> CNatureId) {
        this.CNatureId = CNatureId;
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
