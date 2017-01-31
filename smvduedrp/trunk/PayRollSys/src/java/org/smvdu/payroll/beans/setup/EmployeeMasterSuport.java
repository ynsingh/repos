/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.Date;
/**
 * Manages Attendance in database.
 *   Copyright (c) 2010 - 2011 SMVDU, Katra.
 *   Copyright (c) 2014 - 2017 ETRG, IITK.
 *   All Rights Reserved.
 **  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met: 
 **  Redistributions of source code must retain the above copyright 
 *  notice, this  list of conditions and the following disclaimer. 
 * 
 *  Redistribution in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution. 
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 *  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 * 
 * 
 *  Contributors: Members of ERP Team @ SMVDU, Katra
 *
 * @author : Om Prakash (omprakashkgp@gmail.com), January2017
 */
public class EmployeeMasterSuport implements Serializable {

    public EmployeeMasterSuport() {
    }
    
    private int emsId;
    private String emsCode;
    private String entitledcat;
    private String statusemp;
    private String statusEmp;
    private int salDeptCode;
    private int joiningDept;
    private int joinedDesig;
    private String emsgpfNo;
    private String emsnpsNo;
    private String emsDpsNo;
    private String houseType;
    private String houseNo;
    private String emsECRno;
    private String pageNo;
    private String postingId;
    private String licpolicyNo;
    private Date licDoa;
    private Date licDom;
    private String giPolicyNo;
    private Date gidoa;
    private Date giDom;
    private Date nextincrement;
    private Date probationDate;
    private Date confirmation;
    private Date extentionDate;

    public int getEmsId() {
        return emsId;
    }

    public void setEmsId(int emsId) {
        this.emsId = emsId;
    }

    public String getEmsCode() {
        return emsCode;
    }

    public void setEmsCode(String emsCode) {
        this.emsCode = emsCode;
    }

    public String getEntitledcat() {
        return entitledcat;
    }

    public void setEntitledcat(String entitledcat) {
        this.entitledcat = entitledcat;
    }

    public String getStatusemp() {
        return statusemp;
    }

    public void setStatusemp(String statusemp) {
        this.statusemp = statusemp;
    }

    public String getStatusEmp() {
        return statusEmp;
    }

    public void setStatusEmp(String statusEmp) {
        this.statusEmp = statusEmp;
    }

    public int getSalDeptCode() {
        return salDeptCode;
    }

    public void setSalDeptCode(int salDeptCode) {
        this.salDeptCode = salDeptCode;
    }

    public int getJoiningDept() {
        return joiningDept;
    }

    public void setJoiningDept(int joiningDept) {
        this.joiningDept = joiningDept;
    }

    public int getJoinedDesig() {
        return joinedDesig;
    }

    public void setJoinedDesig(int joinedDesig) {
        this.joinedDesig = joinedDesig;
    }

    public String getEmsgpfNo() {
        return emsgpfNo;
    }

    public void setEmsgpfNo(String emsgpfNo) {
        this.emsgpfNo = emsgpfNo;
    }

    public String getEmsnpsNo() {
        return emsnpsNo;
    }

    public void setEmsnpsNo(String emsnpsNo) {
        this.emsnpsNo = emsnpsNo;
    }

    public String getEmsDpsNo() {
        return emsDpsNo;
    }

    public void setEmsDpsNo(String emsDpsNo) {
        this.emsDpsNo = emsDpsNo;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getEmsECRno() {
        return emsECRno;
    }

    public void setEmsECRno(String emsECRno) {
        this.emsECRno = emsECRno;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId;
    }

    public String getLicpolicyNo() {
        return licpolicyNo;
    }

    public void setLicpolicyNo(String licpolicyNo) {
        this.licpolicyNo = licpolicyNo;
    }
       
    public Date getLicDoa() {
        return licDoa;
    }

    public void setLicDoa(Date licDoa) {
        this.licDoa = licDoa;
    }

    public Date getLicDom() {
        return licDom;
    }

    public void setLicDom(Date licDom) {
        this.licDom = licDom;
    }

    public String getGiPolicyNo() {
        return giPolicyNo;
    }

    public void setGiPolicyNo(String giPolicyNo) {
        this.giPolicyNo = giPolicyNo;
    }

    public Date getGidoa() {
        return gidoa;
    }

    public void setGidoa(Date gidoa) {
        this.gidoa = gidoa;
    }

    public Date getGiDom() {
        return giDom;
    }

    public void setGiDom(Date giDom) {
        this.giDom = giDom;
    }

    public Date getNextincrement() {
        return nextincrement;
    }

    public void setNextincrement(Date nextincrement) {
        this.nextincrement = nextincrement;
    }

    public Date getProbationDate() {
        return probationDate;
    }

    public void setProbationDate(Date probationDate) {
        this.probationDate = probationDate;
    }

    public Date getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Date confirmation) {
        this.confirmation = confirmation;
    }

    public Date getExtentionDate() {
        return extentionDate;
    }

    public void setExtentionDate(Date extentionDate) {
        this.extentionDate = extentionDate;
    }
    
}
