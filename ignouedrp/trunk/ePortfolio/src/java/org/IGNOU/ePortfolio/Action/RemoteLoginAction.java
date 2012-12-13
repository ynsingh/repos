/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts2.ServletActionContext;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;

/**
 *
 * @author IGNOU Team
 */
public class RemoteLoginAction extends ActionSupport {

    private String returnurl = getText("returnURL");
    private String email, resp;
    private String sourceid = getText("iitkScourceId");
    private HttpServletResponse res = ServletActionContext.getResponse();
    final Logger logger = Logger.getLogger(this.getClass());
    private String encd, rand, hash;

    public RemoteLoginAction() {
    }

    public String RemoteLoginIDCheck() throws Exception {
        resp = RemoteAuth.AuthR(email, returnurl, sourceid);
        res.sendRedirect(resp);
        DOMConfigurator.configure("log4j.xml");
        logger.warn("Values are " + email + " " + returnurl + "  " + sourceid);
        return SUCCESS;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the encd
     */
    public String getEncd() {
        return encd;
    }

    /**
     * @param encd the encd to set
     */
    public void setEncd(String encd) {
        this.encd = encd;
    }

    /**
     * @return the rand
     */
    public String getRand() {
        return rand;
    }

    /**
     * @param rand the rand to set
     */
    public void setRand(String rand) {
        this.rand = rand;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
}