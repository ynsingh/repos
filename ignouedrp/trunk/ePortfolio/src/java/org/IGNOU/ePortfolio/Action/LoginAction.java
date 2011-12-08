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
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionContext;
import org.IGNOU.ePortfolio.DAO.LoginDao;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Amit
 */
public class LoginAction extends ActionSupport implements SessionAware {

    private String email_id;
    private String password, role;
    private Map session = ActionContext.getContext().getSession();
    private String msg;
    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private LoginDao ldao = new LoginDao();
    HttpServletResponse resp = ServletActionContext.getResponse();
    @SuppressWarnings("unchecked")
    public String LoginCheck() throws IOException {
        try {
            if (getLdao().FindUser(email_id, password, role)) {
                session.put("user_id", getEmail_id());
                session.put("logged-in", "true");
                DOMConfigurator.configure("log4j.xml");
                logger.error("user logged in" + getEmail_id());
                if(role.endsWith("admin"))
                {
                         resp.sendRedirect("./Admin-Index.jsp");
                }
               if(role.endsWith("faculty")){
                    resp.sendRedirect("./Faculty-Index.jsp");
                }
               if(role.endsWith("institute")){
                    resp.sendRedirect("./Institute-Index.jsp");
                }
               if(role.endsWith("guest")){
                    resp.sendRedirect("./Guest-Index.jsp");
                }
                return SUCCESS;
            } else {
                DOMConfigurator.configure("log4j.xml");
                logger.warn("user name pass not match:-" + getEmail_id() + "password:-" + getPassword());
                msg = "User Name and Password Not Matched";
                return ERROR;
            }
        } catch (Exception e) {
            DOMConfigurator.configure("log4j.xml");
            logger.fatal("login error:-" + e);
            return NONE;
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the email_id
     */
    public String getEmail_id() {
        return email_id;
    }

    /**
     * @param email_id the email_id to set
     */
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    /**
     * @return the session
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    @Override
    public void setSession(Map session) {
        this.session = session;
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
     * @return the ldao
     */
    public LoginDao getLdao() {
        return ldao;
    }

    /**
     * @param ldao the ldao to set
     */
    public void setLdao(LoginDao ldao) {
        this.ldao = ldao;
    }
}
