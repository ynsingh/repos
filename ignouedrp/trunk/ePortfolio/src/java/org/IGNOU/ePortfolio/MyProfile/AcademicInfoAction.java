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

import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.MyProfile.Dao.AddInfoDao;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileAcademic;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created on 09-Sep-2011
 * Edited on 12-Sep-2011
 * @author Vinay
 */
public class AcademicInfoAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private ProfileAcademic PAM = new ProfileAcademic();    
    private AddInfoDao dao = new AddInfoDao();

    public AcademicInfoAction() {
    }

    /**
     * 
     * @return Model(AcademicInfoModel)
     */
    @Override
    public Object getModel() {
        getPAM().setUserId(user_id);
        return getPAM();
    }

    @Override
    public String execute() {
        dao.saveInfo(getPAM());
        return SUCCESS;
    }

    /**
     * @return the PAM
     */
    public ProfileAcademic getPAM() {
        return PAM;
    }

    /**
     * @param PAM the PAM to set
     */
    public void setPAM(ProfileAcademic PAM) {
        this.PAM = PAM;
    }
}