/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Amit
 */
public class LogoAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private File uniLogo;
    private String user_id = new UserSession().getUserInSession();
    private String logopath;
    private Map session = ActionContext.getContext().getSession();

    public String ChangeUnivLogo() throws IOException {

        logopath = session.get("appPath").toString();
        File fileToCreate = new File(logopath, getText("logoName"));
        FileUtils.copyFile(getUniLogo(), fileToCreate);
        return SUCCESS;

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
     * @return the logopath
     */
    public String getLogopath() {
        return logopath;
    }

    /**
     * @param logopath the logopath to set
     */
    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    /**
     * @return the uniLogo
     */
    public File getUniLogo() {
        return uniLogo;
    }

    /**
     * @param uniLogo the uniLogo to set
     */
    public void setUniLogo(File uniLogo) {
        this.uniLogo = uniLogo;
    }
}
