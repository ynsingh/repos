/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
/**
 *
 * @author Dushyant
 */
public class SubLibraryActionForm  extends org.apache.struts.action.ActionForm {


private String sublibrary_name;
private String sublibrary_id;

private String button;

    /**
     *
     */
    public SubLibraryActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
    
     */
    
/**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.

     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        return null;
    }


    /**
     * @return the sublibrary_name
     */
    public String getSublibrary_name() {
        return sublibrary_name;
    }

    /**
     * @param sublibrary_name the sublibrary_name to set
     */
    public void setSublibrary_name(String sublibrary_name) {
        this.sublibrary_name = sublibrary_name;
    }

    /**
     * @return the sublibrary_id
     */
    public String getSublibrary_id() {
        return sublibrary_id;
    }

    /**
     * @param sublibrary_id the sublibrary_id to set
     */
    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

    /**
     * @return the button
     */
    public String getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(String button) {
        this.button = button;
    }



}
