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
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.AddInfoDao;
import org.IGNOU.ePortfolio.Model.ProfileCertification;

/**
 * @version 1.1
 * @since 11-Oct-2011
 * @author IGNOU Team Modified by IGNOU Team on 14-Oct2011. XML Validation are
 * implemented.
 */
public class CertificationAddAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private AddInfoDao dao = new AddInfoDao();
    private ProfileCertification pc = new ProfileCertification();
    private String user_id = new UserSession().getUserInSession();
    private String certificationName;
    private String certificationAuthority;
    private String license;
    private String certificationDate;
    private String validDate;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public CertificationAddAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.ProfileCertificationSave(pc);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getPc().setUserId(user_id);
        return getPc();
    }

    /**
     * @return the pc
     */
    public ProfileCertification getPc() {
        return pc;
    }

    /**
     * @param pc the pc to set
     */
    public void setPc(ProfileCertification pc) {
        this.pc = pc;
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
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }
}
