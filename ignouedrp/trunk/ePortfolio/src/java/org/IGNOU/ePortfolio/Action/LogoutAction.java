package org.IGNOU.ePortfolio.Action;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.Serializable;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.IGNOU.ePortfolio.DAO.LoginDao;
import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;

/**
 *
 * @author IGNOU Team
 */
@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport implements Serializable {

    HttpServletResponse resp = ServletActionContext.getResponse();
    final Logger logger = Logger.getLogger(this.getClass());
    private LoginDao dao = new LoginDao();

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        if (session.isEmpty()) {
            return INPUT;
        }
        String picpath = session.get("appPath").toString();
        String picname = session.get("picname").toString();
        String user = session.get("user_id").toString();
        String logId = session.get("logId").toString();
        File ecardt = new File(picpath + "/" + user.substring(0, 4) + "Ecard.png");
        if (ecardt.isFile()) {
            ecardt.delete();
        } else {
            logger.warn("Nothing to delete");
        }
        if (picname.equals("images/user.png")) {
            logger.warn("Nothing to delete");
        } else {
            new File(picpath + picname).delete();
            session.remove("appPath");
            session.remove("picname");
        }
        dao.logOutUpdate(Long.valueOf(logId));
        session.remove("user_id");
        session.remove("appPath");
        session.remove("picname");
        session.remove("role");
        session.remove("uName");
        session.remove("logId");
        session.clear();
        logger.warn("user Logged Out with userid   " + user);
        return SUCCESS;
    }
}
