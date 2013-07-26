/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.Model.ProfileCertification;

/**
 *
 * @author IGNOU Team
 */
public class CertificationAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private MyProfileDAO dao = new MyProfileDAO();
    private ProfileCertification Certificate;
    private List<ProfileCertification> CertificateList;
    private long certificationId;
    private String userId;
    private String certificationName;
    private String certificationAuthority;
    private String license;
    private String certificationDate;
    private String validDate;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public CertificationAction() {
    }

    @Override
    public String execute() throws Exception {
        CertificateList = dao.ProfileCertificationListByUserId(user_id);
        if (CertificateList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String editCertificateInfo() throws Exception {
        CertificateList = dao.ProfileCertificationByCertificationId(certificationId);
        return SUCCESS;
    }

    public String UpdateCertificateInfo() throws Exception {
        dao.ProfileCertificationUpdate(certificationId, user_id, certificationName, certificationAuthority, license, certificationDate, validDate);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String deleteCertificateInfo() throws Exception {
        dao.ProfileCertificationDelete(certificationId);
        msg = infoDeleted;
        return SUCCESS;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
    public MyProfileDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(MyProfileDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the Certificate
     */
    public ProfileCertification getCertificate() {
        return Certificate;
    }

    /**
     * @param Certificate the Certificate to set
     */
    public void setCertificate(ProfileCertification Certificate) {
        this.Certificate = Certificate;
    }

    /**
     * @return the CertificateList
     */
    public List<ProfileCertification> getCertificateList() {
        return CertificateList;
    }

    /**
     * @param CertificateList the CertificateList to set
     */
    public void setCertificateList(List<ProfileCertification> CertificateList) {
        this.CertificateList = CertificateList;
    }

    /**
     * @return the certificationId
     */
    public long getCertificationId() {
        return certificationId;
    }

    /**
     * @param certificationId the certificationId to set
     */
    public void setCertificationId(long certificationId) {
        this.certificationId = certificationId;
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
     * @return the certificationName
     */
    public String getCertificationName() {
        return certificationName;
    }

    /**
     * @param certificationName the certificationName to set
     */
    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    /**
     * @return the certificationAuthority
     */
    public String getCertificationAuthority() {
        return certificationAuthority;
    }

    /**
     * @param certificationAuthority the certificationAuthority to set
     */
    public void setCertificationAuthority(String certificationAuthority) {
        this.certificationAuthority = certificationAuthority;
    }

    /**
     * @return the license
     */
    public String getLicense() {
        return license;
    }

    /**
     * @param license the license to set
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * @return the certificationDate
     */
    public String getCertificationDate() {
        return certificationDate;
    }

    /**
     * @param certificationDate the certificationDate to set
     */
    public void setCertificationDate(String certificationDate) {
        this.certificationDate = certificationDate;
    }

    /**
     * @return the validDate
     */
    public String getValidDate() {
        return validDate;
    }

    /**
     * @param validDate the validDate to set
     */
    public void setValidDate(String validDate) {
        this.validDate = validDate;
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