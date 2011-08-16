/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp01
 */
public class LogGridActionForm extends org.apache.struts.action.ActionForm {
    

    private String library_id;
    private String sublibrary;

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getSublibrary() {
        return sublibrary;
    }

    public void setSublibrary(String sublibrary) {
        this.sublibrary = sublibrary;
    }
    private String userid;
    private String startdate;
    private String enddate;

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    private String Find;
    private String Print;

    public String getFind() {
        return Find;
    }

    public void setFind(String Find) {
        this.Find = Find;
    }

   

    public String getPrint() {
        return Print;
    }

    public void setPrint(String Print) {
        this.Print = Print;
    }

    

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    
    /**
     *
     */
    public LogGridActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
