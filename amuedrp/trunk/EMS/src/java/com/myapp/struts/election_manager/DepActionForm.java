/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import java.sql.Timestamp;
import java.text.DateFormat;
//import java.security.Timestamp;



/**
 *
 * @author akhtar
 */
public class DepActionForm extends  org.apache.struts.action.ActionForm {

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }
    private String election;

	public String getAction() {
        	return action;
    	}

    public void setAction(String action) {
        this.action = action;
    }
    private String action;

    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
    private String electionId,instituteId,  electionname,description,status,createdby,button;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
    private String list;
    private String critaria,deaprtment,marks,attendence,backlog,criminal,indiscipline;
private Timestamp startdate,enddate,nominationStart,nominationEnd,scrutnyDate,scrutnyEndDate,withdrawlDate,withdrawlEndDate,resultDeclarationDate;

 public Timestamp getResultDeclarationDate() {
        return resultDeclarationDate;
    }

    public void setResultDeclarationDate(Timestamp resultDeclarationDate) {
        this.resultDeclarationDate = resultDeclarationDate;
    }


public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;

    }


    public String getButton() {
        return this.button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    public String getElectionname() {
        return electionname;
    }

    public void setElectionname(String electionname) {
        this.electionname = electionname;
    }


    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the critaria
     */
    public String getCritaria() {
        return critaria;
    }

    /**
     * @param critaria the critaria to set
     */
    public void setCritaria(String critaria) {
        this.critaria = critaria;
    }

    /**
     * @return the deaprtment
     */
    public String getDeaprtment() {
        return deaprtment;
    }

    /**
     * @param deaprtment the deaprtment to set
     */
    public void setDeaprtment(String deaprtment) {
        this.deaprtment = deaprtment;
    }

    /**
     * @return the marks
     */
    public String getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(String marks) {
        this.marks = marks;
    }

    /**
     * @return the attendence
     */
    public String getAttendence() {
        return attendence;
    }

    /**
     * @param attendence the attendence to set
     */
    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

    /**
     * @return the backlog
     */
    public String getBacklog() {
        return backlog;
    }

    /**
     * @param backlog the backlog to set
     */
    public void setBacklog(String backlog) {
        this.backlog = backlog;
    }

    /**
     * @return the criminal
     */
    public String getCriminal() {
        return criminal;
    }

    /**
     * @param criminal the criminal to set
     */
    public void setCriminal(String criminal) {
        this.criminal = criminal;
    }

    /**
     * @return the indiscipline
     */
    public String getIndiscipline() {
        return indiscipline;
    }

    /**
     * @param indiscipline the indiscipline to set
     */
    public void setIndiscipline(String indiscipline) {
        this.indiscipline = indiscipline;
    }

    /**
     * @return the nominationStart
     */
    public Timestamp getNominationStart() {
        return nominationStart;
    }

    /**
     * @param nominationStart the nominationStart to set
     */
    public void setNominationStart(Timestamp nominationStart) {
        this.nominationStart = nominationStart;
    }

    /**
     * @return the nominationEnd
     */
    public Timestamp getNominationEnd() {
        return nominationEnd;
    }

    /**
     * @param nominationEnd the nominationEnd to set
     */
    public void setNominationEnd(Timestamp nominationEnd) {
        this.nominationEnd = nominationEnd;
    }

    /**
     * @return the scrutnyDate
     */
    public Timestamp getScrutnyDate() {
        return scrutnyDate;
    }

    /**
     * @param scrutnyDate the scrutnyDate to set
     */
    public void setScrutnyDate(Timestamp scrutnyDate) {
        this.scrutnyDate = scrutnyDate;
    }

    /**
     * @return the scrutnyEndDate
     */
    public Timestamp getScrutnyEndDate() {
        return scrutnyEndDate;
    }

    /**
     * @param scrutnyEndDate the scrutnyEndDate to set
     */
    public void setScrutnyEndDate(Timestamp scrutnyEndDate) {
        this.scrutnyEndDate = scrutnyEndDate;
    }

    /**
     * @return the withdrawlDate
     */
    public Timestamp getWithdrawlDate() {
        return withdrawlDate;
    }

    /**
     * @param withdrawlDate the withdrawlDate to set
     */
    public void setWithdrawlDate(Timestamp withdrawlDate) {
        this.withdrawlDate = withdrawlDate;
    }

    /**
     * @return the withdrawlEndDate
     */
    public Timestamp getWithdrawlEndDate() {
        return withdrawlEndDate;
    }

    /**
     * @param withdrawlEndDate the withdrawlEndDate to set
     */
    public void setWithdrawlEndDate(Timestamp withdrawlEndDate) {
        this.withdrawlEndDate = withdrawlEndDate;
    }


}
