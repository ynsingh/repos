/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author faraz
 */
public class ShowElectionActionForm extends org.apache.struts.action.ActionForm {
      private String id;
     private String electionName;
     private String description;
     private String startDate;

     private String endDate;
      private String nstart;
       private String nend;
       private String scrutnyDate;
        private String scrutnyEndDate;
         private String withdrawlDate;
       private String withdrawlEndDate;
       private String noofcandi;

    public String getNoofcandi() {
        return noofcandi;
    }

    public void setNoofcandi(String noofcandi) {
        this.noofcandi = noofcandi;
    }

     private String status;
     private String createdBy;
private String no_of_voters;

    public String getNo_of_voters() {
        return no_of_voters;
    }

    public void setNo_of_voters(String no_of_voters) {
        this.no_of_voters = no_of_voters;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNend() {
        return nend;
    }

    public void setNend(String nend) {
        this.nend = nend;
    }

    public String getNstart() {
        return nstart;
    }

    public void setNstart(String nstart) {
        this.nstart = nstart;
    }

    public String getScrutnyDate() {
        return scrutnyDate;
    }

    public void setScrutnyDate(String scrutnyDate) {
        this.scrutnyDate = scrutnyDate;
    }

    public String getScrutnyEndDate() {
        return scrutnyEndDate;
    }

    public void setScrutnyEndDate(String scrutnyEndDate) {
        this.scrutnyEndDate = scrutnyEndDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWithdrawlDate() {
        return withdrawlDate;
    }

    public void setWithdrawlDate(String withdrawlDate) {
        this.withdrawlDate = withdrawlDate;
    }

    public String getWithdrawlEndDate() {
        return withdrawlEndDate;
    }

    public void setWithdrawlEndDate(String withdrawlEndDate) {
        this.withdrawlEndDate = withdrawlEndDate;
    }
}
