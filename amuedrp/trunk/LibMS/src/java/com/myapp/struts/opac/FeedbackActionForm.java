/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Faraz
 */
public class FeedbackActionForm extends org.apache.struts.action.ActionForm {
    
    private String date1;
    private String cardno;
    private String name;
    private String email;
    private String comments;
    private String CMBLib;
    /**
     *
     */
    public FeedbackActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
    
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       // ActionErrors errors = new ActionErrors();
       // if (getName() == null || getName().length() < 1) {
       //     errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
       // }
        return null;
    }

    /**
     * @return the date1
     */
    public String getDate1() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setDate1(String date1) {
        this.date1 = date1;
    }

    /**
     * @return the cardno
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * @param cardno the cardno to set
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

   
    public String getCMBLib() {
        return CMBLib;
    }

    
    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }
}
