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
    
    private String name;
    private String email;
    private String comments;
    private String CMBLib;
    private String CMBSUBLib;

    public String getCMBSUBLib() {
        return CMBSUBLib;
    }

    public void setCMBSUBLib(String CMBSUBLib) {
        this.CMBSUBLib = CMBSUBLib;
    }

    
    
    public FeedbackActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       // ActionErrors errors = new ActionErrors();
       // if (getName() == null || getName().length() < 1) {
       //     errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
       // }
        return null;
    }

    
    public String getDate1() {
        return date1;
    }

    
    public void setDate1(String date1) {
        this.date1 = date1;
    }

    
    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }

   
    public String getEmail() {
        return email;
    }

   
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getComments() {
        return comments;
    }

    
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
