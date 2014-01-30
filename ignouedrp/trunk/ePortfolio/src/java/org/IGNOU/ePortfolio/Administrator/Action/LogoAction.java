/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Amit
 */
public class LogoAction extends ActionSupport implements Serializable, SessionAware {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private Map session = ActionContext.getContext().getSession();
    private String user_id = new UserSession().getUserInSession();
    private File uniLogo;
    private String uniLogoFileName;
    private String uniLogoContentType;
    private String destPath;
    private String logopath, msg, changed = getText("msg.header.save"), notselect = getText("msg.file.not.select");

    public String ChangeUnivLogo() throws IOException {
        String homeDir = System.getProperty("catalina.base");
        logger.warn(this);
        logger.warn(uniLogo + " " + uniLogoContentType + " " + uniLogoFileName + " " + homeDir);
        if (uniLogo != null) {
            logopath = session.get("appPath").toString();
            try {
                File fileToCreate = new File(logopath, uniLogoFileName);
                FileUtils.copyFile(uniLogo, fileToCreate);
                msg = changed;
                logger.warn(uniLogoFileName + " " + msg);
            } catch (IOException ioex) {
                logger.error(ioex);
            }
            return SUCCESS;
        } else {
            msg = notselect;
            return INPUT;
        }
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

    /**
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
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
     * @return the changed
     */
    public String getChanged() {
        return changed;
    }

    /**
     * @param changed the changed to set
     */
    public void setChanged(String changed) {
        this.changed = changed;
    }

    /**
     * @return the uniLogoFileName
     */
    public String getUniLogoFileName() {
        return uniLogoFileName;
    }

    /**
     * @param uniLogoFileName the uniLogoFileName to set
     */
    public void setUniLogoFileName(String uniLogoFileName) {
        this.uniLogoFileName = uniLogoFileName;
    }

    /**
     * @return the uniLogoContentType
     */
    public String getUniLogoContentType() {
        return uniLogoContentType;
    }

    /**
     * @param uniLogoContentType the uniLogoContentType to set
     */
    public void setUniLogoContentType(String uniLogoContentType) {
        this.uniLogoContentType = uniLogoContentType;
    }

    /**
     * @return the destPath
     */
    public String getDestPath() {
        return destPath;
    }

    /**
     * @param destPath the destPath to set
     */
    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    /**
     * @return the notselect
     */
    public String getNotselect() {
        return notselect;
    }

    /**
     * @param notselect the notselect to set
     */
    public void setNotselect(String notselect) {
        this.notselect = notselect;
    }
}
