/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.chat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp01
 */
public class SendMessageActionForm extends org.apache.struts.action.ActionForm {
    
    private String sendmsg;
    private String submit;

    public String getSendmsg() {
        return sendmsg;
    }

    public void setSendmsg(String sendmsg) {
        this.sendmsg = sendmsg;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
   

    /**
     * @return
     */
   

    /**
     * @param i
     */
   

    /**
     *
     */
    public SendMessageActionForm() {
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
